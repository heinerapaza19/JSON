package com.example.sisventas.dto;

import jakarta.persistence.Column;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
public class MatriculaDTO {

    private Long id;
    private LocalDateTime fecha;
    private String observaciones;
    private String estado = "COMPLETADA"; // COMPLETADA, CANCELADA, PENDIENTE
    private String numero;

    @NotNull(message = "Estudiante no puede ser nula")
    private Long estudianteId;

    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String codigo;

    @Valid
    private List<MatriculaDetalleDTO> detalles = new ArrayList<>();

    // 🔹 Nueva lista de pagos asociados a la matrícula
    @Valid

    private List<DetallePagoDTO> pagos = new ArrayList<>();

}
