package com.crud.clinica.clinica.repository;

import com.crud.clinica.clinica.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    // Ejemplo de método personalizado:
    Paciente findByDni(String dni);
}
