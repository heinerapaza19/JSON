package com.gestion.clinica.service;

import com.gestion.clinica.dto.CitaDTO;
import java.util.List;

public interface CitaService {
    List<CitaDTO> findAll();
    CitaDTO findById(Long id);
    CitaDTO save(CitaDTO dto);
    CitaDTO update(Long id, CitaDTO dto);
    void delete(Long id);
}
