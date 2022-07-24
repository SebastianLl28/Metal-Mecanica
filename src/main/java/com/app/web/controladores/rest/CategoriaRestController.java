package com.app.web.controladores.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.app.web.modelo.Categoria;
import com.app.web.servicios.CategoriaServicio;

@RestController
@RequestMapping("/rest/categoria")
public class CategoriaRestController {
	
	@Autowired
	private CategoriaServicio servicio;
	
	@GetMapping
	public ResponseEntity<Object> buscarTodo(){
		List<Categoria> listaCategoria= servicio.buscarTodo();
		return new ResponseEntity<>(listaCategoria, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public ResponseEntity<Object> buscarPorId(@PathVariable("id") int id) {
		Categoria categoria = servicio.buscarPorId(id);
		if (categoria == null)

			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					"Categoria no fue encontrada,id porporcionado no es correcto");
		return new ResponseEntity<Object>(categoria, HttpStatus.OK);

	}
	
	@PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Object> crear(@RequestBody Categoria categoria) {
		servicio.crear(categoria);
		return new ResponseEntity<Object>("Categoria creada correctamente", HttpStatus.OK);
	}
	
	@PutMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Object> actualizar(@PathVariable("id") int id, @RequestBody Categoria categoria) {
		servicio.actualizar(categoria);
		return new ResponseEntity<Object>("Categoria actualizada correctamente", HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Object> borrar(@PathVariable("id") int id) {
		servicio.borrarPorId(id);
		return new ResponseEntity<Object>("Categoria eliminada correctamente", HttpStatus.OK);
	}
	
	
}
