package com.crud.clinica.clinica.controller;


import com.crud.clinica.clinica.dtos.CitaResumenDTO;
import com.crud.clinica.clinica.dtos.PacienteDTO;
import com.crud.clinica.clinica.model.Cita;
import com.crud.clinica.clinica.model.Paciente;
import com.crud.clinica.clinica.service.CitaService;
import com.crud.clinica.clinica.service.PacienteService;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.stream.Collectors;


/**
 * Controlador REST para gestionar pacientes.
 * Expone endpoints CRUD (crear, leer, actualizar y eliminar).
 */
@RestController
@RequestMapping("/pacientes")
@CrossOrigin(origins = "http://localhost:4200")
public class PacienteController {


    private final PacienteService pacienteService;
    private final CitaService citaService;


    // Constructor con inyección de dependencias
    public PacienteController(PacienteService pacienteService, CitaService citaService) {
        this.pacienteService = pacienteService;
        this.citaService = citaService;
    }


    /** Obtener la lista de todos los pacientes. */
    @GetMapping
    public List<Paciente> listarPacientes() {
        return pacienteService.listarPacientes();
    }


    /** Obtener un paciente por su ID. */
    @GetMapping("/{id}")
    public Paciente obtenerPaciente(@PathVariable Long id) {
        return pacienteService.obtenerPacientePorId(id)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
    }


    /**
     * Obtener un paciente junto con sus citas (DTO).
     * Ejemplo: GET /pacientes/1/detalle
     */
    @GetMapping("/{id}/detalle")
    public PacienteDTO obtenerPacienteConCitas(@PathVariable Long id) {
        return pacienteService.obtenerPacientePorId(id)
                .map(p -> new PacienteDTO(
                        p.getId(),
                        p.getDni(),
                        p.getNombre(),
                        p.getApellido(),
                        p.getFechaNacimiento(),
                        p.getTelefono(),
                        p.getCitas().stream()
                                .map(c -> new CitaResumenDTO(c.getId(), c.getMotivo(), c.getFecha()))
                                .collect(Collectors.toList())
                ))
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
    }


    /** Crear un nuevo paciente. */
    @PostMapping
    public Paciente crearPaciente(@RequestBody Paciente paciente) {
        return pacienteService.guardarPaciente(paciente);
    }


    /** Crear una cita para un paciente existente. */
    @PostMapping("/{id}/citas")
    public Cita crearCitaParaPaciente(@PathVariable Long id, @RequestBody Cita cita) {
        Paciente paciente = pacienteService.obtenerPacientePorId(id)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));


        cita.setPaciente(paciente); // asociamos la cita al paciente
        return citaService.guardarCita(cita);
    }


    /** Actualizar un paciente completo. */
    @PutMapping("/{id}")
    public Paciente actualizarPaciente(@PathVariable Long id, @RequestBody Paciente paciente) {
        paciente.setId(id);
        return pacienteService.guardarPaciente(paciente);
    }


    /** Actualizar un paciente parcialmente. */
    @PatchMapping("/{id}")
    public Paciente actualizarParcialPaciente(@PathVariable Long id, @RequestBody Paciente request) {
        return pacienteService.obtenerPacientePorId(id)
                .map(pacienteExistente -> {
                    if (request.getNombre() != null) {
                        pacienteExistente.setNombre(request.getNombre());
                    }
                    if (request.getApellido() != null) {
                        pacienteExistente.setApellido(request.getApellido());
                    }
                    if (request.getDni() != null) {
                        pacienteExistente.setDni(request.getDni());
                    }
                    if (request.getFechaNacimiento() != null) {
                        pacienteExistente.setFechaNacimiento(request.getFechaNacimiento());
                    }
                    if (request.getTelefono() != null) {
                        pacienteExistente.setTelefono(request.getTelefono());
                    }
                    return pacienteService.guardarPaciente(pacienteExistente);
                })
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
    }


    /** Eliminar un paciente por su ID. */
    @DeleteMapping("/{id}")
    public void eliminarPaciente(@PathVariable Long id) {
        pacienteService.eliminarPaciente(id);
    }
}

