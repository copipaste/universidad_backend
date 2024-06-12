package com.examen.controllers;

import com.examen.dtos.ApiResponse;
import com.examen.dtos.asistencia.AsistenciaDTO;
import com.examen.dtos.asistencia.MarcarAsistenciaDTO;
import com.examen.entities.Asistencia;
import com.examen.mappers.AsistenciaMapper;
import com.examen.services.AsistenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/asistencias")
public class AsistenciaController {

    @Autowired
    private AsistenciaService asistenciaService;

    @Autowired
    private AsistenciaMapper asistenciaMapper;

    @PostMapping("/marcar")
    public ApiResponse<Object> marcarAsistencia(@RequestBody MarcarAsistenciaDTO dto) {
        System.out.println("ingresando a AsistenciaService");
        return asistenciaService.marcarAsistencia(dto.getDocenteId(), dto.getHora(), dto.getFecha(),
                dto.getLatitud(), dto.getLongitud(), dto.getMateriaId(), dto.getHorarioId());
    }

    @PostMapping("/marcar/virtual")
    public ApiResponse<Object> marcarAsistenciaVirtual(@RequestBody MarcarAsistenciaDTO dto) {
        return asistenciaService.marcarAsistenciaVirtual(dto.getDocenteId(), dto.getHora(), dto.getFecha(),
                dto.getMateriaId(), dto.getHorarioId());
    }

    @GetMapping
    public List<AsistenciaDTO> obtenerTodasLasAsistencias() {
        return asistenciaService.obtenerTodasLasAsistencias().stream()
                .map(asistenciaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public AsistenciaDTO obtenerAsistenciaPorId(@PathVariable Long id) {
        Asistencia asistencia = asistenciaService.obtenerAsistenciaPorId(id);
        return asistenciaMapper.toDTO(asistencia);
    }

    @DeleteMapping("/{id}")
    public void eliminarAsistencia(@PathVariable Long id) {
        asistenciaService.eliminarAsistencia(id);
    }
}
