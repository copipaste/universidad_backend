package com.examen.mappers;

import com.examen.dtos.LicenciaDTO;
import com.examen.entities.Licencia;
import com.examen.entities.ProgramacionAcademica;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface LicenciaMapper {

    @Mappings({
            @Mapping(source = "programacionAcademica.id", target = "progAcId")
    })
    LicenciaDTO toDTO(Licencia licencia);

    @Mappings({
            @Mapping(source = "progAcId", target = "programacionAcademica")
    })
    Licencia toEntity(LicenciaDTO licenciaDTO);

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
