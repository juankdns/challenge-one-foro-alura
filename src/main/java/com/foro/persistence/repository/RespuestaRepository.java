package com.foro.persistence.repository;

import com.foro.domain.entity.RespuestaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RespuestaRepository extends JpaRepository<RespuestaEntity, Long> {
}
