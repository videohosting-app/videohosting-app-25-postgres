package com.videohost;

import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.opencsv.CSVReader;

/**
 * Додаток для редагування бази даних розкладу коледжу.
 *
 * @SpringBootApplication - анотація для позначення головного класу Spring Boot додатку.
 *
 * Клас реалізує CommandLineRunner для виконання коду після запуску додатку.
 *
 * Методи:
 * - main(String[] args): запускає додаток Spring Boot.
 * - run(String... args): метод, що виконується після запуску додатку. Виводить меню для користувача.
 * - addViewInfoFromCsv(): додає розклад з CSV-файлу до бази даних.
 * - viewAllViewInfo(): виводить всі розклади з бази даних.
 * - dropAllViewInfo(): видаляє всі розклади з бази даних.
 *
 * Поля:
 * - scheduleRepository: репозиторій для роботи з розкладами.
 *
 * Використовує:
 * - Scanner для зчитування вводу користувача.
 * - CSVReader для зчитування даних з CSV-файлу.
 * - ViewInfo для представлення документу розкладу.
 * - ViewInfoRepository для взаємодії з базою даних.
 */
@SpringBootApplication
public class VideohostApplication implements CommandLineRunner {

    @Autowired
    private ViewInfoRepository viewInfoRepository;

    public static void main(String[] args) {
        SpringApplication.run(VideohostApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Додати історію переглядів з CSV-файлу");
            System.out.println("2. Подивитись історію переглядів");
            System.out.println("3. Видалити історію переглядів");
            System.out.println("4. Вихід");
            System.out.print("Введіть номер команди (1-4): ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addViewInfoFromCsv();
                    break;
                case 2:
                    viewAllViewInfo();
                    break;
                case 3:
                    dropAllViewInfo();
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Номер команди некоректний. Спробуй ще.");
            }
        }
    }

    private void addViewInfoFromCsv() {
        try (CSVReader reader = new CSVReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("videohost.csv")))) {
            List<String[]> records = reader.readAll();
            
            records.remove(0); // Видалити перший рядок з назвами стовпців

            List<ViewInfo> viewInfoList = new ArrayList<>();
            for (String[] record : records) {
                ViewInfo viewInfo = new ViewInfo(
                    record[0], // viewer
                    record[1], // producer
                    LocalDate.parse(record[2]), //watchedDate
                    LocalTime.parse(record[3]), //watchedTime
                    record[4], // videoTitle
                    record[5], // videoDuration
                    record[6], // genre
                    record[7], // producerCountry
                    Double.parseDouble(record[8]), // videoRating
                    record[9] // platform
                    );

                viewInfoList.add(viewInfo);
            }
            
            viewInfoRepository.saveAll(viewInfoList);
            System.out.println(viewInfoList.size() + " документів з рядками з історії перегляду завантажено з CSV.");
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