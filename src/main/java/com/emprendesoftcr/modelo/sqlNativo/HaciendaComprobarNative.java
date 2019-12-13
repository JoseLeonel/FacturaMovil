package com.emprendesoftcr.modelo.sqlNativo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@BaseNativeQuery(name = "hacienda_comprobar", query = "SELECT hacienda_xml.id,"
		+ "hacienda_xml.consecutivo,"
		+ "hacienda_xml.empresa_id, "
		+ "hacienda_xml.reintentos_aceptacion, "
		+ "hacienda_xml.created_at, "
		+ "hacienda_xml.estado, "
		+ "hacienda_xml.tipo_doc "
		+ ",empresas.nombre FROM hacienda_xml " 
    + " LEFT join empresas on empresas.id = hacienda_xml.empresa_id "
    + "WHERE hacienda_xml.estado = 3 or hacienda_xml.estado = 10 "
		+ " ORDER by hacienda_xml.empresa_id  asc LIMIT 80" )
@Entity
public class HaciendaComprobarNative implements Serializable {
	
	
	private static final long serialVersionUID = 2788245458119235490L;

	@Id
	@Column(name = "id")
	private Long							id;
	
	@Column(name = "consecutivo")
	private String						consecutivo;
	
	@Column(name = "reintentos_aceptacion")
	private Integer						reintentosAceptacion;
	
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "created_at")
	private Date							created_at;

	@Column(name = "estado")
	private Integer						estado;
	
	@Column(name = "tipo_doc")
	private String						tipoDoc;

	@Column(name = "nombre")
	private String						nombreEmpresa;

	
	public Long getId() {
		return id;
	}

	
	public void setId(Long id) {
		this.id = id;
	}

	
	public String getConsecutivo() {
		return consecutivo;
	}

	
	public void setConsecutivo(String consecutivo) {
		this.consecutivo = consecutivo;
	}

	
	public Integer getReintentosAceptacion() {
		return reintentosAceptacion;
	}

	
	public void setReintentosAceptacion(Integer reintentosAceptacion) {
		this.reintentosAceptacion = reintentosAceptacion;
	}

	
	public Date getCreated_at() {
		return created_at;
	}

	
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	
	public Integer getEstado() {
		return estado;
	}

	
	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	
	public String getTipoDoc() {
		return tipoDoc;
	}

	
	public void setTipoDoc(String tipoDoc) {
		this.tipoDoc = tipoDoc;
	}

	
	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	
	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((consecutivo == null) ? 0 : consecutivo.hashCode());
		result = prime * result + ((created_at == null) ? 0 : created_at.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nombreEmpresa == null) ? 0 : nombreEmpresa.hashCode());
		result = prime * result + ((reintentosAceptacion == null) ? 0 : reintentosAceptacion.hashCode());
		result = prime * result + ((tipoDoc == null) ? 0 : tipoDoc.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HaciendaComprobarNative other = (HaciendaComprobarNative) obj;
		if (consecutivo == null) {
			if (other.consecutivo != null)
				return false;
		} else if (!consecutivo.equals(other.consecutivo))
			return false;
		if (created_at == null) {
			if (other.created_at != null)
				return false;
		} else if (!created_at.equals(other.created_at))
			return false;
		if (estado == null) {
			if (other.estado != null)
				return false;
		} else if (!estado.equals(other.estado))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nombreEmpresa == null) {
			if (other.nombreEmpresa != null)
				return false;
		} else if (!nombreEmpresa.equals(other.nombreEmpresa))
			return false;
		if (reintentosAceptacion == null) {
			if (other.reintentosAceptacion != null)
				return false;
		} else if (!reintentosAceptacion.equals(other.reintentosAceptacion))
			return false;
		if (tipoDoc == null) {
			if (other.tipoDoc != null)
				return false;
		} else if (!tipoDoc.equals(other.tipoDoc))
			return false;
		return true;
	}
	
	
	
	
	
}
