package com.example.sisventas.service;

import com.example.sisventas.dto.EstudianteDTO;

import java.util.List;
public interface EstudianteService {
    List<EstudianteDTO> listar();

    EstudianteDTO buscarPorId(Long id);

    EstudianteDTO guardar(EstudianteDTO estudianteDTO);

    EstudianteDTO editar(EstudianteDTO estudianteDTO);

    void borrar(Long id);
}
