package pe.edu.upeu.sisventas.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.sisventas.dto.CategoriaDTO;
import pe.edu.upeu.sisventas.service.CategoriaService;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> listar() {
        return new ResponseEntity<>(categoriaService.listar(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CategoriaDTO> guardar(@Valid @RequestBody CategoriaDTO categoriaDTO) {
        return new ResponseEntity<>(categoriaService.guardar(categoriaDTO), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> buscar(@PathVariable Long id) {
        return new ResponseEntity<>(categoriaService.buscar(id), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<CategoriaDTO> editar(@Valid @RequestBody CategoriaDTO categoriaDTO) {

        return new ResponseEntity<>(categoriaService.editar(categoriaDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id) {
        categoriaService.borrar(id);
        return new ResponseEntity<>("Eliminado", HttpStatus.OK);
    }

}
