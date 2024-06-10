package com.examen.dtos;

import lombok.Data;

@Data
public class ProgramacionAcademicaDTO {
    private Long id;
    private int ano;
    private String periodo;
    private Long materiaId;
    private Long docenteId;
}
