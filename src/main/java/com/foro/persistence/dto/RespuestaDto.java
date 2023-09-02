package com.foro.persistence.dto;

import com.foro.domain.entity.TopicoEntity;

import java.time.LocalDateTime;

public record RespuestaDto(Long id, Long idTopico, String mensaje, LocalDateTime fechaCreacion, Boolean solucion,
                           TopicoEntity topico) {
}
