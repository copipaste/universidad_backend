package com.examen.mappers;

import com.examen.dtos.HorarioDetalladoDTO;
import com.examen.dtos.horario.HorarioDTO;
import com.examen.entities.Horario;
import com.examen.entities.Aula;
import com.examen.entities.ProgramacionAcademica;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface HorarioMapper {

    @Mappings({
            @Mapping(source = "aula.id", target = "aulasId"),
            @Mapping(source = "programacionAcademica.id", target = "progAcId")
    })
    HorarioDTO toDTO(Horario horario);

    @Mappings({
            @Mapping(source = "aulasId", target = "aula"),
            @Mapping(source = "progAcId", target = "programacionAcademica")
    })
    Horario toEntity(HorarioDTO horarioDTO);


    @Mapping(source = "horario.id", target = "horarioId")
    @Mapping(source = "horario.aula", target = "aula")
    @Mapping(source = "horario.programacionAcademica", target = "programacionAcademica")
    @Mapping(source = "horario.programacionAcademica.materia", target = "materia")
    HorarioDetalladoDTO toDetailedDTO(Horario horario);

    default Aula mapAula(Long id) {
        if (id == null) {
            return null;
        }
        Aula aula = new Aula();
        aula.setId(id);
        return aula;
    }

    default Long mapAula(Aula aula) {
        if (aula == null) {
            return null;
        }
        return aula.getId();
    }

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
