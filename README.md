# Задание 2


## Сборка и запуск

Для сборки проекта требуется JDK 17. Чтобы собрать приложения необходимо выполнить

> mvn clean install

В корне проекта. После можно развернуть приложения в Docker Compose при помощи

>docker-compose up

Приложение Metrics-Producer будет доступно на порту 8081, приложение Metrics-Consumer на порту 8082.

Протестировать API приложений можно в Swagger. Документация к API представлена в Swagger.

> http://localhost:8082/swagger-ui/index.html#/ - consumer

> http://localhost:8081/swagger-ui/index.html#/ - producer

Раз в 30 секунд Metrics-Consumer делает запрос в Producer для сбора логов, после чего читает логи Kafka и сохраняет их в Postgres в формате jsonb.

