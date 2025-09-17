package pe.edu.upeu.sisventas.service;

import pe.edu.upeu.sisventas.dto.ProductoDTO;
import pe.edu.upeu.sisventas.dto.VentaDTO;

import java.util.List;

public interface VentaService {
    List<VentaDTO> listar();

    VentaDTO guardar(VentaDTO ventaDTO);
    VentaDTO actualizarVenta(Long id, VentaDTO ventaDTO);
}
