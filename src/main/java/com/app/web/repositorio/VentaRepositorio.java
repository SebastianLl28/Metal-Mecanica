package com.app.web.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.app.web.modelo.Venta;

public interface VentaRepositorio extends CrudRepository<Venta, Integer>{
	
	@Query(value = "SELECT a FROM Venta a INNER JOIN Cliente c on a.cliente = c.idCliente WHERE c.nombre =?1")
	public List<Venta> buscarVentasPorCliente(String nombre);

	@Query(value = "SELECT a FROM Venta a INNER JOIN Cliente c on a.cliente = c.idCliente WHERE c.nombre like CONCAT(?1, '%')")
	public List<Venta> buscarVentaLikeNombreProducto(String nombre);	
}
