package com.examen.services;

import com.examen.entities.Modulo;
import com.examen.repositories.ModuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ModuloService {

    @Autowired
    private ModuloRepository moduloRepository;

    public Modulo guardarModulo(Modulo modulo) {
        return moduloRepository.save(modulo);
    }

    public List<Modulo> obtenerTodosLosModulos() {
        return moduloRepository.findAll();
    }

    public Modulo obtenerModuloPorId(Long id) {
        return moduloRepository.findById(id).orElse(null);
    }

    public void eliminarModulo(Long id) {
        moduloRepository.deleteById(id);
    }

    @Transactional
    public Modulo actualizarModulo(Long id, Modulo modulo) {
        Modulo existente = moduloRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Modulo not found"));
        existente.setNumero(modulo.getNumero());
        existente.setLatitud(modulo.getLatitud());
        existente.setLongitud(modulo.getLongitud());
        return moduloRepository.save(existente);
    }
}
