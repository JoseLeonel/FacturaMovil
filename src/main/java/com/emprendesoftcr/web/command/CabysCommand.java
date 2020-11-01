package com.emprendesoftcr.web.command;

import java.sql.Blob;
import java.util.Date;

import com.emprendesoftcr.fisco.FacturaElectronicaUtils;
import com.emprendesoftcr.modelo.Cabys;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.utils.Constantes;
import com.emprendesoftcr.utils.Utils;

public class CabysCommand {
	private Long		id;
	
	private String	codigo;
	
	private String	descripcion;
	private Blob	origen;
	private String	uri;
	private Double impuesto;
	private String	estado;
	
	private Date created_at;
	


	private Date updated_at;
	
	private Empresa empresa;

	
	
	
	public CabysCommand(Cabys cabys) {
		super();
		this.id = cabys.getId();
		this.descripcion = cabys.getDescripcion();
		this.codigo = cabys.getCodigo();
		this.estado = cabys.getEstado();
		this.created_at = cabys.getCreated_at();
		this.updated_at = cabys.getUpdated_at();
		this.empresa = cabys.getEmpresa();
		this.origen = cabys.getOrigen();
		this.impuesto= cabys.getImpuesto();
		this.uri = cabys.getUri();

	}


	public Long getId() {
		return id;
	}

	
	public void setId(Long id) {
		this.id = id;
	}
	

	public Double getImpuesto() {
		return impuesto;
	}


	
	public void setImpuesto(Double impuesto) {
		this.impuesto = impuesto;
	}


public String getCreated_atSTR() {
	if (this.created_at != null) {
		return Utils.getFechaGeneraReporte(this.created_at);
	}
	return Constantes.EMPTY;
}

public String getOrigenSTR() throws Exception {
	return FacturaElectronicaUtils.convertirBlodToString(this.origen);
}

public String getOrigenSTRCorte() throws Exception {
	String datos =  FacturaElectronicaUtils.convertirBlodToString(this.origen);
	String mensaje = datos != null ? datos:Constantes.EMPTY;
	return mensaje.length() > 20 ? mensaje.substring(0,20 ): mensaje;
	
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

	
	public Empresa getEmpresa() {
		return empresa;
	}

	
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}


	
	


	
	
	public Blob getOrigen() {
		return origen;
	}


	
	public void setOrigen(Blob origen) {
		this.origen = origen;
	}


	public String getUri() {
		return uri;
	}


	
	public void setUri(String uri) {
		this.uri = uri;
	}
	
	
	
	
}
