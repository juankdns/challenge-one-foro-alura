package com.foro.persistence.repository;

import com.foro.domain.entity.RespuestaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RespuestaRepository extends JpaRepository<RespuestaEntity, Long> {

    @Query(value = "SELECT id_topico FROM respuesta WHERE id = :id", nativeQuery = true)
    Long findIdTopico(@Param("id") Long id);

    @Modifying
    @Query(value = "UPDATE topico SET status = 'NO_RESPONDIDO' WHERE 1 = (SELECT COUNT(*) FROM respuesta WHERE id_topico = :id)", nativeQuery = true)
    void updaterStatus(@Param("id") Long id);

}
