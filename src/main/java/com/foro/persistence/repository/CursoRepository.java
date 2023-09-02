package com.foro.persistence.repository;

import com.foro.domain.entity.CursoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<CursoEntity, Long> {
}
