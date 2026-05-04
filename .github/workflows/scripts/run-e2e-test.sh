#!/usr/bin/env bash
set -euo pipefail

# Готуємо директорії
mkdir -p target/ci-logs target/e2e-artifacts/videos target/e2e-artifacts/screenshots
LOG_FILE="target/ci-logs/e2e-ui-tests.log"

log() {
  echo "$1" | tee -a "$LOG_FILE"
}

: > "$LOG_FILE"

log "Waiting for Selenium Grid..."
for attempt in {1..90}; do
  ready_response="$(curl --silent --connect-timeout 2 http://127.0.0.1:4444/status || echo "")"
  ready="$(echo "$ready_response" | jq -r '.value.ready // false' 2>/dev/null || echo "false")"

  if [[ "$ready" == "true" ]]; then
    log "Selenium Grid reported ready on attempt ${attempt}."
    break
  fi
  log "Selenium Grid not ready on attempt ${attempt}..."
  sleep 2
done

if [[ "${ready:-false}" != "true" ]]; then
  log "Selenium Grid did not become ready in time."
  exit 1
fi

log "Creating Selenium session..."
SESSION_ID=""
CDP_URL=""

for attempt in {1..10}; do
  response="$(curl --silent --request POST --url http://127.0.0.1:4444/session \
    --header "Content-Type: application/json" \
    --data '{"capabilities": {"alwaysMatch": {"browserName": "chrome", "goog:chromeOptions": {"args": ["--headless=new", "--disable-dev-shm-usage", "--no-sandbox"]}}}}')"
  
  SESSION_ID="$(echo "$response" | jq -r '.value.sessionId // empty')"
  CDP_URL="$(echo "$response" | jq -r '.value.capabilities["se:cdp"] // empty')"

  if [[ -n "$SESSION_ID" && -n "$CDP_URL" ]]; then
    log "Created Selenium session on attempt ${attempt}."
    break
  fi
  sleep 2
done

if [[ -z "$SESSION_ID" ]]; then
  log "Failed to create Selenium session."
  exit 1
fi

# Витягуємо шлях CDP і формуємо URL для локального runner-а
CDP_PATH="${CDP_URL#ws://*/}"
export E2E_SELENIUM_CDP_URL="ws://127.0.0.1:4444/${CDP_PATH}"

log "Resolved Playwright CDP endpoint: ${E2E_SELENIUM_CDP_URL}"

cleanup() {
  log "Cleaning up Selenium session ${SESSION_ID}..."
  curl --silent -X DELETE "http://127.0.0.1:4444/session/${SESSION_ID}" > /dev/null || true
}
trap cleanup EXIT

log "Starting Maven E2E UI test run."
mvn -B -P e2e-ui -Dcheckstyle.skip=true -DskipUnitTests=true -DskipIntegrationTests=true verify \
  -Dselenium.session.id="$SESSION_ID" \
  -Dplaywright.cdp.endpoint="$E2E_SELENIUM_CDP_URL" \
  -Dapplication.url="$E2E_BASE_URL" \
  | tee -a "$LOG_FILE"
