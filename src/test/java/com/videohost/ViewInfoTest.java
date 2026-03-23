package com.videohost;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class ViewInfoTest {

    @Test
    void toStringContainsAllImportantFields() throws Exception {
        ViewInfo viewInfo = new ViewInfo(
            "Коваленко Олег Іванович",
            "Петренко Ірина Василівна",
            LocalDate.of(2024, 9, 14),
            LocalTime.of(20, 30, 0),
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
            LocalDate.of(2024, 9, 17),
            LocalTime.of(21, 30, 0),
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
}
