package com.emprendesoftcr.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.Utils.Utils;

/**
 * Modelo para los articulos de una empresa Articulo.
 * @author jose.
 * @since 19 abr. 2018
 */
@Entity
@Table(name = "articulos")
public class Articulo implements Serializable {

	private static final long	serialVersionUID	= 3147925944920227346L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long							id;

	@Column(name = "codigo")
	private String						codigo;

	@Column(name = "descripcion")
	private String						descripcion;

	@Column(name = "serie")
	private String						serie;

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

	@Column(name = "tipo_impuesto")
	private String						tipoImpuesto;
	// Tipo de codigo del producto
	@Column(name = "tipo_codigo")
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

	@ManyToOne
	@JoinColumn(name = "marca_id")
	private Marca							marca;

	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario						usuario;

	@ManyToOne
	@JoinColumn(name = "categoria_id")
	private Categoria					categoria;

	@ManyToOne
	@JoinColumn(name = "empresa_id")
	private Empresa						empresa;

	// 1 = Articulo cocina
	@Column(name = "comanda")
	private Integer						comanda						= 0;

	@Column(name = "prioridad", columnDefinition = "INT default '99999'")
	private Integer						prioridad;

	@Column(name = "peso_transporte", columnDefinition = "Decimal(10,2) default '0.00'")
	private Double						pesoTransporte;

