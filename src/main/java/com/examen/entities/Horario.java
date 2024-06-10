package com.examen.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "horarios")
public class Horario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "hora_inicio", nullable = false)
    private LocalTime horaInicio;

    @Column(name = "hora_fin", nullable = false)
    private LocalTime horaFin;

    @Column(nullable = false)
    private String dia;

    @ManyToOne
    @JoinColumn(name = "aulas_id", nullable = false)
    private Aula aula;

    @ManyToOne
    @JoinColumn(name = "prog_ac_id", nullable = false)
    private ProgramacionAcademica programacionAcademica;
}
