package com.examen.services;

import com.examen.dtos.carrera.CarreraDto;
import com.examen.dtos.carrera.CreateCarreraDto;
import com.examen.entities.Carrera;
import com.examen.entities.Facultad;
import com.examen.mappers.CarreraMapper;
import com.examen.repositories.CarreraRepository;
import com.examen.repositories.FacultadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarreraService {

    private final CarreraRepository carreraRepository;
    private final CarreraMapper carreraMapper;
    private final FacultadRepository facultadRepository;

    public Page<CarreraDto> getCarrerasPage(Pageable paginacion) {
        return carreraRepository.findAll(paginacion).map(carreraMapper::toCarreraDto);
    }

    public CarreraDto createCarrera(CreateCarreraDto createCarreraDto) {
        Carrera carrera = carreraRepository.save(carreraMapper.toCarrera(createCarreraDto));
        carrera.setFacultad(facultadRepository.getReferenceById(createCarreraDto.facultadId()));
        return carreraMapper.toCarreraDto(carrera);
    }

    public CarreraDto getCarrera(Long id) {
        Carrera carrera = carreraRepository.getReferenceById(id);
        return carreraMapper.toCarreraDto(carrera);
    }

    public CarreraDto updateCarrera(Long id, CreateCarreraDto createCarreraDto) {
        Carrera carrera = carreraRepository.getReferenceById(id);
        Facultad facultad = facultadRepository.getReferenceById(createCarreraDto.facultadId());
        carrera.actualizarDatos(createCarreraDto, facultad);
        return carreraMapper.toCarreraDto(carrera);
    }

    public void deactivateCarrera(Long id) {
        Carrera carrera = carreraRepository.getReferenceById(id);
        carrera.desactivarCarrera();
    }

    public void deleteCarrera(Long id) {
        Carrera carrera = carreraRepository.getReferenceById(id);
        carreraRepository.delete(carrera);
    }
}
