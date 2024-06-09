package com.examen.controllers;

import com.examen.dtos.carrera.CarreraDto;
import com.examen.dtos.carrera.CreateCarreraDto;
import com.examen.repositories.CarreraRepository;
import com.examen.services.CarreraService;
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
@RequestMapping("/carreras")
public class CarreraController {

    private final CarreraService carreraService;
    private final CarreraRepository carreraRepository;

    @GetMapping
    public ResponseEntity<Page<CarreraDto>> listarCarreras(Pageable paginacion) {
        return ResponseEntity.ok(carreraService.getCarrerasPage(paginacion));
    }

    @PostMapping
    public ResponseEntity<CarreraDto> guardarCarrera(@RequestBody @Valid CreateCarreraDto createCarreraDto,
                                                       UriComponentsBuilder uriComponentsBuilder) {
        CarreraDto carreraDto = carreraService.createCarrera(createCarreraDto);
        URI url = uriComponentsBuilder.path("/carreras/{id}").buildAndExpand(carreraDto.getId()).toUri();
        return ResponseEntity.created(url).body(carreraDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarreraDto> retornarDatosCarrera(@PathVariable Long id) {
        return ResponseEntity.ok(carreraService.getCarrera(id));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<CarreraDto> actualizarCarrera(@PathVariable Long id,
                                                        @RequestBody @Valid CreateCarreraDto createCarreraDto)
    {
        return ResponseEntity.ok(carreraService.updateCarrera(id, createCarreraDto));
    }

    //Eliminacion Logica
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity desactivarCarrera(@PathVariable Long id) {
        carreraService.deactivateCarrera(id);
        return ResponseEntity.noContent().build();
    }

    //Eliminacion Fisica
    @DeleteMapping("/{id}/hard")
    @Transactional
    public ResponseEntity eliminarCarrera(@PathVariable Long id) {
        carreraService.deleteCarrera(id);
        return ResponseEntity.noContent().build();
    }
}
