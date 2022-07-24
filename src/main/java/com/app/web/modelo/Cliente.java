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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Table(name = "cliente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c")
    , @NamedQuery(name = "Cliente.findByIdCliente", query = "SELECT c FROM Cliente c WHERE c.idCliente = :idCliente")
    , @NamedQuery(name = "Cliente.findByCliente", query = "SELECT c FROM Cliente c WHERE c.nombre = :nombre")})
public class Cliente implements Serializable{
	
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCliente")
    private Integer idCliente;
    
    @NotEmpty
    @Pattern(regexp="[0-9]{8}")
    @Basic(optional = false)
    @Column(name = "dni")
    private String dni;
    
    @NotEmpty
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    
    @NotEmpty
    @Pattern(regexp="[0-9]{9}")
    @Basic(optional = false)
    @Column(name = "telefono")
    private String telefono;
    
    @NotEmpty
    @Basic(optional = false)
    @Column(name = "direccion")
    private String direccion;
    
    @NotEmpty
    @Email
    @Basic(optional = false)
    @Column(name = "correo")
    private String correo;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente")
    @JsonBackReference(value="cliente_venta")
    private List<Venta> ventaList;
    
    
    public Cliente () {
    	
    }
    
    public Cliente(Integer idCliente) {
    	this.idCliente = idCliente;
    }

	public Cliente(Integer idCliente, String dni, String nombre, String telefono, String direccion, String correo) {
		this.idCliente = idCliente;
		this.dni = dni;
		this.nombre = nombre;
		this.telefono = telefono;
		this.direccion = direccion;
		this.correo = correo;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	@XmlTransient
	public List<Venta> getVentaList() {
		return ventaList;
	}

	public void setVentaList(List<Venta> ventaList) {
		this.ventaList = ventaList;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idCliente);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(idCliente, other.idCliente);
	}

	@Override
	public String toString() {
		return "Cliente [idCliente=" + idCliente + "]";
	}
}
