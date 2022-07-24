package com.app.web.servicios;

import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

import com.app.web.modelo.Producto;
import com.app.web.repositorio.ProductoRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;



@Service
@Transactional
public class ProductoServicio {

	@Autowired 
	private ProductoRepositorio repositorio;

	public ProductoServicio() {
		
	}
	
	public List<Producto> buscarTodo(){
		return (List<Producto>) repositorio.findAll();
	}
	
	public Producto crear(Producto producto) {
		return repositorio.save(producto);
	}
	
	public Producto actualizar(Producto productoActualizar) {
		
		Producto productolActual = repositorio.findById(productoActualizar.getIdProducto()).get();
		
		productolActual.setIdProducto(productoActualizar.getIdProducto());
		productolActual.setNombre(productoActualizar.getNombre());
		productolActual.setStock(productoActualizar.getStock());
		productolActual.setCategoria(productoActualizar.getCategoria());
		productolActual.setPrecio(productoActualizar.getPrecio());
		
		
		Producto productoActualizado = repositorio.save(productolActual);
		return productoActualizado;
	}	
	
	public Producto buscarPorId(Integer id) {
		return repositorio.findById(id).get();
	}
	
	public void borrarPorId(Integer id) {
		repositorio.deleteById(id);
	}
	
}











