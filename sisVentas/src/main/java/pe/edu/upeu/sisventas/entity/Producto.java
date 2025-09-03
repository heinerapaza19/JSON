package pe.edu.upeu.sisventas.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El nombre del producto no puede ser nulo")
    @Size(min = 3, max = 150, message = "El nombre debe tener entre 3 y 150 caracteres")
    @Column(nullable = false, length = 150)
    private String nombre;

    @Size(max = 1000, message = "La descripción no puede exceder los 1000 caracteres")
    @Column(length = 1000)
    private String descripcion;

    @NotNull(message = "El código del producto no puede ser nulo")
    @Size(min = 3, max = 20, message = "El código debe tener entre 3 y 20 caracteres")
    @Column(nullable = false, unique = true, length = 20)
    private String codigo;

    @NotNull(message = "El precio no puede ser nulo")
    @DecimalMin(value = "0.01", message = "El precio debe ser mayor a 0")
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "categoria_id", nullable = false,
            foreignKey = @ForeignKey(name = "FK_producto_categoria"))
    private Categoria categoria;

    @Min(value = 0, message = "El stock no puede ser negativo")
    @Column(nullable = false, columnDefinition = "int default 0")
    private Integer stock = 0;

    @Column(nullable = false, columnDefinition = "boolean default true")
    private Boolean activo = true;
}
