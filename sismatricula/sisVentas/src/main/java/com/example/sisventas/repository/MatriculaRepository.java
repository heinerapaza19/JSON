package com.example.sisventas.repository;

import com.example.sisventas.entity.MatriculaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MatriculaRepository extends JpaRepository<MatriculaEntity, Long> {
    @Query("SELECT MAX(m.numero) FROM MatriculaEntity m")
    Integer findMaxNumero();
}
