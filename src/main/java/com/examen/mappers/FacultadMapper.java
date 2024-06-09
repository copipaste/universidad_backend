package com.examen.mappers;

import com.examen.dtos.facultad.CreateFacultadDto;
import com.examen.dtos.facultad.FacultadDto;
import com.examen.entities.Facultad;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FacultadMapper {

    FacultadDto toFacultadDto(Facultad facultad);

    @Mapping(target = "active", constant = "1")
    Facultad toFacultad(CreateFacultadDto createFacultadDto);
}
