package com.examen.services;

import com.examen.entities.ProgramacionAcademica;
import com.examen.repositories.ProgramacionAcademicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgramacionAcademicaService {

    @Autowired
    private ProgramacionAcademicaRepository programacionAcademicaRepository;

    public ProgramacionAcademica guardarProgramacionAcademica(ProgramacionAcademica programacionAcademica) {
        return programacionAcademicaRepository.save(programacionAcademica);
    }

    public List<ProgramacionAcademica> obtenerTodasLasProgramacionesAcademicas() {
        return programacionAcademicaRepository.findAll();
    }

    public List<ProgramacionAcademica> obtenerPAsPorDocenteId(Long docenteId) {
        return programacionAcademicaRepository.findByDocenteId(docenteId);
    }

    public ProgramacionAcademica obtenerProgramacionAcademicaPorId(Long id) {
        return programacionAcademicaRepository.findById(id).orElse(null);
    }

    public void eliminarProgramacionAcademica(Long id) {
        programacionAcademicaRepository.deleteById(id);
    }
}
