package com.examen.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class LicenciaDTO {
    private Long id;
    private LocalDate fechaSolicitada;
    private LocalDate fecha;
    private String justificacion;
    private boolean efectuada;
    private Long progAcId;
}
