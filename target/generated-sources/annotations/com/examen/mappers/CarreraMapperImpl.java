package com.examen.mappers;

import com.examen.dtos.carrera.CarreraDto;
import com.examen.dtos.carrera.CreateCarreraDto;
import com.examen.entities.Carrera;
import com.examen.entities.Facultad;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-17T10:44:43-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class CarreraMapperImpl implements CarreraMapper {

    @Autowired
    private FacultadMapper facultadMapper;

    @Override
    public CarreraDto toCarreraDto(Carrera carrera) {
        if ( carrera == null ) {
            return null;
        }

        CarreraDto.CarreraDtoBuilder carreraDto = CarreraDto.builder();

        carreraDto.facultad( facultadMapper.toFacultadDto( carrera.getFacultad() ) );
        carreraDto.id( carrera.getId() );
        carreraDto.nombre( carrera.getNombre() );
        carreraDto.active( carrera.getActive() );

        return carreraDto.build();
    }

    @Override
    public Carrera toCarrera(CreateCarreraDto createCarreraDto) {
        if ( createCarreraDto == null ) {
            return null;
        }

        Carrera.CarreraBuilder carrera = Carrera.builder();

        carrera.facultad( createCarreraDtoToFacultad( createCarreraDto ) );
        carrera.nombre( createCarreraDto.nombre() );

        carrera.active( 1 );

        return carrera.build();
    }

    protected Facultad createCarreraDtoToFacultad(CreateCarreraDto createCarreraDto) {
        if ( createCarreraDto == null ) {
            return null;
        }

        Facultad.FacultadBuilder facultad = Facultad.builder();

        facultad.id( createCarreraDto.facultadId() );

        return facultad.build();
    }
}
