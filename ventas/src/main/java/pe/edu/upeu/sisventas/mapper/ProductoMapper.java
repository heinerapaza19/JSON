package pe.edu.upeu.sisventas.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import pe.edu.upeu.sisventas.dto.ProductoDTO;
import pe.edu.upeu.sisventas.entity.CategoriaEntity;
import pe.edu.upeu.sisventas.entity.ProductoEntity;

@Mapper(componentModel = "spring")
public interface ProductoMapper {

    ProductoMapper INSTANCE = Mappers.getMapper(ProductoMapper.class);

    @Mapping(source = "categoria.id", target = "categoriaId")
    @Mapping(source = "categoria.nombre", target = "categoriaNombre")
    @Mapping(source = "categoria.codigo", target = "categoriaCodigo")
    ProductoDTO productoToProductoDTO(ProductoEntity productoEntity);

    @Mapping(source = "categoriaId", target = "categoria", qualifiedByName = "idToCategoria")
    ProductoEntity productoDTOToProducto(ProductoDTO productoDTO);

    @Named("idToCategoria")
    default CategoriaEntity idToCategoria(Long categoriaId) {
        if (categoriaId == null) {
            return null;
        }
        // Solo crear referencia con el ID para JPA
        CategoriaEntity categoria = new CategoriaEntity();
        categoria.setId(categoriaId);
        return categoria;
    }
}