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

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;
/**
 * Articulo para asociar y para cobro de condominio
 * @author jose
 *
 */

@Entity
@Table(name = "cliente_articulo")
public class ClienteArticulo  implements Serializable {


	private static final long serialVersionUID = -835908792495374741L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long							id;

	@Column(name = "codigo")
	private String						codigo;
	
	@Column(name = "descripcion")
	private String						descripcion;
	
	@Column(name = "cantidad")
	private Double						cantidad;

	
	@Column(name = "precio")
	private Double						precio;
	
		
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "articulo_id")
	private Articulo					articulo;

	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "cliente_id")
	private Cliente					cliente;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "created_at")
	private Date							created_at;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "updated_at")
	private Date							updated_at;

	public ClienteArticulo(Long id, String codigo, String descripcion, Double cantidad, Double precio, Articulo articulo, Cliente cliente, Date created_at, Date updated_at) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.cantidad = cantidad;
		this.precio = precio;
		this.articulo = articulo;
		this.cliente = cliente;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}

	public ClienteArticulo() {
		super();
	}

	
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

	
	public Double getCantidad() {
		return cantidad;
	}

	
	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}

	
	public Double getPrecio() {
		return precio;
	}

	
	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	
	public Articulo getArticulo() {
		return articulo;
	}

	
	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}

	
	public Cliente getCliente() {
		return cliente;
	}

	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
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
	
	
	
}
