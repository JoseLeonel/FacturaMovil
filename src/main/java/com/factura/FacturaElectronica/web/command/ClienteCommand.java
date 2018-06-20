package com.factura.FacturaElectronica.web.command;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import com.factura.FacturaElectronica.Utils.Constantes;
import com.factura.FacturaElectronica.modelo.Cliente;
import com.factura.FacturaElectronica.modelo.Empresa;

/**
 * ClienteCommand.
 * @author jose.
 * @since 17 mar. 2018
 */
public class ClienteCommand {

	private Integer			id;

	private String			nombreCompleto;

	private String			cedula;

	private String			provincia;

	private String			celular;

	private BigInteger	telefono;

	private String			otraSena;

	private String			correoElectronico;

	private BigDecimal	descuento;

	private String			estado;

	private Date				created_at;

	private Date				updated_at;

	private Empresa			empresa;

	public ClienteCommand(Cliente cliente) {
		super();
		this.id = cliente.getId();
		this.nombreCompleto = cliente.getNombreCompleto();
		this.cedula = cliente.getCedula();
		this.provincia = cliente.getProvincia();
		this.celular = cliente.getCelular();
		this.telefono = cliente.getTelefono();
		this.otraSena = cliente.getOtraSena();
		this.correoElectronico = cliente.getCorreoElectronico();
		this.descuento = cliente.getDescuento();
		this.estado = cliente.getEstado();
		this.created_at = cliente.getCreated_at();
		this.updated_at = cliente.getUpdated_at();
		this.empresa = cliente.getEmpresa();

	}

	public ClienteCommand() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public BigInteger getTelefono() {
		return telefono;
	}

	public void setTelefono(BigInteger telefono) {
		this.telefono = telefono;
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

	public BigDecimal getDescuento() {
		return descuento;
	}

	public void setDescuento(BigDecimal descuento) {
		this.descuento = descuento;
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
}
