package com.examen.services;

import com.examen.dtos.FaltaDTO;
import com.examen.entities.Falta;
import com.examen.entities.Horario;
import com.examen.entities.ProgramacionAcademica;
import com.examen.mappers.FaltaMapper;
import com.examen.repositories.FaltaRepository;
import com.examen.repositories.HorarioRepository;
import com.examen.repositories.ProgramacionAcademicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
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

    private String getDayByDate(LocalDate fecha) {
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
