package com.emprendesoftcr.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.emprendesoftcr.Utils.Constantes;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Modelo para los articulos de una empresa Articulo.
 * @author jose.
 * @since 19 abr. 2018
 */
@Entity
@Table(name = "articulos")
public class Articulo implements Serializable {

	private static final long serialVersionUID = 3147925944920227346L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "codigo")
	private String codigo;

	@Column(name = "descripcion")
	private String descripcion;

	@Column(name = "serie")
	private String serie;

	@Column(name = "unidad_medida")
	private String unidadMedida;

	@Column(name = "contable")
	private String contable;

	@Column(name = "costo")
	private Double costo;

	@Column(name = "impuesto")
	private Double impuesto;

	@Column(name = "precio_publico")
	private Double precioPublico;

	@Column(name = "ganancia_precio_publico")
	private Double gananciaPrecioPublico;

	@Column(name = "precio_mayorista")
	private Double precioMayorista;

	@Column(name = "ganancia_precio_mayorista")
	private Double gananciaPrecioMayorista;

	@Column(name = "precio_especial")
	private Double precioEspecial;

	@Column(name = "ganancia_precio_especial")
	private Double gananciaPrecioEspecial;

	@Column(name = "cantidad")
	private Double cantidad;

	@Column(name = "minimo")
	private Double minimo;

	@Column(name = "maximo")
	private Double maximo;

	@Column(name = "estado")
	private String estado;

	@Column(name = "tipo_impuesto")
	private String tipoImpuesto;
	// Tipo de codigo del producto
	@Column(name = "tipo_codigo")
	private String tipoCodigo;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "created_at")
	private Date created_at;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "updated_at")
	private Date updated_at;

	@ManyToOne
	@JoinColumn(name = "marca_id")
	private Marca marca;

	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	@ManyToOne
	@JoinColumn(name = "categoria_id")
	private Categoria categoria;

	@ManyToOne
	@JoinColumn(name = "empresa_id")
	private Empresa empresa;

	// 1 = Articulo cocina
	@Column(name = "comanda")
	private Integer comanda = 0;

	@Column(name = "prioridad", columnDefinition = "INT default '99999'")
	private Integer prioridad;

	public Articulo(Long id, String codigo, String descripcion, String serie, String unidadMedida, String contable, Double costo, Double impuesto, Double precioPublico, Double gananciaPrecioPublico, Double precioMayorista, Double gananciaPrecioMayorista, Double precioEspecial, Double gananciaPrecioEspecial, Double cantidad, Double minimo, Double maximo, String estado, String tipoImpuesto, String tipoCodigo, Date created_at, Date updated_at, Marca marca, Usuario usuario, Categoria categoria, Empresa empresa, Integer comanda) {
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
		this.maximo = maximo;
		this.estado = estado;
		this.tipoImpuesto = tipoImpuesto;
		this.tipoCodigo = tipoCodigo;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.marca = marca;
		this.usuario = usuario;
		this.categoria = categoria;
		this.empresa = empresa;
		this.comanda = comanda;
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

	public Date getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
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

	@Override
	public String toString() {
		return "Articulo [id=" + id + ", codigo=" + codigo + ", descripcion=" + descripcion + ", serie=" + serie + ", unidadMedida=" + unidadMedida + ", contable=" + contable + ", costo=" + costo + ", impuesto=" + impuesto + ", precioPublico=" + precioPublico + ", gananciaPrecioPublico=" + gananciaPrecioPublico + ", precioMayorista=" + precioMayorista + ", gananciaPrecioMayorista=" + gananciaPrecioMayorista + ", precioEspecial=" + precioEspecial + ", gananciaPrecioEspecial=" + gananciaPrecioEspecial + ", cantidad=" + cantidad + ", minimo=" + minimo + ", maximo=" + maximo + ", estado=" + estado + ", tipoImpuesto=" + tipoImpuesto + ", tipoCodigo=" + tipoCodigo + ", created_at=" + created_at + ", updated_at=" + updated_at + ", marca=" + marca + ", usuario=" + usuario + ", categoria=" + categoria
				+ ", empresa=" + empresa + ", comanda=" + comanda + "]";
	}

}