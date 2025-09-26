package com.example.sisventas.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = "matricula_detalles")
@EqualsAndHashCode(exclude = {"matricula"})
@ToString(exclude = {"matricula"})
public class MatriculaDetalleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relación Many-to-One con Venta
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "matricula_id", nullable = false,
            foreignKey = @ForeignKey(name = "FK_matricula_detalle_matricula"))
    @NotNull(message = "La venta no puede ser nula")
    private MatriculaEntity matricula;

    // Relación Many-to-One con Producto
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id", nullable = false,
            foreignKey = @ForeignKey(name = "FK_matricula_detalle_curso"))
    @NotNull(message = "El curso no puede ser nulo")
    private CursoEntity curso;
}
