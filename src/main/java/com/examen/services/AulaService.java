package com.examen.services;

import com.examen.dtos.AulaDataDTO;
import com.examen.entities.Aula;
import com.examen.entities.Modulo;
import com.examen.repositories.AulaRepository;
import com.examen.repositories.ModuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public Aula actualizarAula(Long id, AulaDataDTO aulaDataDTO) {
        Aula aula = aulaRepository.getReferenceById(id);
        Modulo modulo = moduloRepository.getReferenceById(aulaDataDTO.moduloId());
        aula.actualizarDatos(aulaDataDTO, modulo);
        return aula;
    }

    public void eliminarAula(Long id) {
        aulaRepository.deleteById(id);
    }
}
