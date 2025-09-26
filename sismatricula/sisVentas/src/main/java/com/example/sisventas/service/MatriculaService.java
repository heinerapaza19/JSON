package com.example.sisventas.service;

import com.example.sisventas.dto.EstudianteDTO;
import com.example.sisventas.dto.MatriculaDTO;

import java.util.List;

public interface MatriculaService {
    List<MatriculaDTO> listar();

    MatriculaDTO guardarMatricula(MatriculaDTO matriculaDTO);
}
