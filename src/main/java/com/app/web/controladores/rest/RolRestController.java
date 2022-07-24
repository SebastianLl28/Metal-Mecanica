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

import com.app.web.modelo.Rol;
import com.app.web.servicios.RolServicio;


@RestController
@RequestMapping("/rest/rol")
public class RolRestController {
	@Autowired
	private RolServicio servicio;
	
	@GetMapping
	public ResponseEntity<Object> buscarTodo(){
		List<Rol> listaRol = servicio.buscarTodos();
		return new ResponseEntity<>(listaRol,HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public ResponseEntity<Object> buscarPorId(@PathVariable("id") int id) {
		Rol rol = servicio.buscarPorId(id);
		if (rol == null)

			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					"Rol no encontrada,id porporcionado no es correcto");
		return new ResponseEntity<Object>(rol, HttpStatus.OK);
	}
	
	@PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Object> crear(@RequestBody Rol rol) {
		servicio.crear(rol);
		return new ResponseEntity<Object>("Rol creada correctamente", HttpStatus.OK);
	}
	
	@PutMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Object> actualizar(@PathVariable("id") int id, @RequestBody Rol rol) {
		servicio.actualizar(rol);
		return new ResponseEntity<Object>("Rol actualizado correctamente", HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Object> borrar(@PathVariable("id") int id) {
		servicio.borrarPorId(id);
		return new ResponseEntity<Object>("Rol eliminada correctamente", HttpStatus.OK);

	}
}
