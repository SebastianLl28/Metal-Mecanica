package com.app.web.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.app.web.modelo.Trabajador;

public interface TrabajadorRepositorio extends CrudRepository<Trabajador, Integer> {

	
	@Query(value = "SELECT a FROM Trabajador a WHERE a.nombre =?1")
	public List<Trabajador> buscarTrabajadorsPorNombre(String nombre);

	@Query(value = "SELECT a FROM Trabajador a WHERE a.nombre like CONCAT(?1, '%')")
	public List<Trabajador> buscarTrabajadorLikeNombre(String nombre);

}
