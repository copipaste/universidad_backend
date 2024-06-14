package com.examen.controllers;

import com.examen.dtos.LicenciaDTO;
import com.examen.entities.Licencia;
import com.examen.mappers.LicenciaMapper;
import com.examen.services.LicenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/licencias")
public class LicenciaController {

    @Autowired
    private LicenciaService licenciaService;

    @Autowired
    private LicenciaMapper licenciaMapper;

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
