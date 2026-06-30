package com.golgan.task5.core.entities;


import jakarta.persistence.*;

import lombok.Getter;

@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}