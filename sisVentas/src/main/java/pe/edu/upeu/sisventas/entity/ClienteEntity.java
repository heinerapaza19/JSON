package pe.edu.upeu.sisventas.entity;

import jakarta.persistence.*;
import pe.edu.upeu.sisventas.dto.VentaDTO;

@Entity
public class ClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombres;
    private String apellidos;
    private String dni;

    @ManyToOne
    @JoinColumn(name = "venta_id") // FK hacia Venta
    private VentaEntity venta; // venta ya existe en entity

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }

    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }

    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }

    public VentaEntity getVenta() { return venta; }
    public void setVenta(VentaEntity venta) { this.venta = venta; }
}
