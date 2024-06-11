package com.examen.services;

import com.examen.dtos.ApiResponse;
import com.examen.entities.*;
import com.examen.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class AsistenciaService {

    @Autowired
    private AsistenciaRepository asistenciaRepository;

    @Autowired
    private DocenteRepository docenteRepository;

    @Autowired
    private HorarioRepository horarioRepository;

    @Autowired
    private FaltaRepository faltasRepository;

    @Autowired
    private AtrasoRepository atrasoRepository;

    public ApiResponse<Object> marcarAsistencia(Long docenteId, LocalTime horaMarcada, LocalDate fecha, double latitud, double longitud, Long materiaId, Long horarioId) {
        Optional<Docente> docenteOpt = docenteRepository.findById(docenteId);
        if (docenteOpt.isEmpty()) {
            return new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "Docente no encontrado", null);
        }

        Optional<Horario> horarioOpt = horarioRepository.findById(horarioId);
        if (horarioOpt.isEmpty()) {
            return new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "Horario no encontrado", null);
        }

        Horario horario = horarioOpt.get();
        LocalTime horaInicio = horario.getHoraInicio();
        LocalTime horaFin = horario.getHoraFin();

        if (!isWithinDistance(horario.getAula().getModulo(), latitud, longitud)) {
            return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), "Su ubicacion no esta en el rango aceptado", null);
        }

        if (isWithinTimeRange(horaInicio, horaMarcada, 10)) {
            Asistencia asistencia = new Asistencia();
            asistencia.setDocente(docenteOpt.get());
            asistencia.setHorario(horario);
            asistencia.setHora(horaMarcada);
            asistencia.setFecha(fecha);
            asistencia.setVirtual(false);
            asistenciaRepository.save(asistencia);
            return new ApiResponse<>(HttpStatus.OK.value(), "Asistencia marcada exitosamente", asistencia);
        } else {
            if (horaMarcada.isAfter(horaInicio.plusMinutes(10)) && horaMarcada.isBefore(horaInicio.plusMinutes(30))) {
                Asistencia asistencia = new Asistencia();
                asistencia.setDocente(docenteOpt.get());
                asistencia.setHorario(horario);
                asistencia.setHora(horaMarcada);
                asistencia.setFecha(fecha);
                asistencia.setVirtual(false);
                asistenciaRepository.save(asistencia);

                return new ApiResponse<>(209, "Asistencia marcada con atraso", asistencia); // 209 indica Atraso
            } else if (horaMarcada.isAfter(horaInicio.plusMinutes(30)) && horaMarcada.isBefore(horaFin)) {
                Asistencia asistencia = new Asistencia();
                asistencia.setDocente(docenteOpt.get());
                asistencia.setHorario(horario);
                asistencia.setHora(horaMarcada);
                asistencia.setFecha(fecha);
                asistencia.setVirtual(false);
                asistenciaRepository.save(asistencia);

                Falta falta = new Falta();
                falta.setFecha(fecha);
                falta.setProgramacionAcademica(horario.getProgramacionAcademica());
                faltasRepository.save(falta);

                return new ApiResponse<>(HttpStatus.OK.value(), "Falta por tardanza excedida", asistencia);
            }
        }

        return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), "Aun no esta en hora para marcar", null);
    }

    public ApiResponse<Object> marcarAsistenciaVirtual(Long docenteId, LocalTime horaMarcada, LocalDate fecha, Long materiaId, Long horarioId) {
        Optional<Docente> docenteOpt = docenteRepository.findById(docenteId);
        if (docenteOpt.isEmpty()) {
            return new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "Docente no encontrado", null);
        }

        Optional<Horario> horarioOpt = horarioRepository.findById(horarioId);
        if (horarioOpt.isEmpty()) {
            return new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "Horario no encontrado", null);
        }

        Horario horario = horarioOpt.get();
        LocalTime horaInicio = horario.getHoraInicio();
        LocalTime horaFin = horario.getHoraFin();

        if (isWithinTimeRange(horaInicio, horaMarcada, 10)) {
            Asistencia asistencia = new Asistencia();
            asistencia.setDocente(docenteOpt.get());
            asistencia.setHorario(horario);
            asistencia.setHora(horaMarcada);
            asistencia.setFecha(fecha);
            asistencia.setVirtual(true);
            asistenciaRepository.save(asistencia);
            return new ApiResponse<>(HttpStatus.OK.value(), "Asistencia virtual marcada exitosamente", asistencia);
        } else {
            if (horaMarcada.isAfter(horaInicio.plusMinutes(10)) && horaMarcada.isBefore(horaInicio.plusMinutes(30))) {
                Asistencia asistencia = new Asistencia();
                asistencia.setDocente(docenteOpt.get());
                asistencia.setHorario(horario);
                asistencia.setHora(horaMarcada);
                asistencia.setFecha(fecha);
                asistencia.setVirtual(true);
                asistenciaRepository.save(asistencia);

                return new ApiResponse<>(209, "Asistencia virtual marcada con atraso", asistencia); // 209 indica Atraso
            } else if (horaMarcada.isAfter(horaInicio.plusMinutes(30)) && horaMarcada.isBefore(horaFin)) {
                Asistencia asistencia = new Asistencia();
                asistencia.setDocente(docenteOpt.get());
                asistencia.setHorario(horario);
                asistencia.setHora(horaMarcada);
                asistencia.setFecha(fecha);
                asistencia.setVirtual(true);
                asistenciaRepository.save(asistencia);

                Falta falta = new Falta();
                falta.setFecha(fecha);
                falta.setProgramacionAcademica(horario.getProgramacionAcademica());
                faltasRepository.save(falta);

                return new ApiResponse<>(HttpStatus.OK.value(), "Falta virtual por tardanza excedida", asistencia);
            }
        }

        return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), "Aun no esta en hora para marcar", null);
    }

    private boolean isWithinTimeRange(LocalTime horaInicio, LocalTime horaMarcada, int minutos) {
        LocalTime inicioRango = horaInicio.minusMinutes(minutos);
        LocalTime finRango = horaInicio.plusMinutes(minutos);
        return !horaMarcada.isBefore(inicioRango) && !horaMarcada.isAfter(finRango);
    }

    private boolean isWithinDistance(Modulo modulo, double latitud, double longitud) {
        double distance = calculateDistance(modulo.getLatitud(), modulo.getLongitud(), latitud, longitud);
        return distance <= 20;
    }

    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371; // Radio de la Tierra en kilómetros

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return R * c * 1000; // convertir a metros
    }

    public List<Asistencia> obtenerTodasLasAsistencias() {
        return asistenciaRepository.findAll();
    }

    public Asistencia obtenerAsistenciaPorId(Long id) {
        return asistenciaRepository.findById(id).orElse(null);
    }

    public void eliminarAsistencia(Long id) {
        asistenciaRepository.deleteById(id);
    }
}
