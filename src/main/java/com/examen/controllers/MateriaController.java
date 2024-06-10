package com.examen.controllers;

import com.examen.dtos.MateriaDto;
import com.examen.entities.Materia;
import com.examen.repositories.MateriaRepository;
import com.examen.services.MateriaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org. springframework. web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/materias")
public class MateriaController  {

    private final MateriaService materiaService;

    @GetMapping
    public ResponseEntity<Page<MateriaDto>> getMaterias(Pageable paginacion) {
        return ResponseEntity.ok(materiaService.getMateriasPage(paginacion));
    }

    @PostMapping
    public ResponseEntity<MateriaDto> postMateria(@RequestBody @Valid Materia Materia) {
        MateriaDto materiaCreada = materiaService.createMateria(Materia);
        URI url = UriComponentsBuilder.fromPath("/materias/{id}").buildAndExpand(materiaCreada.getId()).toUri();
        return ResponseEntity.created(url).body(materiaCreada);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MateriaDto> getMateria(@PathVariable Long id) {
        return ResponseEntity.ok(materiaService.getMateria(id));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<MateriaDto> putMateria(@PathVariable Long id,
                                                 @RequestBody @Valid Materia Materia) {
        return ResponseEntity.ok(materiaService.updateMateria(id, Materia));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<MateriaDto> deleteMateria(@PathVariable Long id) {
        materiaService.deleteMateria(id);
        return ResponseEntity.noContent().build();
    }
}
