package pe.edu.upeu.sisventas.controller;



import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.sisventas.dto.ProductoDTO;
import pe.edu.upeu.sisventas.service.ProductoService;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    // Crear o actualizar producto
    @PostMapping
    public ResponseEntity<ProductoDTO> crearProducto(@Valid @RequestBody ProductoDTO productoDTO) {
        ProductoDTO productoGuardado = productoService.guardarProducto(productoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(productoGuardado);
    }

    // Actualizar producto existente
    @PutMapping("/{id}")
    public ResponseEntity<ProductoDTO> actualizarProducto(@PathVariable Long id, @Valid @RequestBody ProductoDTO productoDTO) {
        productoDTO.setId(id);
        ProductoDTO productoActualizado = productoService.guardarProducto(productoDTO);
        return ResponseEntity.ok(productoActualizado);
    }

    // Obtener todos los productos
    @GetMapping
    public List<ProductoDTO> obtenerProductos() {
        return productoService.obtenerTodosLosProductos();
    }

    // Obtener producto por ID
    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> obtenerProducto(@PathVariable Long id) {
        ProductoDTO producto = productoService.obtenerProductoPorId(id);
        return ResponseEntity.ok(producto);
    }

    // Obtener producto por código
    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<ProductoDTO> obtenerProductoPorCodigo(@PathVariable String codigo) {
        ProductoDTO producto = productoService.obtenerProductoPorCodigo(codigo);
        return ResponseEntity.ok(producto);
    }

    // Obtener productos por categoría
    @GetMapping("/categoria/{categoriaId}")
    public List<ProductoDTO> obtenerProductosPorCategoria(@PathVariable Long categoriaId) {
        return productoService.obtenerProductosPorCategoria(categoriaId);
    }

    // Obtener productos activos
    @GetMapping("/activos")
    public List<ProductoDTO> obtenerProductosActivos() {
        return productoService.obtenerProductosActivos();
    }

    // Obtener productos activos por categoría
    @GetMapping("/activos/categoria/{categoriaId}")
    public List<ProductoDTO> obtenerProductosActivosPorCategoria(@PathVariable Long categoriaId) {
        return productoService.obtenerProductosActivosPorCategoria(categoriaId);
    }

    // Buscar productos por nombre
    @GetMapping("/buscar")
    public List<ProductoDTO> buscarProductos(@RequestParam String nombre) {
        return productoService.buscarProductosPorNombre(nombre);
    }

    // Obtener productos con stock disponible
    @GetMapping("/disponibles")
    public List<ProductoDTO> obtenerProductosDisponibles() {
        return productoService.obtenerProductosConStock();
    }

    // Actualizar stock de un producto
    @PatchMapping("/{id}/stock")
    public ResponseEntity<ProductoDTO> actualizarStock(@PathVariable Long id, @RequestParam Integer stock) {
        ProductoDTO productoActualizado = productoService.actualizarStock(id, stock);
        return ResponseEntity.ok(productoActualizado);
    }

    // Activar/Desactivar producto
    @PatchMapping("/{id}/estado")
    public ResponseEntity<ProductoDTO> cambiarEstadoProducto(@PathVariable Long id, @RequestParam Boolean activo) {
        ProductoDTO productoActualizado = productoService.cambiarEstadoProducto(id, activo);
        return ResponseEntity.ok(productoActualizado);
    }

    // Eliminar producto por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }
}