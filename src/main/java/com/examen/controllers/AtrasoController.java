package com.examen.controllers;

import com.examen.dtos.AtrasoDTO;
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
    public AtrasoDTO crearAtraso(@RequestParam Long asistenciaId, @RequestParam String motivo) {
        Atraso atraso = atrasoService.crearAtraso(asistenciaId, motivo);
        return atrasoMapper.toDTO(atraso);
    }
}
