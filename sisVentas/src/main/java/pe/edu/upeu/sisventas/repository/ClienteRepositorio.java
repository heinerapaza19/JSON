package pe.edu.upeu.sisventas.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.sisventas.entity.ClienteEntity;

public interface ClienteRepositorio extends JpaRepository<ClienteEntity, Long> {
}
