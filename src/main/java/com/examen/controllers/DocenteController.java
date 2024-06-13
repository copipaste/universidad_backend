package com.examen.controllers;

import com.examen.dtos.DocenteDTO;
import com.examen.entities.Docente;
import com.examen.entities.User;
import com.examen.mappers.DocenteMapper;
import com.examen.services.DocenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/docentes")
public class DocenteController {

    @Autowired
    private DocenteService docenteService;

    @Autowired
    private DocenteMapper docenteMapper;

    @PostMapping
    public DocenteDTO crearDocente(@RequestBody DocenteDTO docenteDTO) {
        Docente docente = docenteMapper.toEntity(docenteDTO);
        Docente docenteGuardado = docenteService.guardarDocente(docente);
        return docenteMapper.toDTO(docenteGuardado);
    }

    @PostMapping("/con-usuario")
    public DocenteDTO crearDocenteConUsuario(@RequestBody Map<String, Object> payload) {
        // Extract User and Docente data from payload
        Map<String, Object> userMap = (Map<String, Object>) payload.get("user");
        Map<String, Object> docenteMap = (Map<String, Object>) payload.get("docente");

        // Manually map User fields
        User user = new User();
        user.setName((String) userMap.get("name"));
        user.setEmail((String) userMap.get("email"));
        user.setPassword((String) userMap.get("password"));

        // Manually map Docente fields
        Docente docente = new Docente();
        docente.setTelefono((String) docenteMap.get("telefono"));
        docente.setProfesion((String) docenteMap.get("profesion"));
        docente.setSexo((String) docenteMap.get("sexo"));
        docente.setDireccion((String) docenteMap.get("direccion"));
        docente.setUser(user); // Set the User entity

        // Save both User and Docente
        Docente docenteGuardado = docenteService.guardarDocenteYUsuario(docente, user);

        return docenteMapper.toDTO(docenteGuardado);
    }

    @GetMapping
    public List<DocenteDTO> obtenerTodosLosDocentes() {
        return docenteService.obtenerTodosLosDocentes().stream()
                .map(docenteMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public DocenteDTO obtenerDocentePorId(@PathVariable Long id) {
        Docente docente = docenteService.obtenerDocentePorId(id);
        return docenteMapper.toDTO(docente);
    }

    @DeleteMapping("/{id}")
    public void eliminarDocente(@PathVariable Long id) {
        docenteService.eliminarDocente(id);
    }

    @PutMapping("/{id}")
    public DocenteDTO actualizarDocente(@PathVariable Long id, @RequestBody DocenteDTO docenteDTO) {
        Docente docente = docenteMapper.toEntity(docenteDTO);
        Docente docenteActualizado = docenteService.actualizarDocente(id, docente);
        return docenteMapper.toDTO(docenteActualizado);
    }
}
