package com.examen.mappers;

import com.examen.dtos.DocenteDTO;
import com.examen.entities.Docente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DocenteMapper {

    @Mapping(source = "user.id", target = "userId")
    DocenteDTO toDTO(Docente docente);

    @Mapping(source = "userId", target = "user.id")
    Docente toEntity(DocenteDTO docenteDTO);
}
