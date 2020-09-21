package com.emprendesoftcr.web.command;

import java.util.Date;

import com.emprendesoftcr.modelo.Cliente;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.utils.Constantes;
import com.emprendesoftcr.utils.Utils;

/**
 * ClienteCommand.
 * @author jose.
 * @since 17 mar. 2018
 */
public class ClienteCommand {

	private Long		id;

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
	private String	correoElectronico1;
	private String	correoElectronico2;
	private String	correoElectronico3;

	private Integer	descuento;

	private String	estado;

	private Date		created_at;

	private Date		updated_at;
	private String	identificacionExtranjero;

	private String	observacionVenta;

	private String	tipoDocumentoExoneracion;

	private String	numeroDocumentoExoneracion;

	private String	nombreInstitucionExoneracion;

	private Date		fechaEmisionExoneracion;
	private String	fechaEmisionExoneracionSTR;
	private Integer	porcentajeExoneracion;
	private Integer						libreImpuesto;
	private Integer						tipoMag;
	private Double limiteCredito;

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
		this.correoElectronico1 = cliente.getCorreoElectronico1();
		this.correoElectronico2 = cliente.getCorreoElectronico2();
		this.correoElectronico3 = cliente.getCorreoElectronico3();
		this.observacionVenta = cliente.getObservacionVenta();
		this.porcentajeExoneracion = cliente.getPorcentajeExoneracion();
		this.tipoDocumentoExoneracion = cliente.getTipoDocumentoExoneracion();
		this.numeroDocumentoExoneracion = cliente.getNumeroDocumentoExoneracion();
		this.nombreInstitucionExoneracion = cliente.getNombreInstitucionExoneracion();
		this.fechaEmisionExoneracion = cliente.getFechaEmisionExoneracion();
		this.fechaEmisionExoneracionSTR = Utils.fechaExoneracionSTR(cliente.getFechaEmisionExoneracion());
		this.libreImpuesto = cliente.getLibreImpuesto();
		this.tipoMag = cliente.getTipoMag();
		this.limiteCredito = cliente.getLimiteCredito();

	}

	public ClienteCommand() {
		super();
	}

	public Long getId() {
		return id;
	}

	
	public Double getLimiteCredito() {
		return limiteCredito;
	}

	
	public void setLimiteCredito(Double limiteCredito) {
		this.limiteCredito = limiteCredito;
	}

	public Integer getPorcentajeExoneracion() {
		return porcentajeExoneracion;
	}

	public void setPorcentajeExoneracion(Integer porcentajeExoneracion) {
		this.porcentajeExoneracion = porcentajeExoneracion;
	}

	public String getTipoDocumentoExoneracion() {
		return tipoDocumentoExoneracion;
	}

	public void setTipoDocumentoExoneracion(String tipoDocumentoExoneracion) {
		this.tipoDocumentoExoneracion = tipoDocumentoExoneracion;
	}

	public String getNumeroDocumentoExoneracion() {
		return numeroDocumentoExoneracion;
	}

	public void setNumeroDocumentoExoneracion(String numeroDocumentoExoneracion) {
		this.numeroDocumentoExoneracion = numeroDocumentoExoneracion;
	}

	public String getNombreInstitucionExoneracion() {
		return nombreInstitucionExoneracion;
	}

	public void setNombreInstitucionExoneracion(String nombreInstitucionExoneracion) {
		this.nombreInstitucionExoneracion = nombreInstitucionExoneracion;
	}

	public Date getFechaEmisionExoneracion() {
		return fechaEmisionExoneracion;
	}

	public void setFechaEmisionExoneracion(Date fechaEmisionExoneracion) {
		this.fechaEmisionExoneracion = fechaEmisionExoneracion;
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

	public String getCorreoElectronico1() {
		return correoElectronico1;
	}

	public void setCorreoElectronico1(String correoElectronico1) {
		this.correoElectronico1 = correoElectronico1;
	}

	public String getCorreoElectronico2() {
		return correoElectronico2;
	}

	public void setCorreoElectronico2(String correoElectronico2) {
		this.correoElectronico2 = correoElectronico2;
	}

	public String getCorreoElectronico3() {
		return correoElectronico3;
	}

	public void setCorreoElectronico3(String correoElectronico3) {
		this.correoElectronico3 = correoElectronico3;
	}

	public String getObservacionVenta() {
		return observacionVenta;
	}

	public void setObservacionVenta(String observacionVenta) {
		this.observacionVenta = observacionVenta;
	}

	public String getFechaEmisionExoneracionSTR() {
		return fechaEmisionExoneracionSTR;
	}

	public void setFechaEmisionExoneracionSTR(String fechaEmisionExoneracionSTR) {
		this.fechaEmisionExoneracionSTR = fechaEmisionExoneracionSTR;
	}

	
	public Integer getLibreImpuesto() {
		return libreImpuesto;
	}

	
	public void setLibreImpuesto(Integer libreImpuesto) {
		this.libreImpuesto = libreImpuesto;
	}

	
	public Integer getTipoMag() {
		return tipoMag;
	}

	
	public void setTipoMag(Integer tipoMag) {
		this.tipoMag = tipoMag;
	}

	
	
	
}
