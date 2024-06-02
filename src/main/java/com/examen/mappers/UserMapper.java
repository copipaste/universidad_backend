package com.examen.mappers;

import com.examen.dtos.SignUpDto;
import com.examen.dtos.UserDto;
import com.examen.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toUserDto(User user);

    @Mapping(target = "password", ignore = true)
    User signUpToUser(SignUpDto userDto);
}
