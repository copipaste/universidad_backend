package com.examen.mappers;

import com.examen.dtos.asistencia.AsistenciaDTO;
import com.examen.entities.Asistencia;
import com.examen.entities.Docente;
import com.examen.entities.Horario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface AsistenciaMapper {

    @Mappings({
            @Mapping(source = "docente.id", target = "docenteId"),
            @Mapping(source = "horario.id", target = "horarioId")
    })
    AsistenciaDTO toDTO(Asistencia asistencia);

    @Mappings({
            @Mapping(source = "docenteId", target = "docente"),
            @Mapping(source = "horarioId", target = "horario")
    })
    Asistencia toEntity(AsistenciaDTO asistenciaDTO);

    default Docente mapDocente(Long id) {
        if (id == null) {
            return null;
        }
        Docente docente = new Docente();
        docente.setId(id);
        return docente;
    }

    default Long mapDocente(Docente docente) {
        if (docente == null) {
            return null;
        }
        return docente.getId();
    }

    default Horario mapHorario(Long id) {
        if (id == null) {
            return null;
        }
        Horario horario = new Horario();
        horario.setId(id);
        return horario;
    }

    default Long mapHorario(Horario horario) {
        if (horario == null) {
            return null;
        }
        return horario.getId();
    }
}
