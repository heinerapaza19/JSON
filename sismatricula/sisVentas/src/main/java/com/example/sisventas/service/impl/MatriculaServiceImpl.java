package com.example.sisventas.service.impl;

import com.example.sisventas.dto.MatriculaDTO;
import com.example.sisventas.dto.MatriculaDetalleDTO;
import com.example.sisventas.entity.CursoEntity;
import com.example.sisventas.entity.EstudianteEntity;
import com.example.sisventas.entity.MatriculaDetalleEntity;
import com.example.sisventas.entity.MatriculaEntity;
import com.example.sisventas.mapper.MatriculaMapper;
import com.example.sisventas.repository.CursoRepository;
import com.example.sisventas.repository.EstudianteRepository;
import com.example.sisventas.repository.MatriculaRepository;
import com.example.sisventas.service.MatriculaService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MatriculaServiceImpl implements MatriculaService {
    @Autowired
    private MatriculaRepository matriculaRepository;
    @Autowired
    private MatriculaMapper matriculaMapper;
    @Autowired
    private CursoRepository cursoRepository;
    @Autowired
    private EstudianteRepository estudianteRepository;

    @Override
    public List<MatriculaDTO> listar() {
        List<MatriculaEntity> matriculas = matriculaRepository.findAll();
        List<MatriculaDTO> dtos = new ArrayList<>();
        for (MatriculaEntity matricula : matriculas) {
            MatriculaDTO matriculaDTO = matriculaMapper.matriculaToMatriculaDTO(matricula);
            dtos.add(matriculaDTO);
        }
        return dtos;
    }

    @Transactional
    @Override
    public MatriculaDTO guardarMatricula(MatriculaDTO matriculaDTO) {
        //MatriculaEntity matriculaEntity = matriculaMapper.matriculaDTOToMatriculaEntity(matriculaDTO);
        estudianteRepository.findById(matriculaDTO.getEstudianteId()).orElseThrow(()
                -> new EntityNotFoundException("Estudiante no encontrado"));

        if (matriculaDTO.getDetalles() == null || matriculaDTO.getDetalles().isEmpty()) {
            throw new IllegalArgumentException("La matricula debe tener al menos un detalle");

        }
        MatriculaEntity matriculaEntity = new MatriculaEntity();
        matriculaEntity.setEstado(matriculaDTO.getEstado());

        matriculaEntity.setNumero(matriculaRepository.findMaxNumero() == null ? 1 :
                matriculaRepository.findMaxNumero() + 1);
        matriculaEntity.setObservaciones(matriculaDTO.getObservaciones());
        EstudianteEntity estudianteEntity = new EstudianteEntity();
        estudianteEntity.setId(matriculaDTO.getEstudianteId());
        matriculaEntity.setEstudiante(estudianteEntity);
        for (MatriculaDetalleDTO matriculaDetalleDTO : matriculaDTO.getDetalles()) {
            MatriculaDetalleEntity matriculaDetalleEntity = new MatriculaDetalleEntity();
            CursoEntity cursoEntity = cursoRepository.findById(matriculaDetalleDTO.getCursoId()).orElseThrow(()
                    -> new EntityNotFoundException("La curso no existe"));
            if (cursoEntity.getCupos() == null || cursoEntity.getCupos() == 0) {
                throw new IllegalArgumentException("En el curso: " + cursoEntity.getNombre() + " no hay cupos");
            }
            cursoEntity.setCupos(cursoEntity.getCupos() - 1);
            matriculaDetalleEntity.setCurso(cursoEntity);

            matriculaDetalleEntity.setMatricula(matriculaEntity);
            matriculaEntity.addDetalle(matriculaDetalleEntity);
            cursoRepository.save(cursoEntity);
        }
        return matriculaMapper.matriculaToMatriculaDTO(matriculaRepository.save(matriculaEntity));
    }
}
