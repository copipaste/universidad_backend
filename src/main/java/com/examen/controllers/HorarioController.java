package com.examen.controllers;

import com.examen.dtos.HorarioDTO;
import com.examen.entities.Horario;
import com.examen.mappers.HorarioMapper;
import com.examen.services.HorarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/horarios")
public class HorarioController {

    @Autowired
    private HorarioService horarioService;

    @Autowired
    private HorarioMapper horarioMapper;

    @PostMapping
    public HorarioDTO crearHorario(@RequestBody HorarioDTO horarioDTO) {
        Horario horario = horarioMapper.toEntity(horarioDTO);
        Horario horarioGuardado = horarioService.guardarHorario(horario);
        return horarioMapper.toDTO(horarioGuardado);
    }

    @GetMapping
    public List<HorarioDTO> obtenerTodosLosHorarios() {
        return horarioService.obtenerTodosLosHorarios().stream()
                .map(horarioMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public HorarioDTO obtenerHorarioPorId(@PathVariable Long id) {
        Horario horario = horarioService.obtenerHorarioPorId(id);
        return horarioMapper.toDTO(horario);
    }

    @DeleteMapping("/{id}")
    public void eliminarHorario(@PathVariable Long id) {
        horarioService.eliminarHorario(id);
    }
}
