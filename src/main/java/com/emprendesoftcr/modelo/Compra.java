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
import javax.validation.constraints.Size;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "compras")
public class Compra implements Serializable {

	private static final long		serialVersionUID	= -827268617562649428L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long							id;

	@Column(name = "consecutivo")
	private String							consecutivo;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY")
	@Column(name = "fecha_credito")
	private Date								fechaCredito;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY")
	@Column(name = "fecha_pago")
	private Date								fechaPago;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY")
	@Column(name = "fecha_compra")
	private Date								fechaCompra;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "fecha_ingreso")
	private Date								fechaIngreso;

	@Column(name = "tipo_documento")
	private Integer							tipoDocumento;

	@Column(name = "forma_pago")
	private Integer							formaPago;

	@Column(name = "total_impuesto")
	private Double							totalImpuesto;

	@Column(name = "total_descuento")
	private Double							totalDescuento;

	@Column(name = "sub_total")
	private Double							subTotal;

	@Column(name = "total_compra")
	private Double							totalCompra;

	@Size(max = 255)
	@Column(name = "nota")
	private String							nota;

	@Column(name = "estado")
	private Integer							estado;

	@CreatedDate
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at")
	private Date								created_at;

	@LastModifiedDate
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at")
	private Date								updated_at;

	@ManyToOne
	@JoinColumn(name = "proveedor_id")
	private Proveedor						proveedor;

	@ManyToOne
	@JoinColumn(name = "empresa_id")
	private Empresa							empresa;

	@ManyToOne
	@JoinColumn(name = "usuario_id", nullable = false)
	private Usuario							usuarioCreacion;

	@ManyToOne
	@JoinColumn(name = "usuario_ingreso_id", nullable = false)
	private Usuario							usuarioIngresoInventario;

	@JsonProperty("detalleCompras")
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "compra_id", referencedColumnName = "id")
	@OrderBy("compra_id DESC")
	private Set<DetalleCompra>	detalleCompras;

	public Compra() {
		super();
		this.created_at = new Date();
		this.updated_at = new Date();

	}

	
	
	
	
	
	public Compra(Long id, String consecutivo, Date fechaCredito, Date fechaPago, Date fechaCompra, Date fechaIngreso, Integer tipoDocumento, Integer formaPago, Double totalImpuesto, Double totalDescuento, Double subTotal, Double totalCompra, @Size(max = 255) String nota, Integer estado, Date created_at, Date updated_at, Proveedor proveedor, Empresa empresa, Usuario usuarioCreacion, Usuario usuarioIngresoInventario, Set<DetalleCompra> detalleCompras) {
		super();
		this.id = id;
		this.consecutivo = consecutivo;
		this.fechaCredito = fechaCredito;
		this.fechaPago = fechaPago;
		this.fechaCompra = fechaCompra;
		this.fechaIngreso = fechaIngreso;
		this.tipoDocumento = tipoDocumento;
		this.formaPago = formaPago;
		this.totalImpuesto = totalImpuesto;
		this.totalDescuento = totalDescuento;
		this.subTotal = subTotal;
		this.totalCompra = totalCompra;
		this.nota = nota;
		this.estado = estado;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.proveedor = proveedor;
		this.empresa = empresa;
		this.usuarioCreacion = usuarioCreacion;
		this.usuarioIngresoInventario = usuarioIngresoInventario;
		this.detalleCompras = detalleCompras;
	}






	public Long getId() {
		return id;
	}





	
	public void setId(Long id) {
		this.id = id;
	}





	public String getConsecutivo() {
		return consecutivo;
	}

	public void setConsecutivo(String consecutivo) {
		this.consecutivo = consecutivo;
	}

	public Date getFechaCredito() {
		return fechaCredito;
	}

	public void setFechaCredito(Date fechaCredito) {
		this.fechaCredito = fechaCredito;
	}

	public Date getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}

	public Date getFechaCompra() {
		return fechaCompra;
	}

	public void setFechaCompra(Date fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public Integer getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(Integer tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public Integer getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(Integer formaPago) {
		this.formaPago = formaPago;
	}

	public Double getTotalImpuesto() {
		return totalImpuesto;
	}

	public void setTotalImpuesto(Double totalImpuesto) {
		this.totalImpuesto = totalImpuesto;
	}

	public Double getTotalDescuento() {
		return totalDescuento;
	}

	public void setTotalDescuento(Double totalDescuento) {
		this.totalDescuento = totalDescuento;
	}

	public Double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(Double subTotal) {
		this.subTotal = subTotal;
	}

	public Double getTotalCompra() {
		return totalCompra;
	}

	public void setTotalCompra(Double totalCompra) {
		this.totalCompra = totalCompra;
	}

	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
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

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Usuario getUsuarioCreacion() {
		return usuarioCreacion;
	}

	public void setUsuarioCreacion(Usuario usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	public Usuario getUsuarioIngresoInventario() {
		return usuarioIngresoInventario;
	}

	public void setUsuarioIngresoInventario(Usuario usuarioIngresoInventario) {
		this.usuarioIngresoInventario = usuarioIngresoInventario;
	}

	public Set<DetalleCompra> getDetalleCompras() {
		return detalleCompras;
	}

	public void setDetalleCompras(Set<DetalleCompra> detalleCompras) {
		this.detalleCompras = detalleCompras;
	}

	public void addDetalleCompra(DetalleCompra detalleCompra) {

		if (detalleCompra != null) {
			if (detalleCompras == null) {
				detalleCompras = new HashSet<DetalleCompra>();
			}
			detalleCompra.setCompra(this);
			detalleCompras.add(detalleCompra);
		}
	}

}
