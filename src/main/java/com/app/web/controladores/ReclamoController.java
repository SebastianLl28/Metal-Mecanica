package com.app.web.controladores;

import java.util.List;

//import javax.validation.Valid;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import com.app.web.modelo.Reclamo;
import com.app.web.servicios.ReclamoServicio;

@Controller
@RequestMapping("/reclamo")
public class ReclamoController {
	
	@Autowired
	private ReclamoServicio servicio;

	@RequestMapping("/listarTodo")
	public String listarReclamo(Model model) {
		List<Reclamo> listaReclamo= servicio.buscarTodo();
		System.out.println("Listar Reclamo"+ listaReclamo);
		model.addAttribute("listaReclamo", listaReclamo);
		return "/moduloReclamo/listarTodo";		
	}
	
	
	@RequestMapping("/nuevo")
	public String nuevaReclamo(Model model) {
		Reclamo reclamo = new Reclamo();
		model.addAttribute("reclamo", reclamo);
		return "/moduloReclamo/nuevoReclamo";
	}
	
	@RequestMapping(value = "/guardar", method = RequestMethod.POST)
	public String crearReclamo(@ModelAttribute("reclamo") Reclamo reclamo) {
	//public String crearProveedor(@Valid @ModelAttribute("reclamo") Proveedor proveedor, BindingResult result, Model model) {
//		if(result.hasErrors()) {
//			System.out.println("error");
//			model.addAttribute("proveedor", proveedor);
//		
//			return "/moduloProveedor/nuevoProveedor";
//		}
		servicio.crear(reclamo);
		return "redirect:/reclamo/listarTodo";
	}
	
	
	@RequestMapping(value= "/actualizar/{id}")
	public ModelAndView editarReclamo(@PathVariable(name = "id") int id) {
		ModelAndView mav = new ModelAndView("/moduloReclamo/editarReclamo");
		Reclamo reclamo = servicio.BuscarPorId(id);
		mav.addObject("reclamo", reclamo);
		return mav;
	}
	
	
	@RequestMapping(value="/eliminar/{id}")
	public String eliminar(@PathVariable(name = "id") int id) {
		servicio.borrarPorId(id);
		return "redirect:/reclamo/listarTodo";
	}
	
	
}
