package com.examen.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "docente")
public class Docente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String telefono;

    @Column(nullable = false)
    private String profesion;

    @Column(nullable = false)
    private String sexo;

    @Column(nullable = false)
    private String direccion;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
