package com.examen.repositories;

import com.examen.entities.Licencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LicenciaRepository extends JpaRepository<Licencia, Long> {
}
