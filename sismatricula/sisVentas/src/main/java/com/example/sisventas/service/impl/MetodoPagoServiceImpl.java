package com.example.sisventas.service.impl;


import com.example.sisventas.dto.MetodoPagoDTO;
import com.example.sisventas.entity.MetodoPagoEntity;
import com.example.sisventas.mapper.MetodoPagoMapper;
import com.example.sisventas.repository.MetodoPagoRepository;
import com.example.sisventas.service.MetodoPagoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MetodoPagoServiceImpl implements MetodoPagoService {

    private final MetodoPagoRepository repository;
    private final MetodoPagoMapper mapper;

    @Override
    public MetodoPagoDTO crear(MetodoPagoDTO dto) {
        MetodoPagoEntity entity = mapper.toEntity(dto);
        return mapper.toDto(repository.save(entity));
    }

    @Override
    public List<MetodoPagoDTO> listar() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public MetodoPagoDTO buscarPorId(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new RuntimeException("Método de pago no encontrado"));
    }

    @Override
    public MetodoPagoDTO actualizar(Long id, MetodoPagoDTO dto) {
        MetodoPagoEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Método de pago no encontrado"));
        entity.setNombre(dto.getNombre());
        entity.setDescripcion(dto.getDescripcion());
        return mapper.toDto(repository.save(entity));
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}
