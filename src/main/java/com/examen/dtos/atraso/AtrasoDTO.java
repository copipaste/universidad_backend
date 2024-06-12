package com.examen.dtos.atraso;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AtrasoDTO {
    private Long id;
    private String motivo;
    private Long asistenciaId;
}
