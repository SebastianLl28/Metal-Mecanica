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


import com.app.web.modelo.Proveedor;

import com.app.web.servicios.ProveedorServicio;

@Controller
@RequestMapping("/proveedor")
public class ProveedorController {
	
	@Autowired
	private ProveedorServicio servicio;
	
	@RequestMapping("/listarTodo")
	public String listarProveedor(Model model) {
		List<Proveedor> listaProveedor= servicio.buscarTodo();
		System.out.println("Listar Proveedor"+ listaProveedor);
		model.addAttribute("listaProveedor", listaProveedor);
		return "/moduloProveedor/listarTodo";
		
	}
	
	@RequestMapping("/nuevo")
	public String nuevaProveedor(Model model) {
		Proveedor proveedor = new Proveedor();
		model.addAttribute("proveedor", proveedor);
		return "/moduloProveedor/nuevoProveedor";
	}
	
	@RequestMapping(value = "/guardar", method = RequestMethod.POST)
	public String crearProveedor(@Valid @ModelAttribute("proveedor") Proveedor proveedor, BindingResult result, Model model) {
		if(result.hasErrors()) {
			System.out.println("error");
			model.addAttribute("proveedor", proveedor);
		
			return "/moduloProveedor/nuevoProveedor";
		}
		servicio.crear(proveedor);
		return "redirect:/proveedor/listarTodo";
	}
	
	
	@RequestMapping(value= "/actualizar/{id}")
	public ModelAndView editarProveedor(@PathVariable(name = "id") int id) {
		ModelAndView mav = new ModelAndView("/moduloProveedor/editarProveedor");
		Proveedor proveedor = servicio.BuscarPorId(id);
		mav.addObject("proveedor", proveedor);
		return mav;
	}
	
	
	@RequestMapping(value="/eliminar/{id}")
	public String eliminar(@PathVariable(name = "id") int id) {
		servicio.borrarPorId(id);
		return "redirect:/proveedor/listarTodo";
	}
	
}


// configuración de los permisos , crear los qrchivos html cssasdasd




































