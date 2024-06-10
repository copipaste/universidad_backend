package com.examen.controllers;

import com.examen.dtos.ModuloDTO;
import com.examen.entities.Modulo;
import com.examen.mappers.ModuloMapper;
import com.examen.services.ModuloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/modulos")
public class ModuloController {

    @Autowired
    private ModuloService moduloService;

    @Autowired
    private ModuloMapper moduloMapper;

    @PostMapping
    public ModuloDTO crearModulo(@RequestBody ModuloDTO moduloDTO) {
        Modulo modulo = moduloMapper.toEntity(moduloDTO);
        Modulo moduloGuardado = moduloService.guardarModulo(modulo);
        return moduloMapper.toDTO(moduloGuardado);
    }

    @GetMapping
    public List<ModuloDTO> obtenerTodosLosModulos() {
        return moduloService.obtenerTodosLosModulos().stream()
                .map(moduloMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ModuloDTO obtenerModuloPorId(@PathVariable Long id) {
        Modulo modulo = moduloService.obtenerModuloPorId(id);
        return moduloMapper.toDTO(modulo);
    }

    @DeleteMapping("/{id}")
    public void eliminarModulo(@PathVariable Long id) {
        moduloService.eliminarModulo(id);
    }
}
