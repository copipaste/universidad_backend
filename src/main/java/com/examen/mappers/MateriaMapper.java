package com.examen.mappers;

import com.examen.dtos.MateriaDto;
import com.examen.entities.Materia;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MateriaMapper {
    MateriaDto toMateriaDto(Materia materia);
    Materia toMateria(MateriaDto materiaDto);
}
