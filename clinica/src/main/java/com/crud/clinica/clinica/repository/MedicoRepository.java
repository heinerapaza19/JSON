package com.crud.clinica.clinica.repository;


import com.crud.clinica.clinica.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

    // Buscar por email
    Optional<Medico> findByEmail(String email);

    // Buscar por DNI
    Optional<Medico> findByDni(String dni);
}
