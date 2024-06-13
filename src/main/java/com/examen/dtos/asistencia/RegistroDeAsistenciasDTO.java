package com.examen.dtos.asistencia;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegistroDeAsistenciasDTO {
    private Map<String, List<AsistenciaDetalleDTO>> asistencias;
}

