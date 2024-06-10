package com.examen.repositories;

import com.examen.entities.Falta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FaltaRepository extends JpaRepository<Falta, Long> {
}
