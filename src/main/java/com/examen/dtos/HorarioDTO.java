package com.examen.dtos;

import lombok.Data;

import java.time.LocalTime;

@Data
public class HorarioDTO {
    private Long id;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private String dia;
    private Long aulasId;
    private Long progAcId;
}
