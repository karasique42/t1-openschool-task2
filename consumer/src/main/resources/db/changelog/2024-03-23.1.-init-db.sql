--liquibase formatted sql

--changeset anikolotov:1
--comment: init db changeset

create table logs
(
    id uuid primary key,
    time timestamp not null,
    cpu_metrics jsonb,
    memory_metrics jsonb
);
