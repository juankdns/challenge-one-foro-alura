package com.foro.persistence.dto;

import com.foro.domain.entity.TopicoEntity;
import com.foro.validator.SaveValidator;
import com.foro.validator.UpdateValidator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

import java.time.LocalDateTime;

public record RespuestaDto(@NotNull(message = "No puede ser nulo", groups = UpdateValidator.class) Long id,
                           @NotNull(message = "No puede ser nulo", groups = SaveValidator.class) Long idTopico,
                           @NotNull(message = "No puede ser nulo", groups = SaveValidator.class) @NotBlank(message = "No puede estar en blanco", groups = SaveValidator.class) String mensaje,
                           @Null(message = "Tiene valor predeterminado", groups = SaveValidator.class) LocalDateTime fechaCreacion,
                           @Null(message = "Tiene valor predeterminado", groups = SaveValidator.class) Boolean solucion,
                           TopicoEntity topico) {
}
