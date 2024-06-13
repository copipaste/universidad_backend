package com.examen.services;

import com.examen.entities.Docente;
import com.examen.entities.User;
import com.examen.repositories.DocenteRepository;
import com.examen.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class DocenteService {

    @Autowired
    private DocenteRepository docenteRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public Docente guardarDocenteYUsuario(Docente docente, User user) {
        User savedUser = userRepository.save(user);
        docente.setUser(savedUser);
        return docenteRepository.save(docente);
    }

    @Transactional
    public Docente guardarDocente(Docente docente) {
        return docenteRepository.save(docente);
    }

    public List<Docente> obtenerTodosLosDocentes() {
        return docenteRepository.findAll();
    }

    public Docente obtenerDocentePorId(Long id) {
        return docenteRepository.findById(id).orElse(null);
    }

    @Transactional
    public void eliminarDocente(Long id) {
        Optional<Docente> docente = docenteRepository.findById(id);
        if (docente.isPresent()) {
            System.out.println("Eliminando docente con id: " + id);
            docenteRepository.deleteById(id);
            System.out.println("Docente eliminado con éxito");
        } else {
            System.out.println("Docente con id " + id + " no encontrado");
        }
    }
}
