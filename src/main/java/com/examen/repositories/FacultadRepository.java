package com.examen.repositories;

//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
import com.examen.entities.Facultad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacultadRepository extends JpaRepository<Facultad, Long>{

    //Page<Facultad> findByActivo(Integer active, Pageable paginacion);
}
