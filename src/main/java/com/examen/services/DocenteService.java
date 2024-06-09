package com.examen.services;

import com.examen.entities.Docente;
import com.examen.entities.User;
import com.examen.repositories.DocenteRepository;
import com.examen.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    public void eliminarDocente(Long id) {
        docenteRepository.deleteById(id);
    }
}
