package com.example.sisventas.mapper;

import com.example.sisventas.dto.EstudianteDTO;
import com.example.sisventas.entity.EstudianteEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EstudianteMapper {
    EstudianteMapper INSTANCE = Mappers.getMapper(EstudianteMapper.class);

    EstudianteDTO estudianteEntityAEstudianteDTO(EstudianteEntity estudianteEntity);

    List<EstudianteDTO> estudiantesEntitiesAEstudianteDTOs(List<EstudianteEntity> estudiantesEntity);

    EstudianteEntity estudianteDTOAEstudianteEntity(EstudianteDTO estudianteDTO);

}
