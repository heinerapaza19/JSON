package pe.edu.upeu.sisventas.service.serviceImpl;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upeu.sisventas.dto.ClienteDTO;
import pe.edu.upeu.sisventas.entity.ClienteEntity;
import pe.edu.upeu.sisventas.entity.VentaEntity;
import pe.edu.upeu.sisventas.mapper.ClienteMapper;
import pe.edu.upeu.sisventas.repository.ClienteRepository;
import pe.edu.upeu.sisventas.repository.VentaRepository;
import pe.edu.upeu.sisventas.service.ClienteService;
import pe.edu.upeu.sisventas.service.VentaService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private ClienteMapper clienteMapper;

    @Override
    public List<ClienteDTO> listar() {
        return clienteRepository.findAll()
                .stream()
                .map(clienteMapper::clienteEntityAClienteDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ClienteDTO buscarPorId(Long id) {
        ClienteEntity cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado con id: " + id));
        return clienteMapper.clienteEntityAClienteDTO(cliente);
    }



    @Override
    public ClienteDTO guardar(ClienteDTO clienteDTO) {
        // Convertir DTO a entidad
        ClienteEntity clienteEntity = clienteMapper.clienteDTOAClienteEntity(clienteDTO);

        // Asignar ventas si vienen en el DTO
        if (clienteDTO.getVentasIds() != null && !clienteDTO.getVentasIds().isEmpty()) {
            List<VentaEntity> ventas = clienteDTO.getVentasIds().stream()
                    .map(ventaId -> ventaRepository.findById(ventaId)
                            .orElseThrow(() -> new EntityNotFoundException("Venta no encontrada con id: " + ventaId)))
                    .collect(Collectors.toList());

            // Asociar ventas al cliente
            ventas.forEach(venta -> venta.setCliente(clienteEntity));

            clienteEntity.setVentas(ventas);
        }

        // Guardar cliente en la base de datos
        ClienteEntity guardado = clienteRepository.save(clienteEntity);

        // Convertir de nuevo a DTO para devolver
        return clienteMapper.clienteEntityAClienteDTO(guardado);
    }


    @Override
    public ClienteDTO actualizar(Long id, ClienteDTO clienteDTO) {
        ClienteEntity clienteExistente = clienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado con id: " + id));

        clienteExistente.setNombres(clienteDTO.getNombres());
        clienteExistente.setApellidos(clienteDTO.getApellidos());
        clienteExistente.setDni(clienteDTO.getDni());

        // Actualizar ventas si vienen
        if (clienteDTO.getVentasIds() != null) {
            clienteExistente.getVentas().clear();
            for (Long ventaId : clienteDTO.getVentasIds()) {
                VentaEntity venta = ventaRepository.findById(ventaId)
                        .orElseThrow(() -> new EntityNotFoundException("Venta no encontrada con id: " + ventaId));
                venta.setCliente(clienteExistente);
                clienteExistente.getVentas().add(venta);
            }
        }

        ClienteEntity actualizado = clienteRepository.save(clienteExistente);
        return clienteMapper.clienteEntityAClienteDTO(actualizado);
    }

    @Override
    public void eliminar(Long id) {
        ClienteEntity cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado con id: " + id));
        clienteRepository.delete(cliente);
    }
}