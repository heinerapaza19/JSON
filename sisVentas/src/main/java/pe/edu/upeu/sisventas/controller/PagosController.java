package pe.edu.upeu.sisventas.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.sisventas.dto.PagoDTO;
import pe.edu.upeu.sisventas.service.PagoService;

import java.util.List;

@RestController
@RequestMapping("/pagos")
public class PagosController {

    @Autowired
    private PagoService pagoService;

    @GetMapping
    public ResponseEntity<List<PagoDTO>> listar() {
        return new ResponseEntity<>(pagoService.listar(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagoDTO> buscar(@PathVariable Long id) {
        return new ResponseEntity<>(pagoService.buscarPorId(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PagoDTO> guardar(@Valid @RequestBody PagoDTO pagoDTO) {
        return new ResponseEntity<>(pagoService.guardar(pagoDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PagoDTO> actualizar(@PathVariable Long id, @Valid @RequestBody PagoDTO pagoDTO) {
        return new ResponseEntity<>(pagoService.actualizar(id, pagoDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        pagoService.eliminar(id);
        return new ResponseEntity<>("Pago eliminado correctamente", HttpStatus.OK);
    }
}