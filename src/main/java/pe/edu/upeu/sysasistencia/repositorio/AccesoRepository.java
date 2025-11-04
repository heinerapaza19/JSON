package pe.edu.upeu.sysasistencia.repositorio;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upeu.sysasistencia.modelo.Acceso;

@Repository
public interface AccesoRepository extends JpaRepository<Acceso, Long> {
}
