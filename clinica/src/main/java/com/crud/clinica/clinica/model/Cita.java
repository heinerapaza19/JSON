package com.crud.clinica.clinica.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@Table(name = "citas")
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)


    private Long id;
    private String motivo;
    private LocalDate fecha;


    // 🔹 Relaciaón con Paciente
    @ManyToOne
    @JoinColumn(name = "medico_id")
    @JsonIgnoreProperties("citas") // Evita serializar las citas dentro del médico
    private Medico medico;

    @ManyToOne
    @JoinColumn(name = "paciente_id")
    @JsonIgnoreProperties("citas") // Evita serializar las citas dentro del paciente
    private Paciente paciente;



    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }


    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }


    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }
}
