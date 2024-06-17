package com.examen.mappers;

import com.examen.dtos.LicenciaDTO;
import com.examen.entities.Licencia;
import com.examen.entities.ProgramacionAcademica;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-17T10:44:44-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class LicenciaMapperImpl implements LicenciaMapper {

    @Override
    public LicenciaDTO toDTO(Licencia licencia) {
        if ( licencia == null ) {
            return null;
        }

        LicenciaDTO licenciaDTO = new LicenciaDTO();

        licenciaDTO.setProgAcId( licenciaProgramacionAcademicaId( licencia ) );
        licenciaDTO.setId( licencia.getId() );
        licenciaDTO.setFechaSolicitada( licencia.getFechaSolicitada() );
        licenciaDTO.setFecha( licencia.getFecha() );
        licenciaDTO.setJustificacion( licencia.getJustificacion() );
        licenciaDTO.setEfectuada( licencia.isEfectuada() );

        return licenciaDTO;
    }

    @Override
    public Licencia toEntity(LicenciaDTO licenciaDTO) {
        if ( licenciaDTO == null ) {
            return null;
        }

        Licencia.LicenciaBuilder licencia = Licencia.builder();

        licencia.programacionAcademica( mapProgramacionAcademica( licenciaDTO.getProgAcId() ) );
        licencia.id( licenciaDTO.getId() );
        licencia.fechaSolicitada( licenciaDTO.getFechaSolicitada() );
        licencia.fecha( licenciaDTO.getFecha() );
        licencia.justificacion( licenciaDTO.getJustificacion() );
        licencia.efectuada( licenciaDTO.isEfectuada() );

        return licencia.build();
    }

    private Long licenciaProgramacionAcademicaId(Licencia licencia) {
        if ( licencia == null ) {
            return null;
        }
        ProgramacionAcademica programacionAcademica = licencia.getProgramacionAcademica();
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
