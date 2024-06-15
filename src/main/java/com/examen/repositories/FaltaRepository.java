package com.examen.repositories;

import com.examen.entities.Docente;
import com.examen.entities.Falta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FaltaRepository extends JpaRepository<Falta, Long> {
    @Query("SELECT f FROM Falta f WHERE f.programacionAcademica.docente.id = :docenteId")
    List<Falta> findByDocenteId(@Param("docenteId") Long docenteId);

    @Query("SELECT f FROM Falta f WHERE f.programacionAcademica.docente.id = :docenteId ORDER BY f.fecha DESC")
    List<Falta> findByDocenteIdOrderByFechaDesc(@Param("docenteId") Long docenteId);

//    @Query("SELECT f FROM Falta f WHERE f.programacionAcademica.id = :progAcId")
//    List<Falta> findByPA(@Param("progAcId") Long progAcId);
//
//    @Query("SELECT f FROM Falta f WHERE f.fecha >= :fechaInicio AND f.fecha <= :fechaFin")
//    List<Falta> findByRangoFecha(@Param("fechaInicio") LocalDate fechaInicio,
//                                @Param("fechaFin") LocalDate fechaFin);
//
//    @Query("SELECT f FROM Falta f WHERE " +
//            "(:progAcId IS NULL OR f.programacionAcademica.id = :progAcId) AND " +
//            "(:fechaInicio IS NULL OR f.fecha >= :fechaInicio) AND " +
//            "(:fechaFin IS NULL OR f.fecha <= :fechaFin)")
//    List<Falta> findByFilters(@Param("progAcId") Long progAcId,
//                              @Param("fechaInicio") LocalDate fechaInicio,
//                              @Param("fechaFin") LocalDate fechaFin);

    List<Falta> findByProgramacionAcademicaDocenteAndFechaLessThanEqualOrderByFechaDesc(
            Docente docente, LocalDate fecha
    );

    List<Falta> findByProgramacionAcademicaDocenteAndFechaBetweenOrderByFechaDesc(
            Docente docente, LocalDate fechaInicial, LocalDate fechaFinal
    );

    List<Falta> findByFechaLessThanEqualOrderByFechaDesc(LocalDate fecha);

    List<Falta> findByProgramacionAcademicaIdAndFechaLessThanEqualOrderByFechaDesc(Long programacionAcademica_id, LocalDate fecha);

    List<Falta> findByFechaBetweenOrderByFechaDesc(LocalDate fechaInicio, LocalDate fechaFin);

    List<Falta> findByProgramacionAcademicaIdAndFechaBetweenOrderByFechaDesc(Long progAcId,
                                                             LocalDate fechaInicio,
                                                             LocalDate fechaFin);

    void deleteByFechaAndProgramacionAcademicaId(LocalDate fecha, Long progAcId);

}