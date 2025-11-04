package com.example.sisventas.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "matriculas")
public class MatriculaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "La fecha no puede ser nula")
    @Column(nullable = false)
    private LocalDateTime fecha;

    @Column(length = 500)
    private String observaciones;

    @Column(nullable = false, length = 50)
    private String estado = "COMPLETADA"; // COMPLETADA, CANCELADA, PENDIENTE

    @Column(name = "numero", unique = true, length = 20)
    private Integer numero;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estudiante_id", nullable = false,
            foreignKey = @ForeignKey(name = "FK_matricula_estudiante"))
    @NotNull(message = "El estudiante no puede ser nulo")
    private EstudianteEntity estudiante;

    // Relación One-to-Many con MatriculaDetalle
    @OneToMany(mappedBy = "matricula", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<MatriculaDetalleEntity> detalles = new ArrayList<>();

    // 🔹 Nueva relación One-to-Many con DetallePago
    @OneToMany(mappedBy = "matricula", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<DetallePagoEntity> detallesPago = new ArrayList<>();

    @Column(nullable = false)
    private Date fechaCreacion;

    private Date fechaModificacion;

    @PrePersist
    public void prePersist() {
        this.fechaCreacion = new Date();
        this.fechaModificacion = new Date();
        this.fecha = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.fechaModificacion = new Date();
    }

    // Métodos de conveniencia para manejar relación bidireccional con detalles de matrícula
    public void addDetalle(MatriculaDetalleEntity detalle) {
        detalles.add(detalle);
        detalle.setMatricula(this);
    }

    public void removeDetalle(MatriculaDetalleEntity detalle) {
        detalles.remove(detalle);
        detalle.setMatricula(null);
    }

    // Métodos de conveniencia para manejar relación bidireccional con detalles de pago
    public void addDetallePago(DetallePagoEntity detallePago) {
        detallesPago.add(detallePago);
        detallePago.setMatricula(this);
    }

    public void removeDetallePago(DetallePagoEntity detallePago) {
        detallesPago.remove(detallePago);
        detallePago.setMatricula(null);
    }
}
