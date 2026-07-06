package com.golgan.toduo.modules.desks.models;

import com.golgan.toduo.core.entities.AuditableEntity;
import com.golgan.toduo.modules.users.models.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "desks")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeskEntity extends AuditableEntity {

    @Setter
    @Getter
    @Column(nullable = false, length = 100)
    private String name;

    @Setter
    @Getter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private UserEntity owner;

    @Setter
    @Getter
    @OneToMany(mappedBy = "desk", cascade = CascadeType.ALL)
    @OrderBy("sort ASC")
    @Builder.Default
    private List<DeskColumnEntity> columns = new ArrayList<>();


}
