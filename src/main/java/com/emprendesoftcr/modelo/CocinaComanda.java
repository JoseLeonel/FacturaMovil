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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "coci_coman")
public class CocinaComanda implements Serializable {

	private static final long	serialVersionUID	= 5560920601846366179L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long							id;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "created_at")
	private Date							created_at;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "updated_at")
	private Date							updated_at;

	@Column(name = "nomb_impr")
	private String						nombreImpresora;

	@Column(name = "cons_factura")
	private String						consecutivofactura;

	@Column(name = "id_proforma")
	private Long							idProforma;

	@Column(name = "id_art")
	private Long							idArticulo;

	@Column(name = "codigo")
	private String						codigo;

	@Column(name = "descripcion")
	private String						descripcion;

	@Column(name = "comentario")
	private String						comentario;

	@Column(name = "total")
	private Double						total;

	@Column(name = "imp_serv")
	private Double						imp_serv;

	// 1 = Normal 2 =Anulada
	@Column(name = "estado")
	private Integer						estado;

	@Column(name = "reenvios")
	private Integer						reenvios;

	@Column(name = "id_mesa")
	private Long							idMesa;

	@ManyToOne
	@JoinColumn(name = "usua_creo_id")
	private Usuario						MeseroCreo;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "coci_id")
	private Cocina						cocina;

	@ManyToOne
	@JoinColumn(name = "usua_act_id")
	private Usuario						MeseroActualizo;

	@ManyToOne
	@JoinColumn(name = "usua_anul_id")
	private Usuario						MeseroAnulo;

	public CocinaComanda(Long id, Date created_at, Date updated_at, String nombreImpresora, String consecutivofactura, Long idProforma, Long idArticulo, String codigo, String descripcion, String comentario, Double total, Double imp_serv, Integer estado, Integer reenvios, Long idMesa, Usuario meseroCreo, Cocina cocina, Usuario meseroActualizo, Usuario meseroAnulo) {
		super();
		this.id = id;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.nombreImpresora = nombreImpresora;
		this.consecutivofactura = consecutivofactura;
		this.idProforma = idProforma;
		this.idArticulo = idArticulo;
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.comentario = comentario;
		this.total = total;
		this.imp_serv = imp_serv;
		this.estado = estado;
		this.reenvios = reenvios;
		this.idMesa = idMesa;
		MeseroCreo = meseroCreo;
		this.cocina = cocina;
		MeseroActualizo = meseroActualizo;
		MeseroAnulo = meseroAnulo;
	}

	public CocinaComanda() {
		super();
	}

	public Cocina getCocina() {
		return cocina;
	}

	public void setCocina(Cocina cocina) {
		this.cocina = cocina;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getNombreImpresora() {
		return nombreImpresora;
	}

	public void setNombreImpresora(String nombreImpresora) {
		this.nombreImpresora = nombreImpresora;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Double getImp_serv() {
		return imp_serv;
	}

	public void setImp_serv(Double imp_serv) {
		this.imp_serv = imp_serv;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public Integer getReenvios() {
		return reenvios;
	}

	public void setReenvios(Integer reenvios) {
		this.reenvios = reenvios;
	}

	public Long getIdMesa() {
		return idMesa;
	}

	public void setIdMesa(Long idMesa) {
		this.idMesa = idMesa;
	}

	public Usuario getMeseroCreo() {
		return MeseroCreo;
	}

	public void setMeseroCreo(Usuario meseroCreo) {
		MeseroCreo = meseroCreo;
	}

	public Usuario getMeseroActualizo() {
		return MeseroActualizo;
	}

	public void setMeseroActualizo(Usuario meseroActualizo) {
		MeseroActualizo = meseroActualizo;
	}

	public Usuario getMeseroAnulo() {
		return MeseroAnulo;
	}

	public void setMeseroAnulo(Usuario meseroAnulo) {
		MeseroAnulo = meseroAnulo;
	}

	public Long getIdArticulo() {
		return idArticulo;
	}

	public void setIdArticulo(Long idArticulo) {
		this.idArticulo = idArticulo;
	}

	public String getConsecutivofactura() {
		return consecutivofactura;
	}

	public void setConsecutivofactura(String consecutivofactura) {
		this.consecutivofactura = consecutivofactura;
	}

	public Long getIdProforma() {
		return idProforma;
	}

	public void setIdProforma(Long idProforma) {
		this.idProforma = idProforma;
	}

}
