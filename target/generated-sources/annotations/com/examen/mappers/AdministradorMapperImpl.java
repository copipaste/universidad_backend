package com.examen.mappers;

import com.examen.dtos.AdministradorDTO;
import com.examen.entities.Administrador;
import com.examen.entities.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-17T10:44:43-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class AdministradorMapperImpl implements AdministradorMapper {

    @Override
    public AdministradorDTO toDTO(Administrador administrador) {
        if ( administrador == null ) {
            return null;
        }

        AdministradorDTO administradorDTO = new AdministradorDTO();

        administradorDTO.setUserId( administradorUserId( administrador ) );
        administradorDTO.setName( administradorUserName( administrador ) );
        administradorDTO.setEmail( administradorUserEmail( administrador ) );
        administradorDTO.setId( administrador.getId() );
        administradorDTO.setFechaDeNacimiento( administrador.getFechaDeNacimiento() );
        administradorDTO.setCargo( administrador.getCargo() );
        administradorDTO.setTelefono( administrador.getTelefono() );
        administradorDTO.setDireccion( administrador.getDireccion() );

        return administradorDTO;
    }

    @Override
    public Administrador toEntity(AdministradorDTO administradorDTO) {
        if ( administradorDTO == null ) {
            return null;
        }

        Administrador administrador = new Administrador();

        administrador.setUser( administradorDTOToUser( administradorDTO ) );
        administrador.setId( administradorDTO.getId() );
        administrador.setFechaDeNacimiento( administradorDTO.getFechaDeNacimiento() );
        administrador.setCargo( administradorDTO.getCargo() );
        administrador.setTelefono( administradorDTO.getTelefono() );
        administrador.setDireccion( administradorDTO.getDireccion() );

        return administrador;
    }

    private Long administradorUserId(Administrador administrador) {
        if ( administrador == null ) {
            return null;
        }
        User user = administrador.getUser();
        if ( user == null ) {
            return null;
        }
        Long id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String administradorUserName(Administrador administrador) {
        if ( administrador == null ) {
            return null;
        }
        User user = administrador.getUser();
        if ( user == null ) {
            return null;
        }
        String name = user.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private String administradorUserEmail(Administrador administrador) {
        if ( administrador == null ) {
            return null;
        }
        User user = administrador.getUser();
        if ( user == null ) {
            return null;
        }
        String email = user.getEmail();
        if ( email == null ) {
            return null;
        }
        return email;
    }

    protected User administradorDTOToUser(AdministradorDTO administradorDTO) {
        if ( administradorDTO == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.id( administradorDTO.getUserId() );

        return user.build();
    }
}
