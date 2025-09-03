package pe.edu.upeu.sisventas.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upeu.sisventas.dto.ProductoDTO;
import pe.edu.upeu.sisventas.entity.Producto;
import pe.edu.upeu.sisventas.mapper.ProductoMapper;
import pe.edu.upeu.sisventas.repository.CategoriaRepository;
import pe.edu.upeu.sisventas.repository.ProductoRepository;
import pe.edu.upeu.sisventas.service.ProductoService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoServiceImpl implements ProductoService {
@Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProductoMapper productoMapper;

    @Override
    public ProductoDTO guardarProducto(ProductoDTO productoDTO) {
        if (productoDTO.getCategoriaId() != null &&
                !categoriaRepository.existsById(productoDTO.getCategoriaId())) {
            throw new EntityNotFoundException("Categoría con ID " + productoDTO.getCategoriaId() + " no encontrada");
        }

        Producto producto = productoMapper.productoDTOToProducto(productoDTO);
        Producto productoGuardado = productoRepository.save(producto);
        return productoMapper.productoToProductoDTO(productoGuardado);
    }

    @Override
    public List<ProductoDTO> obtenerTodosLosProductos() {
        return productoRepository.findAll()
                .stream()
                .map(productoMapper::productoToProductoDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductoDTO obtenerProductoPorId(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado"));
        return productoMapper.productoToProductoDTO(producto);
    }

    @Override
    public ProductoDTO obtenerProductoPorCodigo(String codigo) {
        Producto producto = productoRepository.findByCodigo(codigo)
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado"));
        return productoMapper.productoToProductoDTO(producto);
    }

    @Override
    public List<ProductoDTO> obtenerProductosPorCategoria(Long categoriaId) {
        return productoRepository.findByCategoriaId(categoriaId)
                .stream()
                .map(productoMapper::productoToProductoDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductoDTO> obtenerProductosActivos() {
        return productoRepository.findByActivoTrue()
                .stream()
                .map(productoMapper::productoToProductoDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductoDTO> obtenerProductosActivosPorCategoria(Long categoriaId) {
        return productoRepository.findByActivoTrueAndCategoriaId(categoriaId)
                .stream()
                .map(productoMapper::productoToProductoDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductoDTO> buscarProductosPorNombre(String nombre) {
        return productoRepository.findByNombreContainingIgnoreCase(nombre)
                .stream()
                .map(productoMapper::productoToProductoDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductoDTO> obtenerProductosConStock() {
        return productoRepository.findByActivoTrueAndStockGreaterThan(0)
                .stream()
                .map(productoMapper::productoToProductoDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductoDTO actualizarStock(Long id, Integer nuevoStock) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado"));
        producto.setStock(nuevoStock);
        return productoMapper.productoToProductoDTO(productoRepository.save(producto));
    }

    @Override
    public ProductoDTO cambiarEstadoProducto(Long id, Boolean activo) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado"));
        producto.setActivo(activo);
        return productoMapper.productoToProductoDTO(productoRepository.save(producto));
    }

    @Override
    public void eliminarProducto(Long id) {
        if (!productoRepository.existsById(id)) {
            throw new EntityNotFoundException("Producto no encontrado");
        }
        productoRepository.deleteById(id);
    }
}
