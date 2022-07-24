package com.app.web.servicios;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.web.modelo.Cliente;
import com.app.web.repositorio.ClienteRepositorio;

@Service
@Transactional
public class ClienteServicio {
	
	@Autowired
	private ClienteRepositorio repositorio;
	
	public ClienteServicio() {
		
	}
	
	public List<Cliente> buscarTodo(){
		return (List<Cliente>) repositorio.findAll();
	}
	
	
	public Cliente crear(Cliente cliente) {
		return repositorio.save(cliente);
	}
	
	public Cliente actualizar (Cliente clienteActualizar) {
		
		Cliente clienteActual = repositorio.findById(clienteActualizar.getIdCliente()).get();
		
		clienteActual.setIdCliente(clienteActualizar.getIdCliente());
		clienteActual.setNombre(clienteActualizar.getNombre());
		clienteActual.setDni(clienteActualizar.getDni());
		clienteActual.setTelefono(clienteActualizar.getTelefono());
		clienteActual.setDireccion(clienteActualizar.getDireccion());
		clienteActual.setCorreo(clienteActualizar.getCorreo());
		
		Cliente clienteActualizado = repositorio.save(clienteActual);
		
		return clienteActualizado;
		
	}
	
	
	public Cliente BuscarPorId(Integer id) {
		return repositorio.findById(id).get();
	}
	
	
	public void borrarPorId(Integer id) {
		repositorio.deleteById(id);
	}
	
}





