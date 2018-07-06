package com.factura.FacturaElectronica.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "barrios")
public class Barrio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer		id;

	@Column(name = "codigo")
	private Integer		codigo;

	@Column(name = "descripcion")
	private String		descripcion;

	@ManyToOne
	@JoinColumn(name = "distrito_id")
	private Distrito	distrito;

	public Barrio(Integer id, Integer codigo, String descripcion, Distrito distrito) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.distrito = distrito;
	}

	public Barrio() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Distrito getDistrito() {
		return distrito;
	}

	public void setDistrito(Distrito distrito) {
		this.distrito = distrito;
	}

}
