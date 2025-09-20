package com.gestion.clinica.controller;

import com.gestion.clinica.dto.CitaDTO;
import com.gestion.clinica.service.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/citas")
public class CitaController{
    @Autowired private CitaService citaService;
    @GetMapping
    public ResponseEntity<List<CitaDTO>>getAll(){return new ResponseEntity<>(citaService.findAll(),HttpStatus.OK);}
    @GetMapping("/{id}")
    public ResponseEntity<CitaDTO>getById(@PathVariable Long id){return new ResponseEntity<>(citaService.findById(id),HttpStatus.OK);}
    @PostMapping
    public ResponseEntity<CitaDTO>create(@Valid @RequestBody CitaDTO d){return new ResponseEntity<>(citaService.save(d),HttpStatus.CREATED);}
    @PutMapping("/{id}")
    public ResponseEntity<CitaDTO>update(@PathVariable Long id,@Valid @RequestBody CitaDTO d){return new ResponseEntity<>(citaService.update(id,d),HttpStatus.OK);}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void>delete(@PathVariable Long id){citaService.delete(id);return new ResponseEntity<>(HttpStatus.NO_CONTENT);}
}
