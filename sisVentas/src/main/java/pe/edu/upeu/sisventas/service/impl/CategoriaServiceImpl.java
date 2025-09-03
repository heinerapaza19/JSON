package pe.edu.upeu.sisventas.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upeu.sisventas.dto.CategoriaDTO;
import pe.edu.upeu.sisventas.entity.Categoria;
import pe.edu.upeu.sisventas.mapper.CategoriaMapper;
import pe.edu.upeu.sisventas.repository.CategoriaRepository;
import pe.edu.upeu.sisventas.service.CategoriaService;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoriaServiceImpl implements CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private CategoriaMapper categoriaMapper;

    @Override
    public List<CategoriaDTO> listar() {
        /*  List<Categoria> categorias = categoriaRepository.findAll();
        return categorias.stream()
                .map(categoriaMapper::categoriaToCategoriaDTO)
                .collect(Collectors.toList());
        * */
        List<Categoria> categorias = categoriaRepository.findAll();
        /*return categorias.stream()
                .map(categoriaMapper::categoriaEntityACategoriaDto)
                .collect(Collectors.toList());*/
        //return List.of();
        List<CategoriaDTO> categoriasDTOs = new ArrayList<>();
        for (Categoria categoriaEntity : categorias) {
            CategoriaDTO categoriaDTO = categoriaMapper.categoriaEntityACategoriaDto(categoriaEntity);
            categoriasDTOs.add(categoriaDTO);
        }
        return categoriasDTOs;
    }

    @Override
    @Transactional
    public CategoriaDTO guardar(CategoriaDTO categoriaDTO) {
        Categoria categoriaEntity = categoriaMapper.categoriaDTOACategoriaEntity(categoriaDTO);
        return categoriaMapper.categoriaEntityACategoriaDto(categoriaRepository.save(categoriaEntity));
    }

    @Override
    public CategoriaDTO buscar(Long id) {
        Categoria categoriaEntity = categoriaRepository.findById(id).orElseThrow(() -> new
                EntityNotFoundException("Categoria no encontrada"));
        return categoriaMapper.categoriaEntityACategoriaDto(categoriaEntity);

    }

    @Override
    @Transactional
    public CategoriaDTO editar(CategoriaDTO categoriaDTO) {
        Categoria categoriaEntity = categoriaRepository.findById(categoriaDTO.getId()).get();
        categoriaEntity.setNombre(categoriaDTO.getNombre());
        categoriaEntity.setDescripcion(categoriaDTO.getDescripcion());
        categoriaEntity.setCodigo(categoriaDTO.getCodigo());
        CategoriaDTO categoriaDTO1 = categoriaMapper.categoriaEntityACategoriaDto(categoriaRepository.save(categoriaEntity));
        return categoriaDTO1;
    }

    @Override
    public void borrar(Long id) {
        categoriaRepository.deleteById(id);
    }
}
