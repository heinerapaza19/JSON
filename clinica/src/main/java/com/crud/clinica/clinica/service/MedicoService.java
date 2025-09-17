package com.crud.clinica.clinica.service;

import com.crud.clinica.clinica.exception.EmailYaExisteException;
import com.crud.clinica.clinica.exception.DniYaExisteException;
import com.crud.clinica.clinica.model.Medico;
import com.crud.clinica.clinica.repository.MedicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicoService {

    private final MedicoRepository medicoRepository;

    public MedicoService(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    // Guardar un médico validando email y DNI duplicados
    public Medico guardar(Medico medico) {

        // Validar email duplicado
        if (medicoRepository.findByEmail(medico.getEmail()).isPresent()) {
            throw new EmailYaExisteException(
                    "Ya existe un médico con ese correo: " + medico.getEmail());
        }

        // Validar DNI duplicado
        if (medicoRepository.findByDni(medico.getDni()).isPresent()) {
            throw new DniYaExisteException(
                    "Ya existe un médico con ese DNI: " + medico.getDni());
        }

        return medicoRepository.save(medico);
    }

    // Listar todos los médicos
    public List<Medico> listar() {
        return medicoRepository.findAll();
    }

    // Buscar médico por ID
    public Medico buscarPorId(Long id) {
        return medicoRepository.findById(id).orElse(null);
    }

    // Eliminar médico
    public void eliminar(Long id) {
        medicoRepository.deleteById(id);
    }
}
