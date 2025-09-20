package com.gestion.clinica.service.impl;

import com.gestion.clinica.dto.CitaDTO;
import com.gestion.clinica.entity.CitaEntity;
import com.gestion.clinica.exception.NotFoundException;
import com.gestion.clinica.mapper.CitaMapper;
import com.gestion.clinica.repository.CitaRepository;
import com.gestion.clinica.service.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CitaServiceImpl implements CitaService {

    @Autowired private CitaRepository citaRepository;
    @Autowired private CitaMapper citaMapper;

    @Override public List<CitaDTO> findAll(){return citaMapper.toDTOList(citaRepository.findAll());}
    @Override public CitaDTO findById(Long id){return citaMapper.toDTO(citaRepository.findById(id).orElseThrow(()->new NotFoundException("Cita no encontrado con ID: "+id)));}
    @Override public CitaDTO save(CitaDTO dto){CitaEntity e=citaMapper.toEntity(dto);return citaMapper.toDTO(citaRepository.save(e));}
    @Override public CitaDTO update(Long id,CitaDTO dto){citaRepository.findById(id).orElseThrow(()->new NotFoundException("Cita no encontrado con ID: "+id));CitaEntity e=citaMapper.toEntity(dto);e.setId(id);return citaMapper.toDTO(citaRepository.save(e));}
    @Override public void delete(Long id){citaRepository.findById(id).orElseThrow(()->new NotFoundException("Cita no encontrado con ID: "+id));citaRepository.deleteById(id);}
}
