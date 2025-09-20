package com.gestion.clinica.service.impl;

import com.gestion.clinica.dto.PacienteDTO;
import com.gestion.clinica.entity.PacienteEntity;
import com.gestion.clinica.exception.NotFoundException;
import com.gestion.clinica.mapper.PacienteMapper;
import com.gestion.clinica.repository.PacienteRepository;
import com.gestion.clinica.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PacienteServiceImpl implements PacienteService {

    @Autowired private PacienteRepository pacienteRepository;
    @Autowired private PacienteMapper pacienteMapper;

    @Override public List<PacienteDTO> findAll(){return pacienteMapper.toDTOList(pacienteRepository.findAll());}
    @Override public PacienteDTO findById(Long id){return pacienteMapper.toDTO(pacienteRepository.findById(id).orElseThrow(()->new NotFoundException("Paciente no encontrado con ID: "+id)));}
    @Override public PacienteDTO save(PacienteDTO dto){PacienteEntity e=pacienteMapper.toEntity(dto);return pacienteMapper.toDTO(pacienteRepository.save(e));}
    @Override public PacienteDTO update(Long id,PacienteDTO dto){pacienteRepository.findById(id).orElseThrow(()->new NotFoundException("Paciente no encontrado con ID: "+id));PacienteEntity e=pacienteMapper.toEntity(dto);e.setId(id);return pacienteMapper.toDTO(pacienteRepository.save(e));}
    @Override public void delete(Long id){pacienteRepository.findById(id).orElseThrow(()->new NotFoundException("Paciente no encontrado con ID: "+id));pacienteRepository.deleteById(id);}
}
