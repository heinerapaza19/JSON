package com.gestion.clinica.dto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

@Data
public class MedicoDTO {

    private Long id;

    private String nombre;

    private String apellido;

    private String especialidad;

    private String numeroColegiado;

    private List<CitaDTO> citas;

    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaModificacion;

}
