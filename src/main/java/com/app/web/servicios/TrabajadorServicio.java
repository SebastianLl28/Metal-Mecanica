package com.app.web.servicios;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.web.modelo.Trabajador;
import com.app.web.repositorio.TrabajadorRepositorio;

@Service
@Transactional
public class TrabajadorServicio {
	
	@Autowired
	private TrabajadorRepositorio repositorio;
	
	public TrabajadorServicio() {
		
	}
	
	public List<Trabajador> buscarTodo(){
		return (List<Trabajador>) repositorio.findAll();
	}
	
	
	public Trabajador crear(Trabajador trabajador) {
		return repositorio.save(trabajador);
	}
	
	
	public Trabajador actualizar(Trabajador trabajadorActualizar) {
		
		Trabajador trabajadorActual = repositorio.findById(trabajadorActualizar.getIdTrabajador()).get();
		
		trabajadorActual.setIdTrabajador(trabajadorActualizar.getIdTrabajador());
		trabajadorActual.setRol(trabajadorActualizar.getRol());
		trabajadorActual.setNombre(trabajadorActualizar.getNombre());
		trabajadorActual.setDni(trabajadorActualizar.getDni());
		trabajadorActual.setCorreo(trabajadorActualizar.getCorreo());
		trabajadorActual.setDireccion(trabajadorActualizar.getDireccion());
		trabajadorActual.setPassaword(trabajadorActualizar.getPassaword());
		
		Trabajador trabajadorActualizado = repositorio.save(trabajadorActual);
		
		return trabajadorActualizado;
	}
	
	
	public Trabajador buscarPorId(Integer id){
		return repositorio.findById(id).get();
	}
	
	public void borrarPorId(Integer id) {
		repositorio.deleteById(id);
	}
	
	
}
