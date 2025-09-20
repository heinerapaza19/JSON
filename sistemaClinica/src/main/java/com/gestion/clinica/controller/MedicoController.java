package com.gestion.clinica.controller;

import com.gestion.clinica.dto.MedicoDTO;
import com.gestion.clinica.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController{
    @Autowired private MedicoService medicoService;
    @GetMapping
    public ResponseEntity<List<MedicoDTO>>getAll(){return new ResponseEntity<>(medicoService.findAll(),HttpStatus.OK);}
    @GetMapping("/{id}")
    public ResponseEntity<MedicoDTO>getById(@PathVariable Long id){return new ResponseEntity<>(medicoService.findById(id),HttpStatus.OK);}
    @PostMapping
    public ResponseEntity<MedicoDTO>create(@Valid @RequestBody MedicoDTO d){return new ResponseEntity<>(medicoService.save(d),HttpStatus.CREATED);}
    @PutMapping("/{id}")
    public ResponseEntity<MedicoDTO>update(@PathVariable Long id,@Valid @RequestBody MedicoDTO d){return new ResponseEntity<>(medicoService.update(id,d),HttpStatus.OK);}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void>delete(@PathVariable Long id){medicoService.delete(id);return new ResponseEntity<>(HttpStatus.NO_CONTENT);}
}
