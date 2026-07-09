package com.golgan.toduo.modules.tasks.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

public enum TaskStatus {
    // AWAITING STATUSES
    AWAIT_WORK("Ожидает выполнения", "awaitWork"),
    AWAIT_CONTROL("Ожидает контроля", "awaitControl"),

    // WORK STATUSES
    IN_WORK("В работе", "inWork"),

    // FINISH STATUSES
    COMPLETED("Завершено", "completed");



    @Getter
    private final String title;

    @Getter
    private final String serializableName;


    TaskStatus(String title, String serializableName) {
        this.title = title;
        this.serializableName = serializableName;
    }

    // Думал что-нибудь придумать с serializableName, но пока хз
    @JsonCreator
    public static TaskStatus fromString(String value) {
        if (value == null) return null;
        return TaskStatus.valueOf(value.toUpperCase());
    }

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
