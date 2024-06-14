package com.examen.services;

import com.examen.dtos.LicenciaDTO;
import com.examen.entities.Licencia;
import com.examen.entities.ProgramacionAcademica;
import com.examen.repositories.LicenciaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LicenciaService {

    @Autowired
    private LicenciaRepository licenciaRepository;

    @Autowired
    private FaltaService faltaService;

    public Licencia guardarLicencia(Licencia licencia) {
        return licenciaRepository.save(licencia);
    }

    public List<Licencia> obtenerTodasLasLicencias() {
        return licenciaRepository.findAll();
    }

    public List<Licencia> obtenerLicenciasPorDocente(Long docenteId) {
        return licenciaRepository.findByDocenteId(docenteId);
    }

    public Licencia obtenerLicenciaPorId(Long id) {
        return licenciaRepository.findById(id).orElse(null);
    }

    public void eliminarLicencia(Long id) {
        licenciaRepository.deleteById(id);
    }

    @Transactional
    public Licencia aprobarLicencia(Long id) {
        Licencia licencia = licenciaRepository.getReferenceById(id);
        licencia.aprobarLicencia();
        faltaService.eliminarFalta(licencia.getFecha(), licencia.getProgramacionAcademica().getId());
        return licencia;
    }
}
