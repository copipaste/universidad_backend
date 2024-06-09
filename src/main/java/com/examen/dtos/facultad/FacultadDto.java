package com.examen.dtos.facultad;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FacultadDto {
    private Long id;
    private String nombre;
    private Integer active;
}
