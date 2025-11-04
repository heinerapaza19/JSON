package pe.edu.upeu.sisventas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.sisventas.dto.ProductoDTO;
import pe.edu.upeu.sisventas.service.ProductoService;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {
    @Autowired
    private ProductoService productoService;

    @GetMapping
    public ResponseEntity<List<ProductoDTO>> listar() {
        return new ResponseEntity<>(productoService.listar(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> findById(@PathVariable Long id) {
        return new ResponseEntity<>(productoService.productoPorId(id), HttpStatus.OK);

    }
    @PostMapping
    public ResponseEntity<ProductoDTO> create(@RequestBody ProductoDTO productoDTO) {
        return new ResponseEntity<>(productoService.guardarProducto(productoDTO), HttpStatus.CREATED);
    }

}
