package com.examen.mappers;

import com.examen.dtos.ModuloDTO;
import com.examen.entities.Modulo;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-17T10:44:44-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class ModuloMapperImpl implements ModuloMapper {

    @Override
    public ModuloDTO toDTO(Modulo modulo) {
        if ( modulo == null ) {
            return null;
        }

        ModuloDTO moduloDTO = new ModuloDTO();

        moduloDTO.setId( modulo.getId() );
        moduloDTO.setNumero( modulo.getNumero() );
        moduloDTO.setLatitud( modulo.getLatitud() );
        moduloDTO.setLongitud( modulo.getLongitud() );

        return moduloDTO;
    }

    @Override
    public Modulo toEntity(ModuloDTO moduloDTO) {
        if ( moduloDTO == null ) {
            return null;
        }

        Modulo.ModuloBuilder modulo = Modulo.builder();

        modulo.id( moduloDTO.getId() );
        modulo.numero( moduloDTO.getNumero() );
        modulo.latitud( moduloDTO.getLatitud() );
        modulo.longitud( moduloDTO.getLongitud() );

        return modulo.build();
    }
}
