package com.examen.services;

import com.examen.dtos.horario.HorarioDTO;
import com.examen.entities.Facultad;
import com.examen.entities.Horario;
import com.examen.entities.ProgramacionAcademica;
import com.examen.mappers.HorarioMapper;
import com.examen.repositories.HorarioRepository;
import com.examen.repositories.ProgramacionAcademicaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HorarioService {

    @Autowired
    private HorarioRepository horarioRepository;

    @Autowired
    private FaltaService faltaService;
    @Autowired
    private HorarioMapper horarioMapper;
    @Autowired
    private ProgramacionAcademicaRepository programacionAcademicaRepository;

    @Transactional
    public Horario guardarHorario(Horario horario) {
        Horario isHexists = horarioRepository.findHorarioRepetido(
                horario.getProgramacionAcademica().getDocente().getId(),
                horario.getDia(),
                horario.getHoraInicio(),
                horario.getHoraFin());

        if (isHexists != null) {
            return null;
        }

        Horario h = horarioRepository.save(horario);
        faltaService.generarClases(horario.getId());
        return h;
    }

//    @Transactional
//    public Horario guardarHorario(Horario horario) {
//        Horario h = horarioRepository.save(horario);
//        faltaService.generarClases(horario.getId());
//        return h;
//    }

    public List<Horario> obtenerTodosLosHorarios() {
        return horarioRepository.findAll();
    }

    public List<Horario> obtenerHorariosPorDocenteId(Long docenteId) {
        return horarioRepository.findHorariosByDocenteId(docenteId);
    }

    public Horario obtenerHorarioPorId(Long id) {
        return horarioRepository.findById(id).orElse(null);
    }

    public void eliminarHorario(Long id) {
        horarioRepository.deleteById(id);
    }
}
