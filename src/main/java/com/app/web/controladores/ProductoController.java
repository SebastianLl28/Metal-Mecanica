package com.app.web.controladores;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.servlet.ModelAndView;

import com.app.web.modelo.Producto;
import com.app.web.servicios.CategoriaServicio;
import com.app.web.servicios.ProductoServicio;


import com.app.web.modelo.Categoria;


@Controller
@RequestMapping("/producto")
public class ProductoController {
	 
	@Autowired
	private ProductoServicio servicio;
	
	@Autowired
	private CategoriaServicio servicio_cat;
	
	@RequestMapping("/listarTodo")
	public String listarProductos(Model model) {
		List<Producto> listaProductos = servicio.buscarTodo();
		System.out.println("LISTA DE PRODUCTOS: "+ listaProductos);
		model.addAttribute("listaProductos", listaProductos);
		return "/moduloProducto/listarTodo"; 
	}
	
	
	@RequestMapping("/nuevo")
	public String nuevaProducto(Model model) {
		Producto producto= new Producto();
		List<Categoria> listacat = servicio_cat.buscarTodo();
		model.addAttribute("producto", producto);
		model.addAttribute("categoria", listacat);
		return "/moduloProducto/nuevoProducto";	
	}
	
	@RequestMapping(value = "/guardar" , method = RequestMethod.POST)
	public String crearProducto(@Valid @ModelAttribute("producto") Producto producto, BindingResult result, Model model) {
		    
		if(result.hasErrors()) {
			System.out.println("error");
			List<Categoria> listacat = servicio_cat.buscarTodo();
			model.addAttribute("producto", producto);
			model.addAttribute("categoria", listacat);
			return "/moduloProducto/nuevoProducto";	
		}
		
		servicio.crear(producto);
		return "redirect:/producto/listarTodo";
	}
	
	@RequestMapping(value = "/actualizar/{id}")
	public ModelAndView editarProducto(@PathVariable(name = "id") int id) {
		List<Categoria> listacat = servicio_cat.buscarTodo();
		ModelAndView mav = new ModelAndView("/moduloProducto/editarProducto");
		Producto producto = servicio.buscarPorId(id);
		mav.addObject("producto", producto);
		mav.addObject("categoria", listacat);
		return mav;
	}
	
	@RequestMapping(value ="/eliminar/{id}")
	public String eliminarProducto(@PathVariable(name = "id") int id) {
		  servicio.borrarPorId(id);
		 return "redirect:/producto/listarTodo";
	}
}
