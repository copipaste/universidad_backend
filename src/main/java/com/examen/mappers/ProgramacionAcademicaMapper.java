package com.examen.mappers;

import com.examen.dtos.ProgramacionAcademicaDTO;
import com.examen.entities.ProgramacionAcademica;
import com.examen.entities.Materia;
import com.examen.entities.Docente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ProgramacionAcademicaMapper {

    @Mappings({
            @Mapping(source = "materia.id", target = "materiaId"),
            @Mapping(source = "docente.id", target = "docenteId")
    })
    ProgramacionAcademicaDTO toDTO(ProgramacionAcademica programacionAcademica);

    @Mappings({
            @Mapping(source = "materiaId", target = "materia"),
            @Mapping(source = "docenteId", target = "docente")
    })
    ProgramacionAcademica toEntity(ProgramacionAcademicaDTO programacionAcademicaDTO);

    default Materia mapMateria(Long id) {
        if (id == null) {
            return null;
        }
        Materia materia = new Materia();
        materia.setId(id);
        return materia;
    }

    default Long mapMateria(Materia materia) {
        if (materia == null) {
            return null;
        }
        return materia.getId();
    }

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
}
