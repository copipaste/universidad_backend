package com.examen.mappers;

import com.examen.dtos.DocenteDTO;
import com.examen.entities.Docente;
import com.examen.entities.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-17T10:44:44-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class DocenteMapperImpl implements DocenteMapper {

    @Override
    public DocenteDTO toDTO(Docente docente) {
        if ( docente == null ) {
            return null;
        }

        DocenteDTO docenteDTO = new DocenteDTO();

        docenteDTO.setUserId( docenteUserId( docente ) );
        docenteDTO.setUserName( docenteUserName( docente ) );
        docenteDTO.setUserEmail( docenteUserEmail( docente ) );
        docenteDTO.setId( docente.getId() );
        docenteDTO.setTelefono( docente.getTelefono() );
        docenteDTO.setProfesion( docente.getProfesion() );
        docenteDTO.setSexo( docente.getSexo() );
        docenteDTO.setDireccion( docente.getDireccion() );

        return docenteDTO;
    }

    @Override
    public Docente toEntity(DocenteDTO docenteDTO) {
        if ( docenteDTO == null ) {
            return null;
        }

        Docente.DocenteBuilder docente = Docente.builder();

        docente.user( docenteDTOToUser( docenteDTO ) );
        docente.id( docenteDTO.getId() );
        docente.telefono( docenteDTO.getTelefono() );
        docente.profesion( docenteDTO.getProfesion() );
        docente.sexo( docenteDTO.getSexo() );
        docente.direccion( docenteDTO.getDireccion() );

        return docente.build();
    }

    private Long docenteUserId(Docente docente) {
        if ( docente == null ) {
            return null;
        }
        User user = docente.getUser();
        if ( user == null ) {
            return null;
        }
        Long id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String docenteUserName(Docente docente) {
        if ( docente == null ) {
            return null;
        }
        User user = docente.getUser();
        if ( user == null ) {
            return null;
        }
        String name = user.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private String docenteUserEmail(Docente docente) {
        if ( docente == null ) {
            return null;
        }
        User user = docente.getUser();
        if ( user == null ) {
            return null;
        }
        String email = user.getEmail();
        if ( email == null ) {
            return null;
        }
        return email;
    }

    protected User docenteDTOToUser(DocenteDTO docenteDTO) {
        if ( docenteDTO == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.id( docenteDTO.getUserId() );

        return user.build();
    }
}
