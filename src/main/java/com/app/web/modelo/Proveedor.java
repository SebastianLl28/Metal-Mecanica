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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlRootElement;



@Entity
@Table(name = "proveedor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Proveedor.findAll", query = "SELECT c FROM Proveedor c")
    , @NamedQuery(name = "Proveedor.findByIdProveedor", query = "SELECT c FROM Proveedor c WHERE c.idProveedor = :idProveedor")
    , @NamedQuery(name = "Proveedor.findByProveedor", query = "SELECT c FROM Proveedor c WHERE c.razonSocial = :razonSocial")})
public class Proveedor implements Serializable{
	
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idProveedor")
    private Integer idProveedor;
    
    @NotEmpty
    @Basic(optional = false)
    @Column(name = "razonSocial")
    private String razonSocial;
    
    @NotEmpty
    @Pattern(regexp="[0-9]{9}")
    @Basic(optional = false)
    @Column(name = "telefono")
    private String telefono;
    
    @NotEmpty
    @Email
    @Basic(optional = false)
    @Column(name = "correo")
    private String correo;
    
    @NotEmpty
    @Basic(optional = false)
    @Column(name = "direccion")
    private String direccion;
    
    @NotEmpty
    @Pattern(regexp="[0-9]{11}")
    @Basic(optional = false)
    @Column(name = "ruc")
    private String ruc;
    
    
    public Proveedor() {
    	
    }
    
    public Proveedor(Integer idProveedor) {
    	this.idProveedor = idProveedor;
    }

	public Proveedor(Integer idProveedor, String razonSocial,String telefono, String correo, String direccion, String ruc) {
		
		this.idProveedor = idProveedor;
		this.razonSocial = razonSocial;
		this.telefono = telefono;
		this.correo = correo;
		this.direccion = direccion;
		this.ruc = ruc;
	}

	public Integer getIdProveedor() {
		return idProveedor;
	}

	public void setIdProveedor(Integer idProveedor) {
		this.idProveedor = idProveedor;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	
	
	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
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

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idProveedor);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Proveedor other = (Proveedor) obj;
		return Objects.equals(idProveedor, other.idProveedor);
	}

	@Override
	public String toString() {
		return "Proveedor [idProveedor=" + idProveedor + "]";
	}

    
}






