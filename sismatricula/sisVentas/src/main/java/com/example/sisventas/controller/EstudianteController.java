package com.example.sisventas.controller;

import com.example.sisventas.dto.EstudianteDTO;
import com.example.sisventas.service.EstudianteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estudiantes")
public class EstudianteController {
    @Autowired
    private EstudianteService estudianteService;

    @GetMapping
    public ResponseEntity<List<EstudianteDTO>> listar() {
        return new ResponseEntity<>(estudianteService.listar(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstudianteDTO> buscarPorId(@PathVariable Long id) {
        return new ResponseEntity<>(estudianteService.buscarPorId(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EstudianteDTO> create(@Valid @RequestBody EstudianteDTO estudianteDTO) {
        return new ResponseEntity<>(estudianteService.guardar(estudianteDTO), HttpStatus.CREATED);

    }

    @PutMapping()
    public ResponseEntity<EstudianteDTO> editar(@Valid @RequestBody EstudianteDTO estudianteDTO) {
        return new ResponseEntity<>(estudianteService.editar(estudianteDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        estudianteService.borrar(id);
        return new ResponseEntity<>("eliminado", HttpStatus.OK);
    }
}
