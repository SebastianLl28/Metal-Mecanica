package com.app.web.modelo;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
//import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonBackReference;



@Entity
@Table(name = "venta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Venta.findAll", query = "SELECT s FROM Venta s")
    , @NamedQuery(name = "Venta.findByIdVenta", query = "SELECT s FROM Venta s WHERE s.idVenta= :idVenta")
    })

public class Venta implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idVenta")
    private Integer idVenta;
	
	@Basic(optional = false)
    @Column(name = "total")
    private Double total;
	
	@JoinColumn(name = "cliente", referencedColumnName = "idCliente")
    @ManyToOne(optional = false)
    private Cliente cliente;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "venta")
    @JsonBackReference(value="venta_detalle")
    private List<Detalleventa> detalleVentaList;
	
	
	public Venta() {
		
	}
	
	public Venta(Integer idVenta) {
		this.idVenta = idVenta;
	}

	public Venta(Integer idVenta, Double total) {
		this.idVenta = idVenta;
		this.total = total;
	}

	public Integer getIdVenta() {
		return idVenta;
	}

	public void setIdVenta(Integer idVenta) {
		this.idVenta = idVenta;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@XmlTransient
	public List<Detalleventa> getDetalleVentaList() {
		return detalleVentaList;
	}

	public void setDetalleVentaList(List<Detalleventa> detalleVentaList) {
		this.detalleVentaList = detalleVentaList;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idVenta);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Venta other = (Venta) obj;
		return Objects.equals(idVenta, other.idVenta);
	}

	@Override
	public String toString() {
		return "Venta [idVenta=" + idVenta + "]";
	}
}
