package pe.edu.upeu.sisventas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.sisventas.entity.ClienteEntity;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {
    // Opcional: puedes agregar consultas personalizadas, por ejemplo:
    // Optional<ClienteEntity> findByDni(String dni);
}