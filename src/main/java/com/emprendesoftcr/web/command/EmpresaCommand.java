package com.emprendesoftcr.web.command;

import com.emprendesoftcr.modelo.Empresa;

public class EmpresaCommand {

	private Integer	id;

	private String	nombre;

	private String	nombreComercial;
	private Integer	numeroConsecutivo;

	
	private String	tipoCedula;

	private String	cedula;

	private String	otraSenas;

	private Integer	codigoPais;

	private Integer	telefono;

	private String	correoElectronico;

	private String	provincia;
	private String	canton;

	private String	distrito;

	private String	barrio;

	private String	logo;

	private String	representante;

	private String	estado;
	private String	cazaMatriz;

	private String	nombreLlaveCriptografica;

	private Integer	claveLlaveCriptografica;
	private Integer	codigoSeguridad;

	public EmpresaCommand(Empresa empresa) {
		super();
		this.nombreLlaveCriptografica = empresa.getNombreLlaveCriptografica();
		this.claveLlaveCriptografica = empresa.getClaveLlaveCriptografica();
		this.codigoSeguridad = empresa.getCodigoSeguridad();
		this.id = empresa.getId();
		this.nombre = empresa.getNombre();
		this.nombreComercial = empresa.getNombreComercial();
		
		this.tipoCedula = empresa.getTipoCedula();
		this.cedula = empresa.getCedula();
		this.otraSenas = empresa.getOtraSenas();
		this.codigoPais = empresa.getCodigoPais();
		this.telefono = empresa.getTelefono();
		this.correoElectronico = empresa.getCorreoElectronico();
		this.provincia = empresa.getProvincia();
		this.distrito = empresa.getDistrito();
		this.barrio = empresa.getBarrio();
		this.canton = empresa.getBarrio();
		this.logo = empresa.getLogo();
		this.representante = empresa.getRepresentante();
		this.estado = empresa.getEstado();
		this.numeroConsecutivo = empresa.getNumeroConsecutivo();
		this.cazaMatriz = empresa.getCazaMatriz();
		

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

	public String getCazaMatriz() {
		return cazaMatriz;
	}

	public void setCazaMatriz(String cazaMatriz) {
		this.cazaMatriz = cazaMatriz;
	}

	public String getCanton() {
		return canton;
	}

	public void setCanton(String canton) {
		this.canton = canton;
	}

	public String getNombreLlaveCriptografica() {
		return nombreLlaveCriptografica;
	}

	public void setNombreLlaveCriptografica(String nombreLlaveCriptografica) {
		this.nombreLlaveCriptografica = nombreLlaveCriptografica;
	}

	public Integer getClaveLlaveCriptografica() {
		return claveLlaveCriptografica;
	}

	public void setClaveLlaveCriptografica(Integer claveLlaveCriptografica) {
		this.claveLlaveCriptografica = claveLlaveCriptografica;
	}

	
	public Integer getCodigoSeguridad() {
		return codigoSeguridad;
	}

	
	public void setCodigoSeguridad(Integer codigoSeguridad) {
		this.codigoSeguridad = codigoSeguridad;
	}
	
	

}
