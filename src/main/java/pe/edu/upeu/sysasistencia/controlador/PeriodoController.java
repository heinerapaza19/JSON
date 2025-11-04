package pe.edu.upeu.sysasistencia.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.sysasistencia.modelo.Periodo;
import pe.edu.upeu.sysasistencia.servicio.PeriodoService;

import java.util.List;

@RestController
@RequestMapping("/periodo" )
public class PeriodoController {

    @Autowired
    private PeriodoService periodoService;
    @GetMapping("/list")
    public ResponseEntity<List<Periodo>> findAll(){
        List<Periodo> p=periodoService.obtenerPeriodos();
        return ResponseEntity.ok(p);
    }
    @GetMapping("/list/{id}")
    public ResponseEntity<Periodo> findAllByPeriodo(@PathVariable Long id){
        Periodo p=periodoService.obtenerPeriodo(id);
        return ResponseEntity.ok(p);
    }
    @PostMapping("/guardar")
    public void guardar(@RequestBody Periodo periodo) {
        periodoService.guardarPeriodo(periodo);
    }
    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable Long id){
        periodoService.eliminarPeriodo(id);

    }
}