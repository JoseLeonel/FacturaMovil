package com.emprendesoftcr.web.command;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.emprendesoftcr.fisco.MapEnums;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.ProveedorSimplificado;
import com.emprendesoftcr.modelo.Usuario;

public class ProveedorSimplificadoCommand {

	private Long		id;

	private String	nombreCompleto;

	private String	nombreComercial;

	private String	tipoCedula;

	private String	cedula;

	private String	identificacionExtranjero;

	private String	correoElectronico;

	private Integer	estado;

	private String	estadoSTR;

	private Date		created_at;

	private Date		updated_at;

	private Empresa	empresa;

	private Usuario	usuario;

	private Integer	codigoPais;

	private Integer	telefono;
	private Integer	codigoProvincia;

	private Integer	codigoCanton;

	private Integer	codigoDistrito;

	private String	codigoActividad;

	public ProveedorSimplificadoCommand(ProveedorSimplificado proveedorSimplificado) {
		super();
		this.id = proveedorSimplificado.getId();
		this.nombreCompleto = proveedorSimplificado.getNombreCompleto();
		this.nombreComercial = proveedorSimplificado.getNombreComercial();
		this.tipoCedula = proveedorSimplificado.getTipoCedula();
		this.cedula = proveedorSimplificado.getCedula();
		this.identificacionExtranjero = proveedorSimplificado.getIdentificacionExtranjero();
		this.correoElectronico = proveedorSimplificado.getCorreoElectronico();
		this.estado = proveedorSimplificado.getEstado();
		this.created_at = proveedorSimplificado.getCreated_at();
		this.updated_at = proveedorSimplificado.getUpdated_at();
		this.empresa = proveedorSimplificado.getEmpresa();
		this.usuario = proveedorSimplificado.getUsuario();
		this.codigoPais = proveedorSimplificado.getCodigoPais();
		this.telefono = proveedorSimplificado.getTelefono();
		this.estadoSTR = MapEnums.ENUM_ESTADO_.get(proveedorSimplificado.getEstado());
		this.codigoActividad = proveedorSimplificado.getCodigoActividad();
    this.codigoProvincia = proveedorSimplificado.getCodigoProvincia();
    this.codigoCanton = proveedorSimplificado.getCodigoCanton();
    this.codigoDistrito = proveedorSimplificado.getCodigoDistrito();
	}

	public ProveedorSimplificadoCommand() {
		super();
	}

	public String getEstadoSTR() {
		return estadoSTR;
	}

	public void setEstadoSTR(String estadoSTR) {
		this.estadoSTR = estadoSTR;
	}

	public Integer getCodigoProvincia() {
		return codigoProvincia;
	}

	public void setCodigoProvincia(Integer codigoProvincia) {
		this.codigoProvincia = codigoProvincia;
	}

	public Integer getCodigoCanton() {
		return codigoCanton;
	}

	public void setCodigoCanton(Integer codigoCanton) {
		this.codigoCanton = codigoCanton;
	}

	public Integer getCodigoDistrito() {
		return codigoDistrito;
	}

	public void setCodigoDistrito(Integer codigoDistrito) {
		this.codigoDistrito = codigoDistrito;
	}

	public Long getId() {
		return id;
	}

	public Integer getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(Integer codigoPais) {
		this.codigoPais = codigoPais;
	}

	public Integer getTelefono() {
		return telefono;
	}

	public void setTelefono(Integer telefono) {
		this.telefono = telefono;
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

	public String getNombreComercial() {
		return nombreComercial;
	}

	public void setNombreComercial(String nombreComercial) {
		this.nombreComercial = nombreComercial;
	}

	public String getTipoCedula() {
		return tipoCedula;
	}

	public void setTipoCedula(String tipoCedula) {
		this.tipoCedula = tipoCedula;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getIdentificacionExtranjero() {
		return identificacionExtranjero;
	}

	public void setIdentificacionExtranjero(String identificacionExtranjero) {
		this.identificacionExtranjero = identificacionExtranjero;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getCodigoActividad() {
		return codigoActividad;
	}

	public void setCodigoActividad(String codigoActividad) {
		this.codigoActividad = codigoActividad;
	}

}
