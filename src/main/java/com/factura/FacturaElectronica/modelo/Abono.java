package com.factura.FacturaElectronica.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
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

import com.factura.FacturaElectronica.Utils.Constantes;

/**
 * Modelo para los abonos de una cuenta por cobrar Abono.
 * @author jose.
 * @since 19 abr. 2018
 */
@Entity
@Table(name = "abonos")
public class Abono implements Serializable {

	private static final long	serialVersionUID	= 3236006908675739266L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer						id;

	@Column(name = "nota")
	private String						nota;

	@Column(name = "recibo")
	private String						recibo;

	@Column(name = "transferencia")
	private String						transferencia;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY")
	@Column(name = "fecha_pago")
	private Date							fechaPago;

	@Column(name = "total_efectivo")
	private BigDecimal						totalEfectivo;

	@Column(name = "total_tarjeta")
	private BigDecimal						totalTarjeta;
	@Column(name = "total_banco")
	private BigDecimal						totalBanco;

	@Column(name = "total")
	private BigDecimal						total;

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
	@JoinColumn(name = "cuentas_cobrar_id")
	private CuentaCobrar			cuentaCobrar;

	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario						usuario;

	public Abono() {
		super();
		this.estado = Constantes.ABONO_ESTADO_PAGADO;
	
		this.transferencia = Constantes.EMPTY;
		this.recibo = Constantes.EMPTY;
		this.nota = Constantes.EMPTY;
		this.created_at = new Date();
		this.updated_at = new Date();

	}

	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNota() {
		return nota;
	}

	

	public void setNota(String nota) {
		this.nota = nota;
	}

	public String getRecibo() {
		return recibo;
	}

	public void setRecibo(String recibo) {
		this.recibo = recibo;
	}

	public String getTransferencia() {
		return transferencia;
	}

	public void setTransferencia(String transferencia) {
		this.transferencia = transferencia;
	}

	public Date getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
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

	public CuentaCobrar getCuentaCobrar() {
		return cuentaCobrar;
	}

	public void setCuentaCobrar(CuentaCobrar cuentaCobrar) {
		this.cuentaCobrar = cuentaCobrar;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}



	
	public BigDecimal getTotalEfectivo() {
		return totalEfectivo;
	}



	
	public void setTotalEfectivo(BigDecimal totalEfectivo) {
		this.totalEfectivo = totalEfectivo;
	}



	
	public BigDecimal getTotalTarjeta() {
		return totalTarjeta;
	}



	
	public void setTotalTarjeta(BigDecimal totalTarjeta) {
		this.totalTarjeta = totalTarjeta;
	}



	
	public BigDecimal getTotalBanco() {
		return totalBanco;
	}



	
	public void setTotalBanco(BigDecimal totalBanco) {
		this.totalBanco = totalBanco;
	}



	
	public BigDecimal getTotal() {
		return total;
	}



	
	public void setTotal(BigDecimal total) {
		this.total = total;
	}

}