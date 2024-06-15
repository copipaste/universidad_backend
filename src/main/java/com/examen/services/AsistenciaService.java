package com.examen.services;

import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import com.examen.dtos.ApiResponse;
import com.examen.dtos.asistencia.AsistenciaDetalleDTO;
import com.examen.dtos.asistencia.AsistenciaRespuestaDTO;
import com.examen.dtos.asistencia.RegistroDeAsistenciasDTO;
import com.examen.entities.*;
import com.examen.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.*;
import java.util.stream.Collectors;

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

    @Autowired
    private LicenciaRepository licenciaRepository;

    @Autowired
    private FaltaService faltaService;

    @Transactional
    public ApiResponse<Object> marcarAsistencia(Long docenteId, LocalTime horaMarcada, LocalDate fecha, double latitud, double longitud, Long materiaId, Long horarioId) {
        Optional<Docente> docenteOpt = docenteRepository.findById(docenteId);
        if (docenteOpt.isEmpty()) {
            return new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "Docente no encontrado", null);
        }

        Optional<Horario> horarioOpt = horarioRepository.findById(horarioId);
        if (horarioOpt.isEmpty()) {
            return new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "Horario no encontrado", null);
        }

        // Verificar si ya existe una asistencia para el mismo docente, horario y fecha
        Optional<Asistencia> asistenciaExistente = asistenciaRepository.findByDocenteIdAndHorarioIdAndFecha(docenteId, horarioId, fecha);
        if (asistenciaExistente.isPresent()) {
            return new ApiResponse<>(HttpStatus.CONFLICT.value(), "Su asistencia de hoy ya fue registrada", null);
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
            AsistenciaRespuestaDTO respuesta = new AsistenciaRespuestaDTO(asistencia);

            faltaService.eliminarFalta(fecha, horario.getProgramacionAcademica().getId());

            return new ApiResponse<>(HttpStatus.OK.value(), "Asistencia marcada exitosamente", respuesta);
        } else {
            if (horaMarcada.isAfter(horaInicio.plusMinutes(10)) && horaMarcada.isBefore(horaInicio.plusMinutes(30))) {
                Asistencia asistencia = new Asistencia();
                asistencia.setDocente(docenteOpt.get());
                asistencia.setHorario(horario);
                asistencia.setHora(horaMarcada);
                asistencia.setFecha(fecha);
                asistencia.setVirtual(false);
                asistenciaRepository.save(asistencia);
                AsistenciaRespuestaDTO respuesta = new AsistenciaRespuestaDTO(asistencia);

                faltaService.eliminarFalta(fecha, horario.getProgramacionAcademica().getId());

                return new ApiResponse<>(209, "Asistencia marcada con atraso", respuesta); // 209 indica Atraso
            } else if (horaMarcada.isAfter(horaInicio.plusMinutes(30)) && horaMarcada.isBefore(horaFin)) {
                Asistencia asistencia = new Asistencia();
                asistencia.setDocente(docenteOpt.get());
                asistencia.setHorario(horario);
                asistencia.setHora(horaMarcada);
                asistencia.setFecha(fecha);
                asistencia.setVirtual(false);
                asistenciaRepository.save(asistencia);

//                Falta falta = new Falta();
//                falta.setFecha(fecha);
//                falta.setProgramacionAcademica(horario.getProgramacionAcademica());
//                faltasRepository.save(falta);

                AsistenciaRespuestaDTO respuesta = new AsistenciaRespuestaDTO(asistencia);
                return new ApiResponse<>(HttpStatus.OK.value(), "Falta por tardanza excedida", respuesta);
            }
        }

        return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), "Aun no esta en hora para marcar", null);
    }
    @Transactional
    public ApiResponse<Object> marcarAsistenciaVirtual(Long docenteId, LocalTime horaMarcada, LocalDate fecha, Long materiaId, Long horarioId) {
        Optional<Docente> docenteOpt = docenteRepository.findById(docenteId);
        if (docenteOpt.isEmpty()) {
            return new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "Docente no encontrado", null);
        }

        Optional<Horario> horarioOpt = horarioRepository.findById(horarioId);
        if (horarioOpt.isEmpty()) {
            return new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "Horario no encontrado", null);
        }

        // Verificar si ya existe una asistencia para el mismo docente, horario y fecha
        Optional<Asistencia> asistenciaExistente = asistenciaRepository.findByDocenteIdAndHorarioIdAndFecha(docenteId, horarioId, fecha);
        if (asistenciaExistente.isPresent()) {
            return new ApiResponse<>(HttpStatus.CONFLICT.value(), "Su asistencia de hoy ya fue registrada", null);
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
            AsistenciaRespuestaDTO respuesta = new AsistenciaRespuestaDTO(asistencia);

            faltaService.eliminarFalta(fecha, horario.getProgramacionAcademica().getId());

            return new ApiResponse<>(HttpStatus.OK.value(), "Asistencia virtual marcada exitosamente", respuesta);
        } else {
            if (horaMarcada.isAfter(horaInicio.plusMinutes(10)) && horaMarcada.isBefore(horaInicio.plusMinutes(30))) {
                Asistencia asistencia = new Asistencia();
                asistencia.setDocente(docenteOpt.get());
                asistencia.setHorario(horario);
                asistencia.setHora(horaMarcada);
                asistencia.setFecha(fecha);
                asistencia.setVirtual(true);
                asistenciaRepository.save(asistencia);
                AsistenciaRespuestaDTO respuesta = new AsistenciaRespuestaDTO(asistencia);

                faltaService.eliminarFalta(fecha, horario.getProgramacionAcademica().getId());

                return new ApiResponse<>(209, "Asistencia virtual marcada con atraso", respuesta); // 209 indica Atraso
            } else if (horaMarcada.isAfter(horaInicio.plusMinutes(30)) && horaMarcada.isBefore(horaFin)) {
                Asistencia asistencia = new Asistencia();
                asistencia.setDocente(docenteOpt.get());
                asistencia.setHorario(horario);
                asistencia.setHora(horaMarcada);
                asistencia.setFecha(fecha);
                asistencia.setVirtual(true);
                asistenciaRepository.save(asistencia);

//                Falta falta = new Falta();
//                falta.setFecha(fecha);
//                falta.setProgramacionAcademica(horario.getProgramacionAcademica());
//                faltasRepository.save(falta);
                AsistenciaRespuestaDTO respuesta = new AsistenciaRespuestaDTO(asistencia);
                return new ApiResponse<>(HttpStatus.OK.value(), "Falta virtual por tardanza excedida", respuesta);
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
        return asistenciaRepository.findAll(Sort.by(Sort.Direction.DESC, "fecha"));
    }

    public Asistencia obtenerAsistenciaPorId(Long id) {
        return asistenciaRepository.findById(id).orElse(null);
    }

    public void eliminarAsistencia(Long id) {
        asistenciaRepository.deleteById(id);
    }

    public RegistroDeAsistenciasDTO obtenerRegistroDeAsistencias(Long docenteId) {
//        List<Asistencia> asistencias = asistenciaRepository.findByDocenteId(docenteId);
//        List<Falta> faltas = faltasRepository.findByDocenteId(docenteId);
//        List<Licencia> licencias = licenciaRepository.findByDocenteId(docenteId);

        List<Asistencia> asistencias = asistenciaRepository.findByDocenteIdOrderByFechaDesc(docenteId);
        List<Falta> faltas = faltasRepository.findByDocenteIdOrderByFechaDesc(docenteId);
        List<Licencia> licencias = licenciaRepository.findByDocenteIdOrderByFechaDesc(docenteId);

        Map<String, List<AsistenciaDetalleDTO>> registro = new HashMap<>();

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        for (Asistencia asistencia : asistencias) {
            String key = generateKey(asistencia.getFecha());
            String tipo = determineTipo(asistencia);

            AsistenciaDetalleDTO detalle = AsistenciaDetalleDTO.builder()
                    .tipo(tipo)
                    .fecha(asistencia.getFecha().format(dateFormatter))
                    .hora(asistencia.getHora().toString())
                    .materia(asistencia.getHorario().getProgramacionAcademica().getMateria().getNombre())
                    .sigla(asistencia.getHorario().getProgramacionAcademica().getMateria().getSigla())
                    .grupo(asistencia.getHorario().getProgramacionAcademica().getMateria().getGrupo())
                    .build();

            registro.computeIfAbsent(key, k -> new ArrayList<>()).add(detalle);
        }

        for (Falta falta : faltas) {
            String key = generateKey(falta.getFecha());
            AsistenciaDetalleDTO detalle = AsistenciaDetalleDTO.builder()
                    .tipo("Falta")
                    .fecha(falta.getFecha().format(dateFormatter))
                    .hora("N/A")
                    .materia(falta.getProgramacionAcademica().getMateria().getNombre())
                    .sigla(falta.getProgramacionAcademica().getMateria().getSigla())
                    .grupo(falta.getProgramacionAcademica().getMateria().getGrupo())
                    .build();

            registro.computeIfAbsent(key, k -> new ArrayList<>()).add(detalle);
        }

        for (Licencia licencia : licencias) {
            String key = generateKey(licencia.getFecha());
            AsistenciaDetalleDTO detalle = AsistenciaDetalleDTO.builder()
                    .tipo("Falta Justificada")
                    .fecha(licencia.getFecha().format(dateFormatter))
                    .hora("N/A")
                    .materia(licencia.getProgramacionAcademica().getMateria().getNombre())
                    .sigla(licencia.getProgramacionAcademica().getMateria().getSigla())
                    .grupo(licencia.getProgramacionAcademica().getMateria().getGrupo())
                    .build();

            registro.computeIfAbsent(key, k -> new ArrayList<>()).add(detalle);
        }

        return new RegistroDeAsistenciasDTO(registro);
    }

    private String generateKey(LocalDate date) {
        int weekOfMonth = getWeekOfMonth(date);
        String month = getSpanishMonth(date.getMonthValue());
        String year = Integer.toString(date.getYear());
        return String.format("%s_%s_semana %d", year, month, weekOfMonth);
    }

    private int getWeekOfMonth(LocalDate date) {
        int dayOfMonth = date.getDayOfMonth();
        if (dayOfMonth <= 7) {
            return 1;
        } else if (dayOfMonth <= 14) {
            return 2;
        } else if (dayOfMonth <= 21) {
            return 3;
        } else if (dayOfMonth <= 28) {
            return 4;
        } else {
            return 5;
        }
    }

    private String getSpanishMonth(int month) {
        String[] months = {"enero", "febrero", "marzo", "abril", "mayo", "junio", "julio", "agosto", "septiembre", "octubre", "noviembre", "diciembre"};
        return months[month - 1];
    }

    private String determineTipo(Asistencia asistencia) {
        if (atrasoRepository.existsByAsistenciaId(asistencia.getId())) {
            return asistencia.isVirtual() ? "Virtual con atraso" : "Presencial con atraso";
        }
        return asistencia.isVirtual() ? "Virtual" : "Presencial";
    }
}