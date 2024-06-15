package com.examen.repositories;

import com.examen.entities.Licencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

@Repository
public interface LicenciaRepository extends JpaRepository<Licencia, Long> {
    @Query("SELECT l FROM Licencia l WHERE l.programacionAcademica.docente.id = :docenteId")
    List<Licencia> findByDocenteId(@Param("docenteId") Long docenteId);

    @Query("SELECT l FROM Licencia l WHERE l.programacionAcademica.docente.id = :docenteId ORDER BY l.fecha DESC")
    List<Licencia> findByDocenteIdOrderByFechaDesc(@Param("docenteId") Long docenteId);
}