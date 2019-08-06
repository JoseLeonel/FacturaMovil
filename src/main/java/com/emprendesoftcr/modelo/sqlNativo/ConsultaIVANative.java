package com.emprendesoftcr.modelo.sqlNativo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;

@BaseNativeQuery(name = "cons_vent_iva", 
query = "select d.id,  d.tipo_impuesto, d.impuesto, i.descripcion, sum(d.imp_neto * f.tipo_cambio) as totalImpuesto, sum(d.monto_total_linea * f.tipo_cambio) as totalVentas"   
		+ " from " 
		+ " detalles d  inner join facturas f on f.id = d.factura_id " 
		+ " inner join hacienda_xml h on  h.consecutivo = f.numero_consecutivo " 
		+ " inner join tarifa t on d.tipo_impuesto = t.tipo_imp " 
		+ " inner join tarifaivai i on t.tarifa_id = i.cod_tarifa " 
		+ " where " 
		+ " f.ref_tipo_doc != 3 and " 
		+ " f.empresa_id    = 1 and " 
		+ " f.estado        = 2 and "
		+ " f.fecha_emision >= :FECHAINICIAL and "
		+ " f.fecha_emision <= :FECHAFINAL and "
		+ " d.cod_tarifa    != ''  and"
		+ " f.estado        = 2 and "
		+ " d.cod_tarifa    = i.cod_tarifa and "
		+ " d.tipo_impuesto = t.tipo_imp "
		+ " group by  "
		+ " d.id, d.tipo_impuesto, d.impuesto, i.descripcion  "
		+ " order by  "
		+ " d.tipo_impuesto")

public class ConsultaIVANative implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8189598032481010858L;
	
	@Id
	@Column(name = "id")
	private Long		id;

	@Column(name = "tipo_impuesto")
	private String tipoImpuesto;
	
	@Column(name = "impuesto")
	private Double impuesto;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	@Column(name = "totalImpuesto")
	private Double totalImpuesto;
	
	@Column(name = "totalVentas")
	private Double totalVentas;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipoImpuesto() {
		return tipoImpuesto;
	}

	public void setTipoImpuesto(String tipoImpuesto) {
		this.tipoImpuesto = tipoImpuesto;
	}

	public Double getImpuesto() {
		return impuesto;
	}

	public void setImpuesto(Double impuesto) {
		this.impuesto = impuesto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Double getTotalImpuesto() {
		return totalImpuesto;
	}

	public void setTotalImpuesto(Double totalImpuesto) {
		this.totalImpuesto = totalImpuesto;
	}

	public Double getTotalVentas() {
		return totalVentas;
	}

	public void setTotalVentas(Double totalVentas) {
		this.totalVentas = totalVentas;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((impuesto == null) ? 0 : impuesto.hashCode());
		result = prime * result + ((tipoImpuesto == null) ? 0 : tipoImpuesto.hashCode());
		result = prime * result + ((totalImpuesto == null) ? 0 : totalImpuesto.hashCode());
		result = prime * result + ((totalVentas == null) ? 0 : totalVentas.hashCode());
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
		ConsultaIVANative other = (ConsultaIVANative) obj;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (impuesto == null) {
			if (other.impuesto != null)
				return false;
		} else if (!impuesto.equals(other.impuesto))
			return false;
		if (tipoImpuesto == null) {
			if (other.tipoImpuesto != null)
				return false;
		} else if (!tipoImpuesto.equals(other.tipoImpuesto))
			return false;
		if (totalImpuesto == null) {
			if (other.totalImpuesto != null)
				return false;
		} else if (!totalImpuesto.equals(other.totalImpuesto))
			return false;
		if (totalVentas == null) {
			if (other.totalVentas != null)
				return false;
		} else if (!totalVentas.equals(other.totalVentas))
			return false;
		return true;
	}

}
