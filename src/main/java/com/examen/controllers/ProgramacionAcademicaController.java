package com.examen.controllers;

import com.examen.dtos.ProgramacionAcademicaDTO;
import com.examen.entities.ProgramacionAcademica;
import com.examen.mappers.ProgramacionAcademicaMapper;
import com.examen.services.ProgramacionAcademicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/programaciones-academicas")
public class ProgramacionAcademicaController {

    @Autowired
    private ProgramacionAcademicaService programacionAcademicaService;

    @Autowired
    private ProgramacionAcademicaMapper programacionAcademicaMapper;

    @PostMapping
    public ProgramacionAcademicaDTO crearProgramacionAcademica(@RequestBody ProgramacionAcademicaDTO programacionAcademicaDTO) {
        System.out.println(programacionAcademicaDTO);
        ProgramacionAcademica programacionAcademica = programacionAcademicaMapper.toEntity(programacionAcademicaDTO);
        ProgramacionAcademica programacionAcademicaGuardada = programacionAcademicaService.guardarProgramacionAcademica(programacionAcademica);
        return programacionAcademicaMapper.toDTO(programacionAcademicaGuardada);
    }

    @GetMapping
    public List<ProgramacionAcademicaDTO> obtenerTodasLasProgramacionesAcademicas() {
        return programacionAcademicaService.obtenerTodasLasProgramacionesAcademicas().stream()
                .map(programacionAcademicaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/docente/{id}")
    public List<ProgramacionAcademicaDTO> obtenerPAsPorDocente(@PathVariable Long id) {

        return programacionAcademicaService.obtenerPAsPorDocenteId(id).stream()
                .map(programacionAcademicaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProgramacionAcademicaDTO obtenerProgramacionAcademicaPorId(@PathVariable Long id) {
        ProgramacionAcademica programacionAcademica = programacionAcademicaService.obtenerProgramacionAcademicaPorId(id);
        return programacionAcademicaMapper.toDTO(programacionAcademica);
    }

    @DeleteMapping("/{id}")
    public void eliminarProgramacionAcademica(@PathVariable Long id) {
        programacionAcademicaService.eliminarProgramacionAcademica(id);
    }
}
