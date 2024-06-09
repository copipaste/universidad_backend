package com.examen.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "administrador")
public class Administrador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @Column(name = "fechaDeNacimiento", nullable = false)
    private LocalDate fechaDeNacimiento;

    @Column(nullable = false)
    private String cargo;

    @Column(nullable = false)
    private String telefono;

    @Column(nullable = false)
    private String direccion;
}
