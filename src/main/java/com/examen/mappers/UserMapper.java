package com.examen.mappers;

import com.examen.dtos.SignUpDto;
import com.examen.dtos.UserDto;
import com.examen.dtos.DocenteDTO;
import com.examen.entities.Docente;
import com.examen.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = DocenteMapper.class)
public interface UserMapper {
    @Mapping(source = "docente", target = "docente")
    UserDto toUserDto(User user);

    @Mapping(target = "password", ignore = true)
    User signUpToUser(SignUpDto userDto);

}
