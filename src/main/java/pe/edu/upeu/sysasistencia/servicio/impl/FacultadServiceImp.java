package pe.edu.upeu.sysasistencia.servicio.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upeu.sysasistencia.modelo.Facultad;
import pe.edu.upeu.sysasistencia.repositorio.FacultadRepository;
import pe.edu.upeu.sysasistencia.servicio.FacultadService;

import java.util.List;

@Service
public class FacultadServiceImp implements FacultadService {

    @Autowired
    FacultadRepository facultadRepository;

    @Override
    public List<Facultad> obtenerFacultades() {
        return facultadRepository.findAll();
    }


    @Override
    public Facultad obtenerFacultad(Long id) {
        return facultadRepository.findById(id).orElse(new Facultad());
    }
    @Override
    public void guardarFacultad(Facultad facultad) {
        facultadRepository.save(facultad);
    }
    @Override
    public void eliminarFacultad(Long id) {
        facultadRepository.deleteById(id);
    }
    @Override
    public void actualizarFacultad(Facultad facultad, Long id) {
        facultadRepository.save(facultad);
    }
    
}
