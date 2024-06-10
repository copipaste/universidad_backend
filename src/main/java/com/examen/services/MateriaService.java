package com.examen.services;

import com.examen.dtos.MateriaDto;
import com.examen.entities.Materia;
import com.examen.mappers.MateriaMapper;
import com.examen.repositories.MateriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MateriaService {

    private final MateriaRepository materiaRepository;
    private final MateriaMapper materiaMapper;

    public Page<MateriaDto> getMateriasPage(Pageable paginacion) {
        return materiaRepository.findAll(paginacion).map(materiaMapper::toMateriaDto);
    }

    public MateriaDto createMateria(Materia materia) {
        return materiaMapper.toMateriaDto(materiaRepository.save(materia));
    }

    public MateriaDto getMateria(Long id) {
        Materia materia = materiaRepository.getReferenceById(id);
        return materiaMapper.toMateriaDto(materia);
    }

    public MateriaDto updateMateria(Long id, Materia materia) {
        materiaRepository.getReferenceById(id);
        materia.setId(id);
        return materiaMapper.toMateriaDto(materiaRepository.save(materia));
    }

    public void deleteMateria(Long id) {
        Materia materia = materiaRepository.getReferenceById(id);
        materiaRepository.delete(materia);
    }
}
