package com.golgan.task5.modules.tasks.models;

import com.golgan.task5.core.entities.AuditableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "tasks")
@NoArgsConstructor
public class TaskEntity extends AuditableEntity {


}
