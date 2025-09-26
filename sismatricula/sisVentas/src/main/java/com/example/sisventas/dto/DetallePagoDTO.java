package com.example.sisventas.dto;

import lombok.Data;
import java.util.Date;

@Data
public class DetallePagoDTO {
    private Long id;
    private Double monto;
    private Date fechaPago;
    private String observacion;

    private Long matriculaId;   // Solo id para relacionar
    private Long metodoPagoId;  // Solo id para relacionar
    private String metodoPagoNombre; // 🔹 Nombre del método de pago
}
