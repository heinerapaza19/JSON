package pe.edu.upeu.sisventas.service.impl;

import org.springframework.stereotype.Service;
import pe.edu.upeu.sisventas.dto.ClienteDTO;
import pe.edu.upeu.sisventas.entity.ClienteEntity;
import pe.edu.upeu.sisventas.entity.VentaEntity;
import pe.edu.upeu.sisventas.mapper.ClienteMapper;
import pe.edu.upeu.sisventas.repository.ClienteRepositorio;
import pe.edu.upeu.sisventas.repository.VentaRepository;
import pe.edu.upeu.sisventas.service.ClienteService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepositorio clienteRepositorio;
    private final VentaRepository ventaRepositorio;
    private final ClienteMapper clienteMapper;

    public ClienteServiceImpl(ClienteRepositorio clienteRepositorio,
                              VentaRepository ventaRepositorio,
                              ClienteMapper clienteMapper) {
        this.clienteRepositorio = clienteRepositorio;
        this.ventaRepositorio = ventaRepositorio;
        this.clienteMapper = clienteMapper;
    }

    @Override
    public ClienteDTO crearCliente(ClienteDTO dto) {
        // Buscar la venta existente
        VentaEntity venta = ventaRepositorio.findById(dto.getVentaId()).orElse(null);
        // Convertir DTO a entidad
        ClienteEntity cliente = clienteMapper.toEntity(dto, venta);
        // Guardar cliente en BD
        clienteRepositorio.save(cliente);
        // Devolver DTO
        return clienteMapper.toDTO(cliente);
    }

    @Override
    public List<ClienteDTO> listarClientes() {
        return clienteRepositorio.findAll()
                .stream()
                .map(clienteMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ClienteDTO obtenerCliente(Long id) {
        return clienteRepositorio.findById(id)
                .map(clienteMapper::toDTO)
                .orElse(null);
    }

    @Override
    public ClienteDTO actualizarCliente(Long id, ClienteDTO dto) {
        return clienteRepositorio.findById(id)
                .map(cliente -> {
                    cliente.setNombres(dto.getNombres());
                    cliente.setApellidos(dto.getApellidos());
                    cliente.setDni(dto.getDni());
                    // Buscar venta asociada
                    VentaEntity venta = ventaRepositorio.findById(dto.getVentaId()).orElse(null);
                    cliente.setVenta(venta);
                    clienteRepositorio.save(cliente);
                    return clienteMapper.toDTO(cliente);
                }).orElse(null);
    }

    @Override
    public void eliminarCliente(Long id) {
        clienteRepositorio.deleteById(id);
    }
}

