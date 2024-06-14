package com.examen.controllers;

import com.examen.dtos.LicenciaDTO;
import com.examen.dtos.LicenciaRespuestaDTO;
import com.examen.dtos.MateriaDto;
import com.examen.dtos.horario.HorarioDTO;
import com.examen.entities.Horario;
import com.examen.entities.Licencia;
import com.examen.entities.ProgramacionAcademica;
import com.examen.mappers.HorarioMapper;
import com.examen.mappers.LicenciaMapper;
import com.examen.mappers.MateriaMapper;
import com.examen.repositories.HorarioRepository;
import com.examen.services.FaltaService;
import com.examen.services.LicenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/licencias")
public class LicenciaController {

    @Autowired
    private LicenciaService licenciaService;

    @Autowired
    private LicenciaMapper licenciaMapper;

    @Autowired
    private MateriaMapper materiaMapper;

    @Autowired
    private HorarioMapper horarioMapper;

    @Autowired
    private HorarioRepository horarioRepository;

    @Autowired
    private FaltaService faltaService;

    @PostMapping
    public LicenciaDTO crearLicencia(@RequestBody LicenciaDTO licenciaDTO) {
        Licencia licencia = licenciaMapper.toEntity(licenciaDTO);
        Licencia licenciaGuardada = licenciaService.guardarLicencia(licencia);
        return licenciaMapper.toDTO(licenciaGuardada);
    }

    @PutMapping("/{id}/aprobar")
    public LicenciaDTO aprobarLicencia(@PathVariable Long id) {
        return licenciaMapper.toDTO(licenciaService.aprobarLicencia(id));
    }

    @GetMapping
    public List<LicenciaDTO> obtenerTodasLasLicencias() {
        return licenciaService.obtenerTodasLasLicencias().stream()
                .map(licenciaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/docente/{id}")
    public List<LicenciaRespuestaDTO> obtenerLicenciasPorDocente(@PathVariable Long id) {
        List<Licencia> licencias = licenciaService.obtenerLicenciasPorDocente(id);
        List<LicenciaRespuestaDTO> licenciaRespuestaDTOS = new ArrayList<>();
        for (Licencia licencia : licencias) {
            ProgramacionAcademica pa = licencia.getProgramacionAcademica();
            LocalDate fecha = licencia.getFecha();
            String dia = faltaService.getDayByDate(fecha);
            MateriaDto materiaDto = materiaMapper.toMateriaDto(pa.getMateria());
            Horario horario = horarioRepository
                    .findByProgramacionAcademicaIdAndDia(pa.getId(), dia);
            HorarioDTO horarioDTO = horarioMapper.toDTO(horario);
            LicenciaRespuestaDTO licResp = new LicenciaRespuestaDTO(licencia, materiaDto, horarioDTO);
            licenciaRespuestaDTOS.add(licResp);
        }
        return licenciaRespuestaDTOS;
    }

    @GetMapping("/{id}")
    public LicenciaDTO obtenerLicenciaPorId(@PathVariable Long id) {
        Licencia licencia = licenciaService.obtenerLicenciaPorId(id);
        return licenciaMapper.toDTO(licencia);
    }

    @DeleteMapping("/{id}")
    public void eliminarLicencia(@PathVariable Long id) {
        licenciaService.eliminarLicencia(id);
    }


}
