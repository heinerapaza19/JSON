package com.gestion.clinica.repository;

import com.gestion.clinica.entity.CitaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitaRepository extends JpaRepository<CitaEntity, Long> {
}
