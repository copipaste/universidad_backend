package com.examen.dtos;

import com.examen.dtos.facultad.FacultadDto;
import com.examen.dtos.horario.HorarioDTO;
import com.examen.entities.Licencia;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LicenciaRespuestaDTO {
    private Long id;
    private LocalDate fechaSolicitada;
    private LocalDate fecha;
    private String justificacion;
    private boolean efectuada;
    private MateriaDto materiaDto;
    private HorarioDTO horarioDTO;

    public LicenciaRespuestaDTO(Licencia licencia, MateriaDto materiaDto, HorarioDTO horarioDTO) {
        this.id = licencia.getId();
        this.fechaSolicitada = licencia.getFechaSolicitada();
        this.fecha = licencia.getFecha();
        this.justificacion = licencia.getJustificacion();
        this.efectuada = licencia.isEfectuada();
        this.materiaDto = materiaDto;
        this.horarioDTO = horarioDTO;
    }
}
