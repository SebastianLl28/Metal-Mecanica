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

import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;




@Entity
@Table(name = "detalleventa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detalleventa.findAll", query = "SELECT s FROM Detalleventa s")
    , @NamedQuery(name = "Detalleventa.findByIdDetalleventa", query = "SELECT s FROM Detalleventa s WHERE s.idDetalleventa= :idDetalleventa")})
public class Detalleventa implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDetalleVenta")
    private Integer idDetalleventa;
	
	
	@Basic(optional = false)
    @Column(name = "cantidad")
    private Integer cantidad;
	
	@Basic(optional = false)
    @Column(name = "precioUnidad")
    private String precioUnidad;
	
	@Basic(optional = false)
    @Column(name = "total")
    private Double total;

	@JoinColumn(name = "producto", referencedColumnName = "idProducto")
    @ManyToOne(optional = false)
    private Producto producto;
	
	@JoinColumn(name = "venta", referencedColumnName = "idVenta")
    @ManyToOne(optional = false)
    private Venta venta;
	
	
	public Detalleventa() {	
	}
	
	
	public Detalleventa(Integer idDetalleventa) {
		
	}


	public Detalleventa(Integer idDetalleventa, Integer cantidad, String precioUnidad, Double total) {
		this.idDetalleventa = idDetalleventa;
		this.cantidad = cantidad;
		this.precioUnidad = precioUnidad;
		this.total = total;
	}


	public Integer getIdDetalleventa() {
		return idDetalleventa;
	}


	public void setIdDetalleventa(Integer idDetalleventa) {
		this.idDetalleventa = idDetalleventa;
	}


	public Integer getCantidad() {
		return cantidad;
	}


	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}


	public String getPrecioUnidad() {
		return precioUnidad;
	}


	public void setPrecioUnidad(String precioUnidad) {
		this.precioUnidad = precioUnidad;
	}


	public Double getTotal() {
		return total;
	}


	public void setTotal(Double total) {
		this.total = total;
	}


	public Producto getProducto() {
		return producto;
	}


	public void setProducto(Producto producto) {
		this.producto = producto;
	}


	public Venta getVenta() {
		return venta;
	}


	public void setVenta(Venta venta) {
		this.venta = venta;
	}


	@Override
	public int hashCode() {
		return Objects.hash(idDetalleventa);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Detalleventa other = (Detalleventa) obj;
		return Objects.equals(idDetalleventa, other.idDetalleventa);
	}

	@Override
	public String toString() {
		return "Detalleventa [idDetalleventa=" + idDetalleventa + "]";
	}

}
