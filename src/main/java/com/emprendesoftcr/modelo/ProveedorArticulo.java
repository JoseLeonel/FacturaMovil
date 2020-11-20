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

@Entity
@Table(name = "proveedor_articulo")
public class ProveedorArticulo implements Serializable {

	private static final long	serialVersionUID	= -3459335050080284097L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long							id;

	@Column(name = "codigo")
	private String						codigo;
	
	@Column(name = "costo")
	private Double						costo;
	
	@Column(name = "cod_provee")
	private String						codigoProveedor;

	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "articulo_id")
	private Articulo					articulo;

	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "proveedor_id")
	private Proveedor					proveedor;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "created_at")
	private Date							created_at;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "updated_at")
	private Date							updated_at;

	@Column(name = "codigo_comercial_tipo1")
	private String						codigoComercialTipo1;
	
	@Column(name = "codigo_comercial_codigo1")
	private String						codigoComercial1;
	
	@Column(name = "codigo_comercial_tipo2")
	private String						codigoComercialTipo2;
	
	@Column(name = "codigo_comercial_codigo2")
	private String						codigoComercial2;
	
	
	@Column(name = "codigo_comercial_tipo3")
	private String						codigoComercialTipo3;
	
	@Column(name = "codigo_comercial_codigo3")
	private String						codigoComercial3;
	
	
	@Column(name = "codigo_cabys")
	private String						codigoCabys;
	
	
	
	
	
	public ProveedorArticulo(Long id, Double costo, Articulo articulo, Proveedor proveedor, Date created_at, Date updated_at,String codigo) {
		super();
		this.id = id;
		this.costo = costo;
		this.articulo = articulo;
		this.proveedor = proveedor;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.codigo = codigo;
	}

	
	

	public ProveedorArticulo() {
		super();
	}




	public Long getId() {
		return id;
	}

	
	public void setId(Long id) {
		this.id = id;
	}

	
	public Double getCosto() {
		return costo;
	}

	
	public void setCosto(Double costo) {
		this.costo = costo;
	}

	
	public Articulo getArticulo() {
		return articulo;
	}

	
	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}

	
	public Proveedor getProveedor() {
		return proveedor;
	}

	
	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
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




	
	public String getCodigo() {
		return codigo;
	}




	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}




	
	public String getCodigoProveedor() {
		return codigoProveedor;
	}




	
	public void setCodigoProveedor(String codigoProveedor) {
		this.codigoProveedor = codigoProveedor;
	}




	
	public String getCodigoComercialTipo1() {
		return codigoComercialTipo1;
	}




	
	public void setCodigoComercialTipo1(String codigoComercialTipo1) {
		this.codigoComercialTipo1 = codigoComercialTipo1;
	}




	
	public String getCodigoComercial1() {
		return codigoComercial1;
	}




	
	public void setCodigoComercial1(String codigoComercial1) {
		this.codigoComercial1 = codigoComercial1;
	}




	
	public String getCodigoComercialTipo2() {
		return codigoComercialTipo2;
	}




	
	public void setCodigoComercialTipo2(String codigoComercialTipo2) {
		this.codigoComercialTipo2 = codigoComercialTipo2;
	}




	
	public String getCodigoComercial2() {
		return codigoComercial2;
	}




	
	public void setCodigoComercial2(String codigoComercial2) {
		this.codigoComercial2 = codigoComercial2;
	}




	
	public String getCodigoComercialTipo3() {
		return codigoComercialTipo3;
	}




	
	public void setCodigoComercialTipo3(String codigoComercialTipo3) {
		this.codigoComercialTipo3 = codigoComercialTipo3;
	}




	
	public String getCodigoComercial3() {
		return codigoComercial3;
	}




	
	public void setCodigoComercial3(String codigoComercial3) {
		this.codigoComercial3 = codigoComercial3;
	}




	
	public String getCodigoCabys() {
		return codigoCabys;
	}




	
	public void setCodigoCabys(String codigoCabys) {
		this.codigoCabys = codigoCabys;
	}




	
	
	
	

}
