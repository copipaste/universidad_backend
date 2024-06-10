package com.examen.services;

import com.examen.entities.Horario;
import com.examen.repositories.HorarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HorarioService {

    @Autowired
    private HorarioRepository horarioRepository;

    public Horario guardarHorario(Horario horario) {
        return horarioRepository.save(horario);
    }

    public List<Horario> obtenerTodosLosHorarios() {
        return horarioRepository.findAll();
    }

    public Horario obtenerHorarioPorId(Long id) {
        return horarioRepository.findById(id).orElse(null);
    }

    public void eliminarHorario(Long id) {
        horarioRepository.deleteById(id);
    }
}
