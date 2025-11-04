package pe.edu.upeu.sysasistencia.controlador;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.sysasistencia.modelo.Facultad;
import pe.edu.upeu.sysasistencia.servicio.FacultadService;

import java.util.List;

@RestController
@RequestMapping("/facultad" )
public class FacultadController {

    @Autowired
    private FacultadService facultadService;

    @GetMapping("/list")
    public ResponseEntity<List<Facultad>> findAll(){
        List<Facultad> p=facultadService.obtenerFacultades();
        return ResponseEntity.ok(p);
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<Facultad> findAllByFacultad(@PathVariable Long id){
        Facultad p=facultadService.obtenerFacultad(id);
        return ResponseEntity.ok(p);
    }

    @PostMapping("/guardar")
    public void guardar(@RequestBody Facultad facultad) {
        facultadService.guardarFacultad(facultad);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable Long id){
        facultadService.eliminarFacultad(id);

    }
    
}
