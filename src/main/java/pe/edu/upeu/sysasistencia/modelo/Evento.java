package pe.edu.upeu.sysasistencia.modelo;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.Length;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "upeu_evento")

public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nombreEvento;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private LocalDate fecha;
    private LocalTime horai;
    private LocalTime minToler;
    private String latitud;
    private String longitud;
    private String estado;
    private String evaluar;
    private String perfilEvento;
    private String userCreate;
    private String mater;
    private String validInsc;
    private String asisSubact;
    private String intsal;
    private String offlinex;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "periodo_id", referencedColumnName = "id", nullable = false,
            foreignKey = @ForeignKey(name = "FK_EVENTO_PERIODO"))
    private Periodo periodoId;


}
