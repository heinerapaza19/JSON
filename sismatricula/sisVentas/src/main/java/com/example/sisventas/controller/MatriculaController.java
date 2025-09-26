package com.example.sisventas.controller;

import com.example.sisventas.dto.MatriculaDTO;
import com.example.sisventas.service.MatriculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/matriculas")
public class MatriculaController {
    @Autowired
    private MatriculaService matriculaService;

    @GetMapping
    public ResponseEntity<List<MatriculaDTO>> getMatriculas() {
        return new ResponseEntity<>(matriculaService.listar(), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<MatriculaDTO> guardarMatricula(@RequestBody MatriculaDTO matriculaDTO) {
        return new ResponseEntity<>(matriculaService.guardarMatricula(matriculaDTO), HttpStatus.OK);
    }
}
