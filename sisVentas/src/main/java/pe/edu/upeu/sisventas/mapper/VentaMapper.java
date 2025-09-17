package pe.edu.upeu.sisventas.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import pe.edu.upeu.sisventas.dto.VentaDTO;
import pe.edu.upeu.sisventas.entity.VentaEntity;

@Mapper(componentModel = "spring", uses = {VentaDetalleMapper.class})
public interface VentaMapper {

    VentaMapper INSTANCE = Mappers.getMapper(VentaMapper.class);

    // Convertir de Entidad a DTO
    @Mapping(source = "detalles", target = "detalles")
    VentaDTO ventaToVentaDTO(VentaEntity venta);

    // Convertir de DTO a Entidad
    @Mapping(source = "detalles", target = "detalles")
    @Mapping(target = "id", ignore = true)
    // Para creación, ignorar ID
    VentaEntity ventaDTOToVenta(VentaDTO ventaDTO);
}