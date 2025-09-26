package com.example.sisventas.service.impl;

import com.example.sisventas.dto.CursoDTO;
import com.example.sisventas.entity.CursoEntity;
import com.example.sisventas.mapper.CursoMapper;
import com.example.sisventas.repository.CursoRepository;
import com.example.sisventas.service.CursoService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoServiceImpl implements CursoService {
    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private CursoMapper cursoMapper;

    @Override
    public List<CursoDTO> listar() {
        return cursoMapper.cursosEntitiesACursoDTOs(cursoRepository.findAll());
    }

    @Override
    public CursoDTO buscarPorId(Long id) {
        CursoEntity cursoEntity = cursoRepository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("Estudiante no encontrado"));

        return cursoMapper.cursoEntityACursoDTO(cursoEntity);
    }

    @Override
    public CursoDTO guardar(CursoDTO cursoDTO) {


        CursoEntity cursoEntity = cursoMapper.CursoDTOACursoEntity(cursoDTO);
        return cursoMapper.cursoEntityACursoDTO(cursoRepository.save(cursoEntity));
    }

    @Override
    public CursoDTO editar(CursoDTO cursoDTO) {
        Optional<CursoEntity> cursoEntity =
                Optional.ofNullable(cursoRepository.findById(cursoDTO.getId()).orElseThrow(()
                        -> new EntityNotFoundException("Estudiante no encontrado")));


        CursoEntity estudianteActualizado = new CursoEntity();
        if (cursoEntity.isPresent()) {
            cursoEntity.get().setNombre(cursoDTO.getNombre());

            cursoEntity.get().setCodigo(cursoDTO.getCodigo());
            estudianteActualizado = cursoRepository.save(cursoEntity.get());
        }
        return cursoMapper.cursoEntityACursoDTO(estudianteActualizado);
    }

    @Override
    public void borrar(Long id) {
        cursoRepository.deleteById(id);
    }
}
