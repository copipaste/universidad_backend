package com.examen.mappers;

import com.examen.dtos.ModuloDTO;
import com.examen.entities.Modulo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ModuloMapper {
    ModuloDTO toDTO(Modulo modulo);

    Modulo toEntity(ModuloDTO moduloDTO);
}
