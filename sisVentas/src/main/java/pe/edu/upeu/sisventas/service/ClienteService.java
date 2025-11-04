package pe.edu.upeu.sisventas.service;

import pe.edu.upeu.sisventas.dto.ClienteDTO;
import java.util.List;

public interface ClienteService {
    List<ClienteDTO> listar();
    ClienteDTO buscarPorId(Long id);
    ClienteDTO guardar(ClienteDTO clienteDTO);
    ClienteDTO actualizar(Long id, ClienteDTO clienteDTO);
    void eliminar(Long id);
}
