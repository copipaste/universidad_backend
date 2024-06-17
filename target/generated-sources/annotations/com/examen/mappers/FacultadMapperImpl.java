package com.examen.mappers;

import com.examen.dtos.facultad.CreateFacultadDto;
import com.examen.dtos.facultad.FacultadDto;
import com.examen.entities.Facultad;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-17T10:44:44-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class FacultadMapperImpl implements FacultadMapper {

    @Override
    public FacultadDto toFacultadDto(Facultad facultad) {
        if ( facultad == null ) {
            return null;
        }

        FacultadDto.FacultadDtoBuilder facultadDto = FacultadDto.builder();

        facultadDto.id( facultad.getId() );
        facultadDto.nombre( facultad.getNombre() );
        facultadDto.active( facultad.getActive() );

        return facultadDto.build();
    }

    @Override
    public Facultad toFacultad(CreateFacultadDto createFacultadDto) {
        if ( createFacultadDto == null ) {
            return null;
        }

        Facultad.FacultadBuilder facultad = Facultad.builder();

        facultad.nombre( createFacultadDto.nombre() );

        facultad.active( 1 );

        return facultad.build();
    }
}
