package com.examen.repositories;

import com.examen.entities.Materia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MateriaRepository extends JpaRepository<Materia,Long> {
}
