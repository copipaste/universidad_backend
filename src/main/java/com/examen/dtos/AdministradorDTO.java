package com.examen.dtos;

import lombok.Data;
import java.time.LocalDate;

@Data
public class AdministradorDTO {
    private Long id;
    private Long userId;  // Añadir userId
    private String name;
    private String email;
    private LocalDate fechaDeNacimiento;
    private String cargo;
    private String telefono;
    private String direccion;
}
