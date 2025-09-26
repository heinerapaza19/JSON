package com.example.sisventas.mapper;



import com.example.sisventas.dto.MetodoPagoDTO;
import com.example.sisventas.entity.MetodoPagoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface MetodoPagoMapper {
    MetodoPagoMapper INSTANCE = Mappers.getMapper(MetodoPagoMapper.class);

    MetodoPagoDTO toDto(MetodoPagoEntity entity);
    MetodoPagoEntity toEntity(MetodoPagoDTO dto);
}
