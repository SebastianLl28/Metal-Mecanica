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

import com.app.web.modelo.Venta;
import com.app.web.servicios.VentaServicio;


@RestController
@RequestMapping("/rest/venta")
public class VentaRestController {
	@Autowired
	private VentaServicio servicio;
	
	@GetMapping
	public ResponseEntity<Object> buscartTodo() {

		List<Venta> listaVenta = servicio.buscarTodo();
		return new ResponseEntity<>(listaVenta, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public ResponseEntity<Object> buscarPorId(@PathVariable("id") int id) {
		Venta venta = servicio.buscarPorId(id);
		if (venta == null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					"Venta no encontrada,id porporcionado no es correcto");
		return new ResponseEntity<Object>(venta, HttpStatus.OK);
	}
	
	@PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Object> crear(@RequestBody Venta venta) {
		servicio.crear(venta);
		return new ResponseEntity<Object>("Venta creada correctamente", HttpStatus.OK);
	}
	
	@PutMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Object> actualizar(@PathVariable("id") int id, @RequestBody Venta venta) {
		servicio.Actualizar(venta);
		return new ResponseEntity<Object>("Venta actualizada correctamente", HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Object> borrar(@PathVariable("id") int id) {
		servicio.borrarPorId(id);
		return new ResponseEntity<Object>("Venta eliminada correctamente", HttpStatus.OK);

	}
}
