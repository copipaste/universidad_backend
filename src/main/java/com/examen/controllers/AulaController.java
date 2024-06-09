package com.examen.controllers;

import com.examen.dtos.AulaDTO;
import com.examen.entities.Aula;
import com.examen.mappers.AulaMapper;
import com.examen.services.AulaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/aulas")
public class AulaController {

    @Autowired
    private AulaService aulaService;

    @Autowired
    private AulaMapper aulaMapper;

    @PostMapping
    public AulaDTO crearAula(@RequestBody AulaDTO aulaDTO) {
        Aula aula = aulaMapper.toEntity(aulaDTO);
        Aula aulaGuardada = aulaService.guardarAula(aula);
        return aulaMapper.toDTO(aulaGuardada);
    }

    @GetMapping
    public List<AulaDTO> obtenerTodasLasAulas() {
        return aulaService.obtenerTodasLasAulas().stream()
                .map(aulaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public AulaDTO obtenerAulaPorId(@PathVariable Long id) {
        Aula aula = aulaService.obtenerAulaPorId(id);
        return aulaMapper.toDTO(aula);
    }

    @DeleteMapping("/{id}")
    public void eliminarAula(@PathVariable Long id) {
        aulaService.eliminarAula(id);
    }
}
