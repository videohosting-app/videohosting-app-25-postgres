package com.videohost;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Веб-додаток для перегляду та редагування таблиці відеохостингу.
 * Завантаження даних із CSV прибрано — тепер усі дані додаються через веб-інтерфейс.
 */
@SpringBootApplication
public class VideohostApplication {

    public static void main(String[] args) {
        SpringApplication.run(VideohostApplication.class, args);
    }
}