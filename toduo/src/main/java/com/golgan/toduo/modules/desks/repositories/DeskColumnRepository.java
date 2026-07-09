package com.golgan.toduo.modules.desks.repositories;

import com.golgan.toduo.modules.desks.models.DeskColumnEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface DeskColumnRepository extends JpaRepository<DeskColumnEntity, Long> {


    Page<DeskColumnEntity> findAllByDeskIdOrderByPositionAsc(Long deskId, Pageable pageable);
    List<DeskColumnEntity> findAllByDeskIdOrderByPositionAsc(Long deskId);


    int countByDeskId(Long deskId);
}
