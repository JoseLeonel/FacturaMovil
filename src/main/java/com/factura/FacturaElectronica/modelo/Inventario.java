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

import com.factura.FacturaElectronica.Utils.Constantes;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Modelo de inventarios por almacen
 * @author jose.
 * @since 19 abr. 2018
 */
@Entity
@Table(name = "inventarios")
public class Inventario implements Serializable {

	private static final long	serialVersionUID	= 3147925944920227346L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer						id;

	@Column(name = "cantidad")
	private Double						cantidad;

	@Column(name = "minimo")
	private Double						minimo;

	@Column(name = "maximo")
	private Double						maximo;

	@Column(name = "estado")
	private String						estado;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "created_at")
	private Date							created_at;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "updated_at")
	private Date							updated_at;

	@ManyToOne
	@JoinColumn(name = "articulo_id")
	private Articulo					articulo;

	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario						usuario;

	@JsonIgnore
	@OneToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinColumn(name = "inventario_id", referencedColumnName = "id")
	@OrderBy("id DESC")
	private Set<Kardex>				kardexs;

	public Inventario() {
		super();
		this.estado = Constantes.ESTADO_ACTIVO;
		this.created_at = new Date();
		this.updated_at = new Date();

	}

	public Inventario(Integer id, Double cantidad, Double minimo, Double maximo, String estado, Date created_at, Date updated_at, Articulo articulo, Usuario usuario, Set<Kardex> kardexs) {
		super();
		this.id = id;
		this.cantidad = cantidad;
		this.minimo = minimo;
		this.maximo = maximo;
		this.estado = estado;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.articulo = articulo;
		this.usuario = usuario;
		this.kardexs = kardexs;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Articulo getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
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
			kardexAsociar.setInventario(this);

			kardexs.add(kardexAsociar);
		}
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}