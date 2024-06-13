package com.examen.entities;

import com.examen.dtos.facultad.CreateFacultadDto;
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
@Table(name = "facultad")
public class Facultad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    @ColumnDefault("1")
    private Integer active = 1;

//    public Facultad(CreateFacultadDto createFacultadDto) {
//        this.nombre = createFacultadDto.nombre();
//    }

    public void actualizarDatos(CreateFacultadDto createFacultadDto) {
        if (createFacultadDto.nombre() != null){
            this.nombre = createFacultadDto.nombre();
        }
    }

    public void desactivarFacultad() {
        this.active = 0;
    }

    public void activarFacultad() {
        this.active = 1;
    }
}