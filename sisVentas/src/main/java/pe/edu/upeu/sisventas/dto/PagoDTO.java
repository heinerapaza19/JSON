package pe.edu.upeu.sisventas.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PagoDTO {

    private Long id;

    @NotNull(message = "El monto es obligatorio")
    @DecimalMin(value = "0.01", message = "El monto debe ser mayor a 0")
    private BigDecimal monto;

    @NotNull(message = "El método es obligatorio")
    @Size(max = 50, message = "El método no puede exceder 50 caracteres")
    private String metodo;

    private LocalDateTime fecha;

    @NotNull(message = "La venta es obligatoria")
    private Long ventaId;
}
