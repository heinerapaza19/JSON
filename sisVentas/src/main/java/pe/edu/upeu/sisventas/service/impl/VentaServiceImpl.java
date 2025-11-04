package pe.edu.upeu.sisventas.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upeu.sisventas.dto.VentaDTO;
import pe.edu.upeu.sisventas.dto.VentaDetalleDTO;
import pe.edu.upeu.sisventas.dto.PagoDTO;
import pe.edu.upeu.sisventas.entity.ProductoEntity;
import pe.edu.upeu.sisventas.entity.VentaDetalleEntity;
import pe.edu.upeu.sisventas.entity.VentaEntity;
import pe.edu.upeu.sisventas.entity.PagoEntity;
import pe.edu.upeu.sisventas.mapper.VentaMapper;
import pe.edu.upeu.sisventas.repository.ProductoRepository;
import pe.edu.upeu.sisventas.repository.VentaRepository;
import pe.edu.upeu.sisventas.service.VentaService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class VentaServiceImpl implements VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private VentaMapper ventaMapper;

    @Override
    public List<VentaDTO> listar() {
        List<VentaEntity> ventas = ventaRepository.findAll();
        List<VentaDTO> dtos = new ArrayList<>();
        for (VentaEntity v : ventas) {
            dtos.add(ventaMapper.ventaToVentaDTO(v));
        }
        return dtos;
    }

    @Transactional
    @Override
    public VentaDTO guardar(VentaDTO ventaDTO) {
        if (ventaDTO.getDetalles() == null || ventaDTO.getDetalles().isEmpty()) {
            throw new IllegalArgumentException("La venta debe tener al menos un detalle");
        }

        VentaEntity venta = new VentaEntity();
        venta.setFecha(ventaDTO.getFecha() != null ? ventaDTO.getFecha() : LocalDateTime.now());
        venta.setObservaciones(ventaDTO.getObservaciones());
        venta.setEstado(ventaDTO.getEstado() != null ? ventaDTO.getEstado() : "COMPLETADA");
        venta.setSerie(ventaDTO.getSerie());

        BigDecimal totalCalculado = BigDecimal.ZERO;

        // Mapear detalles
        for (VentaDetalleDTO detalleDTO : ventaDTO.getDetalles()) {
            ProductoEntity producto = productoRepository.findById(detalleDTO.getProductoId())
                    .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado: " + detalleDTO.getProductoId()));

            if (producto.getStock() < detalleDTO.getCantidad()) {
                throw new IllegalArgumentException("Stock insuficiente para el producto: " + producto.getNombre());
            }

            VentaDetalleEntity detalle = new VentaDetalleEntity();
            detalle.setCantidad(detalleDTO.getCantidad());
            detalle.setPrecioUnitario(detalleDTO.getPrecioUnitario() != null ? detalleDTO.getPrecioUnitario() : producto.getPrecio());
            detalle.setProducto(producto);
            detalle.calcularSubtotal();

            venta.addDetalle(detalle);

            producto.setStock(producto.getStock() - detalleDTO.getCantidad());
            productoRepository.save(producto);

            totalCalculado = totalCalculado.add(detalle.getSubtotal());
        }

        venta.setTotal(ventaDTO.getTotal() != null ? ventaDTO.getTotal() : totalCalculado);
        venta.setNumeroFactura(String.valueOf(ventaRepository.findAll().size() + 1));

        // Mapear pagos y asignar venta
        if (ventaDTO.getPagos() != null && !ventaDTO.getPagos().isEmpty()) {
            for (PagoDTO pagoDTO : ventaDTO.getPagos()) {
                PagoEntity pago = ventaMapper.pagoDTOToEntity(pagoDTO);
                pago.setVenta(venta);
                venta.getPagos().add(pago);
            }
        }

        VentaEntity guardada = ventaRepository.save(venta);
        return ventaMapper.ventaToVentaDTO(guardada);
    }

    @Transactional
    @Override
    public VentaDTO actualizarVenta(Long id, VentaDTO ventaDTO) {
        VentaEntity venta = ventaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Venta no encontrada"));

        venta.setFecha(ventaDTO.getFecha() != null ? ventaDTO.getFecha() : LocalDateTime.now());
        venta.setObservaciones(ventaDTO.getObservaciones());
        venta.setEstado(ventaDTO.getEstado() != null ? ventaDTO.getEstado() : "COMPLETADA");
        venta.setSerie(ventaDTO.getSerie());

        BigDecimal totalCalculado = BigDecimal.ZERO;

        // Limpiar detalles antiguos
        venta.getDetalles().clear();
        for (VentaDetalleDTO detalleDTO : ventaDTO.getDetalles()) {
            ProductoEntity producto = productoRepository.findById(detalleDTO.getProductoId())
                    .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado: " + detalleDTO.getProductoId()));

            VentaDetalleEntity detalle = new VentaDetalleEntity();
            detalle.setCantidad(detalleDTO.getCantidad());
            detalle.setPrecioUnitario(detalleDTO.getPrecioUnitario() != null ? detalleDTO.getPrecioUnitario() : producto.getPrecio());
            detalle.setProducto(producto);
            detalle.calcularSubtotal();

            venta.addDetalle(detalle);
            totalCalculado = totalCalculado.add(detalle.getSubtotal());
        }

        venta.setTotal(ventaDTO.getTotal() != null ? ventaDTO.getTotal() : totalCalculado);
        venta.setNumeroFactura(String.valueOf(ventaRepository.findAll().size() + 1));

        // Actualizar pagos
        venta.getPagos().clear();
        if (ventaDTO.getPagos() != null && !ventaDTO.getPagos().isEmpty()) {
            for (PagoDTO pagoDTO : ventaDTO.getPagos()) {
                PagoEntity pago = ventaMapper.pagoDTOToEntity(pagoDTO);
                pago.setVenta(venta);
                venta.getPagos().add(pago);
            }
        }

        VentaEntity guardada = ventaRepository.save(venta);
        return ventaMapper.ventaToVentaDTO(guardada);
    }

    @Override
    public VentaDTO buscarPorIdDTO(Long id) {
        VentaEntity venta = ventaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Venta no encontrada con ID: " + id));
        return ventaMapper.ventaToVentaDTO(venta);
    }
}
