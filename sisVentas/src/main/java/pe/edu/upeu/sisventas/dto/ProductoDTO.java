package pe.edu.upeu.sisventas.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Getter
@Setter
public class ProductoDTO {

    private Long id;

    @NotNull(message = "El nombre del producto no puede ser nulo")
    @Size(min = 3, max = 150, message = "El nombre debe tener entre 3 y 150 caracteres")
    private String nombre;

    @Size(max = 1000, message = "La descripción no puede exceder los 1000 caracteres")
    private String descripcion;

    @NotNull(message = "El código del producto no puede ser nulo")
    @Size(min = 3, max = 20, message = "El código debe tener entre 3 y 20 caracteres")
    private String codigo;

    @NotNull(message = "El precio no puede ser nulo")
    @DecimalMin(value = "0.01", message = "El precio debe ser mayor a 0")
    private BigDecimal precio;

    @Min(value = 0, message = "El stock no puede ser negativo")
    private Integer stock;

    private Boolean activo;

    // Relación con categoria - usando el ID para evitar referencias circulares
    @NotNull(message = "La categoría no puede ser nula")
    private Long categoriaId;

    // Información adicional de la categoría para mostrar en respuestas
    private String categoriaNombre;
    private String categoriaCodigo;

    private Date fechaCreacion;
    private Date fechaModificacion;

}