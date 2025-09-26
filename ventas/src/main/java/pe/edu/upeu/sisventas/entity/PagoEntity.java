package pe.edu.upeu.sisventas.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "pagos")
public class PagoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El monto es obligatorio")
    @DecimalMin(value = "0.01", message = "El monto debe ser mayor a 0")
    private BigDecimal monto;

    @NotNull(message = "El método es obligatorio")
    @Column(length = 50)
    private String metodo; // Ej: "EFECTIVO", "TARJETA", "YAPE"

    private LocalDateTime fecha = LocalDateTime.now();

    // Relación con venta
    @ManyToOne
    @JoinColumn(name = "venta_id", nullable = false)
    private VentaEntity venta;
}