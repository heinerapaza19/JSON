package pe.edu.upeu.sysasistencia.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "upeu_escula")
public class Escuela {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombreeap;
    private String estado;
    private String inicialeseap;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "facultad_id", referencedColumnName = "id", nullable = false,
            foreignKey = @ForeignKey(name = "FK_FACULTAD_ESCUELA"))
    private Facultad facultadId;

}
