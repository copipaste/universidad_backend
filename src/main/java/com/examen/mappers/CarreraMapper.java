package com.examen.mappers;

import com.examen.dtos.carrera.CarreraDto;
import com.examen.dtos.carrera.CreateCarreraDto;
import com.examen.entities.Carrera;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {FacultadMapper.class})
public interface CarreraMapper {

    @Mapping(source = "facultad", target = "facultad")
    CarreraDto toCarreraDto(Carrera carrera);

    @Mapping(target = "active", constant = "1")
    @Mapping(source = "facultadId", target = "facultad.id")
    Carrera toCarrera(CreateCarreraDto createCarreraDto);
}
