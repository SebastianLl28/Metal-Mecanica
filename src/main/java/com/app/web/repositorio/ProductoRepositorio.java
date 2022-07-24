package com.app.web.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.app.web.modelo.Producto;

public interface ProductoRepositorio extends CrudRepository<Producto, Integer> {

	@Query(value = "SELECT a FROM Producto a WHERE a.nombre =?1")
	public List<Producto> buscarProductoPorNombre(String nombre);

	@Query(value = "SELECT a FROM Producto a WHERE a.nombre like CONCAT(?1, '%')")
	public List<Producto> buscarProductoLikeNombre(String nombre);
}
