package com.app.web.modelo;

import java.io.Serializable;

import java.util.Objects;


import javax.persistence.Basic;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import javax.persistence.Table;
//import javax.validation.constraints.Email;
//import javax.validation.constraints.NotEmpty;
//import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "reclamo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reclamo.findAll", query = "SELECT c FROM Reclamo c")
    , @NamedQuery(name = "Proveedor.findByIdReclamo", query = "SELECT c FROM Reclamo c WHERE c.idReclamo= :idReclamo")
    , @NamedQuery(name = "Proveedor.findByReclamo", query = "SELECT c FROM Reclamo c WHERE c.asunto = :asunto")})
public class Reclamo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idReclamo")
    private Integer idReclamo;
    
    //@NotEmpty
    @Basic(optional = false)
    @Column(name = "correo")
    private String correo;
    
    @Basic(optional = false)
    @Column(name = "asunto")
    private String asunto;
    
    @Basic(optional = false)
    @Column(name = "descripcion")
    private String descripcion;
    
    public Reclamo() {
    	
    }
    
    public Reclamo(Integer idReclamo) {
    	this.idReclamo = idReclamo;
    }

	public Reclamo(Integer idReclamo, String correo, String asunto, String descripcion) {
		super();
		this.idReclamo = idReclamo;
		this.correo = correo;
		this.asunto = asunto;
		this.descripcion = descripcion;
	}

	public Integer getIdReclamo() {
		return idReclamo;
	}

	public void setIdReclamo(Integer idReclamo) {
		this.idReclamo = idReclamo;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idReclamo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reclamo other = (Reclamo) obj;
		return Objects.equals(idReclamo, other.idReclamo);
	}

	@Override
	public String toString() {
		return "Reclamo [idReclamo=" + idReclamo + "]";
	}
}
