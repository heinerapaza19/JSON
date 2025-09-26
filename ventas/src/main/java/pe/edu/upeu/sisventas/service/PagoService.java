
package pe.edu.upeu.sisventas.service;

import pe.edu.upeu.sisventas.dto.PagoDTO;

import java.util.List;

public interface PagoService {

    List<PagoDTO> listar();

    PagoDTO guardar(PagoDTO pagoDTO);

    PagoDTO actualizar(Long id, PagoDTO pagoDTO);

    void eliminar(Long id);

    PagoDTO buscarPorId(Long id);
}
