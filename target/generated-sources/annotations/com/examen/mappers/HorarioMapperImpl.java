package com.examen.mappers;

import com.examen.dtos.AulaDTO;
import com.examen.dtos.HorarioDetalladoDTO;
import com.examen.dtos.MateriaDto;
import com.examen.dtos.ProgramacionAcademicaDTO;
import com.examen.dtos.horario.HorarioDTO;
import com.examen.entities.Aula;
import com.examen.entities.Horario;
import com.examen.entities.Materia;
import com.examen.entities.ProgramacionAcademica;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-17T10:44:44-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class HorarioMapperImpl implements HorarioMapper {

    @Override
    public HorarioDTO toDTO(Horario horario) {
        if ( horario == null ) {
            return null;
        }

        HorarioDTO horarioDTO = new HorarioDTO();

        horarioDTO.setAulasId( horarioAulaId( horario ) );
        horarioDTO.setProgAcId( horarioProgramacionAcademicaId( horario ) );
        horarioDTO.setId( horario.getId() );
        horarioDTO.setHoraInicio( horario.getHoraInicio() );
        horarioDTO.setHoraFin( horario.getHoraFin() );
        horarioDTO.setDia( horario.getDia() );

        return horarioDTO;
    }

    @Override
    public Horario toEntity(HorarioDTO horarioDTO) {
        if ( horarioDTO == null ) {
            return null;
        }

        Horario.HorarioBuilder horario = Horario.builder();

        horario.aula( mapAula( horarioDTO.getAulasId() ) );
        horario.programacionAcademica( mapProgramacionAcademica( horarioDTO.getProgAcId() ) );
        horario.id( horarioDTO.getId() );
        horario.horaInicio( horarioDTO.getHoraInicio() );
        horario.horaFin( horarioDTO.getHoraFin() );
        horario.dia( horarioDTO.getDia() );

        return horario.build();
    }

    @Override
    public HorarioDetalladoDTO toDetailedDTO(Horario horario) {
        if ( horario == null ) {
            return null;
        }

        HorarioDetalladoDTO horarioDetalladoDTO = new HorarioDetalladoDTO();

        horarioDetalladoDTO.setHorarioId( horario.getId() );
        horarioDetalladoDTO.setAula( aulaToAulaDTO( horario.getAula() ) );
        horarioDetalladoDTO.setProgramacionAcademica( programacionAcademicaToProgramacionAcademicaDTO( horario.getProgramacionAcademica() ) );
        horarioDetalladoDTO.setMateria( materiaToMateriaDto( horarioProgramacionAcademicaMateria( horario ) ) );
        horarioDetalladoDTO.setHoraInicio( horario.getHoraInicio() );
        horarioDetalladoDTO.setHoraFin( horario.getHoraFin() );
        horarioDetalladoDTO.setDia( horario.getDia() );

        return horarioDetalladoDTO;
    }

    private Long horarioAulaId(Horario horario) {
        if ( horario == null ) {
            return null;
        }
        Aula aula = horario.getAula();
        if ( aula == null ) {
            return null;
        }
        Long id = aula.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long horarioProgramacionAcademicaId(Horario horario) {
        if ( horario == null ) {
            return null;
        }
        ProgramacionAcademica programacionAcademica = horario.getProgramacionAcademica();
        if ( programacionAcademica == null ) {
            return null;
        }
        Long id = programacionAcademica.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected AulaDTO aulaToAulaDTO(Aula aula) {
        if ( aula == null ) {
            return null;
        }

        AulaDTO aulaDTO = new AulaDTO();

        aulaDTO.setId( aula.getId() );
        aulaDTO.setNumero( aula.getNumero() );
        aulaDTO.setCapacidad( aula.getCapacidad() );

        return aulaDTO;
    }

    protected ProgramacionAcademicaDTO programacionAcademicaToProgramacionAcademicaDTO(ProgramacionAcademica programacionAcademica) {
        if ( programacionAcademica == null ) {
            return null;
        }

        ProgramacionAcademicaDTO programacionAcademicaDTO = new ProgramacionAcademicaDTO();

        programacionAcademicaDTO.setId( programacionAcademica.getId() );
        programacionAcademicaDTO.setAno( programacionAcademica.getAno() );
        programacionAcademicaDTO.setPeriodo( programacionAcademica.getPeriodo() );
        programacionAcademicaDTO.setFechaInicio( programacionAcademica.getFechaInicio() );
        programacionAcademicaDTO.setFechaFin( programacionAcademica.getFechaFin() );

        return programacionAcademicaDTO;
    }

    private Materia horarioProgramacionAcademicaMateria(Horario horario) {
        if ( horario == null ) {
            return null;
        }
        ProgramacionAcademica programacionAcademica = horario.getProgramacionAcademica();
        if ( programacionAcademica == null ) {
            return null;
        }
        Materia materia = programacionAcademica.getMateria();
        if ( materia == null ) {
            return null;
        }
        return materia;
    }

    protected MateriaDto materiaToMateriaDto(Materia materia) {
        if ( materia == null ) {
            return null;
        }

        MateriaDto.MateriaDtoBuilder materiaDto = MateriaDto.builder();

        materiaDto.id( materia.getId() );
        materiaDto.nombre( materia.getNombre() );
        materiaDto.sigla( materia.getSigla() );
        materiaDto.grupo( materia.getGrupo() );

        return materiaDto.build();
    }
}
