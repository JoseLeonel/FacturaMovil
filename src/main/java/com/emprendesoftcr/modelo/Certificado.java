package com.emprendesoftcr.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * Informacion de la llave .p12 Certificado.
 * @author jose.
 * @since 12 jul. 2018
 */
@Entity
@Table(name = "certificados")
public class Certificado implements Serializable {

	private static final long serialVersionUID = 2630706140022604357L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer	id;

	@OneToOne
	@JoinColumn(name = "empresa_id")
	private Empresa	empresa;

	@Column(name = "privateKey")
	private String	privateKey;

	@Column(name = "certificate")
	private String	certificate;

	@Column(name = "x509serialNumber")
	private String	x509serialNumber;

	@Column(name = "x509issuerName")
	private String	x509issuerName;

	@Column(name = "certhash")
	private String	certhash;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "created_at")
	private Date		created_at;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "updated_at")
	private Date		updated_at;

	public Certificado(Integer id, Empresa empresa, String privateKey, String certificate, String x509serialNumber, String x509issuerName, String certhash) {
		super();
		this.id = id;
		this.empresa = empresa;
		this.privateKey = privateKey;
		this.certificate = certificate;
		this.x509serialNumber = x509serialNumber;
		this.x509issuerName = x509issuerName;
		this.certhash = certhash;
	}

	public Certificado() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public String getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}

	public String getCertificate() {
		return certificate;
	}

	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}

	public String getX509serialNumber() {
		return x509serialNumber;
	}

	public void setX509serialNumber(String x509serialNumber) {
		this.x509serialNumber = x509serialNumber;
	}

	public String getX509issuerName() {
		return x509issuerName;
	}

	public void setX509issuerName(String x509issuerName) {
		this.x509issuerName = x509issuerName;
	}

	public String getCerthash() {
		return certhash;
	}

	public void setCerthash(String certhash) {
		this.certhash = certhash;
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

}
