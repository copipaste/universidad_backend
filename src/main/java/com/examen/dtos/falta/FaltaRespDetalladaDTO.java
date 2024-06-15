package com.examen.dtos.falta;

import com.examen.dtos.DocenteDTO;
import com.examen.dtos.MateriaDto;
import com.examen.dtos.ProgramacionAcademicaDTO;
import com.examen.dtos.horario.HorarioDTO;

import java.time.LocalDate;

public record FaltaRespDetalladaDTO(
        Long id,
        LocalDate fecha,
        ProgramacionAcademicaDTO programacionAcademicaDTO,
        DocenteDTO docenteDTO,
        MateriaDto materiaDTO,
        HorarioDTO horarioDTO
) {}
