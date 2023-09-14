package com.foro.persistence.repository;

import com.foro.domain.StatusTopico;
import com.foro.domain.entity.TopicoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TopicoRepository extends JpaRepository<TopicoEntity, Long> {

    @Query(value = "SELECT status FROM topico WHERE id = :id", nativeQuery = true)
    StatusTopico findStatus(@Param("id") Long id);
}
