package com.app.web.modelo;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Table(name = "rol")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rol.findAll", query = "SELECT c FROM Rol c")
    , @NamedQuery(name = "Rol.findByIdRol", query = "SELECT c FROM Rol c WHERE c.idRol = :idRol")
    , @NamedQuery(name = "Rol.findByNombre", query = "SELECT c FROM Rol c WHERE c.nombre = :nombre")})
public class Rol implements Serializable{
	
	private static final long serialVersionUID = 1L;
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idRol")
    private Integer idRol;
    
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rol")
    @JsonBackReference(value="rol_trabajador")
    private List<Trabajador> trabajadorList;
    
    
    public Rol() {
    	
    }
    
    public Rol(Integer idRol) {
    	this.idRol = idRol;
    }

	public Rol(Integer idRol, String nombre) {
		super();
		this.idRol = idRol;
		this.nombre = nombre;
	}

	public Integer getIdRol() {
		return idRol;
	}

	public void setIdRol(Integer idRol) {
		this.idRol = idRol;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@XmlTransient
	public List<Trabajador> getTrabajadorList() {
		return trabajadorList;
	}

	public void setTrabajadorList(List<Trabajador> trabajadorList) {
		this.trabajadorList = trabajadorList;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(idRol);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rol other = (Rol) obj;
		return Objects.equals(idRol, other.idRol);
	}

	@Override
	public String toString() {
		return "Rol [idRol=" + idRol + "]";
	}
	
}

