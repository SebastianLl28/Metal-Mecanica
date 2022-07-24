package com.app.web.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.app.web.modelo.Cliente;

public interface ClienteRepositorio extends CrudRepository<Cliente, Integer>{

	@Query(value = "SELECT a FROM Cliente a WHERE a.nombre =?1")
	public List<Cliente> buscarClientePorNombre(String nombre);

	@Query(value = "SELECT a FROM Cliente a WHERE a.nombre like CONCAT(?1, '%')")
	public List<Cliente> buscarClienteLikeNombre(String nombre);
}
