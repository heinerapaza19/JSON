package com.gestion.clinica.dto;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class CitaDTO {

    private Long id;

    private LocalDateTime fechaHora;

    private String motivoConsulta;

    private String diagnostico;

    private Long pacienteId;
    private String pacienteNombre; // Asume un campo representativo

    private Long medicoId;
    private String medicoNombre; // Asume un campo representativo

    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaModificacion;

}
