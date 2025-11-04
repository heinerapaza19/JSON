package com.example.sisventas.mapper;

import com.example.sisventas.dto.CursoDTO;
import com.example.sisventas.entity.CursoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CursoMapper {
    CursoMapper INSTANCE = Mappers.getMapper(CursoMapper.class);

    CursoDTO cursoEntityACursoDTO(CursoEntity cursoEntity);

    List<CursoDTO> cursosEntitiesACursoDTOs(List<CursoEntity> cursoEntities);

    CursoEntity CursoDTOACursoEntity(CursoDTO cursoDTO);

}
