package com.app.web.servicios;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.web.modelo.Venta;
import com.app.web.repositorio.VentaRepositorio;


@Service
@Transactional
public class VentaServicio {
	
	@Autowired
	private VentaRepositorio repositorio;
	
	public VentaServicio() {
	}
	
	public List<Venta> buscarTodo(){
		return (List<Venta>) repositorio.findAll();
	}
	
	public Venta crear(Venta venta) {
		return repositorio.save(venta);
	}
	
	
	public Venta Actualizar(Venta ventaActualizar) {
		
		Venta ventaActual = repositorio.findById(ventaActualizar.getIdVenta()).get();
		
		ventaActual.setIdVenta(ventaActualizar.getIdVenta());
		ventaActual.setCliente(ventaActualizar.getCliente());
		ventaActual.setTotal(ventaActualizar.getTotal());
		
		Venta ventaActualizado = repositorio.save(ventaActual);
		return ventaActualizado;
	}
	
	
	public Venta buscarPorId(Integer id) {
		return repositorio.findById(id).get();
	}
	
	
	public void borrarPorId(Integer id) {
		repositorio.deleteById(id);
	}
}
