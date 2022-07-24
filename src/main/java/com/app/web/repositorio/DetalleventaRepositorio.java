package com.app.web.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.app.web.modelo.Detalleventa;

public interface DetalleventaRepositorio extends CrudRepository<Detalleventa, Integer> {

	@Query(value = "SELECT a FROM Detalleventa a WHERE a.venta =?1")
	public List<Detalleventa> buscarDetalleventaPorVenta(Integer idVenta);

	@Query(value = "SELECT a FROM Detalleventa a WHERE a.venta like CONCAT(?1, '%')")
	public List<Detalleventa> buscarDetalleventaLikeVenta(Integer idVenta); 
	
	@Query(value = "SELECT a FROM Detalleventa a inner join Venta v on a.venta = v.idVenta inner join Cliente c on v.cliente = c.idCliente WHERE c.nombre like CONCAT(?1, '%')")
	public List<Detalleventa> buscarDetalleventaLikeCliente(String nombre);
	
}
