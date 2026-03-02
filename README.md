# Приклад Java-програми ведення розкладу коледжу з використанням СКБД MongoDB

## Кроки для виконання
1. Завантажте і встановіть Java Development Kit 23 for Windows.
1. Завантажте Maven з https://dlcdn.apache.org/maven/maven-3/3.9.9/binaries/apache-maven-3.9.9-bin.zip і розпакуйте його на локальний комп'ютер.
1. В Windows в параметрах системи додайте системну змінну MAVEN_HOME=<шлях до папки з Maven>
1. В Windows в параметрах системи додайте `;<шлях до папки з Maven>\bin` в системну змінну PATH.
1. Встановіть СКБД MongoDB Community Server вибрати варіант установки Complete -> "Run service as Network Service user"
1. Встановіть MongoDB Compass (GUI).
1. Створіть базу даних `college-db` і колекцію в ній `college-schedule`.
1. Зберить програму використовуючи команду `mvn clean install`.
1. Запустіть програму за допомогою команди `mvn exec:java -Dexec.mainClass="com.videohost.VideohostApplication"`.

## Результати виконання програми
```
[INFO] Scanning for projects...
[INFO] 
[INFO] ---------------------< com.videohost:videohosting >---------------------
[INFO] Building sample 1.0-SNAPSHOT
[INFO]   from pom.xml
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- exec:3.6.2:java (default-cli) @ videohosting ---

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v2.5.4)

2025-11-28 09:26:38.987  INFO 18048 --- [lication.main()] com.videohost.VideohostApplication       : Starting VideohostApplication using Java 25.0.1 on DESKTOP-ORFHBNQ with PID 18048 (D:\anna4\Collage\2_class_KI-24\Databases(Nochevonv)\videohosting-app-mongodb\target\classes started by anna4 in D:\anna4\Collage\2_class_KI-24\Databases(Nochevonv)\videohosting-app-mongodb)
2025-11-28 09:26:38.990  INFO 18048 --- [lication.main()] com.videohost.VideohostApplication       : No active profile set, falling back to default profiles: default
2025-11-28 09:26:39.359  INFO 18048 --- [lication.main()] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data MongoDB repositories in DEFAULT mode.
2025-11-28 09:26:39.407  INFO 18048 --- [lication.main()] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 44 ms. Found 1 MongoDB repository interfaces.
2025-11-28 09:26:39.714  INFO 18048 --- [lication.main()] org.mongodb.driver.cluster               : Cluster created with settings {hosts=[localhost:27017], mode=SINGLE, requiredClusterType=UNKNOWN, serverSelectionTimeout='30000 ms'}
2025-11-28 09:26:39.795  INFO 18048 --- [localhost:27017] org.mongodb.driver.connection            : Opened connection [connectionId{localValue:2, serverValue:23}] to localhost:27017
2025-11-28 09:26:39.796  INFO 18048 --- [localhost:27017] org.mongodb.driver.connection            : Opened connection [connectionId{localValue:1, serverValue:22}] to localhost:27017
2025-11-28 09:26:39.799  INFO 18048 --- [localhost:27017] org.mongodb.driver.cluster               : Monitor thread successfully connected to server with description ServerDescription{address=localhost:27017, type=STANDALONE, state=CONNECTED, ok=true, minWireVersion=0, maxWireVersion=25, maxDocumentSize=16777216, logicalSessionTimeoutMinutes=30, roundTripTimeNanos=21227500}
2025-11-28 09:26:40.064  INFO 18048 --- [lication.main()] com.videohost.VideohostApplication       : Started VideohostApplication in 1.481 seconds (JVM running for 4.711)
1. Додати історію переглядів з CSV-файлу
2. Подивитись історію переглядів
3. Видалити історію переглядів
4. Вихід
Введіть номер команди (1-4): 1
2025-11-28 09:26:43.105  INFO 18048 --- [lication.main()] org.mongodb.driver.connection            : Opened connection [connectionId{localValue:3, serverValue:24}] to localhost:27017
12 документів з рядками з історії перегляду завантажено з CSV.
1. Додати історію переглядів з CSV-файлу
2. Подивитись історію переглядів
3. Видалити історію переглядів
4. Вихід
Введіть номер команди (1-4): 2
Знайдено 12 документів історії перегляду:
ViewInfo { id="69294eb3759e0a56785963c0"
 viewerFirstName="Коваленко Олег Іванович"
 producerFirstName="Петренко Ірина Василівна"
 watchedDate="2024-09-14"
 watchedTime="20:30"
 videoTitle="Весняний ранок"
videoDuration="00:15:45"n videoGenre="Драма"
 producerCountry="Україна"
 platformName="VideoHub"
 videoRating="8.7"
}
ViewInfo { id="69294eb3759e0a56785963c1"
 viewerFirstName="Сидоренко Марія Олексіївна"
 producerFirstName="Петренко Ірина Василівна"
 watchedDate="2024-09-14"
 watchedTime="21:45"
 videoTitle="Весняний ранок"
videoDuration="00:15:45"n videoGenre="Драма"
 producerCountry="Україна"
 platformName="VideoHub"
 videoRating="8.7"
}
ViewInfo { id="69294eb3759e0a56785963c2"
 viewerFirstName="Бондаренко Дмитро Сергійович"
 producerFirstName="Іваненко Андрій Миколайович"
 watchedDate="2024-09-14"
 watchedTime="22:20"
 videoTitle="Нічне небо"
videoDuration="00:22:30"n videoGenre="Документальний"
 producerCountry="США"
 platformName="FilmStream"
 videoRating="9.2"
}
ViewInfo { id="69294eb3759e0a56785963c3"
 viewerFirstName="Ткаченко Юлія Ігорівна"
 producerFirstName="Іваненко Андрій Миколайович"
 watchedDate="2024-09-14"
 watchedTime="23:30"
 videoTitle="Нічне небо"
videoDuration="00:22:30"n videoGenre="Документальний"
 producerCountry="США"
 platformName="FilmStream"
 videoRating="9.2"
}
ViewInfo { id="69294eb3759e0a56785963c4"
 viewerFirstName="Кравченко Сергій Володимирович"
 producerFirstName="Мельник Оксана Петрівна"
 watchedDate="2024-09-15"
 watchedTime="19:15"
 videoTitle="Сміх до сліз"
videoDuration="00:10:20"n videoGenre="Комедія"
 producerCountry="Велика Британія"
 platformName="ComedyVision"
 videoRating="7.8"
}
ViewInfo { id="69294eb3759e0a56785963c5"
 viewerFirstName="Марченко Ірина Олегівна"
 producerFirstName="Мельник Оксана Петрівна"
 watchedDate="2024-09-15"
 watchedTime="21:00"
 videoTitle="Сміх до сліз"
videoDuration="00:10:20"n videoGenre="Комедія"
 producerCountry="Велика Британія"
 platformName="ComedyVision"
 videoRating="7.8"
}
ViewInfo { id="69294eb3759e0a56785963c6"
 viewerFirstName="Шевчук Павло Михайлович"
 producerFirstName="Петренко Ірина Василівна"
 watchedDate="2024-09-15"
 watchedTime="22:45"
 videoTitle="Осінній дощ"
videoDuration="00:18:15"n videoGenre="Мелодрама"
 producerCountry="Україна"
 platformName="VideoHub"
 videoRating="8.3"
}
ViewInfo { id="69294eb3759e0a56785963c7"
 viewerFirstName="Гончарук Наталія Вікторівна"
 producerFirstName="Іваненко Андрій Миколайович"
 watchedDate="2024-09-16"
 watchedTime="20:30"
 videoTitle="Міські джунглі"
videoDuration="00:25:40"n videoGenre="Документальний"
 producerCountry="США"
 platformName="FilmStream"
 videoRating="8.9"
}
ViewInfo { id="69294eb3759e0a56785963c8"
 viewerFirstName="Лисенко Артем Олегович"
 producerFirstName="Мельник Оксана Петрівна"
 watchedDate="2024-09-16"
 watchedTime="22:15"
 videoTitle="Вечірні жарти"
videoDuration="00:12:35"n videoGenre="Комедія"
 producerCountry="Велика Британія"
 platformName="ComedyVision"
 videoRating="7.5"
}
ViewInfo { id="69294eb3759e0a56785963c9"
 viewerFirstName="Коваленко Олег Іванович"
 producerFirstName="Петренко Ірина Василівна"
 watchedDate="2024-09-16"
 watchedTime="23:00"
 videoTitle="Осінній дощ"
videoDuration="00:18:15"n videoGenre="Мелодрама"
 producerCountry="Україна"
 platformName="VideoHub"
 videoRating="8.3"
}
ViewInfo { id="69294eb3759e0a56785963ca"
 viewerFirstName="Ковальчук Михайло Іванович"
 producerFirstName="Іваненко Андрій Миколайович"
 watchedDate="2024-09-17"
 watchedTime="19:45"
 videoTitle="Нічне небо"
videoDuration="00:22:30"n videoGenre="Документальний"
 producerCountry="США"
 platformName="FilmStream"
 videoRating="9.2"
}
ViewInfo { id="69294eb3759e0a56785963cb"
 viewerFirstName="Бондаренко Дмитро Сергійович"
 producerFirstName="Мельник Оксана Петрівна"
 watchedDate="2024-09-17"
 watchedTime="21:30"
 videoTitle="Сміх до сліз"
videoDuration="00:10:20"n videoGenre="Комедія"
 producerCountry="Велика Британія"
 platformName="ComedyVision"
 videoRating="7.8"
}
1. Додати історію переглядів з CSV-файлу
2. Подивитись історію переглядів
3. Видалити історію переглядів
4. Вихід
Введіть номер команди (1-4): 3
Історії перегляду видалено.
1. Додати історію переглядів з CSV-файлу
2. Подивитись історію переглядів
3. Видалити історію переглядів
4. Вихід
Введіть номер команди (1-4): 1
12 документів з рядками з історії перегляду завантажено з CSV.
1. Додати історію переглядів з CSV-файлу
2. Подивитись історію переглядів
3. Видалити історію переглядів
4. Вихід
Введіть номер команди (1-4): 4
<<<<<<< HEAD
2025-11-28 09:26:57.373  INFO 18048 --- [ionShutdownHook] org.mongodb.driver.connection            : Closed connection [connectionId{localValue:3, serverValue:24}] to localhost:27017 because the pool has been closed.'''# videohosting_app_mongodb
=======
2025-11-28 09:26:57.373  INFO 18048 --- [ionShutdownHook] org.mongodb.driver.connection            : Closed connection [connectionId{localValue:3, serverValue:24}] to localhost:27017 because the pool has been closed.'''
>>>>>>> 68b1d280e8e94405226c2797c1d6975e63e6af57
