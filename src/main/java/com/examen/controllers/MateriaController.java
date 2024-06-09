package com.examen.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MateriaController  {
    @GetMapping("/materias")
    public String getMaterias() {
        return "Materias";
    }

    @PostMapping("/materias")
    public String postMaterias() {
        return "Materias";
    }

    @GetMapping("/materias/{id}")
    public String getMateria() {
        return "Materia";
    }

    @PutMapping("/materias/{id}")
    public String putMateria(@PathVariable Long id){
        return "modificar Materia" + id;
    }

    @DeleteMapping("/materias/{id}")
    public String deleteMateria() {
        return "Eliminar";
    }
}
