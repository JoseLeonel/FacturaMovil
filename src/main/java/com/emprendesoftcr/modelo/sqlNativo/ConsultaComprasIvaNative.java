package com.emprendesoftcr.modelo.sqlNativo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@BaseNativeQuery(name = "cons_comp_iva", 
query = "(select " 
          + " (@s\\:=@s+1) as id_consulta, "  
		  + " d.impuesto_codigo, "  
		  + " d.impuesto_codigo_tarifa, " 
		  + " d.impuesto_tarifa, "
		  + " i.descripcion, " 
		  + " sum((IF(d.impuesto_neto != 0 and d.impuesto_neto is not null, d.impuesto_neto, d.impuesto_monto)) * f.tipo_cambio) as total_impuesto, " 
		  + " sum(d.monto_total_linea * f.tipo_cambio) as total_ventas "
		+ " from " 
		   + " (select @s\\:=0) as s, "
		   + " recepcion_factura_detalle d inner join  recepcion_factura f on f.id = d.recepcion_factura_id "
		   + " inner join tarifa t on d.impuesto_codigo = t.tipo_imp "
		   + " inner join tarifaivai i on t.tarifa_id = i.cod_tarifa "
		+ " where "
		+ " f.tipo_doc                  != '03'            and "  
		+ " f.tipo_doc                  != '02'            and "
		   + " f.empresa_id              = :ID_EMPRESA     and "
		   + " f.cod_actividad           = :ACT_COMERCIAL  and "
		   + " f.estado                  = :ESTADO         and "
		   + " f.created_at BETWEEN :FECHAINICIAL          and :FECHAFINAL and "
		   + " d.impuesto_codigo_tarifa != ''              and "
		   + " d.impuesto_codigo        != ''              and "
		   + " d.impuesto_codigo_tarifa  = i.cod_tarifa    and "
		   + " d.impuesto_codigo         = t.tipo_imp  COLLATE 'latin1_spanish_ci' " 
		+ " group by "
		   + " d.impuesto_codigo, d.impuesto_codigo_tarifa, d.impuesto_tarifa, i.descripcion "
		+ " order by "
		   + " id_consulta, d.impuesto_codigo "
		+ " ) "
		+ " union "
		+ " ( "
		+ " select " 
		  + " (@s\\:=@s+1) as id_consulta, " 
		  + " d.impuesto_codigo, "
		  + " d.impuesto_codigo_tarifa, " 
		  + " d.impuesto_tarifa, "
		  + " @txt as descripcion, "
		  + " sum((IF(d.impuesto_neto != 0 and d.impuesto_neto is not null, d.impuesto_neto, d.impuesto_monto)) * f.tipo_cambio) as total_impuesto, " 
		  + " sum(d.monto_total_linea * f.tipo_cambio) as total_ventas "
		+ " from  "
		   + " (select @s\\:=1000) as s, "
		   + " (select @txt\\:=\'No tiene descripcion\') as txt, "
		   + " recepcion_factura_detalle d inner join  recepcion_factura f on f.id = d.recepcion_factura_id "
		+ " where "
		+ " f.tipo_doc                  != '03'            and "  
		+ " f.tipo_doc                  != '02'            and "
		   + " f.empresa_id              = :ID_EMPRESA     and "
		   + " f.cod_actividad           = :ACT_COMERCIAL  and "
		   + " f.estado                  = :ESTADO         and "
		   + " f.created_at BETWEEN :FECHAINICIAL          and :FECHAFINAL and "
		   + " d.impuesto_codigo_tarifa != ''              and "
		   + " d.impuesto_codigo         = ''  COLLATE 'latin1_spanish_ci' "          
		+ " group by "
		   + " d.impuesto_codigo, d.impuesto_codigo_tarifa, d.impuesto_tarifa, descripcion "
		+ " order by "
		   + " id_consulta, d.impuesto_codigo "
		+ " ) "
		+ " union "
		+ " ( "
		+ " select " 
		  + " (@s\\:=@s+1) as id_consulta, " 
		  + " d.impuesto_codigo, "
		  + " d.impuesto_codigo_tarifa, " 
		  + " d.impuesto_tarifa, "
		  + " @txt as descripcion, "
		  + " sum((IF(d.impuesto_neto != 0 and d.impuesto_neto is not null, d.impuesto_neto, d.impuesto_monto)) * f.tipo_cambio) as total_impuesto, " 
		  + " sum(d.monto_total_linea * f.tipo_cambio) as total_ventas "
		+ " from "
		   + " (select @s\\:=10000) as s, "
		   + " (select @txt\\:=\'No tiene descripcion\') as txt, "
		   + " recepcion_factura_detalle d inner join  recepcion_factura f on f.id = d.recepcion_factura_id "
		+ " where "
		+ " f.tipo_doc                  != '03'            and "  
		+ " f.tipo_doc                  != '02'            and "
		   + " f.empresa_id              = :ID_EMPRESA     and "
		   + " f.cod_actividad           = :ACT_COMERCIAL  and "
		   + " f.estado                  = :ESTADO         and "
		   + " f.created_at BETWEEN :FECHAINICIAL          and :FECHAFINAL and "
		   + " d.impuesto_codigo_tarifa = ''               and "
		   + " d.impuesto_codigo        = ''  COLLATE 'latin1_spanish_ci' "           
		+ " group by "
		   + " d.impuesto_codigo, d.impuesto_codigo_tarifa, d.impuesto_tarifa, descripcion "
		+ " order by "
		   + " id_consulta, d.impuesto_codigo "
		+ " )"
		)

@Entity
public class ConsultaComprasIvaNative implements Serializable {
	
	@Id
	@Column(name = "id_consulta")
	private Long							id;
	
	@Column(name = "impuesto_codigo")
	private String tipoImpuesto;
	
	@Column(name = "impuesto_codigo_tarifa")
	private String codTarifa;
			
	@Column(name = "impuesto_tarifa")
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
