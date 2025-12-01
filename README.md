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
mvn exec:java -Dexec.mainClass="com.videohost.VideohostApplication"
Picked up JAVA_TOOL_OPTIONS: -Dstdout.encoding=UTF-8 -Dstderr.encoding=UTF-8
WARNING: A terminally deprecated method in sun.misc.Unsafe has been called
WARNING: sun.misc.Unsafe::staticFieldBase has been called by com.google.inject.internal.aop.HiddenClassDefiner (file:/C:/Users/anna4/AppData/Roaming/Code/User/globalStorage/pleiades.java-extension-pack-jdk/maven/latest/lib/guice-5.1.0-classes.jar)
WARNING: Please consider reporting this to the maintainers of class com.google.inject.internal.aop.HiddenClassDefiner
WARNING: sun.misc.Unsafe::staticFieldBase will be removed in a future release
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

2025-12-01 12:51:28.589  INFO 22928 --- [lication.main()] com.videohost.VideohostApplication       : Starting VideohostApplication using Java 25.0.1 on DESKTOP-ORFHBNQ with PID 22928 (D:\anna4\Collage\2_class_KI-24\Databases(Nochevonv)\videohosting-app-mongodb\target\classes started by anna4 in D:\anna4\Collage\2_class_KI-24\Databases(Nochevonv)\videohosting-app-mongodb)
2025-12-01 12:51:28.593  INFO 22928 --- [lication.main()] com.videohost.VideohostApplication       : No active profile set, falling back to default profiles: default
2025-12-01 12:51:28.815  INFO 22928 --- [lication.main()] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data MongoDB repositories in DEFAULT mode.
2025-12-01 12:51:28.844  INFO 22928 --- [lication.main()] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 26 ms. Found 1 MongoDB repository interfaces.
2025-12-01 12:51:29.320  INFO 22928 --- [lication.main()] org.mongodb.driver.cluster               : Cluster created with settings {hosts=[127.0.0.1:27017], srvHost=cluster0.oqpae1u.mongodb.net, mode=MULTIPLE, requiredClusterType=REPLICA_SET, serverSelectionTimeout='30000 ms', requiredReplicaSetName='atlas-y6dqts-shard-0'}
2025-12-01 12:51:29.488  INFO 22928 --- [e1u.mongodb.net] org.mongodb.driver.cluster               : Adding discovered server ac-jevohhg-shard-00-01.oqpae1u.mongodb.net:27017 to client view of cluster
2025-12-01 12:51:29.544  INFO 22928 --- [e1u.mongodb.net] org.mongodb.driver.cluster               : Adding discovered server ac-jevohhg-shard-00-02.oqpae1u.mongodb.net:27017 to client view of cluster
2025-12-01 12:51:29.548  INFO 22928 --- [e1u.mongodb.net] org.mongodb.driver.cluster               : Adding discovered server ac-jevohhg-shard-00-00.oqpae1u.mongodb.net:27017 to client view of cluster
2025-12-01 12:51:30.845  INFO 22928 --- [ngodb.net:27017] org.mongodb.driver.connection            : Opened connection [connectionId{localValue:6, serverValue:294542}] to ac-jevohhg-shard-00-00.oqpae1u.mongodb.net:27017
2025-12-01 12:51:30.854  INFO 22928 --- [ngodb.net:27017] org.mongodb.driver.cluster               : Monitor thread successfully connected to server with description ServerDescription{address=ac-jevohhg-shard-00-00.oqpae1u.mongodb.net:27017, type=REPLICA_SET_SECONDARY, state=CONNECTED, ok=true, minWireVersion=0, maxWireVersion=25, maxDocumentSize=16777216, logicalSessionTimeoutMinutes=30, roundTripTimeNanos=747150400, setName='atlas-y6dqts-shard-0', canonicalAddress=ac-jevohhg-shard-00-00.oqpae1u.mongodb.net:27017, hosts=[ac-jevohhg-shard-00-00.oqpae1u.mongodb.net:27017, ac-jevohhg-shard-00-01.oqpae1u.mongodb.net:27017, ac-jevohhg-shard-00-02.oqpae1u.mongodb.net:27017], passives=[], arbiters=[], primary='ac-jevohhg-shard-00-01.oqpae1u.mongodb.net:27017', tagSet=TagSet{[Tag{name='availabilityZone', value='europe-west1-b'}, Tag{name='diskState', value='READY'}, Tag{name='nodeType', value='ELECTABLE'}, Tag{name='provider', value='GCP'}, Tag{name='region', value='WESTERN_EUROPE'}, Tag{name='workloadType', value='OPERATIONAL'}]}, electionId=null, setVersion=54, topologyVersion=TopologyVersion{processId=691c9d4f841a000bc371524e, counter=5}, lastWriteDate=Mon Dec 01 12:51:29 EET 2025, lastUpdateTimeNanos=6192949679500}
2025-12-01 12:51:31.177  INFO 22928 --- [lication.main()] com.videohost.VideohostApplication       : Started VideohostApplication in 2.894 seconds (JVM running for 4.764)
1. Додати історію переглядів з CSV-файлу
2. Подивитись історію переглядів
3. Видалити історію переглядів
4. Вихід
Введіть номер команди (1-4): 2025-12-01 12:51:31.238  INFO 22928 --- [ngodb.net:27017] org.mongodb.driver.connection            : Opened connection [connectionId{localValue:1, serverValue:287637}] to ac-jevohhg-shard-00-02.oqpae1u.mongodb.net:27017
2025-12-01 12:51:31.238  INFO 22928 --- [ngodb.net:27017] org.mongodb.driver.connection            : Opened connection [connectionId{localValue:5, serverValue:287638}] to ac-jevohhg-shard-00-02.oqpae1u.mongodb.net:27017
2025-12-01 12:51:31.238  INFO 22928 --- [ngodb.net:27017] org.mongodb.driver.connection            : Opened connection [connectionId{localValue:2, serverValue:310683}] to ac-jevohhg-shard-00-01.oqpae1u.mongodb.net:27017
2025-12-01 12:51:31.239  INFO 22928 --- [ngodb.net:27017] org.mongodb.driver.cluster               : Monitor thread successfully connected to server with description ServerDescription{address=ac-jevohhg-shard-00-02.oqpae1u.mongodb.net:27017, type=REPLICA_SET_SECONDARY, state=CONNECTED, ok=true, minWireVersion=0, maxWireVersion=25, maxDocumentSize=16777216, logicalSessionTimeoutMinutes=30, roundTripTimeNanos=1143081400, setName='atlas-y6dqts-shard-0', canonicalAddress=ac-jevohhg-shard-00-02.oqpae1u.mongodb.net:27017, hosts=[ac-jevohhg-shard-00-00.oqpae1u.mongodb.net:27017, ac-jevohhg-shard-00-01.oqpae1u.mongodb.net:27017, ac-jevohhg-shard-00-02.oqpae1u.mongodb.net:27017], passives=[], arbiters=[], primary='ac-jevohhg-shard-00-01.oqpae1u.mongodb.net:27017', tagSet=TagSet{[Tag{name='availabilityZone', value='europe-west1-d'}, Tag{name='diskState', value='READY'}, Tag{name='nodeType', value='ELECTABLE'}, Tag{name='provider', value='GCP'}, Tag{name='region', value='WESTERN_EUROPE'}, Tag{name='workloadType', value='OPERATIONAL'}]}, electionId=null, setVersion=54, topologyVersion=TopologyVersion{processId=691ca4fa5c966ea6788d921f, counter=3}, lastWriteDate=Mon Dec 01 12:51:29 EET 2025, lastUpdateTimeNanos=6193343519300}
2025-12-01 12:51:31.241  INFO 22928 --- [ngodb.net:27017] org.mongodb.driver.connection            : Opened connection [connectionId{localValue:3, serverValue:294544}] to ac-jevohhg-shard-00-00.oqpae1u.mongodb.net:27017
2025-12-01 12:51:31.252  INFO 22928 --- [ngodb.net:27017] org.mongodb.driver.connection            : Opened connection [connectionId{localValue:4, serverValue:310688}] to ac-jevohhg-shard-00-01.oqpae1u.mongodb.net:27017
2025-12-01 12:51:31.252  INFO 22928 --- [ngodb.net:27017] org.mongodb.driver.cluster               : Monitor thread successfully connected to server with description ServerDescription{address=ac-jevohhg-shard-00-01.oqpae1u.mongodb.net:27017, type=REPLICA_SET_PRIMARY, state=CONNECTED, ok=true, minWireVersion=0, maxWireVersion=25, maxDocumentSize=16777216, logicalSessionTimeoutMinutes=30, roundTripTimeNanos=1158427700, setName='atlas-y6dqts-shard-0', canonicalAddress=ac-jevohhg-shard-00-01.oqpae1u.mongodb.net:27017, hosts=[ac-jevohhg-shard-00-00.oqpae1u.mongodb.net:27017, ac-jevohhg-shard-00-01.oqpae1u.mongodb.net:27017, ac-jevohhg-shard-00-02.oqpae1u.mongodb.net:27017], passives=[], arbiters=[], primary='ac-jevohhg-shard-00-01.oqpae1u.mongodb.net:27017', tagSet=TagSet{[Tag{name='availabilityZone', value='europe-west1-c'}, Tag{name='diskState', value='READY'}, Tag{name='nodeType', value='ELECTABLE'}, Tag{name='provider', value='GCP'}, Tag{name='region', value='WESTERN_EUROPE'}, Tag{name='workloadType', value='OPERATIONAL'}]}, electionId=7fffffff000000000000011c, setVersion=54, topologyVersion=TopologyVersion{processId=691ca0219a48975c966a2722, counter=6}, lastWriteDate=Mon Dec 01 12:51:29 EET 2025, lastUpdateTimeNanos=6193358896100}
2025-12-01 12:51:31.253  INFO 22928 --- [ngodb.net:27017] org.mongodb.driver.cluster               : Setting max election id to 7fffffff000000000000011c from replica set primary ac-jevohhg-shard-00-01.oqpae1u.mongodb.net:27017
2025-12-01 12:51:31.254  INFO 22928 --- [ngodb.net:27017] org.mongodb.driver.cluster               : Setting max set version to 54 from replica set primary ac-jevohhg-shard-00-01.oqpae1u.mongodb.net:27017
2025-12-01 12:51:31.254  INFO 22928 --- [ngodb.net:27017] org.mongodb.driver.cluster               : Discovered replica set primary ac-jevohhg-shard-00-01.oqpae1u.mongodb.net:27017
1
2025-12-01 12:51:54.567  INFO 22928 --- [lication.main()] org.mongodb.driver.connection            : Opened connection [connectionId{localValue:7, serverValue:310667}] to ac-jevohhg-shard-00-01.oqpae1u.mongodb.net:27017
12 документів з рядками з історії перегляду завантажено з CSV.
1. Додати історію переглядів з CSV-файлу
2. Подивитись історію переглядів
3. Видалити історію переглядів
4. Вихід
Введіть номер команди (1-4): 2
Знайдено 12 документів історії перегляду:
ViewInfo { id="692d734866e4f3647d39bcc6"
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
ViewInfo { id="692d734866e4f3647d39bcc7"
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
ViewInfo { id="692d734866e4f3647d39bcc8"
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
ViewInfo { id="692d734866e4f3647d39bcc9"
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
ViewInfo { id="692d734866e4f3647d39bcca"
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
ViewInfo { id="692d734866e4f3647d39bccb"
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
ViewInfo { id="692d734866e4f3647d39bccc"
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
ViewInfo { id="692d734866e4f3647d39bccd"
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
ViewInfo { id="692d734866e4f3647d39bcce"
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
ViewInfo { id="692d734866e4f3647d39bccf"
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
ViewInfo { id="692d734866e4f3647d39bcd0"
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
ViewInfo { id="692d734866e4f3647d39bcd1"
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
2025-12-01 12:52:08.394  INFO 22928 --- [ionShutdownHook] org.mongodb.driver.connection            : Closed connection [connectionId{localValue:7, serverValue:310667}] to ac-jevohhg-shard-00-01.oqpae1u.mongodb.net:27017 because the pool has been closed. 
```