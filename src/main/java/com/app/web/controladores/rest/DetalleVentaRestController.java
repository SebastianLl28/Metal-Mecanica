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

import com.app.web.modelo.Detalleventa;
import com.app.web.servicios.DetalleventaServicio;

@RestController
@RequestMapping("/rest/detalle")
public class DetalleVentaRestController {
	@Autowired
	private DetalleventaServicio servicio;
	
	@GetMapping
	public ResponseEntity<Object> buscarTodo(){
		List<Detalleventa> listaDetalle = servicio.buscarTodo();
		return new ResponseEntity<>(listaDetalle, HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public ResponseEntity<Object> buscarPorId(@PathVariable("id") int id) {
		Detalleventa funcion = servicio.buscarPorId(id);
		if (funcion == null)

			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					"Detalle de Venta no encontrado,id porporcionado no es correcto");
		return new ResponseEntity<Object>(funcion, HttpStatus.OK);
	}
	
	
	@PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Object> crear(@RequestBody Detalleventa detallVenta) {
		servicio.crear(detallVenta);
		return new ResponseEntity<Object>("Detalle de Venta creada correctamente", HttpStatus.OK);
	}
	
	@PutMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Object> actualizar(@PathVariable("id") int id, @RequestBody Detalleventa detalleventa) {
		servicio.actualizar(detalleventa);
		return new ResponseEntity<Object>("Detalle de venta fue actualizado correctamente", HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Object> borrar(@PathVariable("id") int id) {
		servicio.borrarPorId(id);
		return new ResponseEntity<Object>("Detalle de venta eliminada correctamente", HttpStatus.OK);

	}
}
