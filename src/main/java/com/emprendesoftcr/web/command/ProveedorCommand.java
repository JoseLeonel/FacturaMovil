package com.emprendesoftcr.web.command;

import java.util.Date;

import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Proveedor;

public class ProveedorCommand {

	private Integer	id;

	private String	cedula;

	private String	razonSocial;

	private String	nombreCompleto;

	private String	representante;

	private String	email;

	private String	telefono;

	private String	movil;

	private String	direccion;

	private String	estado;

	private Date		created_at;

	private Date		updated_at;

	private Empresa	empresa;

	public ProveedorCommand(Proveedor proveedor) {
		super();
		this.id = proveedor.getId();
		this.nombreCompleto = proveedor.getNombreCompleto();
		this.cedula = proveedor.getCedula();
		this.razonSocial = proveedor.getRazonSocial();
		this.representante = proveedor.getRepresentante();
		this.email = proveedor.getEmail();
		this.movil = proveedor.getMovil();
		this.telefono = proveedor.getTelefono();
		this.direccion = proveedor.getDireccion();
		this.empresa = proveedor.getEmpresa();
		this.estado = proveedor.getEstado();
		this.created_at = proveedor.getCreated_at();
		this.updated_at = proveedor.getUpdated_at();

	}

	public ProveedorCommand() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getRepresentante() {
		return representante;
	}

	public void setRepresentante(String representante) {
		this.representante = representante;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getMovil() {
		return movil;
	}

	public void setMovil(String movil) {
		this.movil = movil;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
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

}
