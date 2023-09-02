package com.foro.persistence.repository;

import com.foro.domain.entity.TopicoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepository extends JpaRepository<TopicoEntity, Long> {
}
