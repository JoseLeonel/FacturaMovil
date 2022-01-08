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

@Entity
@Table(name = "sorteo_horario")
public class Sorteo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1486074228110320401L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long				id;

	@Column(name = "descripcion")
	private String			descripcion;

	@Column(name = "estado")
	private Integer			estado;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "created_at")
	private Date				created_at;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "updated_at")
	private Date				updated_at;

	@ManyToOne
	@JoinColumn(name = "tipoTiem_hor_id")
	private TipoTiempoHorario	tipoTiempoHorario;

	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario	usuarioCreacion;
	
	@ManyToOne
	@JoinColumn(name = "usua_cierr_id")
	private Usuario	usuarioCierraSorteo;

	
}
