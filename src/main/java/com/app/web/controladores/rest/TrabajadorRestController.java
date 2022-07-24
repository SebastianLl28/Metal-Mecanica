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

import com.app.web.modelo.Trabajador;
import com.app.web.servicios.TrabajadorServicio;


@RestController
@RequestMapping("/rest/trabajador")
public class TrabajadorRestController {
	
	@Autowired
	private TrabajadorServicio servicio;
	
	@GetMapping
	public ResponseEntity<Object> buscarTodo(){
		List<Trabajador> listaTrabjador= servicio.buscarTodo();
		return new ResponseEntity<>(listaTrabjador, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public ResponseEntity<Object> buscarPorId(@PathVariable("id") int id){
		Trabajador trabajador = servicio.buscarPorId(id);
		if (trabajador == null) 
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					"Trabajador no encotrada, id proporcionado no es correcto");
		return new ResponseEntity<Object>(trabajador, HttpStatus.OK);
	}
	
	
	@PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Object> crear(@RequestBody Trabajador trabajador) {
		servicio.crear(trabajador);
		return new ResponseEntity<Object>("Funcion creada correctamente", HttpStatus.OK);
	}
	
	@PutMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Object> actualizar(@PathVariable("id") int id, @RequestBody Trabajador trabajador) {
		servicio.actualizar(trabajador);
		return new ResponseEntity<Object>("Trabajador actualizada correctamente", HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Object> borrar(@PathVariable("id") int id) {
		servicio.buscarPorId(id);
		return new ResponseEntity<Object>("Trabajador eliminada correctamente", HttpStatus.OK);

	}
}




