package com.app.web.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.app.web.modelo.Reclamo;




public interface ReclamoRepositorio extends CrudRepository<Reclamo, Integer>{
	
	@Query(value = "SELECT a FROM Reclamo a WHERE a.asunto =?1")
	public List<Reclamo> buscarReclampoPorAsunto(String asunto);

	@Query(value = "SELECT a FROM Reclamo a WHERE a.asunto like CONCAT(?1, '%')")
	public List<Reclamo> buscarReclamoLikeAsunto(String asunto);
}

