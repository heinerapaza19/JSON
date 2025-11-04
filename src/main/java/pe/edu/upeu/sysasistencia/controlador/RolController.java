package pe.edu.upeu.sysasistencia.controlador;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.sysasistencia.modelo.Rol;
import pe.edu.upeu.sysasistencia.servicio.RolService;

import java.util.List;

@RestController
@RequestMapping("/rol" )
public class RolController {

    @Autowired
    private RolService rolService;
    @GetMapping("/list")
    public ResponseEntity<List<Rol>> findAll(){
        List<Rol> p=rolService.obtenerRoles();
        return ResponseEntity.ok(p);
    }
    @GetMapping("/list/{id}")
    public ResponseEntity<Rol> findAllByRol(@PathVariable Long id){
        Rol p=rolService.obtenerRol(id);
        return ResponseEntity.ok(p);
    }
    @PostMapping("/guardar")
    public void guardar(@RequestBody Rol rol) {
        rolService.guardarRol(rol);
    }
    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable Long id){
        rolService.eliminarRol(id);
    }
    
}
