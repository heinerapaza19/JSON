package pe.edu.upeu.sisventas.service;

import pe.edu.upeu.sisventas.dto.CategoriaDTO;
import pe.edu.upeu.sisventas.dto.ProductoDTO;

import java.util.List;

public interface ProductoService {
    List<ProductoDTO> listar();

    ProductoDTO productoPorId(Long id);

    ProductoDTO guardarProducto(ProductoDTO productoDTO);

    ProductoDTO editarProducto(ProductoDTO productoDTO);

    void borrarProducto(Long id);
}
