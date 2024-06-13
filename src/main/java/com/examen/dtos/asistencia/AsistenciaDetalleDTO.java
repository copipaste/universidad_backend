package com.examen.dtos.asistencia;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AsistenciaDetalleDTO {
    private String tipo;
    private String fecha;
    private String hora;
    private String materia;
    private String sigla;
    private String grupo;
}
