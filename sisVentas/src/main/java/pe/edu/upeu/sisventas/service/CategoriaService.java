package pe.edu.upeu.sisventas.service;

import pe.edu.upeu.sisventas.dto.CategoriaDTO;

import java.util.List;

public interface CategoriaService {
    public List<CategoriaDTO> listar();
    public CategoriaDTO guardar(CategoriaDTO categoriaDTO);
    public CategoriaDTO buscar(Long id);
    public CategoriaDTO editar(CategoriaDTO categoriaDTO);
    public void borrar(Long id);
}
