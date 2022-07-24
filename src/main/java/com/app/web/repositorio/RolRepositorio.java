package com.app.web.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.app.web.modelo.Rol;


public interface RolRepositorio extends CrudRepository<Rol, Integer>{
	
	@Query(value = "SELECT a FROM Rol a WHERE a.nombre =?1")
	public List<Rol> buscarRolPorNombre(String nombre);

	@Query(value = "SELECT a FROM Rol a WHERE a.nombre like CONCAT(?1, '%')")
	public List<Rol> buscarRolLikeNombre(String nombre);
}
