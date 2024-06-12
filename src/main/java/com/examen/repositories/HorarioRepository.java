package com.examen.repositories;

import com.examen.entities.Horario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HorarioRepository extends JpaRepository<Horario, Long> {
    Optional<Horario> findById(Long horarioId);
    @Query("SELECT h FROM Horario h " +
            "JOIN FETCH h.aula a " +
            "JOIN FETCH h.programacionAcademica pa " +
            "JOIN FETCH pa.materia m " +
            "WHERE pa.docente.id = :docenteId")
    List<Horario> findHorariosByDocenteId(Long docenteId);
}
