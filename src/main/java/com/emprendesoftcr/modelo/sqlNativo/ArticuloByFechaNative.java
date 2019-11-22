package com.emprendesoftcr.modelo.sqlNativo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.Utils.Utils;

@BaseNativeQuery(name = "invet_fech", query = "SELECT a.id, \n" + 
"       a.codigo, \n" + 
"       a.contable,\n" + 
"       a.costo,\n" + 
"       a.created_at,\n" +
"       a.descripcion,\n" + 
"       a.estado,\n" + 
"       a.ganancia_precio_especial,\n" + 
"       a.ganancia_precio_mayorista,\n" + 
"       a.ganancia_precio_publico,\n" + 
"       a.impuesto,\n" + 
"       a.precio_especial,\n" + 
"       a.precio_mayorista,\n" + 
"       a.precio_publico,\n" + 
"       a.tipo_codigo,\n" + 
"       a.tipo_impuesto,\n" + 
"       a.unidad_medida,\n" + 
"       a.updated_at,\n" + 
"       (select c.descripcion from categorias c where c.id = a.categoria_id) categoria,\n" +
"       (select m.descripcion from marcas m where m.id = a.marca_id) marca,\n" + 
"       a.cantidad,\n"
		+ " a.maximo,a.minimo,\n" + 
"       a.prioridad,\n" + 
		"   a.fecha_compra,\n" + 
"       a.peso_transporte,\n" + 
		"   a.tipo_impuesto1,\n" + 
"       a.cons_compra, \n" + 
		"   a.impuesto1,\n" + 
"       a.base_imponible, \n" + 
		"   a.cod_tarifa, \n" +
"       a.cod_tarifa1,\n" + 
		"   (COALESCE((SELECT kardex.cantidad_nueva FROM kardex \n" + 
"    WHERE kardex.codigo = a.codigo  \n" + 
		"    and kardex.created_at >= :fechaInicial \n" + 
"        and kardex.created_at <= :fechaFinal \n" + 
		"    ORDER by kardex.created_at desc LIMIT 1),0)) cant_actual\n" + 
"FROM articulos a where a.empresa_id = :ID_EMPRESA")
@Entity
public class ArticuloByFechaNative implements Serializable {

	private static final long	serialVersionUID	= 3807188734031661970L;

	@Id
	@Column(name = "id")
	private Long							id;

	@Column(name = "codigo")
	private String						codigo;

	@Column(name = "descripcion")
	private String						descripcion;

	@Column(name = "unidad_medida")
	private String						unidadMedida;

	@Column(name = "contable")
	private String						contable;

	@Column(name = "costo")
	private Double						costo;

	@Column(name = "impuesto")
	private Double						impuesto;

	@Column(name = "precio_publico")
	private Double						precioPublico;

	@Column(name = "ganancia_precio_publico")
	private Double						gananciaPrecioPublico;

	@Column(name = "precio_mayorista")
	private Double						precioMayorista;

	@Column(name = "ganancia_precio_mayorista")
	private Double						gananciaPrecioMayorista;

	@Column(name = "precio_especial")
	private Double						precioEspecial;

	@Column(name = "ganancia_precio_especial")
	private Double						gananciaPrecioEspecial;

	@Column(name = "cantidad")
	private Double						cantidad;

	@Column(name = "minimo")
	private Double						minimo;

	@Column(name = "estado")
	private String						estado;

	@Column(name = "tipo_impuesto", length = 2)
	private String						tipoImpuesto;
	// Tipo de codigo del producto
	@Column(name = "tipo_codigo", length = 2)
	private String						tipoCodigo;

