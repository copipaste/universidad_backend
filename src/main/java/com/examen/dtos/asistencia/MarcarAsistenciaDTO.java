package com.examen.dtos.asistencia;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MarcarAsistenciaDTO {
    private Long docenteId;
    private LocalTime hora;
    private LocalDate fecha;
    private double latitud;
    private double longitud;
    private Long materiaId;
    private Long horarioId;
    private boolean virtual; // Nuevo campo
}
