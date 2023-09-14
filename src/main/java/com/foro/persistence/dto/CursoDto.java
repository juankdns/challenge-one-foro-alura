package com.foro.persistence.dto;

import com.foro.validator.SaveValidator;
import com.foro.validator.UpdateValidator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CursoDto(@NotNull(message = "No puede ser nulo", groups = UpdateValidator.class) Long id,
                       @NotNull(message = "No puede ser nulo", groups = SaveValidator.class) @NotBlank(message = "No puede estar en blanco", groups = SaveValidator.class) String nombre,
                       @NotNull(message = "No puede ser nulo", groups = SaveValidator.class) @NotBlank(message = "No puede estar en blanco", groups = SaveValidator.class) String categoria) {
}
