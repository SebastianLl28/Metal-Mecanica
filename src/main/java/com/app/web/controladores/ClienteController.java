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

import com.app.web.modelo.Cliente;
import com.app.web.servicios.ClienteServicio;



@Controller
@RequestMapping("/cliente")
public class ClienteController {
	
	@Autowired
	private ClienteServicio servicio;
	
	@RequestMapping("/listarTodo")
	public String listarCliente(Model model) {
		List<Cliente> listarCliente = servicio.buscarTodo();
		System.out.println("Listar de Cliente: " + listarCliente);
		model.addAttribute("listarCliente", listarCliente);
		return "/moduloCliente/listarTodo";
	}
	
	@RequestMapping("/nuevo")
	public String nuevaCliente(Model model) {
		Cliente cliente = new Cliente();
		model.addAttribute("cliente", cliente);
		return "/moduloCliente/nuevoCliente";	
	}
	
	@RequestMapping(value = "/guardar" , method = RequestMethod.POST)
	public String crearCliente(@Valid @ModelAttribute("cliente") Cliente cliente, BindingResult result, Model model) {
		if(result.hasErrors()) {
			System.out.println("error");
			model.addAttribute("cliente", cliente);
		
			return "/moduloCliente/nuevoCliente";
		}
		servicio.crear(cliente);
	    return "redirect:/cliente/listarTodo";
	}
	
	
	@RequestMapping(value = "/actualizar/{id}")
	public ModelAndView editarCliente(@PathVariable(name = "id") int id) {
	    ModelAndView mav = new ModelAndView("/moduloCliente/editarCliente");
	    Cliente cliente = servicio.BuscarPorId(id);
	    mav.addObject("cliente", cliente);
	    return mav;
	}
	
	
	@RequestMapping(value ="/eliminar/{id}")
	public String eliminar(@PathVariable(name = "id") int id) {
		  servicio.borrarPorId(id);
		 return "redirect:/cliente/listarTodo";
	}
}
