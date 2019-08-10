package com.emprendesoftcr.modelo.sqlNativo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@BaseNativeQuery(name = "cons_comp_iva", 
query = "select @s\\:=@s+1 as id_consulta, rfd.impuesto_codigo, rfd.impuesto_codigo_tarifa, rfd.impuesto_monto, i.descripcion, sum(rfd.impuesto_neto * rf.tipo_cambio) as total_impuesto, sum(rfd.monto_total_linea * rf.tipo_cambio) as total_ventas"   
		+ " from " 
		+ " (select @s\\:=0) as s, "
		+ " recepcion_factura_detalle rfd inner join  recepcion_factura rf on rf.id = rfd.recepcion_factura_id " 
		+ " inner join hacienda_xml h on  h.consecutivo = rf.factura_consecutivo " 
		+ " inner join tarifa t on rfd.impuesto_codigo = t.tipo_imp " 
		+ " inner join tarifaivai i on t.tarifa_id = i.cod_tarifa " 
		+ " where " 
		+ " rf.tipo_doc != 3 and " 
		+ " rf.empresa_id = 1 and and " 
		+ " rf.estado_firma = 2 and and "
		+ " rf.fecha_emision >= :FECHAINICIAL and "
		+ " rf.fecha_emision <= :FECHAFINAL and "
		+ " rfd.impuesto_codigo_tarifa != '' and "
		+ " h.estado = :ESTADO and "
		+ " rfd.impuesto_codigo_tarifa = i.cod_tarifa and "
		+ " rfd.impuesto_codigo = t.tipo_imp "
		+ " group by  "
		+ " rfd.impuesto_codigo, rfd.impuesto_codigo_tarifa, rfd.impuesto_monto, i.descripcion "
		+ " order by  "
		+ " id_consulta, rfd.impuesto_codigo")


@Entity
public class ConsultaComprasIvaNative implements Serializable {
	
	@Id
	@Column(name = "id_consulta")
	private Long							id;
	
	@Column(name = "impuesto_codigo")
	private String tipoImpuesto;
	
	@Column(name = "impuesto_codigo_tarifa")
	private String codTarifa;
			
	@Column(name = "impuesto_monto")
	private Double impuesto;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	@Column(name = "total_impuesto")
	private Double totalImpuesto;
	
	@Column(name = "total_ventas")
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

	public String getCodTarifa() {
		return codTarifa;
	}

	public void setCodTarifa(String codTarifa) {
		this.codTarifa = codTarifa;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codTarifa == null) ? 0 : codTarifa.hashCode());
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
		ConsultaComprasIvaNative other = (ConsultaComprasIvaNative) obj;
		if (codTarifa == null) {
			if (other.codTarifa != null)
				return false;
		} else if (!codTarifa.equals(other.codTarifa))
			return false;
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
