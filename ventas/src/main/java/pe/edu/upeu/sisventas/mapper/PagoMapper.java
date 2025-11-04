package pe.edu.upeu.sisventas.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import pe.edu.upeu.sisventas.dto.PagoDTO;
import pe.edu.upeu.sisventas.dto.VentaDetalleDTO;
import pe.edu.upeu.sisventas.entity.PagoEntity;
import pe.edu.upeu.sisventas.entity.ProductoEntity;
import pe.edu.upeu.sisventas.entity.VentaDetalleEntity;
import pe.edu.upeu.sisventas.entity.VentaEntity;

@Mapper(componentModel = "spring")
public interface PagoMapper {

    PagoMapper INSTANCE = Mappers.getMapper(PagoMapper.class);

    // De Entity a DTO, trae solo el id de la venta
    @Mapping(source = "venta.id", target = "ventaId")
    PagoDTO pagoToPagoDTO(PagoEntity pago);

    // De DTO a Entity
    @Mapping(source = "ventaId", target = "venta.id")
    PagoEntity pagoDTOToPago(PagoDTO pagoDTO);

    @Mapper(componentModel = "spring")
    interface VentaDetalleMapper {

        VentaDetalleMapper INSTANCE = Mappers.getMapper(VentaDetalleMapper.class);

        // Convertir de Entidad a DTO
        @Mapping(source = "venta.id", target = "ventaId")
        @Mapping(source = "producto.id", target = "productoId")
        @Mapping(source = "producto.nombre", target = "productoNombre")
        @Mapping(source = "producto.codigo", target = "productoCodigo")
        @Mapping(source = "producto.precio", target = "productoPrecio")
        VentaDetalleDTO ventaDetalleToVentaDetalleDTO(VentaDetalleEntity ventaDetalle);

        // Convertir de DTO a Entidad
        @Mapping(source = "ventaId", target = "venta", qualifiedByName = "idToVenta")
        @Mapping(source = "productoId", target = "producto", qualifiedByName = "idToProducto")
        @Mapping(target = "id", ignore = true) // Para creación, ignorar ID
        VentaDetalleEntity ventaDetalleDTOToVentaDetalle(VentaDetalleDTO ventaDetalleDTO);

        @Named("idToVenta")
        default VentaEntity idToVenta(Long ventaId) {
            if (ventaId == null) {
                return null;
            }
            VentaEntity venta = new VentaEntity();
            venta.setId(ventaId);
            return venta;
        }

        @Named("idToProducto")
        default ProductoEntity idToProducto(Long productoId) {
            if (productoId == null) {
                return null;
            }
            ProductoEntity producto = new ProductoEntity();
            producto.setId(productoId);
            return producto;
        }
    }
}