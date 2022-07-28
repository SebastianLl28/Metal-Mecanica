package com.app.web.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.app.web.modelo.Rol;
import com.app.web.modelo.Trabajador;
import com.app.web.servicios.RolServicio;
import com.app.web.servicios.TrabajadorServicio;


@Controller
@RequestMapping("/trabajador")
public class TrabajadorController {
	
	@Autowired
	private TrabajadorServicio servicio;
	
	@Autowired
	private RolServicio servicio_rol;
	
	
	@RequestMapping("/listarTodo")
	public String listarTrabajador(Model model) {
		List<Trabajador> listaTrabjador= servicio.buscarTodo();
		System.out.println("LISTA DE PELICULAS : " + listaTrabjador);
		model.addAttribute("listaTrabajador", listaTrabjador);
		return "/moduloTrabajador/listarTodo";
	}
	
	@RequestMapping("/nuevo")
	public String nuevaPelicula(Model model) {
		Trabajador trabajador = new Trabajador();
		List<Rol> listaRol = servicio_rol.buscarTodos();
		model.addAttribute("trabajador", trabajador);
		model.addAttribute("rol", listaRol);
		return "/moduloTrabajador/nuevoTrabajador";
	}
	
	@RequestMapping(value = "/guardar" , method = RequestMethod.POST)
	public String crearTrabajador(@ModelAttribute("trabjador") Trabajador trabajador) {
		    servicio.crear(trabajador);
		    return "redirect:/trabajador/listarTodo";
	}
	
	
	@RequestMapping(value = "/actualizar/{id}")
	public ModelAndView editarTrabajador(@PathVariable(name = "id") int id) {
	    ModelAndView mav = new ModelAndView("/moduloTrabajador/editarTrabajador");
	    Trabajador trabajador = servicio.buscarPorId(id);
	    List<Rol> listaRol = servicio_rol.buscarTodos();
	    mav.addObject("trabajador", trabajador);
	    mav.addObject("rol", listaRol);
	    return mav;
	}
	
	
	@RequestMapping(value ="/eliminar/{id}")
	public String eliminarTrabajador(@PathVariable(name = "id") int id) {
		 servicio.borrarPorId(id);
		 return "redirect:/trabajador/listarTodo";
	}
	
}
