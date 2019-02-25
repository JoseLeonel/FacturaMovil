package com.emprendesoftcr.web.command;

import java.util.Date;

import javax.persistence.Column;

import com.emprendesoftcr.Utils.Utils;
import com.emprendesoftcr.fisco.MapEnums;
import com.emprendesoftcr.modelo.Hacienda;

/**
 * Status de los documentos HaciendaCommand.
 * @author jose.
 * @since 29 jul. 2018
 */
public class HaciendaCommand {

	private Long		id;

	private String	tipoDoc;
	private String	tipoDocumento;

	private String		fechaEmisor;

	private String	fechaEmisorSTR;

	private String	clave;

	private String	nombreReceptor;

	private String	correoReceptor;

	private Double	totalReceptor;

	private String	consecutivo;
	private String	estado;

	private Date		created_at;
	private String	created_atSTR;

	private Date		updated_at;

	public HaciendaCommand() {
		super();
	}

	public HaciendaCommand(Hacienda hacienda) {
		this.id = hacienda.getId();
		this.fechaEmisorSTR = Utils.getFechaGeneraReporte(hacienda.getFechaEmisor());
		this.created_atSTR = Utils.getFechaGeneraReporte(hacienda.getCreated_at());
		this.fechaEmisor = Utils.getFechaGeneraHacienda(hacienda.getCreated_at());
		this.clave = hacienda.getClave();
		this.consecutivo = hacienda.getConsecutivo();
		this.estado = MapEnums.ENUM_ESTADO_HACIENTA.get(hacienda.getEstado());
		this.created_at = hacienda.getCreated_at();
		this.updated_at = hacienda.getUpdated_at();
		this.tipoDoc = MapEnums.ENUM_TIPO_DOC.get(hacienda.getTipoDoc());
		this.nombreReceptor = hacienda.getNombreReceptor();
		this.correoReceptor = hacienda.getCorreoReceptor();
		this.totalReceptor = hacienda.getTotalReceptor();
		this.tipoDocumento = hacienda.getTipoDoc();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipoDoc() {
		return tipoDoc;
	}

	public void setTipoDoc(String tipoDoc) {
		this.tipoDoc = tipoDoc;
	}

	
	
	public String getCreated_atSTR() {
		return created_atSTR;
	}

	
	public void setCreated_atSTR(String created_atSTR) {
		this.created_atSTR = created_atSTR;
	}


	
	public String getFechaEmisor() {
		return fechaEmisor;
	}

	
	public void setFechaEmisor(String fechaEmisor) {
		this.fechaEmisor = fechaEmisor;
	}

	public String getFechaEmisorSTR() {
		return fechaEmisorSTR;
	}

	public void setFechaEmisorSTR(String fechaEmisorSTR) {
		this.fechaEmisorSTR = fechaEmisorSTR;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getConsecutivo() {
		return consecutivo;
	}

	public void setConsecutivo(String consecutivo) {
		this.consecutivo = consecutivo;
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

	public String getNombreReceptor() {
		return nombreReceptor;
	}

	public void setNombreReceptor(String nombreReceptor) {
		this.nombreReceptor = nombreReceptor;
	}

	public String getCorreoReceptor() {
		return correoReceptor;
	}

	public void setCorreoReceptor(String correoReceptor) {
		this.correoReceptor = correoReceptor;
	}

	public Double getTotalReceptor() {
		return totalReceptor;
	}

	public void setTotalReceptor(Double totalReceptor) {
		this.totalReceptor = totalReceptor;
	}

	
	public String getTipoDocumento() {
		return tipoDocumento;
	}

	
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	
	

}
