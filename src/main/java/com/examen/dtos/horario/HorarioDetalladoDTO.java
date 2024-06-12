package com.examen.dtos;

import lombok.Data;

import java.time.LocalTime;

@Data
public class HorarioDetalladoDTO {
    private Long horarioId;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private String dia;
    private AulaDTO aula;
    private ProgramacionAcademicaDTO programacionAcademica;
    private MateriaDto materia;
}
