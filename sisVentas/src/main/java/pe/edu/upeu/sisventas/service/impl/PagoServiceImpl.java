package pe.edu.upeu.sisventas.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upeu.sisventas.dto.PagoDTO;
import pe.edu.upeu.sisventas.entity.VentaEntity;
import pe.edu.upeu.sisventas.entity.PagoEntity;
import pe.edu.upeu.sisventas.mapper.PagoMapper;
import pe.edu.upeu.sisventas.repository.PagoRepository;
import pe.edu.upeu.sisventas.repository.VentaRepository;
import pe.edu.upeu.sisventas.service.PagoService;

import java.util.ArrayList;
import java.util.List;

@Service
public class PagoServiceImpl implements PagoService {

    @Autowired
    private PagoRepository pagoRepository;

    @Autowired
    private PagoMapper pagoMapper;

    @Autowired
    private VentaRepository ventaRepository;

    @Override
    public List<PagoDTO> listar() {
        List<PagoEntity> pagos = pagoRepository.findAll();
        List<PagoDTO> pagosDTO = new ArrayList<>();
        for (PagoEntity pago : pagos) {
            pagosDTO.add(pagoMapper.pagoToPagoDTO(pago));
        }
        return pagosDTO;
    }

    @Transactional
    @Override
    public PagoDTO guardar(PagoDTO pagoDTO) {
        VentaEntity venta = ventaRepository.findById(pagoDTO.getVentaId())
                .orElseThrow(() -> new EntityNotFoundException("Venta no encontrada con ID: " + pagoDTO.getVentaId()));

        PagoEntity pago = pagoMapper.pagoDTOToPago(pagoDTO);
        pago.setVenta(venta);

        PagoEntity pagoGuardado = pagoRepository.save(pago);
        return pagoMapper.pagoToPagoDTO(pagoGuardado);
    }

    @Transactional
    @Override
    public PagoDTO actualizar(Long id, PagoDTO pagoDTO) {
        PagoEntity pago = pagoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pago no encontrado con ID: " + id));

        VentaEntity venta = ventaRepository.findById(pagoDTO.getVentaId())
                .orElseThrow(() -> new EntityNotFoundException("Venta no encontrada con ID: " + pagoDTO.getVentaId()));

        pago.setMonto(pagoDTO.getMonto());
        pago.setMetodo(pagoDTO.getMetodo());
        pago.setFecha(pagoDTO.getFecha() != null ? pagoDTO.getFecha() : pago.getFecha());
        pago.setVenta(venta);

        PagoEntity pagoActualizado = pagoRepository.save(pago);
        return pagoMapper.pagoToPagoDTO(pagoActualizado);
    }

    @Override
    public void eliminar(Long id) {
        PagoEntity pago = pagoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pago no encontrado con ID: " + id));
        pagoRepository.delete(pago);
    }

    @Override
    public PagoDTO buscarPorId(Long id) {
        PagoEntity pago = pagoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pago no encontrado con ID: " + id));
        return pagoMapper.pagoToPagoDTO(pago);
    }
}
