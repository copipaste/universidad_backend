package com.examen.controllers;

import com.examen.dtos.HorarioDetalladoDTO;
import com.examen.dtos.horario.HorarioDTO;
import com.examen.entities.Horario;
import com.examen.entities.ProgramacionAcademica;
import com.examen.mappers.HorarioMapper;
import com.examen.repositories.ProgramacionAcademicaRepository;
import com.examen.services.HorarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/horarios")
public class HorarioController {

    @Autowired
    private HorarioService horarioService;

    @Autowired
    private HorarioMapper horarioMapper;
    @Autowired
    private ProgramacionAcademicaRepository programacionAcademicaRepository;

    @PostMapping
    public ResponseEntity<HorarioDTO> crearHorario(@RequestBody HorarioDTO horarioDTO,
                                                   UriComponentsBuilder uriComponentsBuilder) {
        Horario horario = horarioMapper.toEntity(horarioDTO);
        ProgramacionAcademica PA = programacionAcademicaRepository.getReferenceById(
                horarioDTO.getProgAcId());
        horario.setProgramacionAcademica(PA);
        Horario horarioGuardado = horarioService.guardarHorario(horario);

        if (horarioGuardado == null) {
            return ResponseEntity.badRequest().build();
        }

        URI url = uriComponentsBuilder.path("/horarios/{id}").buildAndExpand(horarioGuardado.getId()).toUri();
        return ResponseEntity.created(url).body(horarioMapper.toDTO(horarioGuardado));
    }

//    @PostMapping
//    public HorarioDTO crearHorario(@RequestBody HorarioDTO horarioDTO) {
//        Horario horario = horarioMapper.toEntity(horarioDTO);
//        ProgramacionAcademica PA = programacionAcademicaRepository.getReferenceById(
//                horarioDTO.getProgAcId());
//        horario.setProgramacionAcademica(PA);
//        Horario horarioGuardado = horarioService.guardarHorario(horario);
//        return horarioMapper.toDTO(horarioGuardado);
//    }

    @GetMapping
    public List<HorarioDTO> obtenerTodosLosHorarios() {
        return horarioService.obtenerTodosLosHorarios().stream()
                .map(horarioMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/docente/{docenteId}")
    public List<HorarioDetalladoDTO> obtenerHorariosPorDocenteId(@PathVariable Long docenteId) {
        return horarioService.obtenerHorariosPorDocenteId(docenteId).stream()
                .map(horarioMapper::toDetailedDTO)
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
