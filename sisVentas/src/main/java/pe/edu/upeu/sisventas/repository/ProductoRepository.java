package pe.edu.upeu.sisventas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.edu.upeu.sisventas.entity.Categoria;
import pe.edu.upeu.sisventas.entity.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    // Buscar por código (único)
    Optional<Producto> findByCodigo(String codigo);

    // Buscar por categoría
    List<Producto> findByCategoria(Categoria categoria);

    // Buscar por ID de categoría
    List<Producto> findByCategoriaId(Long categoriaId);

    // Buscar productos activos
    List<Producto> findByActivoTrue();

    // Buscar productos activos por categoría
    List<Producto> findByActivoTrueAndCategoriaId(Long categoriaId);

    // Buscar por nombre (contiene - ignorando mayúsculas/minúsculas)
    List<Producto> findByNombreContainingIgnoreCase(String nombre);

    // Buscar productos activos con stock disponible
    List<Producto> findByActivoTrueAndStockGreaterThan(Integer stock);

}
