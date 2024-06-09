package com.examen.mappers;

import com.examen.dtos.facultad.FacultadDto;
import com.examen.entities.Facultad;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FacultadMapper {

    FacultadDto toFacultadDto(Facultad facultad);
}
