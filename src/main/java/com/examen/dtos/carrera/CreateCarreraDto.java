package com.examen.dtos.carrera;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateCarreraDto(

        @NotBlank
        String nombre,

        @NotNull
        Long facultadId
) { }
