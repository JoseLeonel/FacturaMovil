package com.factura.FacturaElectronica.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
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
	
	@JsonIgnore
	@OneToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinColumn(name = "inventario_id", referencedColumnName = "id")
	@OrderBy("id DESC")
	private Set<Kardex>				kardexs;

	

	public Articulo(Integer id, String codigo, String descripcion, String serie, String unidadMedida, String contable, Double costo, Double impuesto, Double precioPublico, Double gananciaPrecioPublico, Double precioMayorista, Double gananciaPrecioMayorista, Double precioEspecial, Double gananciaPrecioEspecial, String estado, String tipoImpuesto, Date created_at, Date updated_at, Marca marca, Usuario usuario, Categoria categoria, Empresa empresa, Set<Inventario> inventarios, Set<Kardex> kardexs) {
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
		this.kardexs = kardexs;
	}

	public Articulo() {
		super();
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

	public Set<Inventario> getInventarios() {
		return inventarios;
	}

	public void setInventarios(Set<Inventario> inventarios) {
		this.inventarios = inventarios;
	}
	
	
	
	
	public Set<Kardex> getKardexs() {
		return kardexs;
	}

	
	public void setKardexs(Set<Kardex> kardexs) {
		this.kardexs = kardexs;
	}

	/**
	 * Asociar Kardex a un inventario
	 * @param inventarioAsociar
	 */
	public void addKardex(Kardex kardexAsociar) {

		if (kardexAsociar != null) {
			if (kardexs == null) {
				kardexs = new HashSet<Kardex>();
			}
			kardexAsociar.setArticulo(this);;

			kardexs.add(kardexAsociar);
		}
	}


}