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

import com.app.web.modelo.Cliente;
import com.app.web.servicios.ClienteServicio;


@RestController
@RequestMapping("/rest/cliente")
public class ClienteRestController {
	@Autowired
	private ClienteServicio servicio;
	
	@GetMapping
	public ResponseEntity<Object> buscarTodo(){
	List<Cliente> listaCliente = servicio.buscarTodo();
	return new ResponseEntity<>(listaCliente, HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public ResponseEntity<Object> buscarPorId(@PathVariable("id") int id) {
		Cliente cliente = servicio.BuscarPorId(id);
		if (cliente == null)

			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					"No se encontro a un Cliente,id porporcionado no es correcto");
		return new ResponseEntity<Object>(cliente, HttpStatus.OK);

	}
	
	@PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Object> crear(@RequestBody Cliente cliente) {
		servicio.crear(cliente);
		return new ResponseEntity<Object>("Cliente creado correctamente", HttpStatus.OK);
	}
	
	@PutMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Object> actualizar(@PathVariable("id") int id, @RequestBody Cliente cliente) {
		servicio.actualizar(cliente);
		return new ResponseEntity<Object>("Cliente actualizado correctamente", HttpStatus.OK);
	}
	

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Object> borrar(@PathVariable("id") int id) {
		servicio.borrarPorId(id);
		return new ResponseEntity<Object>("Cliente eliminado correctamente", HttpStatus.OK);
	}
	
	
}
