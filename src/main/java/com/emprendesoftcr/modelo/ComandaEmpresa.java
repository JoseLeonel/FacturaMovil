package com.emprendesoftcr.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Modelo para los datos de configuracion de la comanda de cocina
 * @author Jairo Cisners
 */
@Entity
@Table(name = "comanda_empresa")
public class ComandaEmpresa implements Serializable {

	private static final long serialVersionUID = 3236006908675739266L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "empresa_id")
	private Empresa empresa;

	@Column(name = "cantidadCaracteresLinea")
	private Integer cantidadCaracteresLinea = 40;

	@Column(name = "nombreImpresora")
	private String nombreImpresora = "PDF";

	@Column(name = "formatoTickete")
	private String	formatoTickete					=    
					"==========  COCINA  ==========\n"+
	  	  	"MESA: ${mesa}\n"+
	  	  	"FECHA: ${fecha}\n"+		
	  	    "=============================\n"+
	  	    "#foreach($detalle in $detalles)"+
	  	    	"$detalle"+
	  	    "#end"+
	  	    "=============================\n"+
	  	    "\n"+
	  	    "\n"+
	  	    "\n"+
	  	    "\n";
	  	  

	public ComandaEmpresa() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Integer getCantidadCaracteresLinea() {
		return cantidadCaracteresLinea;
	}

	public void setCantidadCaracteresLinea(Integer cantidadCaracteresLinea) {
		this.cantidadCaracteresLinea = cantidadCaracteresLinea;
	}

	public String getNombreImpresora() {
		return nombreImpresora;
	}

	public void setNombreImpresora(String nombreImpresora) {
		this.nombreImpresora = nombreImpresora;
	}

	public String getFormatoTickete() {
		return formatoTickete;
	}

	public void setFormatoTickete(String formatoTickete) {
		this.formatoTickete = formatoTickete;
	}

}