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
@Table(name = "categoria")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Categoria.findAll", query = "SELECT c FROM Categoria c")
    , @NamedQuery(name = "Categoria.findByIdCategoria", query = "SELECT c FROM Categoria c WHERE c.idCategoria = :idCategoria")
    , @NamedQuery(name = "Categoria.findByDescripcion", query = "SELECT c FROM Categoria c WHERE c.descripcion = :descripcion")})
public class Categoria implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCategoria")
    private Integer idCategoria;
	
	
	@Basic(optional = false)
    @Column(name = "descripcion")
    private String descripcion;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "categoria")
    @JsonBackReference(value="cat_prod")
    private List<Producto> sedeList;
	
	public Categoria() {
		
	}
	
	public Categoria(Integer idCategoria) {
		
	}

	public Categoria(Integer idCategoria, String descripcion) {
		this.idCategoria = idCategoria;
		this.descripcion = descripcion;
	}

	public Integer getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@XmlTransient
	public List<Producto> getSedeList() {
		return sedeList;
	}

	public void setSedeList(List<Producto> sedeList) {
		this.sedeList = sedeList;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idCategoria);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Categoria other = (Categoria) obj;
		return Objects.equals(idCategoria, other.idCategoria);
	}

	@Override
	public String toString() {
		return "Categoria [idCategoria=" + idCategoria + "]";
	}
	
}
