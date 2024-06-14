package com.examen.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ProgramacionAcademicaDTO {
    private Long id;
    private int ano;
    private String periodo;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Long materiaId;
    private Long docenteId;
}
