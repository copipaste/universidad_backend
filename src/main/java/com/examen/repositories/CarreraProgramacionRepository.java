package com.examen.repositories;

import com.examen.entities.CarreraProgramacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarreraProgramacionRepository extends JpaRepository<CarreraProgramacion, Long> {
}
