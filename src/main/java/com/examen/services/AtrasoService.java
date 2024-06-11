package com.examen.services;

import com.examen.entities.Atraso;
import com.examen.entities.Asistencia;
import com.examen.repositories.AtrasoRepository;
import com.examen.repositories.AsistenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AtrasoService {

    @Autowired
    private AtrasoRepository atrasoRepository;

    @Autowired
    private AsistenciaRepository asistenciaRepository;

    public Atraso crearAtraso(Long asistenciaId, String motivo) {
        Asistencia asistencia = asistenciaRepository.findById(asistenciaId)
                .orElseThrow(() -> new RuntimeException("Asistencia no encontrada"));

        Atraso atraso = Atraso.builder()
                .motivo(motivo)
                .asistencia(asistencia)
                .build();

        return atrasoRepository.save(atraso);
    }
}
