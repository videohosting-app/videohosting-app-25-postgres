package com.videohost;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.junit.jupiter.api.AfterEach;

class VideohostApplicationTest {
    private final OutputStreamState outputStreamState = new OutputStreamState();

    @AfterEach
    void restoreSystemStreams() {
        outputStreamState.restore();
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