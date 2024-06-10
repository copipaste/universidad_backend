package com.examen.repositories;

import com.examen.entities.ProgramacionAcademica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgramacionAcademicaRepository extends JpaRepository<ProgramacionAcademica, Long> {
}
