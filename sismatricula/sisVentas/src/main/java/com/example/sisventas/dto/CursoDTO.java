package com.example.sisventas.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Setter
@Getter
public class CursoDTO {
    private Long id;

    @NotNull(message = "El nombre del estudiante no puede ser nulo")  // Validación de que el campo no sea nulo
    @Size(min = 3, max = 3, message = "El nombre debe tener entre 3 y 100 caracteres") // Validación de longitud
    @Column(nullable = false, unique = false, name = "nombre")
    // Configura la columna como no nula y no  única
    private String nombre;

    private String codigo;

    private Date fechaCreacion;
    private Date fechaModificacion;

}
