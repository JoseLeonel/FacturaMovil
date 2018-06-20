package com.factura.FacturaElectronica.web.command;

import java.math.BigInteger;

import com.factura.FacturaElectronica.modelo.Empresa;

public class EmpresaCommand {

	private Integer			id;

	private String			nombre;

	private String			nombreComercial;
	private Integer			numeroConsecutivo;

	private String			clave;

	private String			tipoCedula;

	private String			cedula;

	private String			otraSenas;

	private BigInteger	codigoPais;

	private BigInteger	telefono;

	private String			correoElectronico;

	private String			provincia;

	private String			distrito;

	private String			barrio;

	private String			logo;

	private String			representante;

	private String			estado;

	public EmpresaCommand(Empresa empresa) {
		super();
		this.id = empresa.getId();
		this.nombre = empresa.getNombre();
		this.nombreComercial = empresa.getNombreComercial();
		this.clave = empresa.getClave();
		this.tipoCedula = empresa.getTipoCedula();
		this.cedula = empresa.getCedula();
		this.otraSenas = empresa.getOtraSenas();
		this.codigoPais = empresa.getCodigoPais();
		this.telefono = empresa.getTelefono();
		this.correoElectronico = empresa.getCorreoElectronico();
		this.provincia = empresa.getProvincia();
		this.distrito = empresa.getDistrito();
		this.barrio = empresa.getBarrio();
		this.logo = empresa.getLogo();
		this.representante = empresa.getRepresentante();
		this.estado = empresa.getEstado();
		this.numeroConsecutivo = empresa.getNumeroConsecutivo();

	}

	public EmpresaCommand() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombreComercial() {
		return nombreComercial;
	}

	public void setNombreComercial(String nombreComercial) {
		this.nombreComercial = nombreComercial;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
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

	public String getOtraSenas() {
		return otraSenas;
	}

	public void setOtraSenas(String otraSenas) {
		this.otraSenas = otraSenas;
	}

	public BigInteger getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(BigInteger codigoPais) {
		this.codigoPais = codigoPais;
	}

	public BigInteger getTelefono() {
		return telefono;
	}

	public void setTelefono(BigInteger telefono) {
		this.telefono = telefono;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getDistrito() {
		return distrito;
	}

	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}

	public String getBarrio() {
		return barrio;
	}

	public void setBarrio(String barrio) {
		this.barrio = barrio;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getRepresentante() {
		return representante;
	}

	public void setRepresentante(String representante) {
		this.representante = representante;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Integer getNumeroConsecutivo() {
		return numeroConsecutivo;
	}

	public void setNumeroConsecutivo(Integer numeroConsecutivo) {
		this.numeroConsecutivo = numeroConsecutivo;
	}

}
