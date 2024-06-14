package com.examen.controllers;

import com.examen.dtos.FaltaDTO;
import com.examen.entities.Falta;
import com.examen.mappers.FaltaMapper;
import com.examen.services.FaltaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/faltas")
public class FaltaController {

    @Autowired
    private FaltaService faltaService;

    @Autowired
    private FaltaMapper faltaMapper;

    @PostMapping
    public FaltaDTO crearFalta(@RequestBody FaltaDTO faltaDTO) {
        Falta falta = faltaMapper.toEntity(faltaDTO);
        Falta faltaGuardada = faltaService.guardarFalta(falta);
        return faltaMapper.toDTO(faltaGuardada);
    }

    @GetMapping
    public List<FaltaDTO> obtenerTodasLasFaltas() {
        return faltaService.obtenerTodasLasFaltas().stream()
                .map(faltaMapper::toDTO)
                .collect(Collectors.toList());
    }

    //todo: Agregar query parameters por docente, materia
    @GetMapping("/reporte")
    public List<FaltaDTO> getFaltasByParams(@RequestParam(required = false) Long pa,
                                            @RequestParam(required = false) LocalDate fecha_inicio,
                                            @RequestParam(required = false) LocalDate fecha_fin) {

        return faltaService.getFaltasReporte(pa,fecha_inicio, fecha_fin)
                .stream().map(faltaMapper::toDTO).toList();
    }

    @GetMapping("/{id}")
    public FaltaDTO obtenerFaltaPorId(@PathVariable Long id) {
        Falta falta = faltaService.obtenerFaltaPorId(id);
        return faltaMapper.toDTO(falta);
    }

    @DeleteMapping("/{id}")
    public void eliminarFalta(@PathVariable Long id) {
        faltaService.eliminarFalta(id);
    }
}
