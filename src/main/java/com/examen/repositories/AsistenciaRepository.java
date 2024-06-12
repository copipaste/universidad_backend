package com.examen.repositories;

import com.examen.entities.Asistencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface AsistenciaRepository extends JpaRepository<Asistencia, Long> {
    @Query("SELECT a FROM Asistencia a WHERE a.docente.id = :docenteId AND a.horario.id = :horarioId AND a.fecha = :fecha")
    Optional<Asistencia> findByDocenteIdAndHorarioIdAndFecha(@Param("docenteId") Long docenteId, @Param("horarioId") Long horarioId, @Param("fecha") LocalDate fecha);
}
