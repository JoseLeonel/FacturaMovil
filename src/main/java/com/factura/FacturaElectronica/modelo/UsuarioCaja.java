package com.factura.FacturaElectronica.modelo;

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

/**
 * Modelo para abrir cajas a usuarios para facturar UsuarioCaja.
 * @author jose.
 * @since 10 jun. 2018
 */
@Entity
@Table(name = "usuarios_cajas")
public class UsuarioCaja implements Serializable {

	private static final long	serialVersionUID	= 8895530294398977996L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer						id;

	@Column(name = "total_fondo_inicial")
	private Double						totalFondoInicial;

	@Column(name = "total_efectivo")
	private Double						totalEfectivo;

	@Column(name = "total_tarjeta")
	private Double						totalTarjeta;

	@Column(name = "total_banco")
	private Double						totalBanco;

	@Column(name = "total_credito")
	private Double						totalCredito;

	@Column(name = "total_abono")
	private Double						totalAbono;

	@Column(name = "total_neto")
	private Double						totalNeto;

	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario						usuario;

	@ManyToOne
	@JoinColumn(name = "caja_id")
	private Caja							caja;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "created_at")
	private Date							created_at;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "updated_at")
	private Date							updated_at;

	@Column(name = "estado")
	private String						estado;

	public UsuarioCaja(Integer id, Double totalFondoInicial, Double totalEfectivo, Double totalTarjeta, Double totalBanco, Double totalCredito, Double totalAbono, Double totalNeto, Usuario usuario, Caja caja, Date created_at, Date updated_at, String estado) {
		super();
		this.id = id;
		this.totalFondoInicial = totalFondoInicial;
		this.totalEfectivo = totalEfectivo;
		this.totalTarjeta = totalTarjeta;
		this.totalBanco = totalBanco;
		this.totalCredito = totalCredito;
		this.totalAbono = totalAbono;
		this.totalNeto = totalNeto;
		this.usuario = usuario;
		this.caja = caja;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.estado = estado;
	}

	public UsuarioCaja() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getTotalFondoInicial() {
		return totalFondoInicial;
	}

	public void setTotalFondoInicial(Double totalFondoInicial) {
		this.totalFondoInicial = totalFondoInicial;
	}

	public Double getTotalEfectivo() {
		return totalEfectivo;
	}

	public void setTotalEfectivo(Double totalEfectivo) {
		this.totalEfectivo = totalEfectivo;
	}

	public Double getTotalTarjeta() {
		return totalTarjeta;
	}

	public void setTotalTarjeta(Double totalTarjeta) {
		this.totalTarjeta = totalTarjeta;
	}

	public Double getTotalBanco() {
		return totalBanco;
	}

	public void setTotalBanco(Double totalBanco) {
		this.totalBanco = totalBanco;
	}

	public Double getTotalCredito() {
		return totalCredito;
	}

	public void setTotalCredito(Double totalCredito) {
		this.totalCredito = totalCredito;
	}

	public Double getTotalAbono() {
		return totalAbono;
	}

	public void setTotalAbono(Double totalAbono) {
		this.totalAbono = totalAbono;
	}

	public Double getTotalNeto() {
		return totalNeto;
	}

	public void setTotalNeto(Double totalNeto) {
		this.totalNeto = totalNeto;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Caja getCaja() {
		return caja;
	}

	public void setCaja(Caja caja) {
		this.caja = caja;
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

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
