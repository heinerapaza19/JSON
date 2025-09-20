package pe.edu.upeu.sisventas.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.sisventas.dto.ClienteDTO;
import pe.edu.upeu.sisventas.entity.ClienteEntity;
import pe.edu.upeu.sisventas.entity.VentaEntity;
import pe.edu.upeu.sisventas.entity.Venta;


@Component
public class ClienteMapper {

    public ClienteEntity toEntity(ClienteDTO dto, VentaEntity venta) {
        ClienteEntity cliente = new ClienteEntity();
        cliente.setNombres(dto.getNombres());
        cliente.setApellidos(dto.getApellidos());
        cliente.setDni(dto.getDni());
        cliente.setVenta(venta);
        return cliente;
    }

    public ClienteDTO toDTO(ClienteDTO cliente) {
        ClienteDTO dto = new ClienteDTO();
        dto.setNombres(cliente.getNombres());
        dto.setApellidos(cliente.getApellidos());
        dto.setDni(cliente.getDni());
        if(cliente.getVenta() != null) {
            dto.setVentaId(cliente.getVenta().getId());
            dto.setFechaVenta(cliente.getVenta().getFecha().toString()); // fecha es LocalDateTime
            dto.setTotalVenta(cliente.getVenta().getTotal().doubleValue());
        }



        return dto;
    }

}
