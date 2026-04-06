package com.videohost;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import com.opencsv.CSVReader;

/**
 * Веб-додаток для перегляду та редагування таблиці відеохостингу.
 *
 * @SpringBootApplication - анотація для позначення головного класу Spring Boot додатку.
 *
 * Методи:
 * - main(String[] args): запускає додаток Spring Boot з вбудованим веб-сервером.
 * - onApplicationReady(): завантажує дані з CSV після старту, якщо база порожня.
 * - addViewInfoFromCsv(): додає дані перегляду з CSV-файлу до бази даних.
 * - viewAllViewInfo(): виводить всі записи перегляду з бази даних.
 * - dropAllViewInfo(): видаляє всі записи перегляду з бази даних.
 *
 * Поля:
 * - viewInfoRepository: репозиторій для роботи з даними відеохостингу.
 *
 * Використовує:
 * - CSVReader для зчитування даних з CSV-файлу.
 * - ViewInfo для представлення документу перегляду.
 * - ViewInfoRepository для взаємодії з базою даних.
 */
@SpringBootApplication
public class VideohostApplication {

    @Autowired
    private ViewInfoRepository viewInfoRepository;

    public static void main(String[] args) {
        SpringApplication.run(VideohostApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {
        if (viewInfoRepository.count() == 0) {
            addViewInfoFromCsv();
        }
    }

    private void addViewInfoFromCsv() {
        try (CSVReader reader = new CSVReader(new InputStreamReader(
            getClass().getClassLoader().getResourceAsStream("videohost.csv"),
            StandardCharsets.UTF_8)
        )) {
            List<String[]> records = reader.readAll();

            records.remove(0); // Видалити перший рядок з назвами стовпців

            List<ViewInfo> viewInfoList = new ArrayList<>();
            for (String[] record : records) {
                ViewInfo viewInfo = new ViewInfo(
                    record[0], // viewer
                    record[1], // producer
                    record[2], // watchedDate
                    record[3], // watchedTime
                    record[4], // videoTitle
                    record[5], // videoDuration
                    record[6], // genre
                    record[7], // producerCountry
                    Double.parseDouble(record[8]), // videoRating
                    record[9]  // platform
                );
                viewInfoList.add(viewInfo);
            }

            viewInfoRepository.deleteAll();
            viewInfoRepository.saveAll(viewInfoList);
            System.out.println(viewInfoList.size()
                + " документів з рядками з історії перегляду завантажено з CSV.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Не вдалось завантажити рядок історії перегляду з CSV.");
        }
    }

    private void viewAllViewInfo() {
        List<ViewInfo> viewInfo = viewInfoRepository.findAll();
        if (viewInfo.isEmpty()) {
            System.out.println("Документи з рядками історії перегляду не знайдено.");
        } else {
            System.out.println("Знайдено " + viewInfo.size() + " документів історії перегляду:");
            viewInfo.forEach(System.out::println);
        }
    }

    private void dropAllViewInfo() {
        viewInfoRepository.deleteAll();
        System.out.println("Історії перегляду видалено.");
    }
}