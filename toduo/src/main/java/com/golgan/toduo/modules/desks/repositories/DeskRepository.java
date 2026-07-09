package com.golgan.toduo.modules.desks.repositories;

import com.golgan.toduo.modules.desks.models.DeskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeskRepository extends JpaRepository<DeskEntity, Long> {


}
