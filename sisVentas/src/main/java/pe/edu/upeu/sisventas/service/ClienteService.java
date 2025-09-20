package pe.edu.upeu.sisventas.service;


import pe.edu.upeu.sisventas.dto.ClienteDTO;

import java.util.List;

public interface ClienteService {
    ClienteDTO crearCliente(ClienteDTO dto);
    List<ClienteDTO> listarClientes();
    ClienteDTO obtenerCliente(Long id);
    ClienteDTO actualizarCliente(Long id, ClienteDTO dto);
    void eliminarCliente(Long id);
}
