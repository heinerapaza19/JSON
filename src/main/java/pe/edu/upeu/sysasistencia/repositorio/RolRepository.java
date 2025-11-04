package pe.edu.upeu.sysasistencia.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.sysasistencia.modelo.Rol;

public interface RolRepository extends JpaRepository<Rol, Long> {
}
