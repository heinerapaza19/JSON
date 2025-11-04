package pe.edu.upeu.sysasistencia.modelo;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "upeu_material")
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "mater_entre", nullable = true, length = 200)
    private String materEntre;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Basic(optional = false)
    @Column(name = "fecha", nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate fecha;
    @Basic(optional = false)
    @Column(name = "hora_reg", nullable = false)
    @Temporal(TemporalType.TIME)
    private LocalTime horaReg;
    private String latitud;
    private String longitud;
    @Basic(optional = false)
    @Column(name = "mod_fh", nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate modFh;
    @Column(name = "offlinex", length = 2, nullable = false)
    private String offlinex;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "eventoinscripcion_id", referencedColumnName =
            "id", nullable = false, foreignKey = @ForeignKey(name =
            "FK_EVENTO_INSCRIPCION_MATERIAL"))
    private EventoInscripcion eventoinscripcionId;
}
