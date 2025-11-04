package pe.edu.upeu.sisventas.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pe.edu.upeu.sisventas.dto.CategoriaDTO;
import pe.edu.upeu.sisventas.entity.CategoriaEntity;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {
    CategoriaMapper INSTANCE = Mappers.getMapper(CategoriaMapper.class);
    CategoriaDTO categoriaEntityACategoriaDto(CategoriaEntity categoriaEntity);
    CategoriaEntity categoriaDTOACategoriaEntity(CategoriaDTO categoriaDTO);
}