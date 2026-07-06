package com.golgan.toduo.modules.tasks.models;



import com.golgan.toduo.core.entities.AuditableEntity;
import com.golgan.toduo.modules.desks.models.DeskColumnEntity;
import com.golgan.toduo.modules.users.models.UserEntity;


import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

/// # Задача
/// - Название
/// - Текст
/// - Крайний срок
/// - Статус
/// - Постановщик
/// - Исполнитель

@Entity
@Table(name = "tasks")
@NoArgsConstructor
public class TaskEntity extends AuditableEntity {

    /// ## Название
    /// Заголовок у задачи, для отображения в списках
    /// или в шапке задачи
    /// ...
    @Setter
    @Getter
    @Column(nullable = false)
    private String name;

    /// ## Текст задачи
    /// Основное содержимое сути задачи
    /// ...
    // В целом можно тут хранить md формат или html верстку...
    @Setter
    @Getter
    @Column()
    private String description;

    @Setter
    @Getter
    @Column(nullable = false)
    private TaskStatus status;

    @Setter
    @Getter
    @Column(name = "deadline")
    private Instant deadline;


    // Исполнитель и постановщик
    @Setter
    @Getter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private UserEntity author;

    @Setter
    @Getter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignee_id")
    private UserEntity assignee;


    // Отношение с колонкой
    @Setter
    @Getter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "column_id", nullable = false)
    private DeskColumnEntity column;
}
