package com.example.sisventas.service;


import com.example.sisventas.dto.DetallePagoDTO;

import java.util.List;

public interface DetallePagoService {
    DetallePagoDTO crear(DetallePagoDTO dto);
    List<DetallePagoDTO> listar();
    DetallePagoDTO buscarPorId(Long id);
    DetallePagoDTO actualizar(Long id, DetallePagoDTO dto);
    void eliminar(Long id);
}
