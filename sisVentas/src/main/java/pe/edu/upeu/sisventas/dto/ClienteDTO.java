package pe.edu.upeu.sisventas.dto;

public class ClienteDTO {
    private String nombres;
    private String apellidos;
    private String dni;
    private Long ventaId;

    // Campos extra para mostrar datos de la venta
    private String fechaVenta;
    private Double totalVenta;

    // Getters y setters de todos los campos
    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }

    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }

    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }

    public Long getVentaId() { return ventaId; }
    public void setVentaId(Long ventaId) { this.ventaId = ventaId; }

    public String getFechaVenta() { return fechaVenta; }
    public void setFechaVenta(String fechaVenta) { this.fechaVenta = fechaVenta; }

    public Double getTotalVenta() { return totalVenta; }
    public void setTotalVenta(Double totalVenta) { this.totalVenta = totalVenta; }
}
