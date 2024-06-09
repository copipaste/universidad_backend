package com.examen.dtos.facultad;

import jakarta.validation.constraints.NotBlank;

public record CreateFacultadDto(

    @NotBlank
    String nombre
) { }
