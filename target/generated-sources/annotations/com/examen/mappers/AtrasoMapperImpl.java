package com.examen.mappers;

import com.examen.dtos.atraso.AtrasoDTO;
import com.examen.entities.Asistencia;
import com.examen.entities.Atraso;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-17T10:44:43-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class AtrasoMapperImpl implements AtrasoMapper {

    @Autowired
    private Helper helper;

    @Override
    public AtrasoDTO toDTO(Atraso atraso) {
        if ( atraso == null ) {
            return null;
        }

        AtrasoDTO.AtrasoDTOBuilder atrasoDTO = AtrasoDTO.builder();

        atrasoDTO.asistenciaId( atrasoAsistenciaId( atraso ) );
        atrasoDTO.id( atraso.getId() );
        atrasoDTO.motivo( atraso.getMotivo() );

        return atrasoDTO.build();
    }

    @Override
    public Atraso toEntity(AtrasoDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Atraso.AtrasoBuilder atraso = Atraso.builder();

        atraso.asistencia( helper.map( dto.getAsistenciaId() ) );
        atraso.id( dto.getId() );
        atraso.motivo( dto.getMotivo() );

        return atraso.build();
    }

    private Long atrasoAsistenciaId(Atraso atraso) {
        if ( atraso == null ) {
            return null;
        }
        Asistencia asistencia = atraso.getAsistencia();
        if ( asistencia == null ) {
            return null;
        }
        Long id = asistencia.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
