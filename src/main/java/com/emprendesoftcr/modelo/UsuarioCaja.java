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

import com.emprendesoftcr.Utils.Utils;

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
	private Long							id;

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

	@Column(name = "impuesto_servicio")
	private Double						totalServicio;

	@Column(name = "total_dolares")
	private Double						totalDolares;

	@Column(name = "bille_50000", columnDefinition = "default '0.00'", precision = 18, scale = 2)
	private Double						billete50000;

	@Column(name = "bille_20000", columnDefinition = "default '0.00'", precision = 18, scale = 2)
	private Double						billete20000;

	@Column(name = "bille_10000", columnDefinition = "default '0.00'", precision = 18, scale = 2)
	private Double						billete10000;

	@Column(name = "bille_5000", columnDefinition = "default '0.00'", precision = 18, scale = 2)
	private Double						billete5000;

	@Column(name = "bille_2000", columnDefinition = "default '0.00'", precision = 18, scale = 2)
	private Double						billete2000;

	@Column(name = "bille_1000", columnDefinition = "default '0.00'", precision = 18, scale = 2)
	private Double						billete1000;

	@Column(name = "mone_500", columnDefinition = "default '0.00'", precision = 18, scale = 2)
	private Double						moneda500;

	@Column(name = "mone_100", columnDefinition = "default '0.00'", precision = 18, scale = 2)
	private Double						moneda100;

	@Column(name = "mone_50", columnDefinition = "default '0.00'", precision = 18, scale = 2)
	private Double						moneda50;

	@Column(name = "mone_25", columnDefinition = "default '0.00'", precision = 18, scale = 2)
	private Double						moneda25;

	@Column(name = "mone_10", columnDefinition = "default '0.00'", precision = 18, scale = 2)
	private Double						moneda10;

	@Column(name = "mone_5", columnDefinition = "default '0.00'", precision = 18, scale = 2)
	private Double						moneda5;

	@Column(name = "conte_tarj", columnDefinition = "default '0.00'", precision = 18, scale = 2)
	private Double						conteoTarjeta;

	@Column(name = "conte_dolar", columnDefinition = "default '0.00'", precision = 18, scale = 2)
	private Double						conteoDolar;

	@Column(name = "total_conver", columnDefinition = "default '0.00'", precision = 18, scale = 2)
	private Double						totalConversionColones;

	@Column(name = "tipo_camb", columnDefinition = "default '0.00'", precision = 18, scale = 2)
	private Double						tipoCambio;

	@Column(name = "nota_cred", columnDefinition = "default '0.00'", precision = 18, scale = 2)
	private Double						notaCredito;

	@Column(name = "nota_deb", columnDefinition = "default '0.00'", precision = 18, scale = 2)
	private Double						notaDebito;
	
	@Column(name = "conteo_manual", columnDefinition = "default '0.00'", precision = 18, scale = 2)
	private Double						ConteoManual;
	
	@Column(name = "diferencia", columnDefinition = "default '0.00'", precision = 18, scale = 2)
	private Double						diferencia;

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

	@Column(name = "notificacion", columnDefinition = "default '0'")
	private Integer						notificacion;

	

	

	

	public UsuarioCaja(Long id, Double totalFondoInicial, Double totalEfectivo, Double totalTarjeta, Double totalBanco, Double totalCredito, Double totalAbono, Double totalNeto, Double totalServicio, Double totalDolares, Double billete50000, Double billete20000, Double billete10000, Double billete5000, Double billete2000, Double billete1000, Double moneda500, Double moneda100, Double moneda50, Double moneda25, Double moneda10, Double moneda5, Double conteoTarjeta, Double conteoDolar, Double totalConversionColones, Double tipoCambio, Double notaCredito, Double notaDebito, Double conteoManual, Double diferencia, Usuario usuario, Caja caja, Date created_at, Date updated_at, String estado, Integer notificacion) {
		super();
		this.id = id;
		this.totalFondoInicial = totalFondoInicial;
		this.totalEfectivo = totalEfectivo;
		this.totalTarjeta = totalTarjeta;
		this.totalBanco = totalBanco;
		this.totalCredito = totalCredito;
		this.totalAbono = totalAbono;
		this.totalNeto = totalNeto;
		this.totalServicio = totalServicio;
		this.totalDolares = totalDolares;
		this.billete50000 = billete50000;
		this.billete20000 = billete20000;
		this.billete10000 = billete10000;
		this.billete5000 = billete5000;
		this.billete2000 = billete2000;
		this.billete1000 = billete1000;
		this.moneda500 = moneda500;
		this.moneda100 = moneda100;
		this.moneda50 = moneda50;
		this.moneda25 = moneda25;
		this.moneda10 = moneda10;
		this.moneda5 = moneda5;
		this.conteoTarjeta = conteoTarjeta;
		this.conteoDolar = conteoDolar;
		this.totalConversionColones = totalConversionColones;
		this.tipoCambio = tipoCambio;
		this.notaCredito = notaCredito;
		this.notaDebito = notaDebito;
		ConteoManual = conteoManual;
		this.diferencia = diferencia;
		this.usuario = usuario;
		this.caja = caja;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.estado = estado;
		this.notificacion = notificacion;
	}

	public UsuarioCaja() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getTotalFondoInicial() {
		return totalFondoInicial;
	}

	public String getTotalFondoInicialSTR() {
		return Utils.formateadorMiles(this.totalFondoInicial);
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

	public Double getTotalCredito() {
		return totalCredito;
	}

	public void setTotalCredito(Double totalCredito) {
		this.totalCredito = totalCredito;
	}

	public String getTotalCreditoSTR() {
		return Utils.formateadorMiles(this.totalCredito);
	}

	public Double getTotalAbono() {
		return totalAbono;
	}

	public void setTotalAbono(Double totalAbono) {
		this.totalAbono = totalAbono;
	}

	public String getTotalAbonoSTR() {
		return Utils.formateadorMiles(this.totalAbono);
	}

	public Double getTotalNeto() {
		return totalNeto;
	}

	public void setTotalNeto(Double totalNeto) {
		this.totalNeto = totalNeto;
	}

	public String getTotalNetoSTR() {
		return Utils.formateadorMiles(this.totalNeto);
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

	public Double getTotalServicio() {
		return totalServicio;
	}

	public void setTotalServicio(Double totalServicio) {
		this.totalServicio = totalServicio;
	}

	public String getTotalServicioSTR() {
		return Utils.formateadorMiles(this.totalServicio);
	}

	public Double getTotalDolares() {
		return totalDolares;
	}

	public void setTotalDolares(Double totalDolares) {
		this.totalDolares = totalDolares;
	}

	public String getTotalDolaresSTR() {
		return Utils.formateadorMiles(this.totalDolares);
	}

	public Double getBillete50000() {
		return billete50000;
	}

	public void setBillete50000(Double billete50000) {
		this.billete50000 = billete50000;
	}

	public Double getBillete20000() {
		return billete20000;
	}

	public void setBillete20000(Double billete20000) {
		this.billete20000 = billete20000;
	}

	public Double getBillete10000() {
		return billete10000;
	}

	public void setBillete10000(Double billete10000) {
		this.billete10000 = billete10000;
	}

	public Double getBillete5000() {
		return billete5000;
	}

	public void setBillete5000(Double billete5000) {
		this.billete5000 = billete5000;
	}

	public Double getBillete2000() {
		return billete2000;
	}

	public void setBillete2000(Double billete2000) {
		this.billete2000 = billete2000;
	}

	public Double getBillete1000() {
		return billete1000;
	}

	public void setBillete1000(Double billete1000) {
		this.billete1000 = billete1000;
	}

	public Double getMoneda500() {
		return moneda500;
	}

	public void setMoneda500(Double moneda500) {
		this.moneda500 = moneda500;
	}

	public Double getMoneda100() {
		return moneda100;
	}

	public void setMoneda100(Double moneda100) {
		this.moneda100 = moneda100;
	}

	public Double getMoneda50() {
		return moneda50;
	}

	public void setMoneda50(Double moneda50) {
		this.moneda50 = moneda50;
	}

	public Double getMoneda25() {
		return moneda25;
	}

	public void setMoneda25(Double moneda25) {
		this.moneda25 = moneda25;
	}

	public Double getMoneda10() {
		return moneda10;
	}

	public void setMoneda10(Double moneda10) {
		this.moneda10 = moneda10;
	}

	public Double getMoneda5() {
		return moneda5;
	}

	public void setMoneda5(Double moneda5) {
		this.moneda5 = moneda5;
	}

	public Double getConteoTarjeta() {
		return conteoTarjeta;
	}

	public void setConteoTarjeta(Double conteoTarjeta) {
		this.conteoTarjeta = conteoTarjeta;
	}

	public Double getConteoDolar() {
		return conteoDolar;
	}

	public void setConteoDolar(Double conteoDolar) {
		this.conteoDolar = conteoDolar;
	}

	public Double getTipoCambio() {
		return tipoCambio;
	}

	public void setTipoCambio(Double tipoCambio) {
		this.tipoCambio = tipoCambio;
	}

	public Double getNotaCredito() {
		return notaCredito;
	}

	public void setNotaCredito(Double notaCredito) {
		this.notaCredito = notaCredito;
	}

	public Double getNotaDebito() {
		return notaDebito;
	}

	public void setNotaDebito(Double notaDebito) {
		this.notaDebito = notaDebito;
	}

	public Integer getNotificacion() {
		return notificacion;
	}

	public void setNotificacion(Integer notificacion) {
		this.notificacion = notificacion;
	}

	
	public Double getConteoManual() {
		return ConteoManual;
	}

	
	public void setConteoManual(Double conteoManual) {
		ConteoManual = conteoManual;
	}

	
	public Double getTotalConversionColones() {
		return totalConversionColones;
	}

	
	public void setTotalConversionColones(Double totalConversionColones) {
		this.totalConversionColones = totalConversionColones;
	}

	
	public Double getDiferencia() {
		return diferencia;
	}

	
	public void setDiferencia(Double diferencia) {
		this.diferencia = diferencia;
	}
	
	

}
