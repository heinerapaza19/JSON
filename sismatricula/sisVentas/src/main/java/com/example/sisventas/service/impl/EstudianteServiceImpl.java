package com.example.sisventas.service.impl;

import com.example.sisventas.dto.EstudianteDTO;
import com.example.sisventas.entity.EstudianteEntity;
import com.example.sisventas.mapper.EstudianteMapper;
import com.example.sisventas.repository.EstudianteRepository;
import com.example.sisventas.service.EstudianteService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstudianteServiceImpl implements EstudianteService {
    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private EstudianteMapper estudianteMapper;

    @Override
    public List<EstudianteDTO> listar() {
        return estudianteMapper.estudiantesEntitiesAEstudianteDTOs(estudianteRepository.findAll());
    }

    @Override
    public EstudianteDTO buscarPorId(Long id) {
        EstudianteEntity estudianteEntity = estudianteRepository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("Estudiante no encontrado"));

        return estudianteMapper.estudianteEntityAEstudianteDTO(estudianteEntity);
    }

    @Override
    public EstudianteDTO guardar(EstudianteDTO estudianteDTO) {


        EstudianteEntity estudianteEntity = estudianteMapper.estudianteDTOAEstudianteEntity(estudianteDTO);
        return estudianteMapper.estudianteEntityAEstudianteDTO(estudianteRepository.save(estudianteEntity));
    }

    @Override
    public EstudianteDTO editar(EstudianteDTO estudianteDTO) {
        Optional<EstudianteEntity> estudianteEntity =
                Optional.ofNullable(estudianteRepository.findById(estudianteDTO.getId()).orElseThrow(()
                        -> new EntityNotFoundException("Estudiante no encontrado")));


        EstudianteEntity estudianteActualizado = new EstudianteEntity();
        if (estudianteEntity.isPresent()) {
            estudianteEntity.get().setNombre(estudianteDTO.getNombre());
            estudianteEntity.get().setApellidoPaterno(estudianteDTO.getApellidoPaterno());
            estudianteEntity.get().setApellidoMaterno(estudianteDTO.getApellidoMaterno());
            estudianteEntity.get().setCodigo(estudianteDTO.getCodigo());
            estudianteActualizado = estudianteRepository.save(estudianteEntity.get());
        }
        return estudianteMapper.estudianteEntityAEstudianteDTO(estudianteActualizado);
    }

    @Override
    public void borrar(Long id) {
        estudianteRepository.deleteById(id);
    }
}
