package com.example.sisventas.service.impl;


import com.example.sisventas.dto.DetallePagoDTO;
import com.example.sisventas.entity.DetallePagoEntity;
import com.example.sisventas.mapper.DetallePagoMapper;
import com.example.sisventas.repository.DetallePagoRepository;
import com.example.sisventas.service.DetallePagoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DetallePagoServiceImpl implements DetallePagoService {

    private final DetallePagoRepository repository;
    private final DetallePagoMapper mapper;

    @Override
    public DetallePagoDTO crear(DetallePagoDTO dto) {
        DetallePagoEntity entity = mapper.toEntity(dto);
        return mapper.toDto(repository.save(entity));
    }

    @Override
    public List<DetallePagoDTO> listar() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public DetallePagoDTO buscarPorId(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new RuntimeException("Detalle de pago no encontrado"));
    }

    @Override
    public DetallePagoDTO actualizar(Long id, DetallePagoDTO dto) {
        DetallePagoEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Detalle de pago no encontrado"));
        entity.setMonto(dto.getMonto());
        entity.setObservacion(dto.getObservacion());
        return mapper.toDto(repository.save(entity));
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}
