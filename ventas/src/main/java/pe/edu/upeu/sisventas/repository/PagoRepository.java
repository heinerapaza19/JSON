package pe.edu.upeu.sisventas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upeu.sisventas.entity.PagoEntity;

import java.util.List;

@Repository
public interface PagoRepository extends JpaRepository<PagoEntity, Long> {

    List<PagoEntity> findByVentaId(Long ventaId);
}