package pe.edu.upeu.sysasistencia.modelo;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "upeu_persona")
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "codigo", nullable = false, length = 10)
    private String codigo;
    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;
    @Column(name = "apellido_pat", nullable = false, length = 40)
    private String apellidoPat;
    @Column(name = "apellido_mat", nullable = false, length = 40)
    private String apellidoMat;
    @Column(name = "celular", nullable = false, length = 12)
    private String celular;
    @Column(name = "correo", nullable = false, length = 40)
    private String correo;
    @Column(name = "tipo", nullable = false, length = 12)
    private String tipo;
    @Column(name = "estado", nullable = false, length = 8)
    private String estado;
    @JoinColumn(name = "escuela_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Escuela escuelaId;
}
