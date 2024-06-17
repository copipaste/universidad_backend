package com.examen.mappers;

import com.examen.dtos.ProgramacionAcademicaDTO;
import com.examen.entities.Docente;
import com.examen.entities.Materia;
import com.examen.entities.ProgramacionAcademica;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-17T10:44:44-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class ProgramacionAcademicaMapperImpl implements ProgramacionAcademicaMapper {

    @Override
    public ProgramacionAcademicaDTO toDTO(ProgramacionAcademica programacionAcademica) {
        if ( programacionAcademica == null ) {
            return null;
        }

        ProgramacionAcademicaDTO programacionAcademicaDTO = new ProgramacionAcademicaDTO();

        programacionAcademicaDTO.setMateriaId( programacionAcademicaMateriaId( programacionAcademica ) );
        programacionAcademicaDTO.setDocenteId( programacionAcademicaDocenteId( programacionAcademica ) );
        programacionAcademicaDTO.setId( programacionAcademica.getId() );
        programacionAcademicaDTO.setAno( programacionAcademica.getAno() );
        programacionAcademicaDTO.setPeriodo( programacionAcademica.getPeriodo() );
        programacionAcademicaDTO.setFechaInicio( programacionAcademica.getFechaInicio() );
        programacionAcademicaDTO.setFechaFin( programacionAcademica.getFechaFin() );

        return programacionAcademicaDTO;
    }

    @Override
    public ProgramacionAcademica toEntity(ProgramacionAcademicaDTO programacionAcademicaDTO) {
        if ( programacionAcademicaDTO == null ) {
            return null;
        }

        ProgramacionAcademica.ProgramacionAcademicaBuilder programacionAcademica = ProgramacionAcademica.builder();

        programacionAcademica.materia( mapMateria( programacionAcademicaDTO.getMateriaId() ) );
        programacionAcademica.docente( mapDocente( programacionAcademicaDTO.getDocenteId() ) );
        programacionAcademica.id( programacionAcademicaDTO.getId() );
        programacionAcademica.ano( programacionAcademicaDTO.getAno() );
        programacionAcademica.periodo( programacionAcademicaDTO.getPeriodo() );
        programacionAcademica.fechaInicio( programacionAcademicaDTO.getFechaInicio() );
        programacionAcademica.fechaFin( programacionAcademicaDTO.getFechaFin() );

        return programacionAcademica.build();
    }

    private Long programacionAcademicaMateriaId(ProgramacionAcademica programacionAcademica) {
        if ( programacionAcademica == null ) {
            return null;
        }
        Materia materia = programacionAcademica.getMateria();
        if ( materia == null ) {
            return null;
        }
        long id = materia.getId();
        return id;
    }

    private Long programacionAcademicaDocenteId(ProgramacionAcademica programacionAcademica) {
        if ( programacionAcademica == null ) {
            return null;
        }
        Docente docente = programacionAcademica.getDocente();
        if ( docente == null ) {
            return null;
        }
        Long id = docente.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
