package com.golgan.toduo.modules.desks.models;



import jakarta.persistence.*;

import lombok.*;


@Entity
@Table(name = "desk_columns")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeskColumnEntity {

    @Id
    @Setter
    @Getter
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "column_seq")
    @SequenceGenerator(name = "column_seq", sequenceName = "column_id_seq", allocationSize = 10)
    private Long id;


    @Setter
    @Getter
    @Column(nullable = false, length = 50)
    private String name;

    @Setter
    @Getter
    @Column(nullable = false)
    private Integer position;

    @Setter
    @Getter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "desk_id", nullable = false)
    private DeskEntity desk;

    // TODO Временно для разработки
    // @Setter
    // @Getter
    // @OneToMany(mappedBy = "column", cascade = CascadeType.ALL)
    // @Builder.Default
    // private List<TaskEntity> tasks = new ArrayList<>();
}
