package com.examen.mappers;

import com.examen.dtos.MateriaDto;
import com.examen.entities.Materia;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MateriaMapper {
    MateriaDto toMateriaDto(Materia materia);
}
