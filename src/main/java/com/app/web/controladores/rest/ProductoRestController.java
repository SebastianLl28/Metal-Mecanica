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

import com.app.web.modelo.Producto;
import com.app.web.servicios.ProductoServicio;




@RestController
@RequestMapping("/rest/producto")
public class ProductoRestController {
	
	@Autowired
	private ProductoServicio servicio;
	
	@GetMapping
	public ResponseEntity<Object> buscarTodo(){
		List<Producto> listaProducto = servicio.buscarTodo();
		return new ResponseEntity<>(listaProducto,HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public ResponseEntity<Object> buscarPorId(@PathVariable("id") int id) {
		Producto producto = servicio.buscarPorId(id);
		if (producto == null)

			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					"Producto no encontrada,id porporcionado no es correcto");
		return new ResponseEntity<Object>(producto, HttpStatus.OK);
	}
	
	@PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Object> crear(@RequestBody Producto producto) {
		servicio.crear(producto);
		return new ResponseEntity<Object>("Producto creada correctamente", HttpStatus.OK);
	}
	
	
	@PutMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Object> actualizar(@PathVariable("id") int id, @RequestBody Producto producto) {
		servicio.actualizar(producto);
		return new ResponseEntity<Object>("Producto actualizada correctamente", HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Object> borrar(@PathVariable("id") int id) {
		servicio.borrarPorId(id);
		return new ResponseEntity<Object>("Producto eliminada correctamente", HttpStatus.OK);
	}
	
}

