package com.app.web.servicios;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import com.app.web.modelo.Reclamo;
import com.app.web.repositorio.ReclamoRepositorio;


@Service
@Transactional
public class ReclamoServicio {
	
	
	@Autowired
	private ReclamoRepositorio repositorio;
	
	public ReclamoServicio() {
		
	}
	
	
	public List<Reclamo> buscarTodo(){
		return (List<Reclamo>) repositorio.findAll();
	}
	
	public Reclamo crear(Reclamo reclamo) {
		return repositorio.save(reclamo);
	}
	
	
	public Reclamo actualizar(Reclamo reclamoActualizar) {
		Reclamo reclamoActual = repositorio.findById(reclamoActualizar.getIdReclamo()).get();
		
		reclamoActual.setIdReclamo(reclamoActualizar.getIdReclamo());
		reclamoActual.setCorreo(reclamoActualizar.getCorreo());
		reclamoActual.setAsunto(reclamoActualizar.getAsunto());
		reclamoActual.setDescripcion(reclamoActualizar.getDescripcion());
		
		Reclamo reclamoActualizado = repositorio.save(reclamoActual);
		
		return reclamoActualizado;

	}
	
	public Reclamo BuscarPorId(Integer id) {
		return repositorio.findById(id).get();
	}
	
	
	public void borrarPorId(Integer id) {
		repositorio.deleteById(id);
	}
	
	
	
	
}
