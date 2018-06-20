package com.factura.FacturaElectronica.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	private Integer						id;

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
	private BigDecimal				costo;

	@Column(name = "impuesto")
	private BigDecimal				impuesto;

	@Column(name = "precio_publico")
	private BigDecimal				precioPublico;

	@Column(name = "ganancia_precio_publico")
	private BigDecimal				gananciaPrecioPublico;

	@Column(name = "precio_mayorista")
	private BigDecimal				precioMayorista;

	@Column(name = "ganancia_precio_mayorista")
	private BigDecimal				gananciaPrecioMayorista;

	@Column(name = "precio_especial")
	private BigDecimal				precioEspecial;

	@Column(name = "ganancia_precio_especial")
	private BigDecimal				gananciaPrecioEspecial;

	@Column(name = "estado")
	private String						estado;
	
	@Column(name = "tipo_impuesto")
	private String						tipoImpuesto;

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

	@JsonIgnore
	@OneToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinColumn(name = "articulo_id", referencedColumnName = "id")
	@OrderBy("id DESC")
	private Set<Inventario>		inventarios;
	
	

	public Articulo(Integer id, String codigo, String descripcion, String serie, String unidadMedida, String contable, BigDecimal costo, BigDecimal impuesto, BigDecimal precioPublico, BigDecimal gananciaPrecioPublico, BigDecimal precioMayorista, BigDecimal gananciaPrecioMayorista, BigDecimal precioEspecial, BigDecimal gananciaPrecioEspecial, String estado, String tipoImpuesto, Date created_at, Date updated_at, Marca marca, Usuario usuario, Categoria categoria, Empresa empresa, Set<Inventario> inventarios) {
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
		this.estado = estado;
		this.tipoImpuesto = tipoImpuesto;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.marca = marca;
		this.usuario = usuario;
		this.categoria = categoria;
		this.empresa = empresa;
		this.inventarios = inventarios;
	}

	public Articulo() {
		super();
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public BigDecimal getCosto() {
		return costo;
	}

	public void setCosto(BigDecimal costo) {
		this.costo = costo;
	}

	

	
	public BigDecimal getImpuesto() {
		return impuesto;
	}

	
	public void setImpuesto(BigDecimal impuesto) {
		this.impuesto = impuesto;
	}

	public BigDecimal getPrecioPublico() {
		return precioPublico;
	}

	public void setPrecioPublico(BigDecimal precioPublico) {
		this.precioPublico = precioPublico;
	}

	public BigDecimal getGananciaPrecioPublico() {
		return gananciaPrecioPublico;
	}

	public void setGananciaPrecioPublico(BigDecimal gananciaPrecioPublico) {
		this.gananciaPrecioPublico = gananciaPrecioPublico;
	}

	public BigDecimal getPrecioMayorista() {
		return precioMayorista;
	}

	public void setPrecioMayorista(BigDecimal precioMayorista) {
		this.precioMayorista = precioMayorista;
	}

	public BigDecimal getGananciaPrecioMayorista() {
		return gananciaPrecioMayorista;
	}

	public void setGananciaPrecioMayorista(BigDecimal gananciaPrecioMayorista) {
		this.gananciaPrecioMayorista = gananciaPrecioMayorista;
	}

	public BigDecimal getPrecioEspecial() {
		return precioEspecial;
	}

	public void setPrecioEspecial(BigDecimal precioEspecial) {
		this.precioEspecial = precioEspecial;
	}

	public BigDecimal getGananciaPrecioEspecial() {
		return gananciaPrecioEspecial;
	}

	public void setGananciaPrecioEspecial(BigDecimal gananciaPrecioEspecial) {
		this.gananciaPrecioEspecial = gananciaPrecioEspecial;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
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

	public String getContable() {
		return contable;
	}

	public void setContable(String contable) {
		this.contable = contable;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Set<Inventario> getInventarios() {
		return inventarios;
	}

	public void setInventarios(Set<Inventario> inventarios) {
		this.inventarios = inventarios;
	}

	
	public String getTipoImpuesto() {
		return tipoImpuesto;
	}

	
	public void setTipoImpuesto(String tipoImpuesto) {
		this.tipoImpuesto = tipoImpuesto;
	}

}