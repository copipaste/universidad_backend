package com.examen.mappers;

import com.examen.dtos.AtrasoDTO;
import com.examen.entities.Atraso;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AtrasoMapper {
    AtrasoDTO toDTO(Atraso atraso);
    Atraso toEntity(AtrasoDTO atrasoDTO);
}
