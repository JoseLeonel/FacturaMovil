package com.emprendesoftcr.modelo;

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

@Entity
@Table(name = "sali_entradas")
public class Salida {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long				id;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "fecha")
	private Date				fechaEmision;

	@Column(name = "motivo")
	private String			motivo;
	
	@Column(name = "monto", precision = 18, scale = 5)
	private Double			monto;

	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario			usuario;

	@ManyToOne
	@JoinColumn(name = "usuariocaja_id")
	private UsuarioCaja	usuarioCaja;
	
	//1 = Salida 2 = entrada
	@Column(name = "tipo", columnDefinition = "INT default '1'")
	private Integer						versionEsquemaXML;

	public Salida(Long id, Date fechaEmision, String motivo, Double monto, Usuario usuario, UsuarioCaja usuarioCaja, Integer versionEsquemaXML) {
		super();
		this.id = id;
		this.fechaEmision = fechaEmision;
		this.motivo = motivo;
		this.monto = monto;
		this.usuario = usuario;
		this.usuarioCaja = usuarioCaja;
		this.versionEsquemaXML = versionEsquemaXML;
	}

	public Salida() {
		super();
	}
	
	public Long getId() {
		return id;
	}

	
	public void setId(Long id) {
		this.id = id;
	}

	
	public Date getFechaEmision() {
		return fechaEmision;
	}

	
	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	
	public String getMotivo() {
		return motivo;
	}

	
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	
	public Double getMonto() {
		return monto;
	}

	
	public void setMonto(Double monto) {
		this.monto = monto;
	}

	
	public Usuario getUsuario() {
		return usuario;
	}

	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	
	public UsuarioCaja getUsuarioCaja() {
		return usuarioCaja;
	}

	
	public void setUsuarioCaja(UsuarioCaja usuarioCaja) {
		this.usuarioCaja = usuarioCaja;
	}

	
	public Integer getVersionEsquemaXML() {
		return versionEsquemaXML;
	}

	
	public void setVersionEsquemaXML(Integer versionEsquemaXML) {
		this.versionEsquemaXML = versionEsquemaXML;
	}

	
	

}
