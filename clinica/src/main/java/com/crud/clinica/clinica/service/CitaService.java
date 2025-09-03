package com.crud.clinica.clinica.service;


import com.crud.clinica.clinica.model.Cita;
import com.crud.clinica.clinica.repository.CitaRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class CitaService {


    private final CitaRepository citaRepository;


    public CitaService(CitaRepository citaRepository) {
        this.citaRepository = citaRepository;
    }


    public List<Cita> listarCitas() {
        return citaRepository.findAll();
    }


    public Optional<Cita> obtenerCitaPorId(Long id) {
        return citaRepository.findById(id);
    }


    public Cita guardarCita(Cita cita) {
        return citaRepository.save(cita);
    }


    public void eliminarCita(Long id) {
        citaRepository.deleteById(id);
    }
}
