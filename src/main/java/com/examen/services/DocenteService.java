package com.examen.services;

import com.examen.entities.Docente;
import com.examen.entities.User;
import com.examen.repositories.DocenteRepository;
import com.examen.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DocenteService {

    @Autowired
    private DocenteRepository docenteRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public Docente guardarDocenteYUsuario(Docente docente, User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
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
    @Transactional
    public Docente actualizarDocente(Long id, Docente docente) {
        Docente existente = docenteRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Docente not found"));
        existente.setTelefono(docente.getTelefono());
        existente.setProfesion(docente.getProfesion());
        existente.setSexo(docente.getSexo());
        existente.setDireccion(docente.getDireccion());
        if (docente.getUser() != null) {
            User user = existente.getUser();
            user.setName(docente.getUser().getName());
            userRepository.save(user);
        }
        return docenteRepository.save(existente);
    }
}
