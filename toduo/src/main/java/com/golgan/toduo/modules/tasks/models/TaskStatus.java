package com.golgan.toduo.modules.tasks.models;

import lombok.Getter;

public enum TaskStatus {
    // AWAITING STATUSES
    AWAIT_WORK("Ожидает выполнения"),
    AWAIT_CONTROL("Ожидает контроля"),

    // WORK STATUSES
    IN_WORK("В работе"),

    // FINISH STATUSES
    COMPLETED("Завершено");



    @Getter
    private final String title;

    TaskStatus(String title) {
        this.title = title;
    }

    public String getName() {
        return this.name();
    }
}
