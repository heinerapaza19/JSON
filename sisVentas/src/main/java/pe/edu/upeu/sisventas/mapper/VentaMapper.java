package pe.edu.upeu.sisventas.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import pe.edu.upeu.sisventas.dto.PagoDTO;
import pe.edu.upeu.sisventas.dto.VentaDTO;
import pe.edu.upeu.sisventas.dto.VentaDetalleDTO;
import pe.edu.upeu.sisventas.entity.PagoEntity;
import pe.edu.upeu.sisventas.entity.VentaDetalleEntity;
import pe.edu.upeu.sisventas.entity.VentaEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VentaMapper {

    VentaMapper INSTANCE = Mappers.getMapper(VentaMapper.class);

    // Mapea VentaEntity a VentaDTO, incluyendo cliente completo y pagos
    @Mapping(source = "cliente.id", target = "clienteId")
    @Mapping(source = "cliente.nombres", target = "clienteNombre")
    @Mapping(source = "cliente.apellidos", target = "clienteApellido")
    @Mapping(source = "cliente.dni", target = "clienteDni")
    @Mapping(source = "detalles", target = "detalles")
    @Mapping(source = "pagos", target = "pagos")
    VentaDTO ventaToVentaDTO(VentaEntity venta);

    // Mapea VentaDTO a VentaEntity, incluyendo cliente.id y pagos
    @Mapping(source = "clienteId", target = "cliente.id")
    @Mapping(source = "detalles", target = "detalles")
    @Mapping(source = "pagos", target = "pagos")
    VentaEntity ventaDTOToVenta(VentaDTO ventaDTO);

    // Mapear lista de detalles
    List<VentaDetalleDTO> ventaDetalleEntityListToDTOList(List<VentaDetalleEntity> detalles);
    List<VentaDetalleEntity> ventaDetalleDTOListToEntityList(List<VentaDetalleDTO> detalles);

    // Mapear detalle individual con campos completos
    @Mapping(source = "producto.id", target = "productoId")
    @Mapping(source = "producto.nombre", target = "productoNombre")
    @Mapping(source = "producto.codigo", target = "productoCodigo")
    @Mapping(source = "producto.precio", target = "productoPrecio")
    @Mapping(source = "venta.id", target = "ventaId")
    VentaDetalleDTO ventaDetalleEntityToDTO(VentaDetalleEntity detalle);

    @Mapping(source = "productoId", target = "producto.id")
    @Mapping(source = "ventaId", target = "venta.id")
    VentaDetalleEntity ventaDetalleDTOToEntity(VentaDetalleDTO detalle);

    // Mapear pagos
    @Mapping(source = "venta.id", target = "ventaId")
    PagoDTO pagoEntityToDTO(PagoEntity pago);

    @Mapping(source = "ventaId", target = "venta.id")
    PagoEntity pagoDTOToEntity(PagoDTO pago);

    List<PagoDTO> pagoEntityListToDTOList(List<PagoEntity> pagos);
    List<PagoEntity> pagoDTOListToEntityList(List<PagoDTO> pagos);
}
