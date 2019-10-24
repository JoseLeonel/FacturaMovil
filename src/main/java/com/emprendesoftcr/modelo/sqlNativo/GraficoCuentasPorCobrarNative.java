package com.emprendesoftcr.modelo.sqlNativo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@BaseNativeQuery(name ="graf_cuentxcobrar" ,query ="SELECT cuentas_cobrar.id,\n" + 
		"        cuentas_cobrar.factura,  \n" + 
		"        clientes.nombre_completo,\n" + 
		"        cuentas_cobrar.fecha_plazo,\n" + 
		"        cuentas_cobrar.total_saldo, \n" + 
		"        DATEDIFF(cuentas_cobrar.fecha_plazo, now()) AS dias \n" + 
		"        FROM cuentas_cobrar \n" + 
		"        inner join clientes on clientes.id = cuentas_cobrar.cliente_id\n" +
		"        WHERE cuentas_cobrar.empresa_id = :ID_EMPRESA  and cuentas_cobrar.total_saldo > 0 and DATEDIFF(cuentas_cobrar.fecha_plazo, now()) < 0" +
		"        ORDER by dias ASC LIMIT 50")
@Entity
public class GraficoCuentasPorCobrarNative implements Serializable {

	private static final long serialVersionUID = -8130193379985566861L;

	@Id
	@Column(name = "id")
	private Long							id;

	@Column(name = "factura")
	private String						numeroFactura;
	
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY")
	@Column(name = "fecha_plazo")
	private Date							fechaPlazo;
	
	
	@Column(name = "total_saldo")
	private Double						totalSaldo;
	
	@Column(name = "dias")
	private Double						totalDiasMoroso;
	
	@Column(name = "nombre_completo")
	private String						nombreCompleto;

	
	public Long getId() {
		return id;
	}

	
	public void setId(Long id) {
		this.id = id;
	}

	
	public String getNumeroFactura() {
		return numeroFactura;
	}

	
	public void setNumeroFactura(String numeroFactura) {
		this.numeroFactura = numeroFactura;
	}

	
	public Date getFechaPlazo() {
		return fechaPlazo;
	}

	
	public void setFechaPlazo(Date fechaPlazo) {
		this.fechaPlazo = fechaPlazo;
	}

	
	public Double getTotalSaldo() {
		return totalSaldo;
	}

	
	public void setTotalSaldo(Double totalSaldo) {
		this.totalSaldo = totalSaldo;
	}

	
	public Double getTotalDiasMoroso() {
		return totalDiasMoroso;
	}

	
	public void setTotalDiasMoroso(Double totalDiasMoroso) {
		this.totalDiasMoroso = totalDiasMoroso;
	}

	
	public String getNombreCompleto() {
		return nombreCompleto;
	}

	
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fechaPlazo == null) ? 0 : fechaPlazo.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nombreCompleto == null) ? 0 : nombreCompleto.hashCode());
		result = prime * result + ((numeroFactura == null) ? 0 : numeroFactura.hashCode());
		result = prime * result + ((totalDiasMoroso == null) ? 0 : totalDiasMoroso.hashCode());
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
		GraficoCuentasPorCobrarNative other = (GraficoCuentasPorCobrarNative) obj;
		if (fechaPlazo == null) {
			if (other.fechaPlazo != null)
				return false;
		} else if (!fechaPlazo.equals(other.fechaPlazo))
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
		if (numeroFactura == null) {
			if (other.numeroFactura != null)
				return false;
		} else if (!numeroFactura.equals(other.numeroFactura))
			return false;
		if (totalDiasMoroso == null) {
			if (other.totalDiasMoroso != null)
				return false;
		} else if (!totalDiasMoroso.equals(other.totalDiasMoroso))
			return false;
		if (totalSaldo == null) {
			if (other.totalSaldo != null)
				return false;
		} else if (!totalSaldo.equals(other.totalSaldo))
			return false;
		return true;
	}
	
	
	
	

}
