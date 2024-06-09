package com.examen.dtos.carrera;

import com.examen.dtos.facultad.FacultadDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarreraDto {
    private Long id;
    private String nombre;
    private FacultadDto facultad;
    private Integer active;
}
