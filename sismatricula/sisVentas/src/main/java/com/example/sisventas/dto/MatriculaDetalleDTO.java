package com.example.sisventas.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Data
@Getter
@Setter
public class MatriculaDetalleDTO {
    private Long id;
    private Long matriculaId;
    private Long cursoId;
    private String cursoNombre;
    private String cursoCodigo;

}
