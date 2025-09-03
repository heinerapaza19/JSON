package com.crud.clinica.clinica.model;

import jakarta.persistence.*;
import java.time.LocalDate;


@Entity
public class Cita {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String nombrePaciente;
    private String apellidoPaciente;
    private LocalDate fecha;
    private String motivo;


    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }


    public String getNombrePaciente() { return nombrePaciente; }
    public void setNombrePaciente(String nombrePaciente) { this.nombrePaciente = nombrePaciente; }


    public String getApellidoPaciente() { return apellidoPaciente; }
    public void setApellidoPaciente(String apellidoPaciente) { this.apellidoPaciente = apellidoPaciente; }


    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }


    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }
}
