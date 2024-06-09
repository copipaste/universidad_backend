package com.examen.mappers;

import com.examen.dtos.AulaDTO;
import com.examen.entities.Aula;
import com.examen.entities.Modulo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AulaMapper {
    AulaMapper INSTANCE = Mappers.getMapper(AulaMapper.class);

    @Mappings({
            @Mapping(source = "modulo.id", target = "moduloId")
    })
    AulaDTO toDTO(Aula aula);

    @Mappings({
            @Mapping(source = "moduloId", target = "modulo")
    })
    Aula toEntity(AulaDTO aulaDTO);

    default Modulo map(Long value) {
        if (value == null) {
            return null;
        }
        Modulo modulo = new Modulo();
        modulo.setId(value);
        return modulo;
    }

    default Long map(Modulo modulo) {
        if (modulo == null) {
            return null;
        }
        return modulo.getId();
    }
}
