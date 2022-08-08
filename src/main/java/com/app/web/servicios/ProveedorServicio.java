package com.app.web.servicios;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.app.web.modelo.Proveedor;
import com.app.web.repositorio.ProveedorRepositorio;


@Service
@Transactional
public class ProveedorServicio {
	
	@Autowired
	private ProveedorRepositorio repositorio;
	
	public ProveedorServicio() {
		
	}
	
	
	public List<Proveedor> buscarTodo(){
		return (List<Proveedor>) repositorio.findAll();
	}
	
	
	public Proveedor crear(Proveedor proveedor) {
		return repositorio.save(proveedor);
	}
	
	
	
	public Proveedor actualizar(Proveedor proveedorActualizar) {
		
		Proveedor proveedorActual = repositorio.findById(proveedorActualizar.getIdProveedor()).get();
		
		proveedorActual.setIdProveedor(proveedorActualizar.getIdProveedor());
		proveedorActual.setRazonSocial(proveedorActualizar.getRazonSocial());
		proveedorActual.setTelefono(proveedorActualizar.getTelefono());
		proveedorActual.setCorreo(proveedorActualizar.getCorreo());
		proveedorActual.setDireccion(proveedorActualizar.getDireccion());
		proveedorActual.setRuc(proveedorActualizar.getRuc());
		
		Proveedor proveedorActualizado = repositorio.save(proveedorActual);
		
		return proveedorActualizado;
		
		
	}
	
	
	public Proveedor BuscarPorId(Integer id) {
		return repositorio.findById(id).get();
	}
	
	
	public void borrarPorId(Integer id) {
		repositorio.deleteById(id);
	}
	
	
	
}




