	@Column(name = "maximo")
	private Double						maximo;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "created_at")
	private Date							created_at;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "updated_at")
	private Date							updated_at;

	@Column(name = "marca")
	private String						marca;

	@Column(name = "categoria")
	private String						categoria;

	@Column(name = "prioridad")
	private Integer						prioridad;

	@Column(name = "peso_transporte")
	private Double						pesoTransporte;

	@Column(name = "cons_compra")
	private String						consecutivoCompra;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "fecha_compra")
	private Date							fechaUltimaCompra;
	


	@Column(name = "tipo_impuesto1")
	private String						tipoImpuesto1;

	@Column(name = "impuesto1")
	private Double						impuesto1;

	@Column(name = "cod_tarifa")
	private String						codigoTarifa;

	@Column(name = "cod_tarifa1")
	private String						codigoTarifa1;

	@Column(name = "base_imponible")
	private Integer						baseImponible;

	@Column(name = "cant_actual")
	private Double						cantidadActual;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getUnidadMedida() {
		return unidadMedida;
	}

	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

	public String getContable() {
		return contable;
	}

	public void setContable(String contable) {
		this.contable = contable;
	}

	public Double getCosto() {
		return costo;
	}

	public void setCosto(Double costo) {
		this.costo = costo;
	}

	public Double getImpuesto() {
		return impuesto;
	}

	public void setImpuesto(Double impuesto) {
		this.impuesto = impuesto;
	}

	public Double getPrecioPublico() {
		return precioPublico;
	}

	public void setPrecioPublico(Double precioPublico) {
		this.precioPublico = precioPublico;
	}

	public Double getGananciaPrecioPublico() {
		return gananciaPrecioPublico;
	}

	public void setGananciaPrecioPublico(Double gananciaPrecioPublico) {
		this.gananciaPrecioPublico = gananciaPrecioPublico;
	}

	public Double getPrecioMayorista() {
		return precioMayorista;
	}

	public void setPrecioMayorista(Double precioMayorista) {
		this.precioMayorista = precioMayorista;
	}

	public Double getGananciaPrecioMayorista() {
		return gananciaPrecioMayorista;
	}

	public void setGananciaPrecioMayorista(Double gananciaPrecioMayorista) {
		this.gananciaPrecioMayorista = gananciaPrecioMayorista;
	}

	public Double getPrecioEspecial() {
		return precioEspecial;
	}

	public void setPrecioEspecial(Double precioEspecial) {
		this.precioEspecial = precioEspecial;
	}

	public Double getGananciaPrecioEspecial() {
		return gananciaPrecioEspecial;
	}

	public void setGananciaPrecioEspecial(Double gananciaPrecioEspecial) {
		this.gananciaPrecioEspecial = gananciaPrecioEspecial;
	}

	public Double getCantidad() {
		return cantidad;
	}

	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}

	public Double getMinimo() {
		return minimo;
	}

	public void setMinimo(Double minimo) {
		this.minimo = minimo;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getTipoImpuesto() {
		return tipoImpuesto;
	}

	public void setTipoImpuesto(String tipoImpuesto) {
		this.tipoImpuesto = tipoImpuesto;
	}

	public String getTipoCodigo() {
		return tipoCodigo;
	}

	public void setTipoCodigo(String tipoCodigo) {
		this.tipoCodigo = tipoCodigo;
	}

	public Double getMaximo() {
		return maximo;
	}

	public void setMaximo(Double maximo) {
		this.maximo = maximo;
	}

	public Date getCreated_at() {
		return created_at;
	}
	public String getCreated_atSTR() {
		if (this.created_at != null) {
			return Utils.getFechaGeneraReporte(this.created_at);
		}
		return Constantes.EMPTY;
	}
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	public String getUpdated_atSTR() {
		if (this.updated_at != null) {
			return Utils.getFechaGeneraReporte(this.updated_at);
		}
		return Constantes.EMPTY;
	}
	public Date getUpdated_at() {
		return updated_at;
	}

	
	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public Integer getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(Integer prioridad) {
		this.prioridad = prioridad;
	}

	public Double getPesoTransporte() {
		return pesoTransporte;
	}

	public void setPesoTransporte(Double pesoTransporte) {
		this.pesoTransporte = pesoTransporte;
	}

	public String getConsecutivoCompra() {
		return consecutivoCompra;
	}

	public void setConsecutivoCompra(String consecutivoCompra) {
		this.consecutivoCompra = consecutivoCompra;
	}

	public Date getFechaUltimaCompra() {
		return fechaUltimaCompra;
	}

	public void setFechaUltimaCompra(Date fechaUltimaCompra) {
		this.fechaUltimaCompra = fechaUltimaCompra;
	}

	public String getTipoImpuesto1() {
		return tipoImpuesto1;
	}

	public void setTipoImpuesto1(String tipoImpuesto1) {
		this.tipoImpuesto1 = tipoImpuesto1;
	}

	public Double getImpuesto1() {
		return impuesto1;
	}

	public void setImpuesto1(Double impuesto1) {
		this.impuesto1 = impuesto1;
	}

	public String getCodigoTarifa() {
		return codigoTarifa;
	}

	public void setCodigoTarifa(String codigoTarifa) {
		this.codigoTarifa = codigoTarifa;
	}

	public String getCodigoTarifa1() {
		return codigoTarifa1;
	}

	public void setCodigoTarifa1(String codigoTarifa1) {
		this.codigoTarifa1 = codigoTarifa1;
	}

	public Integer getBaseImponible() {
		return baseImponible;
	}

	public void setBaseImponible(Integer baseImponible) {
		this.baseImponible = baseImponible;
	}

	public Double getCantidadActual() {
		return cantidadActual;
	}

	public void setCantidadActual(Double cantidadActual) {
		this.cantidadActual = cantidadActual;
	}

	public String getCantidadActualSTR() {
		Double resultado = this.cantidad == null ? Constantes.ZEROS_DOUBLE : this.cantidad;
		Double resultadoCantidadActual = this.cantidadActual == null ? Constantes.ZEROS_DOUBLE : this.cantidadActual;
		resultadoCantidadActual = resultadoCantidadActual > Constantes.ZEROS_DOUBLE ? resultadoCantidadActual : resultado;

		return Utils.formateadorMiles(resultadoCantidadActual);
	}

	public Double getCantidadActualReal() {
		Double resultado = this.cantidad == null ? Constantes.ZEROS_DOUBLE : this.cantidad;
		Double resultadoCantidadActual = this.cantidadActual == null ? Constantes.ZEROS_DOUBLE : this.cantidadActual;
		resultadoCantidadActual = resultadoCantidadActual > Constantes.ZEROS_DOUBLE ? resultadoCantidadActual : resultado;

		return resultadoCantidadActual;
	}
	public Double getTotalCosto() {
		Double costoTem = this.costo != null ? this.costo : Constantes.ZEROS_DOUBLE;
		Double cantidadTem = this.getCantidadActualReal() != null ? this.getCantidadActualReal() : Constantes.ZEROS_DOUBLE;
		return costoTem * cantidadTem;
	}
	
	public Double getTotalPrecioPublico() {
		Double precioPublicoTem = this.precioPublico != null ? this.precioPublico : Constantes.ZEROS_DOUBLE;
		Double cantidadTem = this.getCantidadActualReal() != null ? this.getCantidadActualReal() : Constantes.ZEROS_DOUBLE;
		return precioPublicoTem * cantidadTem;

	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((baseImponible == null) ? 0 : baseImponible.hashCode());
		result = prime * result + ((cantidad == null) ? 0 : cantidad.hashCode());
		result = prime * result + ((cantidadActual == null) ? 0 : cantidadActual.hashCode());
		result = prime * result + ((categoria == null) ? 0 : categoria.hashCode());
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((codigoTarifa == null) ? 0 : codigoTarifa.hashCode());
		result = prime * result + ((codigoTarifa1 == null) ? 0 : codigoTarifa1.hashCode());
		result = prime * result + ((consecutivoCompra == null) ? 0 : consecutivoCompra.hashCode());
		result = prime * result + ((contable == null) ? 0 : contable.hashCode());
		result = prime * result + ((costo == null) ? 0 : costo.hashCode());
		result = prime * result + ((created_at == null) ? 0 : created_at.hashCode());
		result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + ((fechaUltimaCompra == null) ? 0 : fechaUltimaCompra.hashCode());
		result = prime * result + ((gananciaPrecioEspecial == null) ? 0 : gananciaPrecioEspecial.hashCode());
		result = prime * result + ((gananciaPrecioMayorista == null) ? 0 : gananciaPrecioMayorista.hashCode());
		result = prime * result + ((gananciaPrecioPublico == null) ? 0 : gananciaPrecioPublico.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((impuesto == null) ? 0 : impuesto.hashCode());
		result = prime * result + ((impuesto1 == null) ? 0 : impuesto1.hashCode());
		result = prime * result + ((marca == null) ? 0 : marca.hashCode());
		result = prime * result + ((maximo == null) ? 0 : maximo.hashCode());
		result = prime * result + ((minimo == null) ? 0 : minimo.hashCode());
		result = prime * result + ((pesoTransporte == null) ? 0 : pesoTransporte.hashCode());
		result = prime * result + ((precioEspecial == null) ? 0 : precioEspecial.hashCode());
		result = prime * result + ((precioMayorista == null) ? 0 : precioMayorista.hashCode());
		result = prime * result + ((precioPublico == null) ? 0 : precioPublico.hashCode());
		result = prime * result + ((prioridad == null) ? 0 : prioridad.hashCode());
		result = prime * result + ((tipoCodigo == null) ? 0 : tipoCodigo.hashCode());
		result = prime * result + ((tipoImpuesto == null) ? 0 : tipoImpuesto.hashCode());
		result = prime * result + ((tipoImpuesto1 == null) ? 0 : tipoImpuesto1.hashCode());
		result = prime * result + ((unidadMedida == null) ? 0 : unidadMedida.hashCode());
		result = prime * result + ((updated_at == null) ? 0 : updated_at.hashCode());
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
		ArticuloByFechaNative other = (ArticuloByFechaNative) obj;
		if (baseImponible == null) {
			if (other.baseImponible != null)
				return false;
		} else if (!baseImponible.equals(other.baseImponible))
			return false;
		if (cantidad == null) {
			if (other.cantidad != null)
				return false;
		} else if (!cantidad.equals(other.cantidad))
			return false;
		if (cantidadActual == null) {
			if (other.cantidadActual != null)
				return false;
		} else if (!cantidadActual.equals(other.cantidadActual))
			return false;
		if (categoria == null) {
			if (other.categoria != null)
				return false;
		} else if (!categoria.equals(other.categoria))
			return false;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (codigoTarifa == null) {
			if (other.codigoTarifa != null)
				return false;
		} else if (!codigoTarifa.equals(other.codigoTarifa))
			return false;
		if (codigoTarifa1 == null) {
			if (other.codigoTarifa1 != null)
				return false;
		} else if (!codigoTarifa1.equals(other.codigoTarifa1))
			return false;
		if (consecutivoCompra == null) {
			if (other.consecutivoCompra != null)
				return false;
		} else if (!consecutivoCompra.equals(other.consecutivoCompra))
			return false;
		if (contable == null) {
			if (other.contable != null)
				return false;
		} else if (!contable.equals(other.contable))
			return false;
		if (costo == null) {
			if (other.costo != null)
				return false;
		} else if (!costo.equals(other.costo))
			return false;
		if (created_at == null) {
			if (other.created_at != null)
				return false;
		} else if (!created_at.equals(other.created_at))
			return false;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (estado == null) {
			if (other.estado != null)
				return false;
		} else if (!estado.equals(other.estado))
			return false;
		if (fechaUltimaCompra == null) {
			if (other.fechaUltimaCompra != null)
				return false;
		} else if (!fechaUltimaCompra.equals(other.fechaUltimaCompra))
			return false;
		if (gananciaPrecioEspecial == null) {
			if (other.gananciaPrecioEspecial != null)
				return false;
		} else if (!gananciaPrecioEspecial.equals(other.gananciaPrecioEspecial))
			return false;
		if (gananciaPrecioMayorista == null) {
			if (other.gananciaPrecioMayorista != null)
				return false;
		} else if (!gananciaPrecioMayorista.equals(other.gananciaPrecioMayorista))
			return false;
		if (gananciaPrecioPublico == null) {
			if (other.gananciaPrecioPublico != null)
				return false;
		} else if (!gananciaPrecioPublico.equals(other.gananciaPrecioPublico))
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
		if (impuesto1 == null) {
			if (other.impuesto1 != null)
				return false;
		} else if (!impuesto1.equals(other.impuesto1))
			return false;
		if (marca == null) {
			if (other.marca != null)
				return false;
		} else if (!marca.equals(other.marca))
			return false;
		if (maximo == null) {
			if (other.maximo != null)
				return false;
		} else if (!maximo.equals(other.maximo))
			return false;
		if (minimo == null) {
			if (other.minimo != null)
				return false;
		} else if (!minimo.equals(other.minimo))
			return false;
		if (pesoTransporte == null) {
			if (other.pesoTransporte != null)
				return false;
		} else if (!pesoTransporte.equals(other.pesoTransporte))
			return false;
		if (precioEspecial == null) {
			if (other.precioEspecial != null)
				return false;
		} else if (!precioEspecial.equals(other.precioEspecial))
			return false;
		if (precioMayorista == null) {
			if (other.precioMayorista != null)
				return false;
		} else if (!precioMayorista.equals(other.precioMayorista))
			return false;
		if (precioPublico == null) {
			if (other.precioPublico != null)
				return false;
		} else if (!precioPublico.equals(other.precioPublico))
			return false;
		if (prioridad == null) {
			if (other.prioridad != null)
				return false;
		} else if (!prioridad.equals(other.prioridad))
			return false;
		if (tipoCodigo == null) {
			if (other.tipoCodigo != null)
				return false;
		} else if (!tipoCodigo.equals(other.tipoCodigo))
			return false;
		if (tipoImpuesto == null) {
			if (other.tipoImpuesto != null)
				return false;
		} else if (!tipoImpuesto.equals(other.tipoImpuesto))
			return false;
		if (tipoImpuesto1 == null) {
			if (other.tipoImpuesto1 != null)
				return false;
		} else if (!tipoImpuesto1.equals(other.tipoImpuesto1))
			return false;
		if (unidadMedida == null) {
			if (other.unidadMedida != null)
				return false;
		} else if (!unidadMedida.equals(other.unidadMedida))
			return false;
		if (updated_at == null) {
			if (other.updated_at != null)
				return false;
		} else if (!updated_at.equals(other.updated_at))
			return false;
		return true;
	}

}
