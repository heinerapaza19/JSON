package com.example.sisventas.mapper;

import com.example.sisventas.dto.MatriculaDetalleDTO;
import com.example.sisventas.entity.CursoEntity;
import com.example.sisventas.entity.MatriculaDetalleEntity;
import com.example.sisventas.entity.MatriculaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MatriculaDetalleMapper {

    MatriculaDetalleMapper INSTANCE = Mappers.getMapper(MatriculaDetalleMapper.class);

    // Convertir de Entidad a DTO
    @Mapping(source = "matricula.id", target = "matriculaId")
    @Mapping(source = "curso.id", target = "cursoId")
    @Mapping(source = "curso.nombre", target = "cursoNombre")
    @Mapping(source = "curso.codigo", target = "cursoCodigo")
    MatriculaDetalleDTO matriculaDetalleToMatriculaDetalleDTO(MatriculaDetalleEntity matriculaDetalleEntity);

    // Convertir de DTO a Entidad
    @Mapping(source = "matriculaId", target = "matricula", qualifiedByName = "idToMatricula")

    @Mapping(source = "cursoId", target = "curso", qualifiedByName = "idToCurso")

    @Mapping(target = "id", ignore = true) // Para creación, ignorar ID
    MatriculaDetalleEntity ventaDetalleDTOToVentaDetalle(MatriculaDetalleDTO matriculaDetalleDTO);

    @Named("idToMatricula")
    default MatriculaEntity idToMatricula(Long matriculaId) {
        if (matriculaId == null) {
            return null;
        }
        MatriculaEntity matriculaEntity = new MatriculaEntity();
        matriculaEntity.setId(matriculaId);
        return matriculaEntity;
    }

    @Named("idToCurso")
    default CursoEntity idToCurso(Long cursoId) {
        if (cursoId == null) {
            return null;
        }
        CursoEntity cursoEntity = new CursoEntity();
        cursoEntity.setId(cursoId);
        return cursoEntity;
    }
}