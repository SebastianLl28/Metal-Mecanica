package com.app.web.servicios;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.web.modelo.Detalleventa;
import com.app.web.repositorio.DetalleventaRepositorio;

@Service
@Transactional
public class DetalleventaServicio {
	
	@Autowired
	private DetalleventaRepositorio repositorio;
	
	public DetalleventaServicio() {
		
	};
	
	public List<Detalleventa> buscarTodo(){
		return (List<Detalleventa>) repositorio.findAll();
	}
	
	public Detalleventa crear (Detalleventa detalle) {
		return repositorio.save(detalle);
	} 
	
	public Detalleventa actualizar(Detalleventa detalleActualizar) {
		Detalleventa detalleActual = repositorio.findById(detalleActualizar.getIdDetalleventa()).get();
		
		detalleActual.setIdDetalleventa(detalleActualizar.getIdDetalleventa());
		detalleActual.setVenta(detalleActualizar.getVenta());
		detalleActual.setProducto(detalleActualizar.getProducto());
		detalleActual.setCantidad(detalleActualizar.getCantidad());
		detalleActual.setPrecioUnidad(detalleActualizar.getPrecioUnidad());
		detalleActual.setTotal(detalleActualizar.getTotal());
		
		Detalleventa detalleActualizado = repositorio.save(detalleActual);
		return detalleActualizado;
	}
	
	public Detalleventa buscarPorId(Integer id) {
		return repositorio.findById(id).get();	
	}
	
	public void borrarPorId(Integer id) {
		repositorio.deleteById(id);
	}
	
}
