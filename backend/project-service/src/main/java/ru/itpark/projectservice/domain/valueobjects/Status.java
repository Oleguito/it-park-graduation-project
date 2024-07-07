package ru.itpark.projectservice.domain.valueobjects;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Status {

    NEW("Новый"),
    PLANNED("Запланирован"),
    STARTED("В работе"),
    CONFIRMATION("На подтверждении"),
    CONFIRMED("Подтвержден"),
    CANCELLED("Отменен");

    private String name;

    @Override
    public String toString() {
        return name;
    }
}
