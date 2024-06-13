package com.examen.controllers;

import com.examen.dtos.facultad.CreateFacultadDto;
import com.examen.dtos.facultad.FacultadDto;
import com.examen.services.FacultadService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/facultades")
public class FacultadController {

    private final FacultadService facultadService;

    @GetMapping
    public ResponseEntity<Page<FacultadDto>> listarFacultades(Pageable paginacion) {
        return ResponseEntity.ok(facultadService.getFacultadesPage(paginacion));
    }

    @PostMapping
    public ResponseEntity<FacultadDto> guardarFacultad(@RequestBody @Valid CreateFacultadDto createFacultadDto,
                                                       UriComponentsBuilder uriComponentsBuilder) {
        FacultadDto facultadDto = facultadService.createFacultad(createFacultadDto);
        URI url = uriComponentsBuilder.path("/facultades/{id}").buildAndExpand(facultadDto.getId()).toUri();
        return ResponseEntity.created(url).body(facultadDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FacultadDto> retornarDatosFacultad(@PathVariable Long id) {
        return ResponseEntity.ok(facultadService.getFacultad(id));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<FacultadDto> actualizarFacultad(@PathVariable Long id,
                                             @RequestBody @Valid CreateFacultadDto createFacultadDto)
    {
        return ResponseEntity.ok(facultadService.updateFacultad(id, createFacultadDto));
    }

    @PutMapping("/{id}/activar")
    @Transactional
    public ResponseEntity<FacultadDto> activarFacultad(@PathVariable Long id)
    {
        return ResponseEntity.ok(facultadService.activateFacultad(id));
    }

    //Eliminacion Logica
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity desactivarFacultad(@PathVariable Long id) {
        facultadService.deactivateFacultad(id);
        return ResponseEntity.noContent().build();
    }

    //Eliminacion Fisica
    @DeleteMapping("/{id}/hard")
    @Transactional
    public ResponseEntity eliminarFacultad(@PathVariable Long id) {
        facultadService.deleteFacultad(id);
        return ResponseEntity.noContent().build();
    }
}
