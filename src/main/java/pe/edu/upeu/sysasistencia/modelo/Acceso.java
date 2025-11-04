package pe.edu.upeu.sysasistencia.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "upeu_acceso")
public class Acceso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String url;
    private String icono;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "upeu_acceso_rol", joinColumns = @JoinColumn(name =
            "acceso_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id",
                    referencedColumnName = "id")
    )
    private List<Rol> roles;
}
