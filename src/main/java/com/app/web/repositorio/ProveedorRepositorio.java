package com.app.web.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.app.web.modelo.Proveedor;



public interface ProveedorRepositorio extends CrudRepository<Proveedor, Integer>{
	
	@Query(value = "SELECT a FROM Proveedor a WHERE a.razonSocial =?1")
	public List<Proveedor> buscarProveedorPorrazonSocial(String razonSocial);

	@Query(value = "SELECT a FROM Proveedor a WHERE a.razonSocial like CONCAT(?1, '%')")
	public List<Proveedor> buscarProveedorLikerazonSocial(String razonSocial);
}
