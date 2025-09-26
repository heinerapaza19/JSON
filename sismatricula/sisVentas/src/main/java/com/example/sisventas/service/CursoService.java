package com.example.sisventas.service;

import com.example.sisventas.dto.CursoDTO;

import java.util.List;

public interface CursoService {
    List<CursoDTO> listar();

    CursoDTO buscarPorId(Long id);

    CursoDTO guardar(CursoDTO cursoDTO);

    CursoDTO editar(CursoDTO cursoDTO);

    void borrar(Long id);
}
