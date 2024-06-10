package com.examen.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class FaltaDTO {
    private Long id;
    private LocalDate fecha;
    private Long progAcId;
}
