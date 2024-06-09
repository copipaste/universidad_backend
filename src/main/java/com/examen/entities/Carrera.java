package com.examen.entities;

import com.examen.dtos.carrera.CreateCarreraDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "carrera")
public class Carrera {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "facultad_id", nullable = false)
    private Facultad facultad;

    @Column(nullable = false)
    @ColumnDefault("1")
    private Integer active = 1;

    public void actualizarDatos(CreateCarreraDto createCarreraDto, Facultad facultad) {
        if (createCarreraDto.nombre() != null){
            this.nombre = createCarreraDto.nombre();
        }

        if (facultad != null){
            this.facultad = facultad;
        }
    }

    public void desactivarCarrera() {
        this.active = 0;
    }
}
