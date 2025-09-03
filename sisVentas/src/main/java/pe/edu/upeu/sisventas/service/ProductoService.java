package pe.edu.upeu.sisventas.service;

import pe.edu.upeu.sisventas.dto.ProductoDTO;
import java.util.List;

public interface ProductoService {

    ProductoDTO guardarProducto(ProductoDTO productoDTO);

    List<ProductoDTO> obtenerTodosLosProductos();

    ProductoDTO obtenerProductoPorId(Long id);

    ProductoDTO obtenerProductoPorCodigo(String codigo);

    List<ProductoDTO> obtenerProductosPorCategoria(Long categoriaId);

    List<ProductoDTO> obtenerProductosActivos();

    List<ProductoDTO> obtenerProductosActivosPorCategoria(Long categoriaId);

    List<ProductoDTO> buscarProductosPorNombre(String nombre);

    List<ProductoDTO> obtenerProductosConStock();

    ProductoDTO actualizarStock(Long id, Integer nuevoStock);

    ProductoDTO cambiarEstadoProducto(Long id, Boolean activo);

    void eliminarProducto(Long id);
}
