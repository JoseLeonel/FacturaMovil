package com.emprendesoftcr.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

public class configuracionTiempo implements Serializable {

	
	private static final long serialVersionUID = 1146568277989530761L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long		id;

	@Column(name = "descripcion")
	private String	descripcion;

	@Column(name = "estado")
	private Integer	estado;
	@Column(name = "porce_ganancia")
	private Double	porcentajeGanancia;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "created_at")
	private Date		created_at;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "updated_at")
	private Date		updated_at;

	@ManyToOne
	@JoinColumn(name = "empresa_id")
	private Empresa	empresa;

}
