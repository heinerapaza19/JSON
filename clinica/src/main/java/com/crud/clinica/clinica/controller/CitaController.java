package com.crud.clinica.clinica.controller;


import com.crud.clinica.clinica.model.Cita;
import com.crud.clinica.clinica.service.CitaService;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/citas")
public class CitaController {


    private final CitaService citaService;


    public CitaController(CitaService citaService) {
        this.citaService = citaService;
    }


    @GetMapping
    public List<Cita> listarCitas() {
        return citaService.listarCitas();
    }


    @GetMapping("/{id}")
    public Cita obtenerCita(@PathVariable Long id) {
        return citaService.obtenerCitaPorId(id)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada"));
    }


    @PostMapping
    public Cita crearCita(@RequestBody Cita cita) {
        return citaService.guardarCita(cita);
    }


    @PutMapping("/{id}")
    public Cita actualizarCita(@PathVariable Long id, @RequestBody Cita cita) {
        cita.setId(id);
        return citaService.guardarCita(cita);
    }


    @PatchMapping("/{id}")
    public Cita actualizarParcialCita(@PathVariable Long id, @RequestBody Cita request) {
        return citaService.obtenerCitaPorId(id)
                .map(citaExistente -> {
                    if (request.getNombrePaciente() != null) {
                        citaExistente.setNombrePaciente(request.getNombrePaciente());
                    }
                    if (request.getApellidoPaciente() != null) {
                        citaExistente.setApellidoPaciente(request.getApellidoPaciente());
                    }
                    if (request.getFecha() != null) {
                        citaExistente.setFecha(request.getFecha());
                    }
                    if (request.getMotivo() != null) {
                        citaExistente.setMotivo(request.getMotivo());
                    }
                    return citaService.guardarCita(citaExistente);
                })
                .orElseThrow(() -> new RuntimeException("Cita no encontrada"));
    }


    @DeleteMapping("/{id}")
    public void eliminarCita(@PathVariable Long id) {
        citaService.eliminarCita(id);
    }
}
