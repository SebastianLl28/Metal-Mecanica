package com.app.web.servicios;

import java.util.List;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.app.web.modelo.Rol;
import com.app.web.repositorio.RolRepositorio;

@Service
@Transactional
public class RolServicio {
	
	@Autowired 
	private RolRepositorio repositorio;
	
	public RolServicio() {
		
	}
	
	public List<Rol> buscarTodos(){
		return (List<Rol>) repositorio.findAll();
	}
	
	public Rol crear(Rol rol) {
		return repositorio.save(rol);
	}
	
		
	public Rol actualizar(Rol rolActualizar) {
		Rol rolActual = repositorio.findById(rolActualizar.getIdRol()).get();
		
		rolActual.setIdRol(rolActualizar.getIdRol());
		rolActual.setNombre(rolActualizar.getNombre());
		
		Rol rolActualizado = repositorio.save(rolActual);
		return rolActualizado;
	}
	
	
	public Rol buscarPorId(Integer id) {
		return repositorio.findById(id).get();
	}
	
	public void borrarPorId(Integer id) {
		repositorio.deleteById(id);	
	}
	
}
