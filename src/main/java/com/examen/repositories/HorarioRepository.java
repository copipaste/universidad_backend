package com.examen.repositories;

import com.examen.entities.Horario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
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

    Horario findByProgramacionAcademicaIdAndDia(Long programacionAcademicaId, String dia);


    @Query("SELECT h FROM Horario h " +
            "WHERE h.programacionAcademica.docente.id = :docenteId " +
            "AND h.dia = :dia AND ((:horaInicio BETWEEN h.horaInicio AND h.horaFin) " +
            "OR (:horaFin BETWEEN h.horaInicio AND h.horaFin)) ")
    Horario findHorarioRepetido(
            Long docenteId, String dia, LocalTime horaInicio, LocalTime horaFin);
}
