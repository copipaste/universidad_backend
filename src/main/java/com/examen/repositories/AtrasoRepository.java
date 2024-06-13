package com.examen.repositories;

import com.examen.entities.Atraso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtrasoRepository extends JpaRepository<Atraso, Long> {
    boolean existsByAsistenciaId(Long asistenciaId);
}