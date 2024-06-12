package com.examen.controllers;

import com.examen.dtos.atraso.AtrasoDTO;
import com.examen.dtos.atraso.CrearAtrasoDTO;
import com.examen.entities.Atraso;
import com.examen.mappers.AtrasoMapper;
import com.examen.services.AtrasoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/atrasos")
public class AtrasoController {

    @Autowired
    private AtrasoService atrasoService;

    @Autowired
    private AtrasoMapper atrasoMapper;

    @PostMapping
    public AtrasoDTO crearAtraso(@RequestBody CrearAtrasoDTO dto) {
        Atraso atraso = atrasoService.crearAtraso(dto.getAsistenciaId(), dto.getMotivo());
        return atrasoMapper.toDTO(atraso);
    }
}
