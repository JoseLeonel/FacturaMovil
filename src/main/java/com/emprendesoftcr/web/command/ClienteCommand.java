package com.emprendesoftcr.web.command;

import java.util.Date;

import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.modelo.Cliente;
import com.emprendesoftcr.modelo.Empresa;

/**
 * ClienteCommand.
 * @author jose.
 * @since 17 mar. 2018
 */
public class ClienteCommand {

	private Long	id;

	private String	nombreCompleto;

	private String	cedula;
	private String	tipoCedula;

	private String	provincia;
	private String	canton;
	private String	distrito;
	private String	barrio;
	private Integer	codigoPais;
	private String	nombreComercial;

	private Integer	celular;

	private Integer	telefono;

	private String	otraSena;

	private String	correoElectronico;

	private Integer	descuento;

	private String	estado;

	private Date		created_at;

	private Date		updated_at;
	private String	identificacionExtranjero;

	private Empresa	empresa;

	public ClienteCommand(Cliente cliente) {
		super();
		this.id = cliente.getId();
		this.nombreCompleto = cliente.getNombreCompleto();
		this.cedula = cliente.getCedula();
		this.provincia = cliente.getProvincia();
		this.canton = cliente.getCanton();
		this.distrito = cliente.getDistrito();
		this.barrio = cliente.getBarrio();
		this.codigoPais = cliente.getCodigoPais();
    this.tipoCedula = cliente.getTipoCedula();
		this.celular = cliente.getCelular();
		this.telefono = cliente.getTelefono();
		this.otraSena = cliente.getOtraSena();
		this.correoElectronico = cliente.getCorreoElectronico();
		this.descuento = cliente.getDescuento();
		this.estado = cliente.getEstado();
		this.created_at = cliente.getCreated_at();
		this.updated_at = cliente.getUpdated_at();
		this.empresa = cliente.getEmpresa();
		this.identificacionExtranjero = cliente.getIdentificacionExtranjero();
		this.nombreComercial = cliente.getNombreComercial();

	}

	public ClienteCommand() {
		super();
	}

	

	
	public Long getId() {
		return id;
	}

	
	
	public String getTipoCedula() {
		return tipoCedula;
	}

	
	public void setTipoCedula(String tipoCedula) {
		this.tipoCedula = tipoCedula;
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

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public Integer getCelular() {
		return celular;
	}

	public void setCelular(Integer celular) {
		this.celular = celular;
	}

	public String getOtraSena() {
		return otraSena;
	}

	public void setOtraSena(String otraSena) {
		this.otraSena = otraSena;
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

	public String getProvinciaSTR() {
		if (this.provincia.equals(Constantes.PROVINCIA_SAN_JOSE)) {
			return Constantes.PROVINCIA_SAN_JOSE_STR;
		}
		if (this.provincia.equals(Constantes.PROVINCIA_ALAJUELA)) {
			return Constantes.PROVINCIA_ALAJUELA_STR;
		}

		if (this.provincia.equals(Constantes.PROVINCIA_CARTAGO)) {
			return Constantes.PROVINCIA_CARTAGO_STR;
		}

		if (this.provincia.equals(Constantes.PROVINCIA_HEREDIA)) {
			return Constantes.PROVINCIA_HEREDIA_STR;
		}
		if (this.provincia.equals(Constantes.PROVINCIA_GUANACASTE)) {
			return Constantes.PROVINCIA_GUANACASTE_STR;
		}
		if (this.provincia.equals(Constantes.PROVINCIA_PUNTARENAS)) {
			return Constantes.PROVINCIA_PUNTARENAS_STR;
		}
		if (this.provincia.equals(Constantes.PROVINCIA_LIMON)) {
			return Constantes.PROVINCIA_LIMON_STR;
		}

		return this.provincia;
	}

	public String getCanton() {
		return canton;
	}

	public void setCanton(String canton) {
		this.canton = canton;
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

	public String getIdentificacionExtranjero() {
		return identificacionExtranjero;
	}

	public void setIdentificacionExtranjero(String identificacionExtranjero) {
		this.identificacionExtranjero = identificacionExtranjero;
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

	public Integer getDescuento() {
		return descuento;
	}

	public void setDescuento(Integer descuento) {
		this.descuento = descuento;
	}

	public String getNombreComercial() {
		return nombreComercial;
	}

	public void setNombreComercial(String nombreComercial) {
		this.nombreComercial = nombreComercial;
	}

}
