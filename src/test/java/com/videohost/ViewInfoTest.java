package com.videohost;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class ViewInfoTest {

    @Test
    void toStringContainsAllImportantFields() throws Exception {
        ViewInfo viewInfo = new ViewInfo(
            "Коваленко Олег Іванович",
            "Петренко Ірина Василівна",
            "2024-09-14",
            "20:30:00",
            "Весняний ранок",
            "00:15:45",
            "Драма",
            "Україна",
            8.7,
            "VideoHub"
        );
        Field idField = ViewInfo.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(viewInfo, "id-123");

        String value = viewInfo.toString();

        assertTrue(value.contains("id=\"id-123\""));
        assertTrue(value.contains("viewerFirstName=\"Коваленко Олег Іванович\""));
        assertTrue(value.contains("producerFirstName=\"Петренко Ірина Василівна\""));
        assertTrue(value.contains("videoTitle=\"Весняний ранок\""));
        assertTrue(value.contains("videoGenre=\"Драма\""));
        assertTrue(value.contains("platformName=\"VideoHub\""));
    }

    @Test
    void constructorStoresGivenValuesForSecondCsvRow() {
        ViewInfo viewInfo = new ViewInfo(
            "Бондаренко Дмитро Сергійович",
            "Мельник Оксана Петрівна",
            "2024-09-17",
            "21:30:00",
            "Сміх до сліз",
            "00:10:20",
            "Комедія",
            "Велика Британія",
            7.8,
            "ComedyVision"
        );

        String value = viewInfo.toString();

        assertTrue(value.contains("viewerFirstName=\"Бондаренко Дмитро Сергійович\""));
        assertTrue(value.contains("videoTitle=\"Сміх до сліз\""));
    }

    @Test
    void noArgConstructorCreatesEmptyViewInfo() {
        ViewInfo viewInfo = new ViewInfo();

        assertNull(viewInfo.getId());
        assertNull(viewInfo.getViewer());
        assertNull(viewInfo.getProducer());
        assertNull(viewInfo.getWatchedDate());
        assertNull(viewInfo.getWatchedTime());
        assertNull(viewInfo.getVideoTitle());
        assertNull(viewInfo.getVideoDuration());
        assertNull(viewInfo.getGenre());
        assertNull(viewInfo.getProducerCountry());
        assertNull(viewInfo.getPlatform());
    }

    @Test
    void settersAndGettersRoundTripAllFields() {
        ViewInfo viewInfo = new ViewInfo();

        viewInfo.setViewer("Коваленко Олег Іванович");
        viewInfo.setProducer("Петренко Ірина Василівна");
        viewInfo.setWatchedDate("2024-09-14");
        viewInfo.setWatchedTime("20:30:00");
        viewInfo.setVideoTitle("Весняний ранок");
        viewInfo.setVideoDuration("00:15:45");
        viewInfo.setGenre("Драма");
        viewInfo.setProducerCountry("Україна");
        viewInfo.setVideoRating(8.7);
        viewInfo.setPlatform("VideoHub");

        assertEquals("Коваленко Олег Іванович", viewInfo.getViewer());
        assertEquals("Петренко Ірина Василівна", viewInfo.getProducer());
        assertEquals("2024-09-14", viewInfo.getWatchedDate());
        assertEquals("20:30:00", viewInfo.getWatchedTime());
        assertEquals("Весняний ранок", viewInfo.getVideoTitle());
        assertEquals("00:15:45", viewInfo.getVideoDuration());
        assertEquals("Драма", viewInfo.getGenre());
        assertEquals("Україна", viewInfo.getProducerCountry());
        assertEquals(8.7, viewInfo.getVideoRating());
        assertEquals("VideoHub", viewInfo.getPlatform());
    }

    @Test
    void gettersReturnValuesFromParameterizedConstructor() throws Exception {
        ViewInfo viewInfo = new ViewInfo(
            "Ткаченко Юлія Ігорівна",
            "Іваненко Андрій Миколайович",
            "2024-09-14",
            "23:30:00",
            "Нічне небо",
            "00:22:30",
            "Документальний",
            "США",
            9.2,
            "FilmStream"
        );

        Field idField = ViewInfo.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(viewInfo, "id-456");

        assertEquals("id-456", viewInfo.getId());
        assertEquals("Ткаченко Юлія Ігорівна", viewInfo.getViewer());
        assertEquals("Іваненко Андрій Миколайович", viewInfo.getProducer());
        assertEquals("2024-09-14", viewInfo.getWatchedDate());
        assertEquals("23:30:00", viewInfo.getWatchedTime());
        assertEquals("Нічне небо", viewInfo.getVideoTitle());
        assertEquals("00:22:30", viewInfo.getVideoDuration());
        assertEquals("Документальний", viewInfo.getGenre());
        assertEquals("США", viewInfo.getProducerCountry());
        assertEquals(9.2, viewInfo.getVideoRating());
        assertEquals("FilmStream", viewInfo.getPlatform());
    }
}