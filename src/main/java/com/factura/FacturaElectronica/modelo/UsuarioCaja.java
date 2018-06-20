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
 * Modelo para abrir cajas a usuarios para facturar UsuarioCaja.
 * @author jose.
 * @since 10 jun. 2018
 */
@Entity
@Table(name = "usuarios_cajas")
public class UsuarioCaja implements Serializable {

	
	private static final long serialVersionUID = 8895530294398977996L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer	id;

	@Column(name = "total_fondo_inicial")
	private BigDecimal	totalFondoInicial;

	@Column(name = "total_efectivo")
	private BigDecimal	totalEfectivo;

	@Column(name = "total_tarjeta")
	private BigDecimal	totalTarjeta;

	@Column(name = "total_banco")
	private BigDecimal	totalBanco;

	@Column(name = "total_credito")
	private BigDecimal	totalCredito;

	@Column(name = "total_abono")
	private BigDecimal	totalAbono;

	@Column(name = "total_neto")
	private BigDecimal	totalNeto;

	
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario	usuario;

	@ManyToOne
	@JoinColumn(name = "caja_id")
	private Caja		caja;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "created_at")
	private Date		created_at;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "updated_at")
	private Date		updated_at;

	@Column(name = "estado")
	private String	estado;

	

	

	public UsuarioCaja(Integer id, BigDecimal totalFondoInicial, BigDecimal totalEfectivo, BigDecimal totalTarjeta, BigDecimal totalBanco, BigDecimal totalCredito, BigDecimal totalAbono, BigDecimal totalNeto, Usuario usuario, Caja caja, Date created_at, Date updated_at, String estado) {
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
		this.totalFondoInicial = BigDecimal.ZERO;
		this.totalEfectivo = BigDecimal.ZERO;
		this.totalTarjeta = BigDecimal.ZERO;
		this.totalBanco = BigDecimal.ZERO;
		this.totalCredito = BigDecimal.ZERO;
		this.totalNeto = BigDecimal.ZERO;
		this.estado = Constantes.ESTADO_ACTIVO;
		this.totalAbono = BigDecimal.ZERO;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	
	public BigDecimal getTotalFondoInicial() {
		return totalFondoInicial;
	}

	
	public void setTotalFondoInicial(BigDecimal totalFondoInicial) {
		this.totalFondoInicial = totalFondoInicial;
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

	
	public BigDecimal getTotalCredito() {
		return totalCredito;
	}

	
	public void setTotalCredito(BigDecimal totalCredito) {
		this.totalCredito = totalCredito;
	}

	
	public BigDecimal getTotalAbono() {
		return totalAbono;
	}

	
	public void setTotalAbono(BigDecimal totalAbono) {
		this.totalAbono = totalAbono;
	}

	
	public BigDecimal getTotalNeto() {
		return totalNeto;
	}

	
	public void setTotalNeto(BigDecimal totalNeto) {
		this.totalNeto = totalNeto;
	}

	
		

}
