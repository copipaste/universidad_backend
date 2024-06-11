package com.examen.dtos.asistencia;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class AsistenciaDTO {
    private Long id;
    private LocalTime hora;
    private LocalDate fecha;
    private boolean virtual;
    private Long docenteId;
    private Long horarioId;
}
