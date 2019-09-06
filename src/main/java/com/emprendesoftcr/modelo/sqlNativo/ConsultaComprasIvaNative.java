package com.emprendesoftcr.modelo.sqlNativo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@BaseNativeQuery(name = "cons_comp_iva", 
query = "(select " 
          + " @a\\:=@a+1 as id_consulta, "  
		  + " d.impuesto_codigo, "  
		  + " d.impuesto_codigo_tarifa, " 
		  + " d.impuesto_tarifa, " 
		  + " sum(IFNULL(d.impuesto_monto, 0)   * f.tipo_cambio) as total_impuesto, " 
		  + " sum(IFNULL(d.monto_total_linea, 0) * f.tipo_cambio) as total_ventas, "
		  + " sum(IFNULL(d.imp_exo_monto, 0)     * f.tipo_cambio) as total_exoneraciones "
		+ " from " 
		+ " (select @a\\:=0) as a, " 
		   + " recepcion_factura_detalle d inner join  recepcion_factura f on f.id = d.recepcion_factura_id "
		+ " where "
		+ " f.tipo_doc                  != '03'            and "  
		+ " f.tipo_doc                  != '02'            and "
		   + " f.empresa_id              = :ID_EMPRESA     and "
		   + " f.cod_actividad           :ACT_COMERCIAL   and "
		   + " f.estado                  = :ESTADO         and "
		   + " f.created_at BETWEEN :FECHAINICIAL          and :FECHAFINAL and "
		   + " d.impuesto_codigo_tarifa != ''              and "
		   + " d.impuesto_codigo        != '' "
		+ " group by "
		   + " d.impuesto_codigo, d.impuesto_codigo_tarifa, d.impuesto_tarifa "
		+ " order by "
		   + " id_consulta, d.impuesto_codigo "
		+ " ) "
		+ " union "
		+ " ( "
		+ " select " 
        + " @a\\:=@a+1 as id_consulta, "  
		  + " d.impuesto_codigo, "  
		  + " d.impuesto_codigo_tarifa, " 
		  + " d.impuesto_tarifa, " 
		  + " sum(IFNULL(d.impuesto_monto, 0)   * f.tipo_cambio) as total_impuesto, " 
		  + " sum(IFNULL(d.monto_total_linea, 0) * f.tipo_cambio) as total_ventas, "
		  + " sum(IFNULL(d.imp_exo_monto, 0)     * f.tipo_cambio) as total_exoneraciones "
		+ " from  "
		+ " (select @b\\:=1000) as b, "
		   + " recepcion_factura_detalle d inner join  recepcion_factura f on f.id = d.recepcion_factura_id "
		+ " where "
		+ " f.tipo_doc                  != '03'            and "  
		+ " f.tipo_doc                  != '02'            and "
		   + " f.empresa_id              = :ID_EMPRESA     and "
		   + " f.cod_actividad            :ACT_COMERCIAL  and "
		   + " f.estado                  = :ESTADO         and "
		   + " f.created_at BETWEEN :FECHAINICIAL          and :FECHAFINAL and "
		   + " d.impuesto_codigo_tarifa != ''              and "
		   + " d.impuesto_codigo         = '' "          
		+ " group by "
		   + " d.impuesto_codigo, d.impuesto_codigo_tarifa, d.impuesto_tarifa "
		+ " order by "
		   + " id_consulta, d.impuesto_codigo "
		+ " ) "
		+ " union "
		+ " ( "
		+ " select " 
        + " @a\\:=@a+1 as id_consulta, "  
		  + " d.impuesto_codigo, "  
		  + " d.impuesto_codigo_tarifa, " 
		  + " d.impuesto_tarifa, " 
		  + " sum(IFNULL(d.impuesto_monto, 0)   * f.tipo_cambio) as total_impuesto, " 
		  + " sum(IFNULL(d.monto_total_linea, 0) * f.tipo_cambio) as total_ventas, "
		  + " sum(IFNULL(d.imp_exo_monto, 0)     * f.tipo_cambio) as total_exoneraciones "
		+ " from "
		+ " (select @c\\:=10000) as c, "
		   + " recepcion_factura_detalle d inner join  recepcion_factura f on f.id = d.recepcion_factura_id "
		+ " where "
		+ " f.tipo_doc                  != '03'            and "  
		+ " f.tipo_doc                  != '02'            and "
		   + " f.empresa_id              = :ID_EMPRESA     and "
		   + " f.cod_actividad             :ACT_COMERCIAL  and "
		   + " f.estado                  = :ESTADO         and "
		   + " f.created_at BETWEEN :FECHAINICIAL          and :FECHAFINAL and "
		   + " d.impuesto_codigo_tarifa = ''               and "
		   + " d.impuesto_codigo        = '' "           
		+ " group by "
		   + " d.impuesto_codigo, d.impuesto_codigo_tarifa, d.impuesto_tarifa"
		+ " order by "
		   + " id_consulta, d.impuesto_codigo "
		+ " )"
		)

@Entity
public class ConsultaComprasIvaNative implements Serializable {
	
	/**
	 * Comentario para <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = -865132227659867968L;

	@Id
	@Column(name = "id_consulta")
	private Long							id;
	
	@Column(name = "impuesto_codigo")
	private String tipoImpuesto;
	
	@Column(name = "impuesto_codigo_tarifa")
	private String codTarifa;
			
	@Column(name = "impuesto_tarifa")
	private Double impuesto;
	
	
	@Column(name = "total_impuesto")
	private Double totalImpuesto;
	
	@Column(name = "total_ventas")
	private Double totalVentas;

	@Column(name = "total_exoneraciones")
	private Double totalExoneraciones;

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

	public Double getTotalExoneraciones() {
		return totalExoneraciones;
	}

	public void setTotalExoneraciones(Double totalExoneraciones) {
		this.totalExoneraciones = totalExoneraciones;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codTarifa == null) ? 0 : codTarifa.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((impuesto == null) ? 0 : impuesto.hashCode());
		result = prime * result + ((tipoImpuesto == null) ? 0 : tipoImpuesto.hashCode());
		result = prime * result + ((totalExoneraciones == null) ? 0 : totalExoneraciones.hashCode());
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
		if (totalExoneraciones == null) {
			if (other.totalExoneraciones != null)
				return false;
		} else if (!totalExoneraciones.equals(other.totalExoneraciones))
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
