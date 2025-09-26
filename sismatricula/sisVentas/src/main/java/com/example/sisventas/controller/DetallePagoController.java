package com.example.sisventas.controller;



import com.example.sisventas.dto.DetallePagoDTO;
import com.example.sisventas.service.DetallePagoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/detalles")
@RequiredArgsConstructor
public class DetallePagoController {

    private final DetallePagoService service;

    @PostMapping
    public ResponseEntity<DetallePagoDTO> crear(@RequestBody DetallePagoDTO dto) {
        return ResponseEntity.ok(service.crear(dto));
    }

    @GetMapping
    public ResponseEntity<List<DetallePagoDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetallePagoDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetallePagoDTO> actualizar(@PathVariable Long id, @RequestBody DetallePagoDTO dto) {
        return ResponseEntity.ok(service.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}

