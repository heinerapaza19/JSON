package pe.edu.upeu.sisventas.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El nombre de la categoría no puede ser nulo")  // Validación de que el campo no sea nulo
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres") // Validación de longitud
    @Column(nullable = false, unique = true, name = "nombre", length = 10, insertable = true)
    // Configura la columna como no nula y única
    private String nombre;

    @Size(max = 500, message = "La descripción no puede exceder los 500 caracteres") // Validación de longitud
    private String descripcion;

    @NotNull(message = "El código no puede ser nulo") // Validación de que el campo no sea nulo
    @Size(min = 3, max = 10, message = "El código debe tener entre 3 y 10 caracteres") // Validación de longitud
    @Column(nullable = false, unique = true) // Configura la columna como no nula y única
    private String codigo;
    @NotNull(message = "La fecha de creacion no puede ser nulo") // Validación de que el campo no sea nulo

    @Column(nullable = false)
    private Date fechaCreacion;
    @Column(nullable = false)
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
