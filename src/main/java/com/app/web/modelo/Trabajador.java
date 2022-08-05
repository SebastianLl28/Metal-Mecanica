package com.app.web.modelo;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Basic;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;




@Entity
@Table(name = "trabajador")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Trabajador.findAll", query = "SELECT c FROM Trabajador c")
    , @NamedQuery(name = "Trabajador.findByIdTrabajador", query = "SELECT c FROM Trabajador c WHERE c.idTrabajador = :idTrabajador")
    , @NamedQuery(name = "Trabajador.findByNombre", query = "SELECT c FROM Trabajador c WHERE c.nombre = :nombre")})
public class Trabajador implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTrabajador")
    private Integer idTrabajador;
	
	@NotEmpty
	@Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
	
	@NotEmpty
	@Pattern(regexp="[0-9]{8}")
	@Basic(optional = false)
    @Column(name = "dni")
    private String dni;
	
	@Email
	@NotEmpty
	@Basic(optional = false)
    @Column(name = "correo")
    private String correo;
	
	@NotEmpty
	@Basic(optional = false)
    @Column(name = "direccion")
    private String direccion;
	
	@NotEmpty
	@Basic(optional = false)
    @Column(name = "passaword")
    private String passaword;
	
	
	@JoinColumn(name = "rol", referencedColumnName = "idRol")
    @ManyToOne(optional = false)
    private Rol rol;
		
	public Trabajador() {
		
	}
	
	

	public void setIdTrabajador(Integer idTrabajador) {
		this.idTrabajador = idTrabajador;
	}

	
	

	public Trabajador(Integer idTrabajador, String nombre, String dni, String correo,
			String direccion, String passaword) {
		super();
		this.idTrabajador = idTrabajador;
		this.nombre = nombre;
		this.dni = dni;
		this.correo = correo;
		
		this.direccion = direccion;
		this.passaword = passaword;
	}

	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public String getDni() {
		return dni;
	}



	public void setDni(String dni) {
		this.dni = dni;
	}



	public String getCorreo() {
		return correo;
	}



	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getDireccion() {
		return direccion;
	}



	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}



	public String getPassaword() {
		return passaword;
	}



	public void setPassaword(String passaword) {
		this.passaword = passaword;
	}



	public Rol getRol() {
		return rol;
	}



	public void setRol(Rol rol) {
		this.rol = rol;
	}



	public Integer getIdTrabajador() {
		return idTrabajador;
	}



	@Override
	public int hashCode() {
		return Objects.hash(idTrabajador);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Trabajador other = (Trabajador) obj;
		return Objects.equals(idTrabajador, other.idTrabajador);
	}

	@Override
	public String toString() {
		return "Trabajador [idTrabajador=" + idTrabajador + "]";
	}
	
}









