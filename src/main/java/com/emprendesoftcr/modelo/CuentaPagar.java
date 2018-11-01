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

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import com.emprendesoftcr.Utils.Utils;

@Entity
@Table(name = "cuentas_pagar")
public class CuentaPagar implements Serializable {

	private static final long	serialVersionUID	= 4405985467981146988L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long							id;

	@Column(name = "consecutivo")
	private String						consecutivo;

	@Column(name = "total")
	private Double						total;

	@Column(name = "total_abono")
	private Double						totalAbono;

	@Column(name = "total_saldo")
	private Double						totalSaldo;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY")
	@Column(name = "fecha_credito")
	private Date							fechaCredito;

	@CreatedDate
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at")
	private Date							created_at;

	@LastModifiedDate
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at")
	private Date							updated_at;

	@ManyToOne
	@JoinColumn(name = "proveedor_id")
	private Proveedor					proveedor;

	@ManyToOne
	@JoinColumn(name = "empresa_id")
	private Empresa						empresa;

	@ManyToOne
	@JoinColumn(name = "usuario_id", nullable = false)
	private Usuario						usuarioCreacion;
	
	@Column(name = "estado")
	private String						estado;


	public CuentaPagar() {
		super();
	}

	
	public CuentaPagar(Long id, String consecutivo, Double total, Double totalAbono, Double totalSaldo, Date fechaCredito, Date created_at, Date updated_at, Proveedor proveedor, Empresa empresa, Usuario usuarioCreacion, String estado) {
		super();
		this.id = id;
		this.consecutivo = consecutivo;
		this.total = total;
		this.totalAbono = totalAbono;
		this.totalSaldo = totalSaldo;
		this.fechaCredito = fechaCredito;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.proveedor = proveedor;
		this.empresa = empresa;
		this.usuarioCreacion = usuarioCreacion;
		this.estado = estado;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	

	
	

	
	public String getEstado() {
		return estado;
	}


	
	public void setEstado(String estado) {
		this.estado = estado;
	}


	public String getConsecutivo() {
		return consecutivo;
	}

	public void setConsecutivo(String consecutivo) {
		this.consecutivo = consecutivo;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Double getTotalAbono() {
		return totalAbono;
	}

	public void setTotalAbono(Double totalAbono) {
		this.totalAbono = totalAbono;
	}

	public Double getTotalSaldo() {
		return totalSaldo;
	}

	public void setTotalSaldo(Double totalSaldo) {
		this.totalSaldo = totalSaldo;
	}
	public String getTotalSTR() {
		return Utils.formateadorMiles(this.total);
	}
	public String getTotalAbonoSTR() {
		return Utils.formateadorMiles(this.totalAbono);
	}


	public String getTotalSaldoSTR() {
		return Utils.formateadorMiles(this.totalSaldo);
	}

	public Date getFechaCredito() {
		return fechaCredito;
	}

	public void setFechaCredito(Date fechaCredito) {
		this.fechaCredito = fechaCredito;
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

}
