package com.example.sisventas.mapper;

import com.example.sisventas.dto.MatriculaDTO;
import com.example.sisventas.entity.EstudianteEntity;
import com.example.sisventas.entity.MatriculaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {MatriculaDetalleMapper.class, DetallePagoMapper.class})
public interface MatriculaMapper {

    MatriculaMapper INSTANCE = Mappers.getMapper(MatriculaMapper.class);

    // De entidad a DTO
    @Mapping(source = "estudiante.id", target = "estudianteId")
    @Mapping(source = "estudiante.nombre", target = "nombre")
    @Mapping(source = "estudiante.apellidoPaterno", target = "apellidoPaterno")
    @Mapping(source = "estudiante.apellidoMaterno", target = "apellidoMaterno")
    @Mapping(source = "estudiante.codigo", target = "codigo")
    @Mapping(source = "detalles", target = "detalles")
    @Mapping(source = "detallesPago", target = "pagos") // 🔹 mapear lista de pagos
    MatriculaDTO matriculaToMatriculaDTO(MatriculaEntity matricula);

    // De DTO a entidad
    @Mapping(source = "detalles", target = "detalles")
    @Mapping(source = "pagos", target = "detallesPago") // 🔹 el source es el DTO, target es la entidad
    @Mapping(source = "estudianteId", target = "estudiante", qualifiedByName = "idToEstudiante")
    @Mapping(target = "id", ignore = true) // ignorar ID para creación
    MatriculaEntity matriculaDTOToMatriculaEntity(MatriculaDTO matriculaDTO);


    @Named("idToEstudiante")
    default EstudianteEntity idToEstudiante(Long estudianteId) {
        if (estudianteId == null) {
            return null;
        }
        EstudianteEntity estudianteEntity = new EstudianteEntity();
        estudianteEntity.setId(estudianteId);
        return estudianteEntity;
    }
}
