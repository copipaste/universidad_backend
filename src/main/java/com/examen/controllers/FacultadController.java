package com.examen.controllers;

import com.examen.dtos.facultad.FacultadDto;
import com.examen.services.FacultadService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/facultades")
public class FacultadController {

    private final FacultadService facultadService;

    @GetMapping
    public ResponseEntity<Page<FacultadDto>> listarFacultades(Pageable paginacion) {
        return ResponseEntity.ok(facultadService.getFacultadesPage(paginacion));
    }
}
