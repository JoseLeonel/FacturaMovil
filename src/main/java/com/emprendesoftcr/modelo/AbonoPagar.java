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

import org.springframework.format.annotation.DateTimeFormat;

import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.Utils.Utils;

/**
 * Modelo para los abonos de una cuenta por cobrar Abono.
 * @author jose.
 * @since 19 abr. 2018
 */
@Entity
@Table(name = "abono_pagar")
public class AbonoPagar implements Serializable {

	private static final long	serialVersionUID	= 3236006908675739266L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long							id;

	@Column(name = "nota", length = 60)
	private String						nota;

	@Column(name = "recibo", length = 30)
	private String						recibo;

	@Column(name = "nomb_banco", length = 20)
	private String						nombreBanco;

	@Column(name = "transferencia", length = 30)
	private String						transferencia;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY")
	@Column(name = "fecha_pago")
	private Date							fechaPago;

	@Column(name = "total_efectivo")
	private Double						totalEfectivo;

	@Column(name = "total_tarjeta")
	private Double						totalTarjeta;
	@Column(name = "total_banco")
	private Double						totalBanco;

	@Column(name = "total")
	private Double						total;

	@Column(name = "estado", length = 20)
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
	@JoinColumn(name = "cuentas_pagar_id")
	private CuentaPagar				cuentaPagar;

	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario						usuario;

	public AbonoPagar() {
		super();
		this.estado = Constantes.ABONO_ESTADO_PAGADO;

		this.transferencia = Constantes.EMPTY;
		this.recibo = Constantes.EMPTY;
		this.nota = Constantes.EMPTY;
		this.created_at = new Date();
		this.updated_at = new Date();

	}

	public AbonoPagar(Long id, String nota, String recibo, String nombreBanco, String transferencia, Date fechaPago, Double totalEfectivo, Double totalTarjeta, Double totalBanco, Double total, String estado, Date created_at, Date updated_at, CuentaPagar cuentaPagar, Usuario usuario) {
		super();
		this.id = id;
		this.nota = nota;
		this.recibo = recibo;
		this.nombreBanco = nombreBanco;
		this.transferencia = transferencia;
		this.fechaPago = fechaPago;
		this.totalEfectivo = totalEfectivo;
		this.totalTarjeta = totalTarjeta;
		this.totalBanco = totalBanco;
		this.total = total;
		this.estado = estado;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.cuentaPagar = cuentaPagar;
		this.usuario = usuario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public Double getTotalEfectivo() {
		return totalEfectivo;
	}

	public void setTotalEfectivo(Double totalEfectivo) {
		this.totalEfectivo = totalEfectivo;
	}

	public String getTotalEfectivoSTR() {
		return Utils.formateadorMiles(this.totalEfectivo);
	}

	public Double getTotalTarjeta() {
		return totalTarjeta;
	}

	public void setTotalTarjeta(Double totalTarjeta) {
		this.totalTarjeta = totalTarjeta;
	}

	public String getTotalTarjetaSTR() {
		return Utils.formateadorMiles(this.totalTarjeta);
	}

	public Double getTotalBanco() {
		return totalBanco;
	}

	public void setTotalBanco(Double totalBanco) {
		this.totalBanco = totalBanco;
	}

	public String getTotalBancoSTR() {
		return Utils.formateadorMiles(this.totalBanco);
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public String getTotalSTR() {
		return Utils.formateadorMiles(this.total);
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

	public CuentaPagar getCuentaPagar() {
		return cuentaPagar;
	}

	public void setCuentaPagar(CuentaPagar cuentaPagar) {
		this.cuentaPagar = cuentaPagar;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getNombreBanco() {
		return nombreBanco;
	}

	public void setNombreBanco(String nombreBanco) {
		this.nombreBanco = nombreBanco;
	}

}