package com.examen.mappers;

import com.examen.dtos.MateriaDto;
import com.examen.entities.Materia;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-17T10:44:44-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class MateriaMapperImpl implements MateriaMapper {

    @Override
    public MateriaDto toMateriaDto(Materia materia) {
        if ( materia == null ) {
            return null;
        }

        MateriaDto.MateriaDtoBuilder materiaDto = MateriaDto.builder();

        materiaDto.id( materia.getId() );
        materiaDto.nombre( materia.getNombre() );
        materiaDto.sigla( materia.getSigla() );
        materiaDto.grupo( materia.getGrupo() );

        return materiaDto.build();
    }

    @Override
    public Materia toMateria(MateriaDto materiaDto) {
        if ( materiaDto == null ) {
            return null;
        }

        Materia.MateriaBuilder materia = Materia.builder();

        if ( materiaDto.getId() != null ) {
            materia.id( materiaDto.getId() );
        }
        materia.nombre( materiaDto.getNombre() );
        materia.sigla( materiaDto.getSigla() );
        materia.grupo( materiaDto.getGrupo() );

        return materia.build();
    }
}
