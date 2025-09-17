package pe.edu.upeu.sisventas.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upeu.sisventas.dto.VentaDTO;
import pe.edu.upeu.sisventas.dto.VentaDetalleDTO;
import pe.edu.upeu.sisventas.entity.ProductoEntity;
import pe.edu.upeu.sisventas.entity.VentaDetalleEntity;
import pe.edu.upeu.sisventas.entity.VentaEntity;
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
    private VentaMapper ventaMapper;
    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public List<VentaDTO> listar() {
        List<VentaEntity> ventaEntities = ventaRepository.findAll();
        List<VentaDTO> ventaDTOS = new ArrayList<>();
        for (VentaEntity venta : ventaEntities) {
            VentaDTO ventaDTO = ventaMapper.ventaToVentaDTO(venta);
            ventaDTOS.add(ventaDTO);
        }
        return ventaDTOS;
    }

    @Transactional
    @Override
    public VentaDTO guardar(VentaDTO ventaDTO) {
        if (ventaDTO.getDetalles() == null || ventaDTO.getDetalles().isEmpty()) {
            throw new IllegalArgumentException("La venta debe tener al menos un detalle");
        }

        // Crear la venta principal
        VentaEntity venta = new VentaEntity();
        venta.setFecha(ventaDTO.getFecha() != null ? ventaDTO.getFecha() : LocalDateTime.now());
        venta.setObservaciones(ventaDTO.getObservaciones());
        venta.setEstado(ventaDTO.getEstado() != null ? ventaDTO.getEstado() : "COMPLETADA");
        venta.setSerie(ventaDTO.getSerie());

        BigDecimal totalCalculado = BigDecimal.ZERO;

        // Procesar cada detalle
        for (VentaDetalleDTO detalleDTO : ventaDTO.getDetalles()) {

            System.out.println("=================================");
            System.out.println(detalleDTO.toString());
            // Verificar que el producto existe y tiene stock suficiente
            ProductoEntity producto = productoRepository.findById(detalleDTO.getProductoId())
                    .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado: " + detalleDTO.getProductoId()));

            // Verificar stock disponible
            if (producto.getStock() < detalleDTO.getCantidad()) {
                throw new IllegalArgumentException("Stock insuficiente para el producto: " + producto.getNombre() +
                        ". Stock disponible: " + producto.getStock() + ", solicitado: " + detalleDTO.getCantidad());
            }

            // Crear el detalle
            VentaDetalleEntity detalle = new VentaDetalleEntity();
            detalle.setCantidad(detalleDTO.getCantidad());
            detalle.setPrecioUnitario(detalleDTO.getPrecioUnitario() != null ?
                    detalleDTO.getPrecioUnitario() : producto.getPrecio());
            detalle.setProducto(producto);
            detalle.calcularSubtotal();

            // Agregar a la venta
            venta.addDetalle(detalle);

            // Actualizar stock del producto
            producto.setStock(producto.getStock() - detalleDTO.getCantidad());
            productoRepository.save(producto);

            // Acumular total
            totalCalculado = totalCalculado.add(detalle.getSubtotal());
        }

        // Establecer total calculado o usar el proporcionado
        venta.setTotal(ventaDTO.getTotal() != null ? ventaDTO.getTotal() : totalCalculado);
        List<VentaEntity> ventaEntities = ventaRepository.findAll();
        venta.setNumeroFactura(ventaEntities.size() + 1);
        // Guardar la venta (cascade guarda los detalles)
        VentaEntity ventaGuardada = ventaRepository.save(venta);

        return ventaMapper.ventaToVentaDTO(ventaGuardada);
    }
    @Override
    public VentaDTO actualizarVenta(Long id, VentaDTO ventaDTO) {
        VentaEntity venta = ventaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Venta no encontrada"));

        String estadoAnterior = venta.getEstado();
        venta.setEstado(ventaDTO.getEstado());
        venta.setFecha(ventaDTO.getFecha() != null ? ventaDTO.getFecha() : LocalDateTime.now());
        venta.setObservaciones(ventaDTO.getObservaciones());
        venta.setEstado(ventaDTO.getEstado() != null ? ventaDTO.getEstado() : "COMPLETADA");
        venta.setSerie(ventaDTO.getSerie());

        BigDecimal totalCalculado = BigDecimal.ZERO;

        // Procesar cada detalle
        for (VentaDetalleDTO detalleDTO : ventaDTO.getDetalles()) {

            System.out.println("=================================");
            System.out.println(detalleDTO.toString());
            // Verificar que el producto existe y tiene stock suficiente
            ProductoEntity producto = productoRepository.findById(detalleDTO.getProductoId())
                    .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado: " + detalleDTO.getProductoId()));

            // Verificar stock disponible
            if (producto.getStock() < detalleDTO.getCantidad()) {
                throw new IllegalArgumentException("Stock insuficiente para el producto: " + producto.getNombre() +
                        ". Stock disponible: " + producto.getStock() + ", solicitado: " + detalleDTO.getCantidad());
            }

            // Crear el detalle
            VentaDetalleEntity detalle = new VentaDetalleEntity();
            detalle.setCantidad(detalleDTO.getCantidad());
            detalle.setPrecioUnitario(detalleDTO.getPrecioUnitario() != null ?
                    detalleDTO.getPrecioUnitario() : producto.getPrecio());
            detalle.setProducto(producto);
            detalle.calcularSubtotal();

            // Agregar a la venta
            venta.addDetalle(detalle);

            // Actualizar stock del producto
            producto.setStock(producto.getStock() - detalleDTO.getCantidad());
            productoRepository.save(producto);

            // Acumular total
            totalCalculado = totalCalculado.add(detalle.getSubtotal());
        }

        // Establecer total calculado o usar el proporcionado
        venta.setTotal(ventaDTO.getTotal() != null ? ventaDTO.getTotal() : totalCalculado);
        List<VentaEntity> ventaEntities = ventaRepository.findAll();
        venta.setNumeroFactura(ventaEntities.size() + 1);
        // Guardar la venta (cascade guarda los detalles)
        VentaEntity ventaGuardada = ventaRepository.save(venta);

        return ventaMapper.ventaToVentaDTO(ventaGuardada);
    }
}
