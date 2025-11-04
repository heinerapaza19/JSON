
package pe.edu.upeu.sisventas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.sisventas.entity.ProductoEntity;

public interface ProductoRepository extends JpaRepository<ProductoEntity, Long> {
}
