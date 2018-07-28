package com.emprendesoftcr.web.command;

import java.util.Date;

import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Vendedor;

public class VendedorCommand {

	private Long	id;

	private String	nombreCompleto;

	private String	cedula;

	private Integer	celular;

	private Integer	telefono;

	private String	correoElectronico;

	private String	otraSena;

	private Double		descuento;
	private Double		porcentajeComision;

	private String	estado;

	private Date		created_at;

	private Date		updated_at;

	private Empresa	empresa;

	public VendedorCommand(Vendedor vendedor) {
		super();
		this.id = vendedor.getId();
		this.nombreCompleto = vendedor.getNombreCompleto();
		this.cedula = vendedor.getCedula();
		this.celular = vendedor.getCelular();
		this.telefono = vendedor.getTelefono();
		this.correoElectronico = vendedor.getCorreoElectronico();
		this.descuento = vendedor.getDescuento();
		this.estado = vendedor.getEstado();
		this.created_at = vendedor.getCreated_at();
		this.updated_at = vendedor.getUpdated_at();
		this.empresa = vendedor.getEmpresa();
		this.otraSena = vendedor.getOtraSena();
		this.descuento = vendedor.getDescuento();
		this.porcentajeComision = vendedor.getPorcentajeComision();

	}

	public VendedorCommand() {
		super();
	}

	

	
	public Long getId() {
		return id;
	}

	
	public void setId(Long id) {
		this.id = id;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	

	
	public Integer getCelular() {
		return celular;
	}

	
	public void setCelular(Integer celular) {
		this.celular = celular;
	}

	
	public Integer getTelefono() {
		return telefono;
	}

	
	public void setTelefono(Integer telefono) {
		this.telefono = telefono;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Date getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public String getOtraSena() {
		return otraSena;
	}

	public void setOtraSena(String otraSena) {
		this.otraSena = otraSena;
	}

	public Double getDescuento() {
		return descuento;
	}

	public void setDescuento(Double descuento) {
		this.descuento = descuento;
	}

	public Double getPorcentajeComision() {
		return porcentajeComision;
	}

	public void setPorcentajeComision(Double porcentajeComision) {
		this.porcentajeComision = porcentajeComision;
	}

}
