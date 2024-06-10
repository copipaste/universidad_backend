package com.examen.services;

import com.examen.entities.Licencia;
import com.examen.repositories.LicenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LicenciaService {

    @Autowired
    private LicenciaRepository licenciaRepository;

    public Licencia guardarLicencia(Licencia licencia) {
        return licenciaRepository.save(licencia);
    }

    public List<Licencia> obtenerTodasLasLicencias() {
        return licenciaRepository.findAll();
    }

    public Licencia obtenerLicenciaPorId(Long id) {
        return licenciaRepository.findById(id).orElse(null);
    }

    public void eliminarLicencia(Long id) {
        licenciaRepository.deleteById(id);
    }
}
