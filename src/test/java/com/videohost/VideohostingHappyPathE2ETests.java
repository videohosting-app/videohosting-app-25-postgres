package com.videohost;

import static org.assertj.core.api.Assertions.assertThat;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.ScreenshotType;
import com.microsoft.playwright.options.WaitForSelectorState;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.List;
import java.util.function.BooleanSupplier;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class VideohostingHappyPathE2ETests {

    private static final String BASE_URL_PROPERTY = "e2e.base-url";
    private static final String BASE_URL_ENV = "E2E_BASE_URL";
    private static final String SELENIUM_CDP_URL_PROPERTY = "e2e.selenium-cdp-url";
    private static final String SELENIUM_CDP_URL_ENV = "E2E_SELENIUM_CDP_URL";
    private static final String ARTIFACTS_DIR_PROPERTY = "e2e.artifacts-dir";
    private static final String ARTIFACTS_DIR_ENV = "E2E_ARTIFACTS_DIR";
    private static final String PAGE_READY_TIMEOUT_MILLIS_PROPERTY = "e2e.page-ready-timeout-millis";
    private static final String PAGE_READY_TIMEOUT_MILLIS_ENV = "E2E_PAGE_READY_TIMEOUT_MILLIS";
    private static final String VIDEOHOSTING_PAGE_TITLE = "Відеохостінг";
    private static final String RENDER_HOST_SUFFIX = ".onrender.com";
    private static final int WAIT_TIMEOUT_MILLIS = 15000;
    private static final int POLL_INTERVAL_MILLIS = 250;
    private static final int DEFAULT_PAGE_READY_TIMEOUT_MILLIS = 180000;
    private static final int DEFAULT_TIMEOUT_MILLIS = 30000;
    private static final int RENDER_TIMEOUT_MILLIS = 180000;
    private static final int VIEWPORT_WIDTH = 1440;
    private static final int VIEWPORT_HEIGHT = 1200;
    private static final int VIDEO_WIDTH = 1280;
    private static final int VIDEO_HEIGHT = 720;

    private String baseUrl;
    private String seleniumCdpUrl;
    private int pageReadyTimeoutMillis;
    private Path artifactsDir;
    private Path videosDir;
    private Path screenshotsDir;
    private Playwright playwright;
    private Browser browser;
    private BrowserContext browserContext;
    private Page page;
    private String createdVideoTitle;

    @BeforeEach
    void setUp() {
        baseUrl = resolveBaseUrl();
        if (baseUrl == null || baseUrl.isBlank()) {
            throw new IllegalStateException("Set e2e.base-url or E2E_BASE_URL to run E2E UI tests.");
        }
        seleniumCdpUrl = resolveOptionalValue(SELENIUM_CDP_URL_PROPERTY, SELENIUM_CDP_URL_ENV);
        pageReadyTimeoutMillis = resolvePageReadyTimeoutMillis();
        artifactsDir = resolveArtifactsDir();
        videosDir = artifactsDir.resolve("videos");
        screenshotsDir = artifactsDir.resolve("screenshots");
        createDirectory(videosDir);
        createDirectory(screenshotsDir);

        playwright = Playwright.create();
        browser = createBrowser();
        browserContext = browser.newContext(new Browser.NewContextOptions()
            .setRecordVideoDir(videosDir)
            .setRecordVideoSize(VIDEO_WIDTH, VIDEO_HEIGHT)
            .setViewportSize(VIEWPORT_WIDTH, VIEWPORT_HEIGHT));
        page = browserContext.newPage();
        page.setDefaultTimeout(resolvePlaywrightTimeoutMillis());
        page.setDefaultNavigationTimeout(resolvePlaywrightTimeoutMillis());
    }

    @AfterEach
    void tearDown() {
        if (page != null && createdVideoTitle != null) {
            try {
                deleteVideohostRow(createdVideoTitle);
            } catch (Exception exception) {
                // Cleanup is best effort.
            } finally {
                createdVideoTitle = null;
            }
        }
        if (browserContext != null) {
            browserContext.close();
        }
        if (browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
        }
    }

    // Happy-path тест: створення і видалення запису.
    @Test
    void videohostingHappyPathCreatesAndDeletesEntry() {
        runWithDiagnostics("videohosting-happy-path-creates-and-deletes-entry", () -> {
            String uniqueSuffix = String.valueOf(Instant.now().toEpochMilli());
            String uniqueVideoTitle = "E2E Video " + uniqueSuffix;
            String uniqueViewer = "E2E Viewer " + uniqueSuffix;
            createdVideoTitle = uniqueVideoTitle;

            // Переходимо на сторінку додавання і заповнюємо форму.
            page.navigate(baseUrl + "/add");
            waitForAddPageForm();
            assertThat(page.locator("form").count()).isEqualTo(1);

            setInputValue("viewer", uniqueViewer);
            setInputValue("producer", "E2E Producer");
            setInputValue("watchedDate", "2026-05-04");
            setInputValue("watchedTime", "20:00");
            setInputValue("videoTitle", uniqueVideoTitle);
            setInputValue("videoDuration", "01:30:00");
            setInputValue("genre", "Drama");
            setInputValue("producerCountry", "Ukraine");
            setInputValue("videoRating", "8.5");
            setInputValue("platform", "E2E Platform");

            // Відправляємо форму і перевіряємо появу запису в таблиці.
            page.locator("button[type='submit']").click();
            page.waitForURL(baseUrl + "/");
            waitForVideohostingPageHeader();

            Locator createdRow = waitForRowContaining(uniqueVideoTitle,
                "Created videohosting row did not appear on the main page.");
            assertThat(createdRow.textContent())
                .contains(uniqueViewer)
                .contains(uniqueVideoTitle);

            // Видаляємо створений запис.
            deleteVideohostRow(uniqueVideoTitle);
            createdVideoTitle = null;
        });
    }

    // Happy-path тест: створення, редагування і видалення запису.
    @Test
    void videohostingHappyPathEditsEntry() {
        runWithDiagnostics("videohosting-happy-path-edits-entry", () -> {
            String uniqueSuffix = String.valueOf(Instant.now().toEpochMilli());
            String uniqueVideoTitle = "E2E Edit Video " + uniqueSuffix;
            String uniqueViewer = "E2E Edit Viewer " + uniqueSuffix;
            String updatedVideoTitle = "E2E Edited Video " + uniqueSuffix;
            String updatedViewer = "E2E Edited Viewer " + uniqueSuffix;
            createdVideoTitle = updatedVideoTitle;

            // Крок 1: створюємо новий запис через форму додавання.
            page.navigate(baseUrl + "/add");
            waitForAddPageForm();

            setInputValue("viewer", uniqueViewer);
            setInputValue("producer", "Edit Producer");
            setInputValue("watchedDate", "2026-05-04");
            setInputValue("watchedTime", "18:00");
            setInputValue("videoTitle", uniqueVideoTitle);
            setInputValue("videoDuration", "02:00:00");
            setInputValue("genre", "Comedy");
            setInputValue("producerCountry", "Poland");
            setInputValue("videoRating", "7.0");
            setInputValue("platform", "Edit Platform");

            page.locator("button[type='submit']").click();
            page.waitForURL(baseUrl + "/");
            waitForVideohostingPageHeader();

            // Крок 2: перевіряємо що запис з'явився і натискаємо "Редагувати".
            Locator createdRow = waitForRowContaining(uniqueVideoTitle,
                "Created row did not appear before editing.");
            assertThat(createdRow.textContent()).contains(uniqueVideoTitle);

            createdRow.locator("a.btn-warning").click();
            waitForEditPageForm();
            assertThat(page.locator("form").count()).isEqualTo(1);

            // Крок 3: змінюємо дані в формі редагування.
            clearAndSetInputValue("viewer", updatedViewer);
            clearAndSetInputValue("videoTitle", updatedVideoTitle);
            clearAndSetInputValue("genre", "Thriller");
            clearAndSetInputValue("videoRating", "9.2");

            // Крок 4: зберігаємо зміни і перевіряємо що оновлений запис з'явився в таблиці.
            page.locator("button[type='submit']").click();
            page.waitForURL(baseUrl + "/");
            waitForVideohostingPageHeader();

            Locator updatedRow = waitForRowContaining(updatedVideoTitle,
                "Updated videohosting row did not appear on the main page.");
            assertThat(updatedRow.textContent())
                .contains(updatedViewer)
                .contains(updatedVideoTitle)
                .contains("Thriller")
                .contains("9.2");

            // Крок 5: перевіряємо що старий заголовок більше не відображається.
            assertThat(page.locator("body").textContent()).doesNotContain(uniqueVideoTitle);

            // Крок 6: видаляємо оновлений запис.
            deleteVideohostRow(updatedVideoTitle);
            createdVideoTitle = null;
        });
    }

    // Видаляє рядок із таблиці за назвою відео і перевіряє що він зник.
    private void deleteVideohostRow(String uniqueVideoTitle) {
        page.navigate(baseUrl + "/");
        waitForVideohostingPageHeader();

        Locator rowToDelete = findRowContaining(uniqueVideoTitle);
        if (rowToDelete == null) {
            return;
        }

        rowToDelete.locator("button[type='submit']").click();
        page.waitForURL(baseUrl + "/");
        waitForVideohostingPageHeader();
        waitFor(() -> findRowContaining(uniqueVideoTitle) == null,
            "Deleted videohosting row is still visible on the main page.");

        assertThat(page.locator("body").textContent()).doesNotContain(uniqueVideoTitle);
    }

    // Чекає появи рядка з потрібним текстом і повертає знайдений locator.
    private Locator waitForRowContaining(String text, String failureMessage) {
        waitFor(() -> findRowContaining(text) != null, failureMessage);
        return findRowContaining(text);
    }

    // Шукає перший рядок таблиці, у тексті якого міститься потрібне значення.
    private Locator findRowContaining(String text) {
        Locator rows = page.locator("tbody tr");
        int count = rows.count();
        for (int index = 0; index < count; index++) {
            Locator row = rows.nth(index);
            String rowText = row.textContent();
            if (rowText != null && rowText.contains(text)) {
                return row;
            }
        }
        return null;
    }

    // Заповнює input-поле за його HTML name-атрибутом.
    private void setInputValue(String fieldName, String value) {
        page.locator("[name='" + fieldName + "']").fill(value);
    }

    // Очищає поле і встановлює нове значення (для форми редагування з уже заповненими полями).
    private void clearAndSetInputValue(String fieldName, String value) {
        Locator input = page.locator("[name='" + fieldName + "']");
        input.clear();
        input.fill(value);
    }

    // Чекає поки на головній сторінці з'явиться заголовок таблиці відеохостингу.
    private void waitForVideohostingPageHeader() {
        page.locator("h1")
            .filter(new Locator.FilterOptions().setHasText(VIDEOHOSTING_PAGE_TITLE))
            .first()
            .waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.VISIBLE)
                .setTimeout((double) pageReadyTimeoutMillis));
    }

    // Чекає поки сторінка додавання повністю відрендерить форму.
    private void waitForAddPageForm() {
        page.locator("form")
            .first()
            .waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.VISIBLE)
                .setTimeout((double) pageReadyTimeoutMillis));
    }

    // Чекає поки сторінка редагування повністю відрендерить форму.
    private void waitForEditPageForm() {
        page.locator("form")
            .first()
            .waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.VISIBLE)
                .setTimeout((double) pageReadyTimeoutMillis));
        // Додатково переконуємось що поле videoTitle вже містить дані (форма заповнена).
        waitFor(
            () -> {
                String value = page.locator("[name='videoTitle']").inputValue();
                return value != null && !value.isBlank();
            },
            "Edit form did not pre-populate videoTitle field."
        );
    }

    // Простий polling helper для умов що не мають зручного вбудованого wait у Playwright.
    private void waitFor(BooleanSupplier condition, String failureMessage) {
        long deadline = System.currentTimeMillis() + WAIT_TIMEOUT_MILLIS;
        while (System.currentTimeMillis() < deadline) {
            if (condition.getAsBoolean()) {
                return;
            }
            page.waitForTimeout(POLL_INTERVAL_MILLIS);
        }
        throw new AssertionError(failureMessage);
    }

    private String resolveBaseUrl() {
        String configuredUrl = resolveOptionalValue(BASE_URL_PROPERTY, BASE_URL_ENV);
        if (configuredUrl == null || configuredUrl.isBlank()) {
            return null;
        }
        return normalizeBaseUrl(configuredUrl);
    }

    private String normalizeBaseUrl(String configuredUrl) {
        String normalizedUrl = configuredUrl.endsWith("/")
            ? configuredUrl.substring(0, configuredUrl.length() - 1)
            : configuredUrl;
        URI uri = URI.create(normalizedUrl);
        if (uri.getScheme() == null || uri.getHost() == null) {
            throw new IllegalStateException(
                "E2E base URL must be an absolute URL, for example https://example.onrender.com"
            );
        }
        return normalizedUrl;
    }

    private String resolveOptionalValue(String propertyName, String envName) {
        String value = System.getProperty(propertyName);
        if (value == null || value.isBlank()) {
            value = System.getenv(envName);
        }
        return value;
    }

    private Browser createBrowser() {
        if (seleniumCdpUrl != null && !seleniumCdpUrl.isBlank()) {
            return playwright.chromium().connectOverCDP(seleniumCdpUrl);
        }
        return playwright.chromium().launch(new BrowserType.LaunchOptions()
            .setHeadless(true)
            .setArgs(List.of("--disable-dev-shm-usage", "--no-sandbox")));
    }

    private Path resolveArtifactsDir() {
        String dir = resolveOptionalValue(ARTIFACTS_DIR_PROPERTY, ARTIFACTS_DIR_ENV);
        if (dir == null || dir.isBlank()) {
            dir = "target/e2e-artifacts";
        }
        return Paths.get(dir).toAbsolutePath().normalize();
    }

    private int resolvePageReadyTimeoutMillis() {
        String timeout = resolveOptionalValue(
            PAGE_READY_TIMEOUT_MILLIS_PROPERTY, PAGE_READY_TIMEOUT_MILLIS_ENV);
        if (timeout == null || timeout.isBlank()) {
            return isRenderBaseUrl() ? RENDER_TIMEOUT_MILLIS : DEFAULT_PAGE_READY_TIMEOUT_MILLIS;
        }
        return Integer.parseInt(timeout);
    }

    private double resolvePlaywrightTimeoutMillis() {
        return isRenderBaseUrl() ? RENDER_TIMEOUT_MILLIS : DEFAULT_TIMEOUT_MILLIS;
    }

    private boolean isRenderBaseUrl() {
        if (baseUrl == null || baseUrl.isBlank()) {
            return false;
        }
        URI uri = URI.create(baseUrl);
        String host = uri.getHost();
        return host != null && host.endsWith(RENDER_HOST_SUFFIX);
    }

    private void createDirectory(Path directory) {
        try {
            Files.createDirectories(directory);
        } catch (Exception exception) {
            throw new IllegalStateException(
                "Unable to create E2E artifacts directory " + directory, exception);
        }
    }

    private void runWithDiagnostics(String testName, Runnable testBody) {
        try {
            testBody.run();
        } catch (Throwable throwable) {
            captureFailureScreenshot(testName);
            rethrowUnchecked(throwable);
        }
    }

    private void captureFailureScreenshot(String testName) {
        if (page == null) {
            return;
        }
        Path screenshotPath = screenshotsDir.resolve(buildArtifactFileName(testName, "png"));
        page.screenshot(new Page.ScreenshotOptions()
            .setPath(screenshotPath)
            .setFullPage(true)
            .setType(ScreenshotType.PNG));
    }

    private String buildArtifactFileName(String testName, String extension) {
        return sanitizeFileName(testName) + "-" + Instant.now().toEpochMilli() + "." + extension;
    }

    private String sanitizeFileName(String value) {
        return value.replaceAll("[^a-zA-Z0-9-_]+", "-");
    }

    private void rethrowUnchecked(Throwable throwable) {
        if (throwable instanceof RuntimeException) {
            throw (RuntimeException) throwable;
        }
        if (throwable instanceof Error) {
            throw (Error) throwable;
        }
        throw new IllegalStateException(throwable);
    }
}