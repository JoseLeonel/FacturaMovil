package com.emprendesoftcr.modelo.sqlNativo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@BaseNativeQuery(name = "cons_vent_iva", 
query = "( select @a\\:=@a+1 as id_consulta, 	d.tipo_impuesto, d.cod_tarifa, d.impuesto, i.descripcion, sum(d.imp_neto * f.tipo_cambio) as total_impuesto, sum(d.monto_total_linea * f.tipo_cambio) as total_ventas "   
		+ " from " 
		+ " (select @a\\:=0) as a, "
		+ " detalles d  inner join facturas f on f.id = d.factura_id " 
		+ " inner join tarifa t on d.tipo_impuesto = t.tipo_imp " 
		+ " inner join tarifaivai i on t.tarifa_id = i.cod_tarifa " 
		+ " where " 
			+ " f.ref_codigo    != '01'                             and " 
			+ " f.empresa_id     = :ID_EMPRESA                      and " 
			+ " f.act_comercial  = :ACT_COMERCIAL                   and "
			+ " f.estado         = :ESTADO                          and "
            + " f.created_at BETWEEN :FECHAINICIAL  and :FECHAFINAL and "
			+ " d.tipo_impuesto != ''                               and "
			+ " d.cod_tarifa    != ''                               and "
			+ " d.cod_tarifa     = i.cod_tarifa                     and "
			+ " d.tipo_impuesto  = t.tipo_imp "
		+ " group by  "
		+ " d.tipo_impuesto, d.cod_tarifa, d.impuesto, i.descripcion  "
		+ " order by  "
		+ " id_consulta, d.tipo_impuesto "
		+ " ) "
		+ " union "
		+ " ( "
		+ " select @b\\:=@b+1 as id_consulta, d.tipo_impuesto, d.cod_tarifa, d.impuesto, @txt as descripcion, sum(d.imp_neto * f.tipo_cambio) as total_impuesto, sum(d.monto_total_linea * f.tipo_cambio) as total_ventas "
		+ " from "
		+ " (select @b\\:=1000) as b, "
		+ " (select @txt\\:=\'No tiene descripcion\') as txt, "
		+ " detalles d  inner join facturas f on f.id = d.factura_id " 
		+ " where "
			+ " f.ref_codigo    != '01'                             and "
			+ " f.empresa_id     = :ID_EMPRESA                      and "
			+ " f.act_comercial  = :ACT_COMERCIAL                   and "
			+ " f.estado         = :ESTADO                          and "
            + " f.created_at BETWEEN :FECHAINICIAL  and :FECHAFINAL and "
			+ " d.tipo_impuesto != ''                               and "
			+ " d.cod_tarifa     = '' "
		+ " group by " 
	    + " d.tipo_impuesto, d.cod_tarifa, d.impuesto, descripcion "
	    + " order by "
		+ " id_consulta, d.tipo_impuesto "
		+ " ) "
	    + " union "
	    + " ( "
	    + " select @c\\:=@c+1 as id_consulta, d.tipo_impuesto, d.cod_tarifa, d.impuesto, @txt as descripcion, sum(d.imp_neto * f.tipo_cambio) as total_impuesto, sum(d.monto_total_linea * f.tipo_cambio) as total_ventas "   
	    + " from "
	    + " (select @c\\:=10000) as c, "
	    + " (select @txt\\:=\'No tiene descripcion\') as txt, "
	    + " detalles d  inner join facturas f on f.id = d.factura_id "
	    + " where "
		    + " f.ref_codigo      != '01'                           and "
		    + " f.empresa_id       = :ID_EMPRESA                    and "
		    + " f.act_comercial    = :ACT_COMERCIAL                 and "
		    + " f.estado           = :ESTADO                        and "
            + " f.created_at BETWEEN :FECHAINICIAL  and :FECHAFINAL and "
		    + " d.tipo_impuesto    = ''                             and "
		    + " d.cod_tarifa       = '' " 
	    + " group by "
	    	+ " d.tipo_impuesto, d.cod_tarifa, d.impuesto, descripcion "
	    + " order by "
	    	+ " id_consulta, d.tipo_impuesto "
	    + " ) "	
		)


@Entity
public class ConsultaIVANative implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8189598032481010858L;
	
	@Id
	@Column(name = "id_consulta")
	private Long							id;
	
	@Column(name = "tipo_impuesto")
	private String tipoImpuesto;
	
	@Column(name = "cod_tarifa")
	private String codTarifa;
			
	@Column(name = "impuesto")
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

	public static long getSerialversionuid() {
		return serialVersionUID;
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
		ConsultaIVANative other = (ConsultaIVANative) obj;
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
