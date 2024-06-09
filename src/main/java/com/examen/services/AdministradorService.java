package com.examen.services;

import com.examen.entities.Administrador;
import com.examen.entities.User;
import com.examen.repositories.AdministradorRepository;
import com.examen.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdministradorService {

    @Autowired
    private AdministradorRepository administradorRepository;

    @Autowired
    private UserRepository userRepository;

    public Administrador guardarAdministrador(Administrador administrador) {
        // Asegurarse de que el usuario existe
        User user = userRepository.findById(administrador.getUser().getId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        administrador.setUser(user);
        return administradorRepository.save(administrador);
    }

    @Transactional
    public Administrador guardarAdministradorYUsuario(Administrador administrador, User user) {
        User savedUser = userRepository.save(user);
        administrador.setUser(savedUser);
        return administradorRepository.save(administrador);
    }

    public List<Administrador> obtenerTodosLosAdministradores() {
        return administradorRepository.findAll();
    }

    public Administrador obtenerAdministradorPorId(Long id) {
        return administradorRepository.findById(id).orElse(null);
    }

    public void eliminarAdministrador(Long id) {
        administradorRepository.deleteById(id);
    }
}
