package com.crud.clinica.clinica.dtos;

import java.time.LocalDate;
import java.util.List;


public class PacienteDTO {
    private Long id;
    private String dni;
    private String nombre;
    private String apellido;
    private LocalDate fechaNacimiento;
    private String telefono;
    private List<CitaResumenDTO> citas;


    public PacienteDTO(Long id, String dni, String nombre, String apellido,
                       LocalDate fechaNacimiento, String telefono,
                       List<CitaResumenDTO> citas) {
        this.id = id;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.citas = citas;
    }


    // Getters y Setters
    public Long getId() { return id; }
    public String getDni() { return dni; }
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public String getTelefono() { return telefono; }
    public List<CitaResumenDTO> getCitas() { return citas; }
}
