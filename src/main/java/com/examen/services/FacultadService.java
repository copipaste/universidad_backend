package com.examen.services;

import com.examen.dtos.facultad.CreateFacultadDto;
import com.examen.dtos.facultad.FacultadDto;
import com.examen.entities.Facultad;
import com.examen.mappers.FacultadMapper;
import com.examen.repositories.FacultadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FacultadService {

    private final FacultadRepository facultadRepository;
    private final FacultadMapper facultadMapper;

    public Page<FacultadDto> getFacultadesPage(Pageable paginacion) {
        return facultadRepository.findAll(paginacion).map(facultadMapper::toFacultadDto);
    }

    public FacultadDto createFacultad(CreateFacultadDto createFacultadDto) {
        Facultad facultad = facultadRepository.save(facultadMapper.toFacultad(createFacultadDto));
        return facultadMapper.toFacultadDto(facultad);
    }

    public FacultadDto getFacultad(Long id) {
        Facultad facultad = facultadRepository.getReferenceById(id);
        return facultadMapper.toFacultadDto(facultad);
    }

    public FacultadDto updateFacultad(Long id, CreateFacultadDto createFacultadDto) {
        Facultad facultad = facultadRepository.getReferenceById(id);
        facultad.actualizarDatos(createFacultadDto);
        return facultadMapper.toFacultadDto(facultad);
    }

    public FacultadDto activateFacultad(Long id) {
        Facultad facultad = facultadRepository.getReferenceById(id);
        facultad.activarFacultad();
        return facultadMapper.toFacultadDto(facultad);
    }

    public void deactivateFacultad(Long id) {
        Facultad facultad = facultadRepository.getReferenceById(id);
        facultad.desactivarFacultad();
    }

    public void deleteFacultad(Long id) {
        Facultad facultad = facultadRepository.getReferenceById(id);
        facultadRepository.delete(facultad);
    }
}
