package com.gestion.clinica.dto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

@Data
public class PacienteDTO {

    private Long id;

    private String nombre;

    private String apellido;

    private String dni;

    private String telefono;

    private LocalDateTime fechaNacimiento;

    private List<CitaDTO> citas;

    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaModificacion;

}
