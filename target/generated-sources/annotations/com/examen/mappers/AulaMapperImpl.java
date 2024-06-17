package com.examen.mappers;

import com.examen.dtos.AulaDTO;
import com.examen.entities.Aula;
import com.examen.entities.Modulo;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-17T10:44:43-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class AulaMapperImpl implements AulaMapper {

    @Override
    public AulaDTO toDTO(Aula aula) {
        if ( aula == null ) {
            return null;
        }

        AulaDTO aulaDTO = new AulaDTO();

        aulaDTO.setModuloId( aulaModuloId( aula ) );
        aulaDTO.setId( aula.getId() );
        aulaDTO.setNumero( aula.getNumero() );
        aulaDTO.setCapacidad( aula.getCapacidad() );

        return aulaDTO;
    }

    @Override
    public Aula toEntity(AulaDTO aulaDTO) {
        if ( aulaDTO == null ) {
            return null;
        }

        Aula.AulaBuilder aula = Aula.builder();

        aula.modulo( map( aulaDTO.getModuloId() ) );
        aula.id( aulaDTO.getId() );
        aula.numero( aulaDTO.getNumero() );
        aula.capacidad( aulaDTO.getCapacidad() );

        return aula.build();
    }

    private Long aulaModuloId(Aula aula) {
        if ( aula == null ) {
            return null;
        }
        Modulo modulo = aula.getModulo();
        if ( modulo == null ) {
            return null;
        }
        Long id = modulo.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
