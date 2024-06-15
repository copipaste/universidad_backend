package com.examen.dtos.falta;

import com.examen.dtos.MateriaDto;
import com.examen.dtos.ProgramacionAcademicaDTO;
import com.examen.dtos.horario.HorarioDTO;

import java.time.LocalDate;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FaltaPorDocenteRespDTO {
    private Long id;
    private LocalDate fecha;
    private ProgramacionAcademicaDTO programacionAcademicaDTO;
    private MateriaDto materiaDTO;
    private HorarioDTO horarioDTO;

    public FaltaPorDocenteRespDTO (
            Long id,
            LocalDate fecha,
            ProgramacionAcademicaDTO programacionAcademicaDTO,
            HorarioDTO horarioDTO,
            MateriaDto materiaDto){

        this.id = id;
        this.fecha = fecha;
        this.programacionAcademicaDTO = programacionAcademicaDTO;
        this.materiaDTO = materiaDto;
        this.horarioDTO = horarioDTO;
    }
}
