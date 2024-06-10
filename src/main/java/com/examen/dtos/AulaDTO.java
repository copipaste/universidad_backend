package com.examen.dtos;

import lombok.Data;

@Data
public class AulaDTO {
    private Long id;
    private int numero;
    private int capacidad;
    private Long moduloId;
}
