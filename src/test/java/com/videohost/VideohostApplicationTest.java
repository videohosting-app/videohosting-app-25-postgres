package com.videohost;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.videohost.support.ViewInfoTestDataBuilder;

class VideohostApplicationTest {
    private final InputStreamState inputStreamState = new InputStreamState();
    private final OutputStreamState outputStreamState = new OutputStreamState();

    @AfterEach
    void restoreSystemStreams() {
        inputStreamState.restore();
        outputStreamState.restore();
    }

    @Test
    void addViewInfoFromCsvSavesRecordsWhenCsvIsPresent() throws Exception {
        ViewInfoRepository repository = mock(ViewInfoRepository.class);
        VideohostApplication app = appWithRepository(repository);

        invokePrivate(app, "addViewInfoFromCsv");

        verify(repository, times(1)).saveAll(anyList());
        assertTrue(outputStreamState.value().contains("CSV"));
    }

    @Test
    void addViewInfoFromCsvOutputsTextWhenCsvIsAbsent() throws Exception {
        ViewInfoRepository repository = mock(ViewInfoRepository.class);
        VideohostApplication app = appWithRepository(repository);

        invokePrivate(app, "addViewInfoFromCsv");

        assertTrue(outputStreamState.value().length() > 0);
    }

    @Test
    void viewAllViewInfoPrintsNotFoundForEmptyRepository() throws Exception {
        ViewInfoRepository repository = mock(ViewInfoRepository.class);
        when(repository.findAll()).thenReturn(Collections.emptyList());
        VideohostApplication app = appWithRepository(repository);

        invokePrivate(app, "viewAllViewInfo");

        assertTrue(outputStreamState.value().contains("не знайдено"));
        verify(repository, times(1)).findAll();
    }

    @Test
    void viewAllViewInfoPrintsRowsWhenRepositoryHasData() throws Exception {
        ViewInfoRepository repository = mock(ViewInfoRepository.class);
        ViewInfo viewInfo = new ViewInfoTestDataBuilder().build();
        when(repository.findAll()).thenReturn(List.of(viewInfo));
        VideohostApplication app = appWithRepository(repository);

        invokePrivate(app, "viewAllViewInfo");

        String output = outputStreamState.value();
        assertTrue(output.contains("Знайдено 1"));
        assertTrue(output.contains("ViewInfo"));
        verify(repository, times(1)).findAll();
    }

    @Test
    void dropAllViewInfoDeletesDataAndPrintsMessage() throws Exception {
        ViewInfoRepository repository = mock(ViewInfoRepository.class);
        VideohostApplication app = appWithRepository(repository);

        invokePrivate(app, "dropAllViewInfo");

        verify(repository, times(1)).deleteAll();
        assertTrue(outputStreamState.value().contains("видалено"));
    }

    @Test
    void runExecutesChoicesAndStopsWhenInputIsExhausted() throws Exception {
        ViewInfoRepository repository = mock(ViewInfoRepository.class);
        when(repository.findAll()).thenReturn(Collections.emptyList());
        VideohostApplication app = appWithRepository(repository);
        inputStreamState.replace("2\n3\nx\n");

        assertThrows(NoSuchElementException.class, () -> app.run());

        verify(repository, times(1)).findAll();
        verify(repository, times(1)).deleteAll();
    }

    @Test
    void runPrintsMessageForOutOfRangeChoice() throws Exception {
        ViewInfoRepository repository = mock(ViewInfoRepository.class);
        VideohostApplication app = appWithRepository(repository);
        inputStreamState.replace("5\nx\n");

        assertThrows(NoSuchElementException.class, () -> app.run());

        assertTrue(outputStreamState.value().contains("некоректний"));
    }

    private static VideohostApplication appWithRepository(ViewInfoRepository repository) throws Exception {
        VideohostApplication app = new VideohostApplication();
        Field field = VideohostApplication.class.getDeclaredField("viewInfoRepository");
        field.setAccessible(true);
        field.set(app, repository);
        return app;
    }

    private static void invokePrivate(VideohostApplication app, String methodName) throws Exception {
        Method method = VideohostApplication.class.getDeclaredMethod(methodName);
        method.setAccessible(true);
        method.invoke(app);
    }

    private static final class InputStreamState {
        private final java.io.InputStream original = System.in;

        private void replace(String input) {
            System.setIn(new ByteArrayInputStream(input.getBytes(java.nio.charset.StandardCharsets.UTF_8)));
        }

        private void restore() {
            System.setIn(original);
        }
    }

    private static final class OutputStreamState {
        private final PrintStream original = System.out;
        private final ByteArrayOutputStream captured = new ByteArrayOutputStream();

        private OutputStreamState() {
            System.setOut(new PrintStream(captured, true, java.nio.charset.StandardCharsets.UTF_8));
        }

        private String value() {
            return captured.toString(java.nio.charset.StandardCharsets.UTF_8);
        }

        private void restore() {
            System.setOut(original);
        }
    }
}