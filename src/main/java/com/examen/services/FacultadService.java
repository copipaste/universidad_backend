package com.examen.services;

import com.examen.dtos.facultad.FacultadDto;
import com.examen.entities.Facultad;
import com.examen.mappers.FacultadMapper;
import com.examen.repositories.FacultadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@RequiredArgsConstructor
public class FacultadService {

    private static final Logger logger = LoggerFactory.getLogger(FacultadService.class);

    private final FacultadRepository facultadRepository;
    private final FacultadMapper facultadMapper;

    public Page<FacultadDto> getFacultadesPage(Pageable paginacion) {
        Page<Facultad> facultadesPage = facultadRepository.findAll(paginacion);
        facultadesPage.forEach(facultad -> logger.debug("Facultad: {}", facultad));
        return facultadesPage.map(facultadMapper::toFacultadDto);
//        return facultadRepository.findAll(paginacion).map(facultadMapper::toFacultadDto);
    }
}
