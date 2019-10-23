package com.emprendesoftcr.modelo.sqlNativo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.emprendesoftcr.modelo.Empresa;

@BaseNativeQuery(name = "s_hacienda", query = "SELECT id,fecha_emisor,tipo_doc,consecutivo,nombre_receptor,total_receptor,estado,empresa_id,created_at from hacienda_xml" 
                                       + " where hacienda_xml.empresa_id = :ID_EMPRESA and created_at >=  :fechaInicial and  created_at <=  :fechaFinal")
@Entity
public class HaciendaNative implements Serializable {

	private static final long	serialVersionUID	= 5100288651728474300L;

	@Id
	@Column(name = "id")
	private Long							id;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd’T’HH:mm:ssZ")
	@Column(name = "fecha_emisor")
	private Date							fechaEmisor;

	@Column(name = "tipo_doc")
	private String						tipoDoc;

	@Column(name = "total_receptor")
	private Double						totalReceptor;

	@Column(name = "consecutivo")
	private String						consecutivo;
	@Column(name = "estado")
	private Integer						estado;

	@ManyToOne
	@JoinColumn(name = "empresa_id")
	private Empresa						empresa;

	@Column(name = "nombre_receptor")
	private String						nombreReceptor;
	
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "created_at")
	private Date							created_at;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFechaEmisor() {
		return fechaEmisor;
	}

	public void setFechaEmisor(Date fechaEmisor) {
		this.fechaEmisor = fechaEmisor;
	}

	public String getTipoDoc() {
		return tipoDoc;
	}

	public void setTipoDoc(String tipoDoc) {
		this.tipoDoc = tipoDoc;
	}

	public Double getTotalReceptor() {
		return totalReceptor;
	}

	public void setTotalReceptor(Double totalReceptor) {
		this.totalReceptor = totalReceptor;
	}

	public String getConsecutivo() {
		return consecutivo;
	}

	public void setConsecutivo(String consecutivo) {
		this.consecutivo = consecutivo;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public String getNombreReceptor() {
		return nombreReceptor;
	}

	public void setNombreReceptor(String nombreReceptor) {
		this.nombreReceptor = nombreReceptor;
	}

	
	
	
	public Date getCreated_at() {
		return created_at;
	}

	
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((consecutivo == null) ? 0 : consecutivo.hashCode());
		result = prime * result + ((created_at == null) ? 0 : created_at.hashCode());
		result = prime * result + ((empresa == null) ? 0 : empresa.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + ((fechaEmisor == null) ? 0 : fechaEmisor.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nombreReceptor == null) ? 0 : nombreReceptor.hashCode());
		result = prime * result + ((tipoDoc == null) ? 0 : tipoDoc.hashCode());
		result = prime * result + ((totalReceptor == null) ? 0 : totalReceptor.hashCode());
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
		HaciendaNative other = (HaciendaNative) obj;
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
		if (empresa == null) {
			if (other.empresa != null)
				return false;
		} else if (!empresa.equals(other.empresa))
			return false;
		if (estado == null) {
			if (other.estado != null)
				return false;
		} else if (!estado.equals(other.estado))
			return false;
		if (fechaEmisor == null) {
			if (other.fechaEmisor != null)
				return false;
		} else if (!fechaEmisor.equals(other.fechaEmisor))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nombreReceptor == null) {
			if (other.nombreReceptor != null)
				return false;
		} else if (!nombreReceptor.equals(other.nombreReceptor))
			return false;
		if (tipoDoc == null) {
			if (other.tipoDoc != null)
				return false;
		} else if (!tipoDoc.equals(other.tipoDoc))
			return false;
		if (totalReceptor == null) {
			if (other.totalReceptor != null)
				return false;
		} else if (!totalReceptor.equals(other.totalReceptor))
			return false;
		return true;
	}

	
}
