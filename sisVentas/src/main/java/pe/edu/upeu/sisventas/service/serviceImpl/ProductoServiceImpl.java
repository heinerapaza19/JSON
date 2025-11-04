package pe.edu.upeu.sisventas.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upeu.sisventas.dto.ProductoDTO;
import pe.edu.upeu.sisventas.entity.ProductoEntity;
import pe.edu.upeu.sisventas.mapper.ProductoMapper;
import pe.edu.upeu.sisventas.repository.ProductoRepository;
import pe.edu.upeu.sisventas.service.ProductoService;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {
    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private ProductoMapper productoMapper;

    @Override
    public List<ProductoDTO> listar() {
        List<ProductoEntity> productoEntities = productoRepository.findAll();
        List<ProductoDTO> productoDTOS = new ArrayList<>();
        for (ProductoEntity productoEntity : productoEntities) {
            ProductoDTO productoDTO = productoMapper.productoToProductoDTO(productoEntity);
            productoDTOS.add(productoDTO);
        }
        return productoDTOS;
    }

    @Override
    public ProductoDTO productoPorId(Long id) {
        return productoMapper.productoToProductoDTO(productoRepository.findById(id).get());
    }

    @Override
    public ProductoDTO guardarProducto(ProductoDTO productoDTO) {
        return productoMapper.productoToProductoDTO(productoRepository.save(productoMapper.productoDTOToProducto(productoDTO)));
    }

    @Override
    public ProductoDTO editarProducto(ProductoDTO productoDTO) {
        return productoMapper.productoToProductoDTO(productoRepository.save(productoMapper.productoDTOToProducto(productoDTO)));
    }

    @Override
    public void borrarProducto(Long id) {
        productoRepository.deleteById(id);
    }


}