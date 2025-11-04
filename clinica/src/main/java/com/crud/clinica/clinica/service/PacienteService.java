package com.crud.clinica.clinica.service;

import com.crud.clinica.clinica.model.Paciente;
import com.crud.clinica.clinica.repository.PacienteRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;


@Service
public class PacienteService {


    private final PacienteRepository pacienteRepository;


    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }


    public List<Paciente> listarPacientes() {
        return pacienteRepository.findAll();
    }


    public Optional<Paciente> obtenerPacientePorId(Long id) {
        return pacienteRepository.findById(id);
    }


    public Paciente guardarPaciente(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }


    public void eliminarPaciente(Long id) {
        pacienteRepository.deleteById(id);
    }
}

