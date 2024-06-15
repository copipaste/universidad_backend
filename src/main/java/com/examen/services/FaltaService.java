package com.examen.services;

import com.examen.dtos.MateriaDto;
import com.examen.dtos.ProgramacionAcademicaDTO;
import com.examen.dtos.falta.FaltaDTO;
import com.examen.dtos.falta.FaltaPorDocenteRespDTO;
import com.examen.dtos.horario.HorarioDTO;
import com.examen.entities.Docente;
import com.examen.entities.Falta;
import com.examen.entities.Horario;
import com.examen.entities.ProgramacionAcademica;
import com.examen.mappers.FaltaMapper;
import com.examen.mappers.HorarioMapper;
import com.examen.mappers.MateriaMapper;
import com.examen.mappers.ProgramacionAcademicaMapper;
import com.examen.repositories.DocenteRepository;
import com.examen.repositories.FaltaRepository;
import com.examen.repositories.HorarioRepository;
import com.examen.repositories.ProgramacionAcademicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class FaltaService {

    @Autowired
    private FaltaRepository faltaRepository;

    @Autowired
    private HorarioRepository horarioRepository;

    @Autowired
    private ProgramacionAcademicaRepository programacionAcademicaRepository;

    @Autowired
    private FaltaMapper faltaMapper;

    @Autowired
    private DocenteRepository docenteRepository;

    @Autowired
    private HorarioMapper horarioMapper;

    @Autowired
    private ProgramacionAcademicaMapper programacionAcademicaMapper;
    @Autowired
    private MateriaMapper materiaMapper;

    public Falta guardarFalta(Falta falta) {
        return faltaRepository.save(falta);
    }

    public List<Falta> obtenerTodasLasFaltas() {
        return faltaRepository.findAll();
    }

    public Falta obtenerFaltaPorId(Long id) {
        return faltaRepository.findById(id).orElse(null);
    }

    public void eliminarFalta(Long id) {
        faltaRepository.deleteById(id);
    }

    public List<Falta> getFaltasReporte(Long progAcId, LocalDate fechaInicio, LocalDate fechaFin) {
        LocalDate today = LocalDate.now();
        if (progAcId == null && fechaInicio == null && fechaFin == null) {
            return faltaRepository.findByFechaLessThanEqualOrderByFechaDesc(today);
        }

        if (progAcId != null && fechaInicio == null && fechaFin == null) {
            return faltaRepository
                    .findByProgramacionAcademicaIdAndFechaLessThanEqualOrderByFechaDesc(progAcId, today);
        }

        if (fechaInicio != null || fechaFin != null){
            if (fechaInicio == null && fechaFin != null) {
                fechaInicio = fechaFin;
            }
            if (fechaInicio != null && fechaFin == null) {
                fechaFin = fechaInicio;
            }

            if (fechaFin.isAfter(today)) {
                fechaFin = today;
            }
        }
        if (progAcId == null) {
            return faltaRepository
                    .findByFechaBetweenOrderByFechaDesc(fechaInicio, fechaFin);
        }

        return faltaRepository
                .findByProgramacionAcademicaIdAndFechaBetweenOrderByFechaDesc(progAcId, fechaInicio, fechaFin);
    }

    public List<FaltaPorDocenteRespDTO> getFaltasPorDocente(Long docenteId, LocalDate fechaInicio, LocalDate fechaFin) {
        LocalDate today = LocalDate.now();
        Docente docente = docenteRepository.getReferenceById(docenteId);
        List<Falta> faltas = new ArrayList<>();
        if (fechaInicio == null && fechaFin == null) {
            faltas = faltaRepository.findByProgramacionAcademicaDocenteAndFechaLessThanEqualOrderByFechaDesc(
                    docente, today
            );
        }

        if (fechaInicio == null && fechaFin != null) {
            fechaInicio = fechaFin;
        }
        if (fechaInicio != null && fechaFin == null) {
            fechaFin = fechaInicio;
        }

        if (fechaFin != null && fechaFin.isAfter(today)) {
            fechaFin = today;
        }

        if (fechaInicio != null) {
            faltas = faltaRepository
                    .findByProgramacionAcademicaDocenteAndFechaBetweenOrderByFechaDesc(
                            docente, fechaInicio, fechaFin
                    );
        }

        return toFaltaPorDocenteRespDTO(faltas);
    }

    private List<FaltaPorDocenteRespDTO> toFaltaPorDocenteRespDTO(List<Falta> faltas) {
        List<FaltaPorDocenteRespDTO> flist = new ArrayList<>();
        for (Falta falta : faltas) {
            String dia = getDayByDate(falta.getFecha());
            ProgramacionAcademica pa = falta.getProgramacionAcademica();
            HorarioDTO horarioDTO = horarioMapper.toDTO(
                    horarioRepository
                            .findByProgramacionAcademicaIdAndDia(pa.getId(),dia)
            );
            ProgramacionAcademicaDTO paDTO = programacionAcademicaMapper.toDTO(pa);
            MateriaDto materiaDto = materiaMapper.toMateriaDto(
                    falta.getProgramacionAcademica().getMateria()
            );
            FaltaPorDocenteRespDTO f = new FaltaPorDocenteRespDTO(
                    falta.getId(),
                    falta.getFecha(),
                    paDTO,
                    horarioDTO,
                    materiaDto
            );
            flist.add(f);
        }
        return flist;
    }

    public void eliminarFalta(LocalDate fecha, Long progAcId) {
        faltaRepository.deleteByFechaAndProgramacionAcademicaId(fecha, progAcId);
    }

    public List<FaltaDTO> generarClases(Long horarioId) {
        Horario horario = horarioRepository.getReferenceById(horarioId);
        String dia = horario.getDia().toLowerCase();
        ProgramacionAcademica PA = horario.getProgramacionAcademica();
        LocalDate fechaClase = getFirstClassDate(PA.getFechaInicio(), dia);
        LocalDate fechaFin = PA.getFechaFin();

        List<Falta> clases = new ArrayList<>();
        while (fechaClase.isBefore(fechaFin) || fechaClase.isEqual(fechaFin)) {
            Falta falta = Falta.builder()
                    .fecha(fechaClase)
                    .programacionAcademica(PA)
                    .build();
            clases.add(falta);
            fechaClase = fechaClase.plusWeeks(1);
        }
        faltaRepository.saveAll(clases);
//        List<FaltaDTO> c = clases.stream().map(faltaMapper::toDTO).toList();
//        System.out.println(c);
//        return c;
        return clases.stream().map(faltaMapper::toDTO).toList();
    }

    private LocalDate getFirstClassDate(LocalDate fechaInicio, String dia) {
        while (true) {
            if (getDayByDate(fechaInicio).equals(dia)){
                return fechaInicio;
            }
            fechaInicio = fechaInicio.plusDays(1);
        }
    }

    public String getDayByDate(LocalDate fecha) {
        DayOfWeek day = fecha.getDayOfWeek();
        switch (day) {
            case MONDAY:
                return "lunes";
            case TUESDAY:
                return "martes";
            case WEDNESDAY:
                return "miercoles";
            case THURSDAY:
                return "jueves";
            case FRIDAY:
                return "viernes";
            case SATURDAY:
                return "sabado";
            case SUNDAY:
                return "domingo";
        }
        return "desconocido";
    }
}
