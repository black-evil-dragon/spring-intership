package com.golgan.task5.modules.tasks.models;

import com.golgan.task5.core.entities.AuditableEntity;
import com.golgan.task5.modules.users.models.UserEntity;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "tasks")
@NoArgsConstructor
public class TaskEntity extends AuditableEntity {

    @Setter
    @Getter
    @Column(nullable = false)
    private String title;

    @Setter
    @Getter
    @Column(nullable = false)
    private String text;

    @Setter
    @Getter
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

}
