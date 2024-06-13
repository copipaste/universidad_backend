package com.examen.repositories;

import com.examen.entities.Falta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

@Repository
public interface FaltaRepository extends JpaRepository<Falta, Long> {
    @Query("SELECT f FROM Falta f WHERE f.programacionAcademica.docente.id = :docenteId")
    List<Falta> findByDocenteId(@Param("docenteId") Long docenteId);
}