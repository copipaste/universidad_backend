package com.examen.mappers;

import com.examen.dtos.FaltaDTO;
import com.examen.entities.Falta;
import com.examen.entities.ProgramacionAcademica;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface FaltaMapper {

    @Mappings({
            @Mapping(source = "programacionAcademica.id", target = "progAcId")
    })
    FaltaDTO toDTO(Falta falta);

    @Mappings({
            @Mapping(source = "progAcId", target = "programacionAcademica")
    })
    Falta toEntity(FaltaDTO faltaDTO);

    default ProgramacionAcademica mapProgramacionAcademica(Long id) {
        if (id == null) {
            return null;
        }
        ProgramacionAcademica programacionAcademica = new ProgramacionAcademica();
        programacionAcademica.setId(id);
        return programacionAcademica;
    }

    default Long mapProgramacionAcademica(ProgramacionAcademica programacionAcademica) {
        if (programacionAcademica == null) {
            return null;
        }
        return programacionAcademica.getId();
    }
}
