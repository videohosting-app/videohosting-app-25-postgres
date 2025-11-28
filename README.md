# Кроки для виконання
1. Встановіть локально PostgreSQL, Maven та Java.
1. Створіть базу даних college_db.
1. Вкажіть правильне значення властивості `hibernate.connection.password` у файлі "src\main\resources\hibernate.cfg.xml".
1. Зберіть програму за допомогою команди: `mvn clean install`.
1. Запустіть програму за допомогою команди: `mvn exec:java -D"exec.mainClass=com.college.MainApp"`.
1. Якщо термінал відображає знаки питання "?" замість українських символів, зконфігуруйте термінал на відображення шрифту в форматі UTF-8 виконавши команду `chcp 65001`.

# Результати виконання програми
```
лист. 13, 2025 9:31:46 ДП org.hibernate.Version logVersion
INFO: HHH000412: Hibernate ORM core version 5.6.14.Final
лист. 13, 2025 9:31:46 ДП org.hibernate.boot.jaxb.internal.stax.LocalXmlResourceResolver resolveEntity
WARN: HHH90000012: Recognized obsolete hibernate namespace http://hibernate.sourceforge.net/hibernate-configuration. Use namespace http://www.hibernate.org/dtd/hibernate-configuration instead.  Support for obsolete DTD/XSD namespaces may be removed at any time.
лист. 13, 2025 9:31:47 ДП org.hibernate.annotations.common.reflection.java.JavaReflectionManager <clinit>
INFO: HCANN000001: Hibernate Commons Annotations {5.1.2.Final}
лист. 13, 2025 9:31:47 ДП org.hibernate.engine.jdbc.connections.internal.ConnectionProviderInitiator instantiateC3p0Provider
WARN: HHH000022: c3p0 properties were encountered, but the c3p0 provider class was not found on the classpath; these properties are going to be ignored.
лист. 13, 2025 9:31:47 ДП org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl configure
WARN: HHH10001002: Using Hibernate built-in connection pool (not for production use!)
лист. 13, 2025 9:31:47 ДП org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl buildCreator
INFO: HHH10001005: using driver [org.postgresql.Driver] at URL [jdbc:postgresql://localhost:5432/videohost_db]
лист. 13, 2025 9:31:47 ДП org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl buildCreator
INFO: HHH10001001: Connection properties: {user=postgres, password=****}
лист. 13, 2025 9:31:47 ДП org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl buildCreator
INFO: HHH10001003: Autocommit mode: false
лист. 13, 2025 9:31:47 ДП org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl$PooledConnections <init>
INFO: HHH000115: Hibernate connection pool size: 20 (min=1)
лист. 13, 2025 9:31:48 ДП org.hibernate.dialect.Dialect <init>
INFO: HHH000400: Using dialect: org.hibernate.dialect.PostgreSQLDialect
Hibernate: 

    alter table producer
       drop constraint FK3o8amrtol4mbe5uod5igq15mt
лист. 13, 2025 9:31:49 ДП org.hibernate.resource.transaction.backend.jdbc.internal.DdlTransactionIsolatorNonJtaImpl getIsolatedConnection
INFO: HHH10001501: Connection obtained from JdbcConnectionAccess [org.hibernate.engine.jdbc.env.internal.JdbcEnvironmentInitiator$ConnectionProviderJdbcConnectionAccess@1f631c65] for (non-JTA) DDL execution was not in auto-commit mode; the Connection 'local transaction' will be committed and the Connection will be set into auto-commit mode.
Hibernate: 

    alter table video
       drop constraint FKiy2bruoa02j2sxwxjl0rcd8v6
Hibernate:

    alter table video
       drop constraint FKeqceehcooicjyi3j7a87ku98b
Hibernate:

    alter table view_event
       drop constraint FK92n9le4p132o2m94akvq9lkia
Hibernate:

    alter table view_event
       drop constraint FKbwvw3hymlkn5l3867l3vdbktx
Hibernate:

    alter table viewer
       drop constraint FK8bpfo4t9tnyiet2ttwcg7477w
Hibernate: 

    drop table if exists platform cascade
Hibernate: 

    drop table if exists producer cascade
Hibernate: 

    drop table if exists producer_name cascade
Hibernate:

    drop table if exists video cascade
Hibernate: 

    drop table if exists view_event cascade
Hibernate:

    drop table if exists viewer cascade
Hibernate:

    drop table if exists viewer_name cascade
Hibernate: 

    create table platform (
       platform_id  bigserial not null,
        name varchar(100),
        primary key (platform_id)
    )
лист. 13, 2025 9:31:49 ДП org.hibernate.resource.transaction.backend.jdbc.internal.DdlTransactionIsolatorNonJtaImpl getIsolatedConnection
INFO: HHH10001501: Connection obtained from JdbcConnectionAccess [org.hibernate.engine.jdbc.env.internal.JdbcEnvironmentInitiator$ConnectionProviderJdbcConnectionAccess@49601185] for (non-JTA) DDL execution was not in auto-commit mode; the Connection 'local transaction' will be committed and the Connection will be set into auto-commit mode.
Hibernate: 

    create table producer (
       producer_id  bigserial not null,
        country varchar(100),
        producer_name_id int8,
        primary key (producer_id)
    )
Hibernate: 

    create table producer_name (
       producer_name_id  bigserial not null,
        fatherly varchar(100),
        first_name varchar(100),
        last_name varchar(100),
        primary key (producer_name_id)
    )
Hibernate:

    create table video (
       video_id  bigserial not null,
        duration varchar(255),
        genre varchar(100),
        rating float8,
        title varchar(100),
        platform_id int8,
        producer_id int8,
        primary key (video_id)
    )
Hibernate: 

    create table view_event (
       view_id  bigserial not null,
        view_date date,
        view_time time,
        video_id int8,
        viewer_id int8,
        primary key (view_id)
    )
Hibernate:

    create table viewer (
       viewer_id  bigserial not null,
        viewer_name_id int8,
        primary key (viewer_id)
    )
Hibernate: 

    create table viewer_name (
       viewer_name_id  bigserial not null,
        fatherly varchar(100),
        first_name varchar(100),
        last_name varchar(100),
        primary key (viewer_name_id)
    )
Hibernate: 

    alter table producer
       add constraint FK3o8amrtol4mbe5uod5igq15mt
       foreign key (producer_name_id)
       references producer_name
Hibernate: 

    alter table video
       add constraint FKiy2bruoa02j2sxwxjl0rcd8v6
       foreign key (platform_id)
       references platform
Hibernate:

    alter table video
       add constraint FKeqceehcooicjyi3j7a87ku98b
       foreign key (producer_id)
       references producer
Hibernate:

    alter table view_event
       add constraint FK92n9le4p132o2m94akvq9lkia
       foreign key (video_id)
       references video
Hibernate: 

    alter table view_event
       add constraint FKbwvw3hymlkn5l3867l3vdbktx
       foreign key (viewer_id)
       references viewer
Hibernate:

    alter table viewer
       add constraint FK8bpfo4t9tnyiet2ttwcg7477w
       foreign key (viewer_name_id)
       references viewer_name
Hibernate: 
    insert
    into
        platform
        (name)
    values
        (?)
Hibernate: 
    insert
    into
        platform
        (name)
    values
        (?)
Hibernate:
    insert
    into
        platform
        (name)
    values
        (?)
Hibernate:
    insert
    into
        producer_name
        (fatherly, first_name, last_name)
    values
        (?, ?, ?)
Hibernate: 
    insert
    into
        producer_name
        (fatherly, first_name, last_name)
    values
        (?, ?, ?)
Hibernate:
    insert
    into
        producer_name
        (fatherly, first_name, last_name)
    values
        (?, ?, ?)
Hibernate:
    insert
    into
        producer
        (country, producer_name_id)
    values
        (?, ?)
Hibernate:
    insert
    into
        producer
        (country, producer_name_id)
    values
        (?, ?)
Hibernate:
    insert
    into
        producer
        (country, producer_name_id)
    values
        (?, ?)
Hibernate: 
    insert
    into
        video
        (duration, genre, platform_id, producer_id, rating, title)
    values
        (?, ?, ?, ?, ?, ?)
Hibernate:
    insert
    into
        video
        (duration, genre, platform_id, producer_id, rating, title)
    values
        (?, ?, ?, ?, ?, ?)
Hibernate: 
    insert
    into
        video
        (duration, genre, platform_id, producer_id, rating, title)
    values
        (?, ?, ?, ?, ?, ?)
Hibernate:
    insert
    into
        video
        (duration, genre, platform_id, producer_id, rating, title)
    values
        (?, ?, ?, ?, ?, ?)
Hibernate:
    insert
    into
        video
        (duration, genre, platform_id, producer_id, rating, title)
    values
        (?, ?, ?, ?, ?, ?)
Hibernate:
    insert
    into
        video
        (duration, genre, platform_id, producer_id, rating, title)
    values
        (?, ?, ?, ?, ?, ?)
Hibernate: 
    insert
    into
        viewer_name
        (fatherly, first_name, last_name)
    values
        (?, ?, ?)
Hibernate:
    insert
    into
        viewer_name
        (fatherly, first_name, last_name)
    values
        (?, ?, ?)
Hibernate:
    insert
    into
        viewer_name
        (fatherly, first_name, last_name)
    values
        (?, ?, ?)
Hibernate:
    insert
    into
        viewer_name
        (fatherly, first_name, last_name)
    values
        (?, ?, ?)
Hibernate:
    insert
    into
        viewer_name
        (fatherly, first_name, last_name)
    values
        (?, ?, ?)
Hibernate:
    insert
    into
        viewer_name
        (fatherly, first_name, last_name)
    values
        (?, ?, ?)
Hibernate:
    insert
    into
        viewer_name
        (fatherly, first_name, last_name)
    values
        (?, ?, ?)
Hibernate:
    insert
    into
        viewer_name
        (fatherly, first_name, last_name)
    values
        (?, ?, ?)
Hibernate:
    insert
    into
        viewer_name
        (fatherly, first_name, last_name)
    values
        (?, ?, ?)
Hibernate: 
    insert
    into
        viewer_name
        (fatherly, first_name, last_name)
    values
        (?, ?, ?)
Hibernate:
    insert
    into
        viewer
        (viewer_name_id)
    values
        (?)
Hibernate:
    insert
    into
        viewer
        (viewer_name_id)
    values
        (?)
Hibernate:
    insert
    into
        viewer
        (viewer_name_id)
    values
        (?)
Hibernate:
    insert
    into
        viewer
        (viewer_name_id)
    values
        (?)
Hibernate:
    insert
    into
        viewer
        (viewer_name_id)
    values
        (?)
Hibernate:
    insert
    into
        viewer
        (viewer_name_id)
    values
        (?)
Hibernate:
    insert
    into
        viewer
        (viewer_name_id)
    values
        (?)
Hibernate:
    insert
    into
        viewer
        (viewer_name_id)
    values
        (?)
Hibernate:
    insert
    into
        viewer
        (viewer_name_id)
    values
        (?)
Hibernate: 
    insert
    into
        viewer
        (viewer_name_id)
    values
        (?)
Hibernate:
    insert
    into
        view_event
        (video_id, view_date, view_time, viewer_id)
    values
        (?, ?, ?, ?)
Hibernate:
    insert
    into
        view_event
        (video_id, view_date, view_time, viewer_id)
    values
        (?, ?, ?, ?)
Hibernate:
    insert
    into
        view_event
        (video_id, view_date, view_time, viewer_id)
    values
        (?, ?, ?, ?)
Hibernate:
    insert
    into
        view_event
        (video_id, view_date, view_time, viewer_id)
    values
        (?, ?, ?, ?)
Hibernate:
    insert
    into
        view_event
        (video_id, view_date, view_time, viewer_id)
    values
        (?, ?, ?, ?)
Hibernate:
    insert
    into
        view_event
        (video_id, view_date, view_time, viewer_id)
    values
        (?, ?, ?, ?)
Hibernate: 
    insert
    into
        view_event
        (video_id, view_date, view_time, viewer_id)
    values
        (?, ?, ?, ?)
Hibernate:
    insert
    into
        view_event
        (video_id, view_date, view_time, viewer_id)
    values
        (?, ?, ?, ?)
Hibernate:
    insert
    into
        view_event
        (video_id, view_date, view_time, viewer_id)
    values
        (?, ?, ?, ?)
Hibernate:
    insert
    into
        view_event
        (video_id, view_date, view_time, viewer_id)
    values
        (?, ?, ?, ?)
Hibernate:
    insert
    into
        view_event
        (video_id, view_date, view_time, viewer_id)
    values
        (?, ?, ?, ?)
Hibernate:
    insert
    into
        view_event
        (video_id, view_date, view_time, viewer_id)
    values
        (?, ?, ?, ?)
✅ Дані успішно збережено у базу!
Hibernate: 
    select
        viewername2_.first_name as col_0_0_,
        viewername2_.last_name as col_1_0_,
        viewername2_.fatherly as col_2_0_,
        producerna5_.first_name as col_3_0_,
        producerna5_.last_name as col_4_0_,
        producerna5_.fatherly as col_5_0_,
        video3_.title as col_6_0_,
        video3_.genre as col_7_0_,
        producer4_.country as col_8_0_,
        platform6_.name as col_9_0_,
        video3_.rating as col_10_0_,
        viewevent0_.view_date as col_11_0_,
        viewevent0_.view_time as col_12_0_
    from
        view_event viewevent0_
    inner join
        viewer viewer1_
            on viewevent0_.viewer_id=viewer1_.viewer_id
    inner join
        viewer_name viewername2_
            on viewer1_.viewer_name_id=viewername2_.viewer_name_id
    inner join
        video video3_
            on viewevent0_.video_id=video3_.video_id
    inner join
        producer producer4_
            on video3_.producer_id=producer4_.producer_id
    inner join
        producer_name producerna5_
            on producer4_.producer_name_id=producerna5_.producer_name_id cross
    join
        platform platform6_
    where
        video3_.platform_id=platform6_.platform_id

📺 Інформація про перегляди відео:
--------------------------------------------------
Глядач: Олег Коваленко Іванович
Продюсер: Ірина Петренко Василівна
Відео: Весняний ранок
Жанр: Драма
Країна продюсера: Україна
Платформа: VideoHub
Рейтинг: 8.7
Дата перегляду: 2024-09-14
Час перегляду: 20:30
--------------------------------------------------
Глядач: Марія Сидоренко Олексіївна
Продюсер: Ірина Петренко Василівна
Відео: Весняний ранок
Жанр: Драма
Країна продюсера: Україна
Платформа: VideoHub
Рейтинг: 8.7
Дата перегляду: 2024-09-14
Час перегляду: 21:45
--------------------------------------------------
Глядач: Дмитро Бондаренко Сергійович
Продюсер: Андрій Іваненко Миколайович
Відео: Нічне небо
Жанр: Документальний
Країна продюсера: США
Платформа: FilmStream
Рейтинг: 9.2
Дата перегляду: 2024-09-14
Час перегляду: 22:20
--------------------------------------------------
Глядач: Юлія Ткаченко Ігорівна
Продюсер: Андрій Іваненко Миколайович
Відео: Нічне небо
Жанр: Документальний
Країна продюсера: США
Платформа: FilmStream
Рейтинг: 9.2
Дата перегляду: 2024-09-14
Час перегляду: 23:30
--------------------------------------------------
Глядач: Сергій Кравченко Володимирович
Продюсер: Оксана Мельник Петрівна
Відео: Сміх до сліз
Жанр: Комедія
Країна продюсера: Велика Британія
Платформа: ComedyVision
Рейтинг: 7.8
Дата перегляду: 2024-09-15
Час перегляду: 19:15
--------------------------------------------------
Глядач: Ірина Марченко Олегівна
Продюсер: Оксана Мельник Петрівна
Відео: Сміх до сліз
Жанр: Комедія
Країна продюсера: Велика Британія
Платформа: ComedyVision
Рейтинг: 7.8
Дата перегляду: 2024-09-15
Час перегляду: 21:00
--------------------------------------------------
Глядач: Павло Шевчук Михайлович
Продюсер: Ірина Петренко Василівна
Відео: Осінній дощ
Жанр: Мелодрама
Країна продюсера: Україна
Платформа: VideoHub
Рейтинг: 8.3
Дата перегляду: 2024-09-15
Час перегляду: 22:45
--------------------------------------------------
Глядач: Наталія Гончарук Вікторівна
Продюсер: Андрій Іваненко Миколайович
Відео: Міські джунглі
Жанр: Документальний
Країна продюсера: США
Платформа: FilmStream
Рейтинг: 8.9
Дата перегляду: 2024-09-16
Час перегляду: 20:30
--------------------------------------------------
Глядач: Артем Лисенко Олегович
Продюсер: Оксана Мельник Петрівна
Відео: Вечірні жарти
Жанр: Комедія
Країна продюсера: Велика Британія
Платформа: ComedyVision
Рейтинг: 7.5
Дата перегляду: 2024-09-16
Час перегляду: 22:15
--------------------------------------------------
Глядач: Олег Коваленко Іванович
Продюсер: Ірина Петренко Василівна
Відео: Осінній дощ
Жанр: Мелодрама
Країна продюсера: Україна
Платформа: VideoHub
Рейтинг: 8.3
Дата перегляду: 2024-09-16
Час перегляду: 23:00
--------------------------------------------------
Глядач: Михайло Ковальчук Іванович
Продюсер: Андрій Іваненко Миколайович
Відео: Нічне небо
Жанр: Документальний
Країна продюсера: США
Платформа: FilmStream
Рейтинг: 9.2
Дата перегляду: 2024-09-17
Час перегляду: 19:45
--------------------------------------------------
Глядач: Дмитро Бондаренко Сергійович
Продюсер: Оксана Мельник Петрівна
Відео: Сміх до сліз
Жанр: Комедія
Країна продюсера: Велика Британія
Платформа: ComedyVision
Рейтинг: 7.8
Дата перегляду: 2024-09-17
Час перегляду: 21:30
--------------------------------------------------
лист. 13, 2025 9:31:50 ДП org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl$PoolState stop
INFO: HHH10001008: Cleaning up connection pool [jdbc:postgresql://localhost:5432/videohost_db]
```"# videohosting-app-25-postgres" 
