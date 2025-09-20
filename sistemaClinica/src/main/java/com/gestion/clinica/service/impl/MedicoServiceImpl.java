package com.gestion.clinica.service.impl;

import com.gestion.clinica.dto.MedicoDTO;
import com.gestion.clinica.entity.MedicoEntity;
import com.gestion.clinica.exception.NotFoundException;
import com.gestion.clinica.mapper.MedicoMapper;
import com.gestion.clinica.repository.MedicoRepository;
import com.gestion.clinica.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MedicoServiceImpl implements MedicoService {

    @Autowired private MedicoRepository medicoRepository;
    @Autowired private MedicoMapper medicoMapper;

    @Override public List<MedicoDTO> findAll(){return medicoMapper.toDTOList(medicoRepository.findAll());}
    @Override public MedicoDTO findById(Long id){return medicoMapper.toDTO(medicoRepository.findById(id).orElseThrow(()->new NotFoundException("Medico no encontrado con ID: "+id)));}
    @Override public MedicoDTO save(MedicoDTO dto){MedicoEntity e=medicoMapper.toEntity(dto);return medicoMapper.toDTO(medicoRepository.save(e));}
    @Override public MedicoDTO update(Long id,MedicoDTO dto){medicoRepository.findById(id).orElseThrow(()->new NotFoundException("Medico no encontrado con ID: "+id));MedicoEntity e=medicoMapper.toEntity(dto);e.setId(id);return medicoMapper.toDTO(medicoRepository.save(e));}
    @Override public void delete(Long id){medicoRepository.findById(id).orElseThrow(()->new NotFoundException("Medico no encontrado con ID: "+id));medicoRepository.deleteById(id);}
}
