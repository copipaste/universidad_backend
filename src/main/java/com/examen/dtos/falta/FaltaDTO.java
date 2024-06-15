package com.examen.dtos.falta;

import java.time.LocalDate;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FaltaDTO {
    private Long id;
    private LocalDate fecha;
    private Long progAcId;
}
