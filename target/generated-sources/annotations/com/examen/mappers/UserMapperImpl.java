package com.examen.mappers;

import com.examen.dtos.SignUpDto;
import com.examen.dtos.UserDto;
import com.examen.entities.User;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-17T10:44:44-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Autowired
    private DocenteMapper docenteMapper;

    @Override
    public UserDto toUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto.UserDtoBuilder userDto = UserDto.builder();

        userDto.docente( docenteMapper.toDTO( user.getDocente() ) );
        userDto.id( user.getId() );
        userDto.name( user.getName() );
        userDto.email( user.getEmail() );

        return userDto.build();
    }

    @Override
    public User signUpToUser(SignUpDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.name( userDto.name() );
        user.email( userDto.email() );

        return user.build();
    }
}
