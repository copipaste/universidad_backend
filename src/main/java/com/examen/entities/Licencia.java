package com.examen.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "licencias")
public class Licencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate fechaSolicitada;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(nullable = false)
    private String justificacion;

    @Column(nullable = false)
    private boolean efectuada;

    @ManyToOne
    @JoinColumn(name = "prog_ac_id", nullable = false)
    private ProgramacionAcademica programacionAcademica;
}
