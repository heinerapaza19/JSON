package pe.edu.upeu.sysasistencia.servicio.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upeu.sysasistencia.modelo.Rol;
import pe.edu.upeu.sysasistencia.repositorio.RolRepository;
import pe.edu.upeu.sysasistencia.servicio.RolService;

import java.util.List;

@Service
public class RolServiceImp implements RolService {

    @Autowired
    RolRepository rolRepository;

    @Override
    public List<Rol> obtenerRoles() {
        return rolRepository.findAll();
    }


    @Override
    public Rol obtenerRol(Long id) {
        return rolRepository.findById(id).orElse(new Rol());
    }

    @Override
    public void guardarRol(Rol rol) {
        rolRepository.save(rol);
    }

    @Override
    public void eliminarRol(Long id) {
        rolRepository.deleteById(id);
    }

    @Override
    public void actualizarRol(Rol rol, Long id) {
        rolRepository.save(rol);
    }
}
