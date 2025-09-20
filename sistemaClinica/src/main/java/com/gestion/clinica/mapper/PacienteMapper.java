package com.gestion.clinica.mapper;

import com.gestion.clinica.dto.PacienteDTO;
import com.gestion.clinica.entity.PacienteEntity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring", uses = {CitaMapper.class})
public interface PacienteMapper {

    PacienteDTO toDTO(PacienteEntity entity);
    

    PacienteEntity toEntity(PacienteDTO dto);

    List<PacienteDTO> toDTOList(List<PacienteEntity> entityList);

}
