package com.examen.services;

import com.examen.entities.Aula;
import com.examen.repositories.AulaRepository;
import com.examen.repositories.ModuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AulaService {

    @Autowired
    private AulaRepository aulaRepository;

    @Autowired
    private ModuloRepository moduloRepository;

    public Aula guardarAula(Aula aula) {
        return aulaRepository.save(aula);
    }

    public List<Aula> obtenerTodasLasAulas() {
        return aulaRepository.findAll();
    }

    public Aula obtenerAulaPorId(Long id) {
        return aulaRepository.findById(id).orElse(null);
    }

    public void eliminarAula(Long id) {
        aulaRepository.deleteById(id);
    }
}