	@Column(name = "cons_compra")
	private String						consecutivoCompra;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "fecha_compra")
	private Date							fechaUltimaCompra;

	@Column(name = "tipo_impuesto1")
	private String						tipoImpuesto1;

	@Column(name = "impuesto1", columnDefinition = "Decimal(10,2) default '0.00'")
	private Double						impuesto1;
	
	@Column(name = "cod_tarifa")
	private String						codigoTarifa;
	
	@Column(name = "cod_tarifa1")
	private String						codigoTarifa1;

	@Column(name = "base_imponible", columnDefinition = "INT default '0'")
	private Integer						baseImponible;


	

	

	public Articulo(Long id, String codigo, String descripcion, String serie, String unidadMedida, String contable, Double costo, Double impuesto, Double precioPublico, Double gananciaPrecioPublico, Double precioMayorista, Double gananciaPrecioMayorista, Double precioEspecial, Double gananciaPrecioEspecial, Double cantidad, Double minimo, String estado, String tipoImpuesto, String tipoCodigo, Double maximo, Date created_at, Date updated_at, Marca marca, Usuario usuario, Categoria categoria, Empresa empresa, Integer comanda, Integer prioridad, Double pesoTransporte, String consecutivoCompra, Date fechaUltimaCompra, String tipoImpuesto1, Double impuesto1, String codigoTarifa, String codigoTarifa1, Integer baseImponible) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.serie = serie;
		this.unidadMedida = unidadMedida;
		this.contable = contable;
		this.costo = costo;
		this.impuesto = impuesto;
		this.precioPublico = precioPublico;
		this.gananciaPrecioPublico = gananciaPrecioPublico;
		this.precioMayorista = precioMayorista;
		this.gananciaPrecioMayorista = gananciaPrecioMayorista;
		this.precioEspecial = precioEspecial;
		this.gananciaPrecioEspecial = gananciaPrecioEspecial;
		this.cantidad = cantidad;
		this.minimo = minimo;
		this.estado = estado;
		this.tipoImpuesto = tipoImpuesto;
		this.tipoCodigo = tipoCodigo;
		this.maximo = maximo;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.marca = marca;
		this.usuario = usuario;
		this.categoria = categoria;
		this.empresa = empresa;
		this.comanda = comanda;
		this.prioridad = prioridad;
		this.pesoTransporte = pesoTransporte;
		this.consecutivoCompra = consecutivoCompra;
		this.fechaUltimaCompra = fechaUltimaCompra;
		this.tipoImpuesto1 = tipoImpuesto1;
		this.impuesto1 = impuesto1;
		this.codigoTarifa = codigoTarifa;
		this.codigoTarifa1 = codigoTarifa1;
		this.baseImponible = baseImponible;
	}



	public Articulo() {
		super();
		this.costo = Constantes.ZEROS_DOUBLE;
		this.impuesto = Constantes.ZEROS_DOUBLE;
		this.precioPublico = Constantes.ZEROS_DOUBLE;
		this.gananciaPrecioPublico = Constantes.ZEROS_DOUBLE;
		this.precioMayorista = Constantes.ZEROS_DOUBLE;
		this.gananciaPrecioMayorista = Constantes.ZEROS_DOUBLE;
		this.precioEspecial = Constantes.ZEROS_DOUBLE;
		this.gananciaPrecioEspecial = Constantes.ZEROS_DOUBLE;
		this.created_at = new Date();
		this.updated_at = new Date();
		this.maximo = Constantes.ZEROS_DOUBLE;
		this.minimo = Constantes.ZEROS_DOUBLE;
		this.cantidad = Constantes.ZEROS_DOUBLE;

	}

	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public String getCodigoTarifa1() {
		return codigoTarifa1;
	}

	
	public void setCodigoTarifa1(String codigoTarifa1) {
		this.codigoTarifa1 = codigoTarifa1;
	}

	public String getCodigoTarifa() {
		return codigoTarifa;
	}

	public void setCodigoTarifa(String codigoTarifa) {
		this.codigoTarifa = codigoTarifa;
	}

	public Integer getComanda() {
		return comanda;
	}

	public void setComanda(Integer comanda) {
		this.comanda = comanda;
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

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
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

	public String getPrecioPublicoSTR() {
		return Utils.formateadorMiles(this.precioPublico);
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

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public String getCreated_atSTR() {
		if (this.created_at != null) {
			return Utils.getFechaGeneraReporte(this.created_at);
		}
		return Constantes.EMPTY;
	}

	public Date getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}

	public String getUpdated_atSTR() {
		if (this.updated_at != null) {
			return Utils.getFechaGeneraReporte(this.updated_at);
		}
		return Constantes.EMPTY;
	}

	public Double getTotalCosto() {
		Double costoTem = this.costo != null ? this.costo : Constantes.ZEROS_DOUBLE;
		Double cantidadTem = this.cantidad != null ? this.cantidad : Constantes.ZEROS_DOUBLE;
		return costoTem * cantidadTem;
	}

	public Double getTotalPrecioPublico() {
		Double precioPublicoTem = this.precioPublico != null ? this.precioPublico : Constantes.ZEROS_DOUBLE;
		Double cantidadTem = this.cantidad != null ? this.cantidad : Constantes.ZEROS_DOUBLE;
		return precioPublicoTem * cantidadTem;

	}

	public Double getGananciaTotalPublico() {

		Double costoTem = this.costo != null ? this.costo : Constantes.ZEROS_DOUBLE;
		Double cantidadTem = this.cantidad != null ? this.cantidad : Constantes.ZEROS_DOUBLE;
		Double precioPublicoTem = this.precioPublico != null ? this.precioPublico : Constantes.ZEROS_DOUBLE;
		Double totalCosto = costoTem * cantidadTem;

		Double totalVenta = totalCosto == 0 ? Constantes.ZEROS_DOUBLE : precioPublicoTem * cantidadTem;
		Double valorImpuesto1 = this.impuesto1 != null ? this.impuesto1 : Constantes.ZEROS_DOUBLE;
		Double valorImpuesto = this.impuesto == null ? Constantes.ZEROS_DOUBLE : this.impuesto;
		valorImpuesto = valorImpuesto + valorImpuesto1;
		valorImpuesto = (valorImpuesto / 100) + 1;
		Double valor = this.impuesto != null ? totalVenta / valorImpuesto : Constantes.ZEROS_DOUBLE;
		Double totalImpuesto = totalVenta > 0 ? totalVenta - valor : Constantes.ZEROS_DOUBLE;
		totalVenta = totalVenta - totalImpuesto;
		return totalVenta - totalCosto;
	}

	public Double getTotalImpuesto() {
		Double costoTem = this.costo != null ? this.costo : Constantes.ZEROS_DOUBLE;
		Double cantidadTem = this.cantidad != null ? this.cantidad : Constantes.ZEROS_DOUBLE;
		Double precioPublicoTem = this.precioPublico != null ? this.precioPublico : Constantes.ZEROS_DOUBLE;
		Double totalCosto = costoTem * cantidadTem;

		Double totalVenta = totalCosto == 0 ? Constantes.ZEROS_DOUBLE : precioPublicoTem * cantidadTem;
		Double valorImpuesto1 = this.impuesto1 != null ? this.impuesto1 : Constantes.ZEROS_DOUBLE;
		Double valorImpuesto = this.impuesto == null ? Constantes.ZEROS_DOUBLE : this.impuesto;
		valorImpuesto = valorImpuesto + valorImpuesto1;
		valorImpuesto = (valorImpuesto / 100) + 1;
		Double valor = this.impuesto != null ? totalVenta / valorImpuesto : Constantes.ZEROS_DOUBLE;
		Double totalImpuesto = totalVenta > 0 ? totalVenta - valor : Constantes.ZEROS_DOUBLE;

		return totalImpuesto;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public String getTipoCodigo() {
		return tipoCodigo;
	}

	public void setTipoCodigo(String tipoCodigo) {
		this.tipoCodigo = tipoCodigo;
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

	public Double getMaximo() {
		return maximo;
	}

	public void setMaximo(Double maximo) {
		this.maximo = maximo;
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

	public String getFechaUltimaCompraSTR() {
		if (this.fechaUltimaCompra != null) {
			return Utils.getFechaGeneraReporte(this.getFechaUltimaCompra());
		}
		return Constantes.EMPTY;
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

	
	public Integer getBaseImponible() {
		return baseImponible;
	}

	
	public void setBaseImponible(Integer baseImponible) {
		this.baseImponible = baseImponible;
	}
	
	

}