package pe.edu.upeu.sysasistencia.servicio;

import pe.edu.upeu.sysasistencia.modelo.Facultad;

import java.util.List;

public interface FacultadService {

    public List<Facultad> obtenerFacultades();
    public Facultad obtenerFacultad(Long id);
    public void guardarFacultad(Facultad facultad);
    public void eliminarFacultad(Long id);
    public void actualizarFacultad(Facultad facultad, Long id);


}
