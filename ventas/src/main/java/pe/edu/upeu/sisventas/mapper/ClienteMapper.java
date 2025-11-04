package pe.edu.upeu.sisventas.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import pe.edu.upeu.sisventas.dto.ClienteDTO;
import pe.edu.upeu.sisventas.entity.ClienteEntity;
import java.util.stream.Collectors;
import java.util.ArrayList;

@Mapper(componentModel = "spring", imports = {Collectors.class, ArrayList.class})
public interface ClienteMapper {
    ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);

    @Mapping(target = "ventasIds",
            expression = "java(clienteEntity.getVentas() != null ? clienteEntity.getVentas().stream().map(v -> v.getId()).collect(Collectors.toList()) : new ArrayList<>())")
    ClienteDTO clienteEntityAClienteDTO(ClienteEntity clienteEntity);

    @Mapping(target = "ventas", ignore = true)
    ClienteEntity clienteDTOAClienteEntity(ClienteDTO clienteDTO);
}