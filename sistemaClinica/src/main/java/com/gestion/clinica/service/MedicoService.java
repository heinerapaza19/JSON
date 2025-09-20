package com.gestion.clinica.service;

import com.gestion.clinica.dto.MedicoDTO;
import java.util.List;

public interface MedicoService {
    List<MedicoDTO> findAll();
    MedicoDTO findById(Long id);
    MedicoDTO save(MedicoDTO dto);
    MedicoDTO update(Long id, MedicoDTO dto);
    void delete(Long id);
}
