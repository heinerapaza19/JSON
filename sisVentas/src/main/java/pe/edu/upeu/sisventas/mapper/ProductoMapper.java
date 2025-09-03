package pe.edu.upeu.sisventas.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import pe.edu.upeu.sisventas.dto.ProductoDTO;
import pe.edu.upeu.sisventas.entity.Categoria;
import pe.edu.upeu.sisventas.entity.Producto;

@Mapper(componentModel = "spring")
public interface ProductoMapper {

    ProductoMapper INSTANCE = Mappers.getMapper(ProductoMapper.class);

    @Mapping(source = "categoria.id", target = "categoriaId")
    @Mapping(source = "categoria.nombre", target = "categoriaNombre")
    @Mapping(source = "categoria.codigo", target = "categoriaCodigo")
    ProductoDTO productoToProductoDTO(Producto producto);

    @Mapping(source = "categoriaId", target = "categoria", qualifiedByName = "idToCategoria")
    Producto productoDTOToProducto(ProductoDTO productoDTO);

    @Named("idToCategoria")
    default Categoria idToCategoria(Long categoriaId) {
        if (categoriaId == null) {
            return null;
        }
        // Solo crear referencia con el ID para JPA
        Categoria categoria = new Categoria();
        categoria.setId(categoriaId);
        return categoria;
    }
}