package com.example.sisventas.mapper;

import com.example.sisventas.dto.DetallePagoDTO;
import com.example.sisventas.entity.DetallePagoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DetallePagoMapper {
    DetallePagoMapper INSTANCE = Mappers.getMapper(DetallePagoMapper.class);

    // De entidad a DTO
    @Mapping(source = "matricula.id", target = "matriculaId")
    @Mapping(source = "metodoPago.id", target = "metodoPagoId")
    @Mapping(source = "metodoPago.nombre", target = "metodoPagoNombre") // 🔹 mapear nombre del método
    DetallePagoDTO toDto(DetallePagoEntity entity);

    // De DTO a entidad
    @Mapping(source = "matriculaId", target = "matricula.id")
    @Mapping(source = "metodoPagoId", target = "metodoPago.id")
    DetallePagoEntity toEntity(DetallePagoDTO dto);
}
