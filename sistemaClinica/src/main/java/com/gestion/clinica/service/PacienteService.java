package com.gestion.clinica.service;

import com.gestion.clinica.dto.PacienteDTO;
import java.util.List;

public interface PacienteService {
    List<PacienteDTO> findAll();
    PacienteDTO findById(Long id);
    PacienteDTO save(PacienteDTO dto);
    PacienteDTO update(Long id, PacienteDTO dto);
    void delete(Long id);
}
