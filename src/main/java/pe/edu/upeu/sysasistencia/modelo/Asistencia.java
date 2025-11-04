package pe.edu.upeu.sysasistencia.modelo;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "upeu_asistencia")
public class Asistencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Basic(optional = false)
    @Column(name = "fecha", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @Column(name = "hora_reg", nullable = false)
    @Temporal(TemporalType.TIME)
    private Date horaReg;
    private String latituda;
    private String longituda;

    @Column(name = "tipo", length = 20, nullable = false)
    private String tipo;
    @Column(name = "calificacion", nullable = false)
    private int calificacion;
    @Column(name = "tipo_reg", nullable = false, length = 12)
    private String tipoReg;
    @Column(name = "entsal", nullable = false, length = 2)
    private String entsal;
    @Column(name = "subactasis_id", nullable = true)
    private Long subactasisId;
    @Column(name = "offlinex", length = 2, nullable = false)
    private String offlinex;

    @JoinColumn(name = "evento_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Evento eventoId;
    @JoinColumn(name = "eventoinscripcion_id", referencedColumnName =
            "id")
    @ManyToOne(optional = false)
    private EventoInscripcion eventoinscripcionId;
}