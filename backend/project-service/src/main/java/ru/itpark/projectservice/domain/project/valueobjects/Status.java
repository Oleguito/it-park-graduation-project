package ru.itpark.projectservice.domain.project.valueobjects;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Status {

    NEW,
    PLANNED,
    STARTED,
    CONFIRMATION,
    CONFIRMED,
    CANCELLED

//    private String name;
//
//    @Override
//    public String toString() {
//        return name;
//    }
}
