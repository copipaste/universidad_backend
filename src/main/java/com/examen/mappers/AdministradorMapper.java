package com.examen.mappers;

import com.examen.dtos.AdministradorDTO;
import com.examen.entities.Administrador;
import com.examen.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AdministradorMapper {
    AdministradorMapper INSTANCE = Mappers.getMapper(AdministradorMapper.class);

    @Mapping(source = "user.id", target = "userId")
    AdministradorDTO toDTO(Administrador administrador);

    @Mapping(source = "userId", target = "user.id")
    Administrador toEntity(AdministradorDTO administradorDTO);
}
