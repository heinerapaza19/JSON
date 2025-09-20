package pe.edu.upeu.sisventas.service;

import pe.edu.upeu.sisventas.dto.VentaDTO;

import java.util.List;

public interface VentaService {

    List<VentaDTO> listar();

    VentaDTO guardar(VentaDTO ventaDTO);

    VentaDTO actualizarVenta(Long id, VentaDTO ventaDTO);

    VentaDTO buscarPorIdDTO(Long id); // Devuelve DTO completo con cliente y pagos
}
