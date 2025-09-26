package pe.edu.upeu.sisventas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.sisventas.entity.VentaEntity;

import java.util.Optional;

public interface VentaRepository extends JpaRepository<VentaEntity, Long> {
    Optional<VentaEntity> findTopByOrderByIdDesc();
}