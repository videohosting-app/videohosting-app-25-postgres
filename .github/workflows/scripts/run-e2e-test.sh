#!/usr/bin/env bash
set -euo pipefail

mkdir -p target/ci-logs target/e2e-artifacts/videos target/e2e-artifacts/screenshots
LOG_FILE="target/ci-logs/e2e-ui-tests.log"

log() {
  echo "$1" | tee -a "$LOG_FILE"
}

: > "$LOG_FILE"

# Чекаємо готовності Selenium Grid.
for attempt in {1..90}; do
  ready_response="$(
    curl --silent --show-error \
      --connect-timeout 2 \
      --max-time 10 \
      http://127.0.0.1:4444/status
  )" || ready_response=""

  if [[ -n "$ready_response" ]]; then
    ready="$(jq -r '.value.ready // false' <<<"$ready_response" 2>/dev/null)" || ready="false"
  else
    ready="false"
  fi

  if [[ "$ready" == "true" ]]; then
    log "Selenium Grid reported ready on attempt ${attempt}."
    break
  fi

  log "Selenium Grid not ready on attempt ${attempt}."
  sleep 2
done

if [[ "${ready:-false}" != "true" ]]; then
  log "Selenium Grid did not become ready in time."
  exit 1
fi

SESSION_ID=""
CDP_URL=""

for attempt in {1..30}; do
  tmp_response_file="$(mktemp)"
  http_code="$(
    curl --silent --show-error \
      --connect-timeout 2 \
      --max-time 15 \
      --output "$tmp_response_file" \
      --write-out "%{http_code}" \
      --request POST \
      --url http://127.0.0.1:4444/session \
      --header "Content-Type: application/json" \
      --data '{
        "capabilities": {
          "alwaysMatch": {
            "browserName": "chrome",
            "goog:chromeOptions": {
              "args": ["--headless=new", "--disable-dev-shm-usage", "--no-sandbox"]
            }
          }
        }
      }'
  )" || http_code="curl_failed"

  response="$(cat "$tmp_response_file")"
  rm -f "$tmp_response_file"

  if [[ "$http_code" == "200" ]]; then
    SESSION_ID="$(jq -r '.value.sessionId // empty' <<<"$response")"
    CDP_URL="$(jq -r '.value.capabilities["se:cdp"] // empty' <<<"$response")"
    if [[ -n "$SESSION_ID" && -n "$CDP_URL" ]]; then
      log "Created Selenium session on attempt ${attempt}."
      break
    fi
    log "Selenium session response on attempt ${attempt} did not contain session metadata."
    log "$response"
  else
    log "Selenium session creation attempt ${attempt} failed with status ${http_code}."
    if [[ -n "$response" ]]; then
      log "$response"
    fi
  fi

  sleep 2
done

if [[ -z "$SESSION_ID" || -z "$CDP_URL" ]]; then
  log "Unable to resolve Selenium session metadata after retries."
  exit 1
fi

cleanup() {
  if [[ -n "${SESSION_ID:-}" ]]; then
    curl --silent --show-error --fail-with-body \
      --request DELETE \
      --url "http://127.0.0.1:4444/session/${SESSION_ID}" >/dev/null || true
  fi
}

trap cleanup EXIT

CDP_PATH="${CDP_URL#ws://*/}"
export E2E_SELENIUM_CDP_URL="ws://127.0.0.1:4444/${CDP_PATH}"
export E2E_ARTIFACTS_DIR="${E2E_ARTIFACTS_DIR:-target/e2e-artifacts}"

log "Resolved Playwright CDP endpoint: ${E2E_SELENIUM_CDP_URL}"
log "Starting Maven E2E UI test run."

set -o pipefail
mvn -B -P e2e-ui -Dcheckstyle.skip=true -DskipUnitTests=true -DskipIntegrationTests=true verify \
  | tee -a "$LOG_FILE"