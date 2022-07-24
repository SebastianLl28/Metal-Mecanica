package com.app.web.modelo;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.xml.bind.annotation.XmlRootElement;


import com.fasterxml.jackson.annotation.JsonBackReference;




@Entity
@Table(name = "producto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Producto.findAll", query = "SELECT s FROM Producto s")
    , @NamedQuery(name = "Producto.findByIdSala", query = "SELECT s FROM Producto s WHERE s.idProducto = :idProducto")
    , @NamedQuery(name = "Producto.findByNombre", query = "SELECT s FROM Producto s WHERE s.nombre = :nombre")})
public class Producto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idProducto")
	private Integer idProducto;
	
	@NotEmpty
	@Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
	
	@PositiveOrZero
	@NotNull
	@Basic(optional = false)
    @Column(name = "stock")
    private Integer stock;
	
	@NotNull
	@JoinColumn(name = "categoria", referencedColumnName = "idCategoria")
    @ManyToOne(optional = false)
    private Categoria categoria;
	
	@PositiveOrZero
	@NotNull
	@Basic(optional = false)
    @Column(name = "precio")
    private Double precio;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "producto",fetch=FetchType.LAZY)
    @JsonBackReference(value="producto_detalle")
    private List<Detalleventa> detalleVentaList;
	

	
	public Producto() {
		
	}
	
	public Producto(Integer idProducto) {
		this.idProducto = idProducto;
	}

	public Producto(Integer idProducto, String nombre, Integer stock, Categoria categoria, Double precio) {
		this.idProducto = idProducto;
		this.nombre = nombre;
		this.stock = stock;
		this.categoria = categoria;
		this.precio = precio;
	}

	public Integer getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public List<Detalleventa> getDetalleVentaList() {
		return detalleVentaList;
	}

	public void setDetalleVentaList(List<Detalleventa> detalleVentaList) {
		this.detalleVentaList = detalleVentaList;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idProducto);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Producto other = (Producto) obj;
		return Objects.equals(idProducto, other.idProducto);
	}

	@Override
	public String toString() {
		return "Producto [idProducto=" + idProducto + "]";
	}
		
}
