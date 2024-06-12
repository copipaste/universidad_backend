package com.examen.dtos.asistencia;

import com.examen.entities.Asistencia;
import com.examen.entities.Docente;
import com.examen.entities.Horario;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

public record AsistenciaRespuestaDTO(
        Long id,
        LocalTime hora,
        LocalDate fecha,
        boolean virtual,
        Long docenteID
) {
    public AsistenciaRespuestaDTO(Asistencia asistencia){
        this(asistencia.getId(), asistencia.getHora(), asistencia.getFecha(), asistencia.isVirtual(), asistencia.getDocente().getId());
    };
}
