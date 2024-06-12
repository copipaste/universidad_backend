package com.examen.mappers;

import com.examen.dtos.atraso.AtrasoDTO;
import com.examen.entities.Atraso;
import com.examen.entities.Asistencia;
import com.examen.repositories.AsistenciaRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring", uses = {AtrasoMapper.Helper.class})
public interface AtrasoMapper {

    @Mapping(source = "asistencia.id", target = "asistenciaId")
    AtrasoDTO toDTO(Atraso atraso);

    @Mapping(source = "asistenciaId", target = "asistencia")
    Atraso toEntity(AtrasoDTO dto);

    @Component
    class Helper {

        @Autowired
        private AsistenciaRepository asistenciaRepository;

        public Asistencia map(Long asistenciaId) {
            return asistenciaRepository.findById(asistenciaId).orElse(null);
        }

        public Long map(Asistencia asistencia) {
            return asistencia != null ? asistencia.getId() : null;
        }
    }
}
