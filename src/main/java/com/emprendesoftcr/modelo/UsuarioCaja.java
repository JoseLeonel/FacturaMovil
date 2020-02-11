package com.emprendesoftcr.modelo;

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

import com.emprendesoftcr.Utils.Utils;

/**
 * Modelo para abrir cajas a usuarios para facturar UsuarioCaja.
 * @author jose.
 * @since 10 jun. 2018
 */
@Entity
@Table(name = "usuarios_cajas")
public class UsuarioCaja implements Serializable {

	private static final long			serialVersionUID	= 8895530294398977996L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long									id;

	@Column(name = "total_fondo_inicial")
	private Double								totalFondoInicial;

	@Column(name = "total_efectivo")
	private Double								totalEfectivo;

	@Column(name = "total_tarjeta")
	private Double								totalTarjeta;

	@Column(name = "total_banco")
	private Double								totalBanco;

	@Column(name = "total_credito")
	private Double								totalCredito;

	@Column(name = "total_abono")
	private Double								totalAbono;

	@Column(name = "total_neto")
	private Double								totalNeto;

	@Column(name = "impuesto_servicio")
	private Double								totalServicio;

	@Column(name = "total_dolares")
	private Double								totalDolares;

	@Column(name = "conte_tarj")
	private Double								conteoTarjeta;

	@Column(name = "conte_dolar")
	private Double								conteoDolar;

	@Column(name = "total_conver")
	private Double								totalConversionColones;

	@Column(name = "tipo_camb")
	private Double								tipoCambio;

	@Column(name = "nota_cred")
	private Double								notaCredito;

	@Column(name = "nota_deb")
	private Double								notaDebito;

	@Column(name = "conteo_manual")
	private Double								ConteoManual;

	@Column(name = "diferencia")
	private Double								diferencia;

	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario								usuario;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "created_at")
	private Date									created_at;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "updated_at")
	private Date									updated_at;

	@Column(name = "estado")
	private String								estado;

	@Column(name = "notificacion")
	private Integer								notificacion;

	@OneToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinColumn(name = "usua_caja_id", referencedColumnName = "ID")
	@OrderBy("usua_caja_id DESC")
	private Set<ConteoManualCaja>	conteoManualCajas;

	@ManyToOne
	@JoinColumn(name = "caja_id")
	private Caja									caja;

	public UsuarioCaja(Long id, Double totalFondoInicial, Double totalEfectivo, Double totalTarjeta, Double totalBanco, Double totalCredito, Double totalAbono, Double totalNeto, Double totalServicio, Double totalDolares, Double conteoTarjeta, Double conteoDolar, Double totalConversionColones, Double tipoCambio, Double notaCredito, Double notaDebito, Double conteoManual, Double diferencia, Usuario usuario, Date created_at, Date updated_at, String estado, Integer notificacion, Set<ConteoManualCaja> conteoManualCajas, Caja caja) {
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
		this.conteoTarjeta = conteoTarjeta;
		this.conteoDolar = conteoDolar;
		this.totalConversionColones = totalConversionColones;
		this.tipoCambio = tipoCambio;
		this.notaCredito = notaCredito;
		this.notaDebito = notaDebito;
		this.ConteoManual = conteoManual;
		this.diferencia = diferencia;
		this.usuario = usuario;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.estado = estado;
		this.notificacion = notificacion;
		this.conteoManualCajas = conteoManualCajas;
		this.caja = caja;
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

	public void addConteoManual(ConteoManualCaja conteoManualCaja) {
    if (conteoManualCaja != null) {
        if (conteoManualCajas == null) {
        	conteoManualCajas = new HashSet<ConteoManualCaja>();
        }
        conteoManualCaja.setUsuarioCaja(this);
        conteoManualCajas.add(conteoManualCaja);
    }
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

	public Set<ConteoManualCaja> getConteoManualCajas() {
		return conteoManualCajas;
	}

	public void setConteoManualCajas(Set<ConteoManualCaja> conteoManualCajas) {
		this.conteoManualCajas = conteoManualCajas;
	}

}
