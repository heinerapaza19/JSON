package com.example.sisventas.controller;

import com.example.sisventas.dto.CursoDTO;
import com.example.sisventas.service.CursoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursoController {
    @Autowired
    private CursoService cursoService;

    @GetMapping
    public ResponseEntity<List<CursoDTO>> listar() {
        return new ResponseEntity<>(cursoService.listar(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CursoDTO> buscarPorId(@PathVariable Long id) {
        return new ResponseEntity<>(cursoService.buscarPorId(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CursoDTO> create(@Valid @RequestBody CursoDTO cursoDTO) {
        return new ResponseEntity<>(cursoService.guardar(cursoDTO), HttpStatus.CREATED);

    }

    @PutMapping()
    public ResponseEntity<CursoDTO> editar(@Valid @RequestBody CursoDTO cursoDTO) {
        return new ResponseEntity<>(cursoService.editar(cursoDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        cursoService.borrar(id);
        return new ResponseEntity<>("eliminado", HttpStatus.OK);
    }
}
