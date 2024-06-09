package com.examen.dtos;

import lombok.Data;

@Data
public class DocenteDTO {
    private Long id;
    private String telefono;
    private String profesion;
    private String sexo;
    private String direccion;
    private Long userId;
}
