package com.app.web.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.app.web.modelo.Categoria;


public interface CategoriaRepositorio extends CrudRepository<Categoria, Integer>{
	
	@Query(value = "SELECT a FROM Categoria a WHERE a.descripcion =?1")
	public List<CategoriaRepositorio> buscarCategoriasPordescripcion(String descripcion);

	@Query(value = "SELECT a FROM Categoria a WHERE a.descripcion like CONCAT(?1, '%')")
	public List<CategoriaRepositorio> buscarCategoriaLikedescripcion(String descripcion);
}
