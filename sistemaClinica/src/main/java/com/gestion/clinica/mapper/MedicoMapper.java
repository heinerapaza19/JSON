package com.gestion.clinica.mapper;

import com.gestion.clinica.dto.MedicoDTO;
import com.gestion.clinica.entity.MedicoEntity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring", uses = {CitaMapper.class})
public interface MedicoMapper {

    MedicoDTO toDTO(MedicoEntity entity);
    

    MedicoEntity toEntity(MedicoDTO dto);

    List<MedicoDTO> toDTOList(List<MedicoEntity> entityList);

}
