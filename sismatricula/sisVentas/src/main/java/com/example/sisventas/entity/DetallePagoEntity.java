package com.example.sisventas.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "detalle_pago")
public class DetallePagoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El monto no puede ser nulo")
    @Column(nullable = false)
    private Double monto;

    @Column(nullable = false)
    private Date fechaPago;

    private String observacion;

    // Relación con Matrícula
    @ManyToOne
    @JoinColumn(name = "matricula_id", nullable = false)
    private MatriculaEntity matricula;

    // Relación con Método de Pago
    @ManyToOne
    @JoinColumn(name = "metodo_pago_id", nullable = false)
    private MetodoPagoEntity metodoPago;

    @PrePersist
    private void beforePersist() {
        this.fechaPago = new Date();
    }
}
