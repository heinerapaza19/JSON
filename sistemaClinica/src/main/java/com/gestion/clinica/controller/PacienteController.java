package com.gestion.clinica.controller;

import com.gestion.clinica.dto.PacienteDTO;
import com.gestion.clinica.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;
@RestController
@RequestMapping("/pacientes")
public class PacienteController{
    @Autowired private PacienteService pacienteService;
    @GetMapping
    public ResponseEntity<List<PacienteDTO>>getAll(){return new ResponseEntity<>(pacienteService.findAll(),HttpStatus.OK);}
    @GetMapping("/{id}")
    public ResponseEntity<PacienteDTO>getById(@PathVariable Long id){return new ResponseEntity<>(pacienteService.findById(id),HttpStatus.OK);}
    @PostMapping
    public ResponseEntity<PacienteDTO>create(@Valid @RequestBody PacienteDTO d){return new ResponseEntity<>(pacienteService.save(d),HttpStatus.CREATED);}
    @PutMapping("/{id}")
    public ResponseEntity<PacienteDTO>update(@PathVariable Long id,@Valid @RequestBody PacienteDTO d){return new ResponseEntity<>(pacienteService.update(id,d),HttpStatus.OK);}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void>delete(@PathVariable Long id){pacienteService.delete(id);return new ResponseEntity<>(HttpStatus.NO_CONTENT);}
}
