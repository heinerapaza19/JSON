package com.example.sisventas.service;

import com.example.sisventas.dto.MetodoPagoDTO;

import java.util.List;

public interface MetodoPagoService {
    MetodoPagoDTO crear(MetodoPagoDTO dto);
    List<MetodoPagoDTO> listar();
    MetodoPagoDTO buscarPorId(Long id);
    MetodoPagoDTO actualizar(Long id, MetodoPagoDTO dto);
    void eliminar(Long id);
}
