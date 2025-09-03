package pe.edu.upeu.sisventas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.sisventas.entity.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
