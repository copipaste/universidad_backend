package com.examen.mappers;

import com.examen.dtos.falta.FaltaDTO;
import com.examen.entities.Falta;
import com.examen.entities.ProgramacionAcademica;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-17T10:44:44-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class FaltaMapperImpl implements FaltaMapper {

    @Override
    public FaltaDTO toDTO(Falta falta) {
        if ( falta == null ) {
            return null;
        }

        FaltaDTO.FaltaDTOBuilder faltaDTO = FaltaDTO.builder();

        faltaDTO.progAcId( faltaProgramacionAcademicaId( falta ) );
        faltaDTO.id( falta.getId() );
        faltaDTO.fecha( falta.getFecha() );

        return faltaDTO.build();
    }

    @Override
    public Falta toEntity(FaltaDTO faltaDTO) {
        if ( faltaDTO == null ) {
            return null;
        }

        Falta.FaltaBuilder falta = Falta.builder();

        falta.programacionAcademica( mapProgramacionAcademica( faltaDTO.getProgAcId() ) );
        falta.id( faltaDTO.getId() );
        falta.fecha( faltaDTO.getFecha() );

        return falta.build();
    }

    private Long faltaProgramacionAcademicaId(Falta falta) {
        if ( falta == null ) {
            return null;
        }
        ProgramacionAcademica programacionAcademica = falta.getProgramacionAcademica();
        if ( programacionAcademica == null ) {
            return null;
        }
        Long id = programacionAcademica.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
