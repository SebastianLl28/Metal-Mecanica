package com.app.web.servicios;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.web.modelo.Categoria;
import com.app.web.repositorio.CategoriaRepositorio;

@Service
@Transactional
public class CategoriaServicio {
	
	@Autowired
	private CategoriaRepositorio repositorio;
	
	public CategoriaServicio() {
		
	}
	
	public List<Categoria> buscarTodo(){
		return (List<Categoria>) repositorio.findAll();
	}
	
	public Categoria crear(Categoria categoria) {
		return repositorio.save(categoria);
	}
	
	
	public Categoria actualizar(Categoria categoriaActualizar) {
		Categoria categoriaActual = repositorio.findById(categoriaActualizar.getIdCategoria()).get();
		
		categoriaActual.setIdCategoria(categoriaActualizar.getIdCategoria());
		categoriaActual.setDescripcion(categoriaActualizar.getDescripcion());
		 
		Categoria categoriaActualizado = repositorio.save(categoriaActual);
		return categoriaActualizado;
	}
	
	public Categoria buscarPorId(Integer id) {
		return repositorio.findById(id).get();
	}
	
	public void borrarPorId(Integer id) {
		repositorio.deleteById(id);
	}
	
}
