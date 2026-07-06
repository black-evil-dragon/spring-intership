package com.golgan.toduo.modules.desks.models;

import com.golgan.toduo.core.entities.BaseEntity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "boards")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeskColumnEntity extends BaseEntity {

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false)
    private Integer sort;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "desk_id", nullable = false)
    private DeskEntity desk;

}
