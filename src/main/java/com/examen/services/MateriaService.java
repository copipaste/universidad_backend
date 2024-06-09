package com.examen.services;

import com.examen.dtos.MateriaDto;
import com.examen.entities.Materia;
import com.examen.mappers.MateriaMapper;
import com.examen.repositories.MateriaRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.Page;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;

@Service
@RequiredArgsConstructor
public class MateriaService {

    private final MateriaRepository materiaRepository;
    private final MateriaMapper materiaMapper;

    public Page<MateriaDto> getMateriasPage(Pageable paginacion) {
        return materiaRepository.findAll(paginacion).map(materiaMapper::toMateriaDto);
    }

    public Materia getMateriaById(Long id){
        Optional<Materia> materia = materiaRepository.findById(id);
        if(materia.isPresent()){
            return materia.get();
        }
        throw new RuntimeException("Materia no encontrada con el id: "+id);
    }

    public Materia saveMateria(Materia materia){
        return materiaRepository.save(materia);
    }

    public Materia updateMateria(Long id, Materia materia){
        Optional<Materia> materiaCheck = materiaRepository.findById(id);
        if(materiaCheck.isEmpty()){
            throw new RuntimeException("Materia no encontrada con el id: "+id+". ");
        }
        materia.setId(id);
        return materiaRepository.save(materia);

    }

    public void deleteMateria(Long id){
        materiaRepository.deleteById(id);
    }

    public List<Materia> getMateriasByNombre(String nombre){
        return materiaRepository.findByNombre(nombre);
    }
}
