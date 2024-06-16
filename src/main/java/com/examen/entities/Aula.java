package com.examen.entities;

import com.examen.dtos.AulaDataDTO;
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
@Table(name = "aulas")
public class Aula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int numero;

    @Column(nullable = false)
    private int capacidad;

    @ManyToOne
    @JoinColumn(name = "modulo_id", nullable = false)
    private Modulo modulo;

    public void actualizarDatos(AulaDataDTO aulaDataDTO, Modulo modulo) {
        if (aulaDataDTO.numero() != null){
            this.numero = aulaDataDTO.numero();
        }

        if (aulaDataDTO.capacidad() != null){
            this.capacidad = aulaDataDTO.capacidad();
        }
        if (modulo != null){
            this.modulo = modulo;
        }
    }
}
