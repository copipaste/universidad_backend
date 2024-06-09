package com.examen.controllers;

import com.examen.dtos.AdministradorDTO;
import com.examen.entities.Administrador;
import com.examen.entities.User;
import com.examen.mappers.AdministradorMapper;
import com.examen.services.AdministradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/administradores")
public class AdministradorController {

    @Autowired
    private AdministradorService administradorService;

    @Autowired
    private AdministradorMapper administradorMapper;

    @PostMapping
    public AdministradorDTO crearAdministrador(@RequestBody AdministradorDTO administradorDTO) {
        Administrador administrador = administradorMapper.toEntity(administradorDTO);
        Administrador administradorGuardado = administradorService.guardarAdministrador(administrador);
        return administradorMapper.toDTO(administradorGuardado);
    }

    @PostMapping("/con-usuario")
    public AdministradorDTO crearAdministradorConUsuario(@RequestBody Map<String, Object> payload) {
        // Extract User and Administrador data from payload
        Map<String, Object> userMap = (Map<String, Object>) payload.get("user");
        Map<String, Object> administradorMap = (Map<String, Object>) payload.get("administrador");

        // Manually map User fields
        User user = new User();
        user.setName((String) userMap.get("name"));
        user.setEmail((String) userMap.get("email"));
        user.setPassword((String) userMap.get("password"));

        // Manually map Administrador fields
        Administrador administrador = new Administrador();
        administrador.setFechaDeNacimiento(LocalDate.parse((String) administradorMap.get("fechaDeNacimiento")));
        administrador.setCargo((String) administradorMap.get("cargo"));
        administrador.setTelefono((String) administradorMap.get("telefono"));
        administrador.setDireccion((String) administradorMap.get("direccion"));
        administrador.setUser(user); // Set the User entity

        // Save both User and Administrador
        Administrador administradorGuardado = administradorService.guardarAdministradorYUsuario(administrador, user);

        return administradorMapper.toDTO(administradorGuardado);
    }

    @GetMapping
    public List<AdministradorDTO> obtenerTodosLosAdministradores() {
        return administradorService.obtenerTodosLosAdministradores().stream()
                .map(administradorMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public AdministradorDTO obtenerAdministradorPorId(@PathVariable Long id) {
        Administrador administrador = administradorService.obtenerAdministradorPorId(id);
        return administradorMapper.toDTO(administrador);
    }

    @DeleteMapping("/{id}")
    public void eliminarAdministrador(@PathVariable Long id) {
        administradorService.eliminarAdministrador(id);
    }
}
