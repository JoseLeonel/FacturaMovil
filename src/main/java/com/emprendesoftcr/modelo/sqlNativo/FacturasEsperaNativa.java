package com.emprendesoftcr.modelo.sqlNativo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.emprendesoftcr.Utils.Constantes;

@BaseNativeQuery(name = "fact_espera	", query = "\n" + 
		"SELECT facturas.id,"
		+ "facturas.consecutivo_proforma,"
		+ "facturas.nombre_factura,"
		+ "facturas.numero_consecutivo, \n" 
		+ "clientes.nombre_completo \n" + 
		"       FROM facturas \n" +
		"       inner join clientes on clientes.id = facturas.cliente_id \n" + 
		"       WHERE facturas.empresa_id = :ID_EMPRESA  and facturas.estado in ('04','01','87')")
@Entity
public class FacturasEsperaNativa implements Serializable {
	
	private static final long serialVersionUID = 5604641152800995737L;


	@Id
	@Column(name = "id")
	private Long id;
	
	
	@Column(name = "numero_consecutivo")
	private String						numeroConsecutivo;

	@Column(name = "consecutivo_proforma")
	private String						consecutivoProforma;

	@Column(name = "nombre_completo")
	private String						nombreCompleto;

	@Column(name = "nombre_factura")
	private String						nombreFactura;

	
	public Long getId() {
		return id;
	}

	
	public void setId(Long id) {
		this.id = id;
	}

	
	public String getNumeroConsecutivo() {
		
		return numeroConsecutivo == null?Constantes.EMPTY:numeroConsecutivo;
	}

	
	public void setNumeroConsecutivo(String numeroConsecutivo) {
		this.numeroConsecutivo = numeroConsecutivo;
	}

	
	public String getConsecutivoProforma() {
		return consecutivoProforma ==null?Constantes.EMPTY:consecutivoProforma;
	}

	
	public void setConsecutivoProforma(String consecutivoProforma) {
		this.consecutivoProforma = consecutivoProforma;
	}


	
	public String getNombreCompleto() {
		return nombreCompleto == null?Constantes.EMPTY:nombreCompleto;
	}


	
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	
	

	
	public String getNombreFactura() {
		return nombreFactura ==null?Constantes.EMPTY:nombreFactura;
	}


	
	public void setNombreFactura(String nombreFactura) {
		this.nombreFactura = nombreFactura;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((consecutivoProforma == null) ? 0 : consecutivoProforma.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nombreCompleto == null) ? 0 : nombreCompleto.hashCode());
		result = prime * result + ((nombreFactura == null) ? 0 : nombreFactura.hashCode());
		result = prime * result + ((numeroConsecutivo == null) ? 0 : numeroConsecutivo.hashCode());
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
		FacturasEsperaNativa other = (FacturasEsperaNativa) obj;
		if (consecutivoProforma == null) {
			if (other.consecutivoProforma != null)
				return false;
		} else if (!consecutivoProforma.equals(other.consecutivoProforma))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nombreCompleto == null) {
			if (other.nombreCompleto != null)
				return false;
		} else if (!nombreCompleto.equals(other.nombreCompleto))
			return false;
		if (nombreFactura == null) {
			if (other.nombreFactura != null)
				return false;
		} else if (!nombreFactura.equals(other.nombreFactura))
			return false;
		if (numeroConsecutivo == null) {
			if (other.numeroConsecutivo != null)
				return false;
		} else if (!numeroConsecutivo.equals(other.numeroConsecutivo))
			return false;
		return true;
	}



	
	
	
	
	

}
