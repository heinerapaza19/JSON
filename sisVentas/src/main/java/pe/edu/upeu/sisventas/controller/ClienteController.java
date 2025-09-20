package pe.edu.upeu.sisventas.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.sisventas.dto.ClienteDTO;
import pe.edu.upeu.sisventas.service.ClienteService;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ClienteDTO crearCliente(@RequestBody ClienteDTO dto) {
        return clienteService.crearCliente(dto);
    }

    @GetMapping
    public List<ClienteDTO> listarClientes() {
        return clienteService.listarClientes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> obtenerCliente(@PathVariable Long id) {
        ClienteDTO cliente = clienteService.obtenerCliente(id);
        if(cliente != null) return ResponseEntity.ok(cliente);
        else return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> actualizarCliente(@PathVariable Long id, @RequestBody ClienteDTO dto) {
        ClienteDTO cliente = clienteService.actualizarCliente(id, dto);
        if(cliente != null) return ResponseEntity.ok(cliente);
        else return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long id) {
        clienteService.eliminarCliente(id);
        return ResponseEntity.noContent().build();
    }
}
