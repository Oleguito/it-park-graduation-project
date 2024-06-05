"# it-park-graduation-project" 

@1ommy @padree @et_lana_banana
1)
Идея для приложения: Платформа для Организации и Управления Совместными Проектами
Описание:
Приложение представляет собой платформу для организации и управления совместными проектами, которая позволяет командам эффективно планировать, выполнять и отслеживать прогресс задач. Платформа обеспечивает интеграцию с популярными инструментами для совместной работы, такими как календари, системы управления документами и чаты.

Микросервисы:

▬ Сервис Аутентификации и Авторизации
Функционал: Управление учетными записями пользователей, аутентификация, авторизация и контроль доступа.
Технологии: OAuth2, JWT, базы данных пользователей (например, PostgreSQL).

▬ Сервис Управления Проектами
Функционал: Создание, редактирование и удаление проектов, управление задачами, назначение ответственных.
Технологии: REST API, база данных для хранения информации о проектах и задачах (например, MongoDB).

▬ Сервис Календаря и Планирования
Функционал: Интеграция с календарями, планирование событий и задач, уведомления о сроках.
Технологии: REST API, интеграция с календарными сервисами (например, Google Calendar API).

▬ Сервис Управления Документами
Функционал: Загрузка, хранение и совместная работа с документами, управление доступом к документам.
Технологии: Облачное хранилище (например, AWS S3), редакторы документов (например, Google Docs API).

▬ Сервис Чата и Коммуникаций
Функционал: Встроенные чаты для общения команды, уведомления о новых сообщениях и событиях.
Технологии: WebSockets для реального времени, база данных для хранения истории чатов (например, MongoDB).

▬ Сервис Аналитики и Отчетов
Функционал: Сбор данных о прогрессе задач, анализ эффективности команды, генерация отчетов.
Технологии: Big Data технологии (например, Hadoop, Spark), база данных для аналитики (например, Elasticsearch).

Взаимодействие между микросервисами:
► API Gateway: Центральная точка доступа для всех запросов, маршрутизация запросов к соответствующим микросервисам.
► Message Broker: Используется для асинхронного взаимодействия между микросервисами (например, RabbitMQ, Kafka).
► Service Registry: Для обнаружения и управления микросервисами (например, Eureka).
► Monitoring и Logging: Инструменты для мониторинга и логирования работы микросервисов (например, Prometheus, ELK Stack).

В команде:
Oleguito
@et_lana_banana
@padree

2) тимлид Oleguito

3) https://tracker.yandex.ru/pages/projects/1

4) https://github.com/Oleguito/it-park-graduation-project

5) Диаграммы (dbdiagram, excalidraw) в папке Diagrams