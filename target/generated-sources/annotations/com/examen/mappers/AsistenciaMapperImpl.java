package com.examen.mappers;

import com.examen.dtos.asistencia.AsistenciaDTO;
import com.examen.entities.Asistencia;
import com.examen.entities.Docente;
import com.examen.entities.Horario;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-17T10:44:43-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class AsistenciaMapperImpl implements AsistenciaMapper {

    @Override
    public AsistenciaDTO toDTO(Asistencia asistencia) {
        if ( asistencia == null ) {
            return null;
        }

        AsistenciaDTO asistenciaDTO = new AsistenciaDTO();

        asistenciaDTO.setDocenteId( asistenciaDocenteId( asistencia ) );
        asistenciaDTO.setHorarioId( asistenciaHorarioId( asistencia ) );
        asistenciaDTO.setId( asistencia.getId() );
        asistenciaDTO.setHora( asistencia.getHora() );
        asistenciaDTO.setFecha( asistencia.getFecha() );
        asistenciaDTO.setVirtual( asistencia.isVirtual() );

        return asistenciaDTO;
    }

    @Override
    public Asistencia toEntity(AsistenciaDTO asistenciaDTO) {
        if ( asistenciaDTO == null ) {
            return null;
        }

        Asistencia.AsistenciaBuilder asistencia = Asistencia.builder();

        asistencia.docente( mapDocente( asistenciaDTO.getDocenteId() ) );
        asistencia.horario( mapHorario( asistenciaDTO.getHorarioId() ) );
        asistencia.id( asistenciaDTO.getId() );
        asistencia.hora( asistenciaDTO.getHora() );
        asistencia.fecha( asistenciaDTO.getFecha() );
        asistencia.virtual( asistenciaDTO.isVirtual() );

        return asistencia.build();
    }

    private Long asistenciaDocenteId(Asistencia asistencia) {
        if ( asistencia == null ) {
            return null;
        }
        Docente docente = asistencia.getDocente();
        if ( docente == null ) {
            return null;
        }
        Long id = docente.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long asistenciaHorarioId(Asistencia asistencia) {
        if ( asistencia == null ) {
            return null;
        }
        Horario horario = asistencia.getHorario();
        if ( horario == null ) {
            return null;
        }
        Long id = horario.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
