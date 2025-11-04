package com.crud.clinica.clinica.service;

import com.crud.clinica.clinica.model.Medico;
import com.crud.clinica.clinica.repository.MedicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicoService {

    private final MedicoRepository medicoRepository;

    public MedicoService(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    public List<Medico> listar() {
        return medicoRepository.findAll();
    }

    public Medico guardar(Medico medico) {
        return medicoRepository.save(medico);
    }

    public Medico buscarPorId(Long id) {
        return medicoRepository.findById(id).orElse(null);
    }

    public void eliminar(Long id) {
        medicoRepository.deleteById(id);
    }

    public Medico actualizar(Long id, Medico medicoDetalles) {
        Optional<Medico> optionalMedico = medicoRepository.findById(id);
        if (optionalMedico.isPresent()) {
            Medico medico = optionalMedico.get();
            medico.setNombre(medicoDetalles.getNombre());
            medico.setApellido(medicoDetalles.getApellido());
            medico.setEspecialidad(medicoDetalles.getEspecialidad());
            medico.setTelefono(medicoDetalles.getTelefono());
            medico.setEmail(medicoDetalles.getEmail());
            medico.setDni(medicoDetalles.getDni());
            return medicoRepository.save(medico);
        }
        return null;
    }
}
