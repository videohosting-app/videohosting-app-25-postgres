package com.videohost;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class VideohostApplicationTest {
    private final OutputStreamState outputStreamState = new OutputStreamState();

    @AfterEach
    void restoreSystemStreams() {
        outputStreamState.restore();
    }

    @Test
    void addViewInfoFromCsvReplacesExistingDataAndSavesParsedRows() throws Exception {
        ViewInfoRepository repository = mock(ViewInfoRepository.class);
        VideohostApplication app = appWithRepository(repository);

        invokePrivate(app, "addViewInfoFromCsv");

        verify(repository, times(1)).deleteAll();
        verify(repository, times(1)).saveAll(anyList());
        assertTrue(outputStreamState.value().contains("CSV"));
    }

    @Test
    void addViewInfoFromCsvPrintsFailureMessageWhenRepositoryThrows() throws Exception {
        ViewInfoRepository repository = mock(ViewInfoRepository.class);
        doThrow(new RuntimeException("repository unavailable")).when(repository).deleteAll();
        VideohostApplication app = appWithRepository(repository);

        invokePrivate(app, "addViewInfoFromCsv");

        assertTrue(outputStreamState.value().contains("CSV"));
        verify(repository, times(1)).deleteAll();
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
        ViewInfo viewInfo = new com.videohost.support.ViewInfoTestDataBuilder().build();
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