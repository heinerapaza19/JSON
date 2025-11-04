package com.example.sisventas.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "curso")
public class CursoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El nombre del curso no puede ser nulo")  // Validación de que el campo no sea nulo
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres") // Validación de longitud
    @Column(nullable = false, unique = false, name = "nombre")
    // Configura la columna como no nula y no  única
    private String nombre;

    @NotNull(message = "El código no puede ser nulo") // Validación de que el campo no sea nulo
    @Size(min = 3, max = 10, message = "El código debe tener entre 3 y 10 caracteres") // Validación de longitud
    @Column(nullable = false, unique = true) // Configura la columna como no nula y única
    private String codigo;

    private Integer cupos;

    @Column(nullable = false)
    private Date fechaCreacion;

    @Column(nullable = false)
    private Date fechaModificacion;

    @PrePersist
    public void prePersist() {
        this.fechaCreacion = new Date();
        this.fechaModificacion = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        this.fechaModificacion = new Date();
    }
}
