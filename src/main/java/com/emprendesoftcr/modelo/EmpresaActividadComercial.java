package com.emprendesoftcr.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "emp_act_comer")
public class EmpresaActividadComercial implements Serializable {

	private static final long	serialVersionUID	= 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer						id;

	@Column(name = "codigo", length = 6)
	private String						codigo;

	@Column(name = "descripcion", length = 80)
	private String						descripcion;
	
	@Column(name = "princial", columnDefinition = "INT default '0'")
	private Integer principal;

	@ManyToOne
	@JoinColumn(name = "empresa_id")
	private Empresa						empresa;


	
	
	public EmpresaActividadComercial(Integer id, String codigo, String descripcion, Integer principal, Empresa empresa) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.principal = principal;
		this.empresa = empresa;
	}

	public EmpresaActividadComercial() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	
	public Integer getPrincipal() {
		return principal;
	}

	
	public void setPrincipal(Integer principal) {
		this.principal = principal;
	}

	
}
