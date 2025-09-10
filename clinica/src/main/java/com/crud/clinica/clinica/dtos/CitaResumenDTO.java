package com.crud.clinica.clinica.dtos;

import java.time.LocalDate;

public class CitaResumenDTO {
    private Long id;
    private String motivo;
    private LocalDate fecha;


    public CitaResumenDTO(Long id, String motivo, LocalDate fecha) {
        this.id = id;
        this.motivo = motivo;
        this.fecha = fecha;
    }


    // Getters
    public Long getId() { return id; }
    public String getMotivo() { return motivo; }
    public LocalDate getFecha() { return fecha; }
}

