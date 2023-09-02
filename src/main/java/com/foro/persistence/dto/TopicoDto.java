package com.foro.persistence.dto;

import com.foro.domain.entity.CursoEntity;
import com.foro.domain.entity.RespuestaEntity;

import java.time.LocalDateTime;
import java.util.List;

public record TopicoDto(Long id, Long idCurso, String titulo, String mensaje, LocalDateTime fechaCreacion,
                        String status, CursoEntity curso, List<RespuestaEntity> respuestas) {
}
