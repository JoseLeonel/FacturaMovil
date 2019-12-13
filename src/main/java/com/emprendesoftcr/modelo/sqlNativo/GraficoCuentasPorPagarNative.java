package com.emprendesoftcr.modelo.sqlNativo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@BaseNativeQuery(name ="graf_cuentxpagarr" ,query ="SELECT cuentas_pagar.id,\n" + 
		"        cuentas_pagar.consecutivo,  \n" + 
		"        proveedores.nombre_completo,\n" + 
		"        cuentas_pagar.fecha_credito,\n" + 
		"        cuentas_pagar.total_saldo, \n" + 
		"        DATEDIFF(cuentas_pagar.fecha_credito, now()) AS total_dias_morosos \n" + 
		"        FROM cuentas_pagar \n" + 
		"        inner join proveedores on proveedores.id = cuentas_pagar.proveedor_id\n" +
		"        WHERE cuentas_pagar.empresa_id = :ID_EMPRESA  and cuentas_pagar.total_saldo > 0 and DATEDIFF(cuentas_pagar.fecha_credito, now()) < 0" +
		"        ORDER by total_dias_morosos ASC LIMIT 50")
@Entity
public class GraficoCuentasPorPagarNative implements Serializable {

	
	private static final long serialVersionUID = 4811593761608975759L;

	@Id
	@Column(name = "id")
	private Long							id;

	@Column(name = "consecutivo")
	private String						consecutivo;

	
	@Column(name = "total_saldo")
	private Double						totalSaldo;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY")
	@Column(name = "fecha_credito")
	private Date							fechaCredito;
	
	@Column(name = "nombre_completo")
	private String nombreCompleto;

	@Column(name = "total_dias_morosos")
	private Integer totalDiasMorosos;
	
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

	
	public Double getTotalSaldo() {
		return totalSaldo;
	}

	
	public void setTotalSaldo(Double totalSaldo) {
		this.totalSaldo = totalSaldo;
	}

	
	public Date getFechaCredito() {
		return fechaCredito;
	}

	
	public void setFechaCredito(Date fechaCredito) {
		this.fechaCredito = fechaCredito;
	}

	
	public String getNombreCompleto() {
		return nombreCompleto;
	}

	
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}


	
	public Integer getTotalDiasMorosos() {
		return totalDiasMorosos;
	}


	
	public void setTotalDiasMorosos(Integer totalDiasMorosos) {
		this.totalDiasMorosos = totalDiasMorosos;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((consecutivo == null) ? 0 : consecutivo.hashCode());
		result = prime * result + ((fechaCredito == null) ? 0 : fechaCredito.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nombreCompleto == null) ? 0 : nombreCompleto.hashCode());
		result = prime * result + ((totalDiasMorosos == null) ? 0 : totalDiasMorosos.hashCode());
		result = prime * result + ((totalSaldo == null) ? 0 : totalSaldo.hashCode());
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
		GraficoCuentasPorPagarNative other = (GraficoCuentasPorPagarNative) obj;
		if (consecutivo == null) {
			if (other.consecutivo != null)
				return false;
		} else if (!consecutivo.equals(other.consecutivo))
			return false;
		if (fechaCredito == null) {
			if (other.fechaCredito != null)
				return false;
		} else if (!fechaCredito.equals(other.fechaCredito))
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
		if (totalDiasMorosos == null) {
			if (other.totalDiasMorosos != null)
				return false;
		} else if (!totalDiasMorosos.equals(other.totalDiasMorosos))
			return false;
		if (totalSaldo == null) {
			if (other.totalSaldo != null)
				return false;
		} else if (!totalSaldo.equals(other.totalSaldo))
			return false;
		return true;
	}


	
	
  
}
