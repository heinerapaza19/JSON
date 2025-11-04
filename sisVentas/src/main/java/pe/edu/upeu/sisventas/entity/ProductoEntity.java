package pe.edu.upeu.sisventas.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "producto")
public class ProductoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El nombre del producto no puede ser nulo")
    @Size(min = 3, max = 150, message = "El nombre debe tener entre 3 y 150 caracteres")
    @Column(nullable = false)
    private String nombre;

    @Size(max = 1000, message = "La descripción no puede exceder los 1000 caracteres")
    private String descripcion;

    @NotNull(message = "El código del producto no puede ser nulo")
    @Size(min = 3, max = 20, message = "El código debe tener entre 3 y 20 caracteres")
    @Column(nullable = false, unique = true)
    private String codigo;

    @NotNull(message = "El precio no puede ser nulo")
    @DecimalMin(value = "0.01", message = "El precio debe ser mayor a 0")
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;

    @Min(value = 0, message = "El stock no puede ser negativo")
    @Column(nullable = false)
    private Integer stock = 0;


    // Relación Many-to-One con Categoria
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id", nullable = false,
            foreignKey = @ForeignKey(name = "FK_producto_categoria"))
    @NotNull(message = "La categoría no puede ser nula")
    private CategoriaEntity categoria;

    @Column(name = "activo", nullable = false)
    private Boolean activo = true;

    @Column(nullable = false)
    private Date fechaCreacion;
    private Date fechaModificacion;

    @PrePersist
    private void beforePersist() {
        this.fechaCreacion = new Date();
        this.fechaModificacion = new Date();

    }

    @PreUpdate
    private void beforeUpdate() {
        this.fechaModificacion = new Date();
    }
}
