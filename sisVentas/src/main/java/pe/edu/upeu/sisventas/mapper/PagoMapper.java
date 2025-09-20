package pe.edu.upeu.sisventas.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import pe.edu.upeu.sisventas.dto.PagoDTO;
import pe.edu.upeu.sisventas.entity.PagoEntity;

@Mapper(componentModel = "spring")
public interface PagoMapper {

    PagoMapper INSTANCE = Mappers.getMapper(PagoMapper.class);

    // De Entity a DTO, trae solo el id de la venta
    @Mapping(source = "venta.id", target = "ventaId")
    PagoDTO pagoToPagoDTO(PagoEntity pago);

    // De DTO a Entity
    @Mapping(source = "ventaId", target = "venta.id")
    PagoEntity pagoDTOToPago(PagoDTO pagoDTO);
}
