package com.examen.services;

import com.examen.entities.Falta;
import com.examen.repositories.FaltaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FaltaService {

    @Autowired
    private FaltaRepository faltaRepository;

    public Falta guardarFalta(Falta falta) {
        return faltaRepository.save(falta);
    }

    public List<Falta> obtenerTodasLasFaltas() {
        return faltaRepository.findAll();
    }

    public Falta obtenerFaltaPorId(Long id) {
        return faltaRepository.findById(id).orElse(null);
    }

    public void eliminarFalta(Long id) {
        faltaRepository.deleteById(id);
    }
}
