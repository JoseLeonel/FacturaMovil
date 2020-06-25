package com.emprendesoftcr.modelo.sqlNativo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.Utils.Utils;

@BaseNativeQuery(name = "cons_vent_gana", query = "SELECT det.id,det.cantidad,fact.tipo_doc,articulos.tipo_codigo,fact.numero_consecutivo,"
		+ " DATE_FORMAT(fact.fecha_emision, \"%d-%c-%Y\") as fecha,"
		+ "categorias.descripcion as nomb_categ ,"
		+ "det.codigo,det.descripcion as nomb_product,"
		+ "IFNULL(det.monto_total_linea,0) as venta,"
		+ " IFNULL(det.costo,0) as costo"
		+ " FROM detalles det" + " inner join facturas fact on fact.id =det.factura_id " 
		+ " inner join clientes on clientes.id =fact.cliente_id " 
		+ " left join articulos on articulos.codigo =det.codigo " 
		+ " inner join categorias on categorias.id = articulos.categoria_id " 
		+ " where fact.empresa_id = :ID_EMPRESA fact.estado in and fact.tipo_doc !='88'  " 
		+ "and fact.fecha_emision >= :fechaInicial and fact.fecha_emision <= :fechaFinal "
		+ "and categorias.id = and fact.cliente_id and fact.act_comercial and det.codigo and fact.numero_consecutivo ")
@Entity
public class ConsultaUtilidadNative implements Serializable {

	private static final long	serialVersionUID	= 4020391061634341281L;

	@Id
	@Column(name = "id")
	private Long							id;

	@Column(name = "fecha")
	private String						fechaEmision;

	@Column(name = "codigo")
	private String						codigo;

	@Column(name = "tipo_doc")
	private String						tipoDoc;

	@Column(name = "nomb_product")
	private String						nombreArticulo;

	@Column(name = "nomb_categ")
	private String						nombreCategoria;

	@Column(name = "costo")
	private Double						costo;

	@Column(name = "venta")
	private Double						venta;

	@Column(name = "cantidad")
	private Double						cantidad;

	@Column(name = "tipo_codigo")
	private String						tipoCodigo;

	@Column(name = "numero_consecutivo")
	private String						numeroConsecutivo;

	public String getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(String fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombreArticulo() {
		return nombreArticulo =nombreArticulo.length()>=25?nombreArticulo.substring(0, 25)+"...":nombreArticulo;
	}

	public void setNombreArticulo(String nombreArticulo) {
		this.nombreArticulo = nombreArticulo;
	}

	public String getNombreCategoria() {
		return nombreCategoria.length() >= 25?nombreCategoria.substring(0, 25)+"...":nombreCategoria;
	}

	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}

	public String getTipoDoc() {
		return tipoDoc;
	}

	public void setTipoDoc(String tipoDoc) {
		this.tipoDoc = tipoDoc;
	}

	public Double getCosto() {
		return costo;
	}

	public Double getTotalCosto() {
		Double valor = this.tipoDoc.equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO) || this.tipoDoc.equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO) ? -1d : 1d;
		return this.tipoCodigo.equals(Constantes.TIPO_CODIGO_ARTICULO_USO_INTERNO) ? Constantes.ZEROS_DOUBLE : (costo * this.cantidad) * valor;
	}

	public String getTotalCostoSTR() {
		return Utils.formateadorMiles(getTotalCosto());
	}

	public Double getTotalUtilidad() {

		Double valor = this.tipoDoc.equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO) || this.tipoDoc.equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO) ? -1d : 1d;

		return (this.venta - getTotalCosto()) * valor;
	}

	public String getTotalUtilidadSTR() {

		return Utils.formateadorMiles(getTotalUtilidad());
	}

	
	public void setCosto(Double costo) {
		this.costo = costo;
	}

	public String getVentaSTR() {
		return Utils.formateadorMiles(this.venta);
	}

	public Double getVenta() {
		return venta;
	}

	public void setVenta(Double venta) {
		this.venta = venta;
	}

	public String getTipoCodigo() {
		return tipoCodigo;
	}

	public void setTipoCodigo(String tipoCodigo) {
		this.tipoCodigo = tipoCodigo;
	}

	public String getNumeroConsecutivo() {
		return numeroConsecutivo;
	}

	public void setNumeroConsecutivo(String numeroConsecutivo) {
		this.numeroConsecutivo = numeroConsecutivo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getCantidad() {
		return cantidad;
	}

	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cantidad == null) ? 0 : cantidad.hashCode());
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((costo == null) ? 0 : costo.hashCode());
		result = prime * result + ((fechaEmision == null) ? 0 : fechaEmision.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nombreArticulo == null) ? 0 : nombreArticulo.hashCode());
		result = prime * result + ((nombreCategoria == null) ? 0 : nombreCategoria.hashCode());
		result = prime * result + ((numeroConsecutivo == null) ? 0 : numeroConsecutivo.hashCode());
		result = prime * result + ((tipoCodigo == null) ? 0 : tipoCodigo.hashCode());
		result = prime * result + ((tipoDoc == null) ? 0 : tipoDoc.hashCode());
		result = prime * result + ((venta == null) ? 0 : venta.hashCode());
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
		ConsultaUtilidadNative other = (ConsultaUtilidadNative) obj;
		if (cantidad == null) {
			if (other.cantidad != null)
				return false;
		} else if (!cantidad.equals(other.cantidad))
			return false;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (costo == null) {
			if (other.costo != null)
				return false;
		} else if (!costo.equals(other.costo))
			return false;
		if (fechaEmision == null) {
			if (other.fechaEmision != null)
				return false;
		} else if (!fechaEmision.equals(other.fechaEmision))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nombreArticulo == null) {
			if (other.nombreArticulo != null)
				return false;
		} else if (!nombreArticulo.equals(other.nombreArticulo))
			return false;
		if (nombreCategoria == null) {
			if (other.nombreCategoria != null)
				return false;
		} else if (!nombreCategoria.equals(other.nombreCategoria))
			return false;
		if (numeroConsecutivo == null) {
			if (other.numeroConsecutivo != null)
				return false;
		} else if (!numeroConsecutivo.equals(other.numeroConsecutivo))
			return false;
		if (tipoCodigo == null) {
			if (other.tipoCodigo != null)
				return false;
		} else if (!tipoCodigo.equals(other.tipoCodigo))
			return false;
		if (tipoDoc == null) {
			if (other.tipoDoc != null)
				return false;
		} else if (!tipoDoc.equals(other.tipoDoc))
			return false;
		if (venta == null) {
			if (other.venta != null)
				return false;
		} else if (!venta.equals(other.venta))
			return false;
		return true;
	}

	


}
