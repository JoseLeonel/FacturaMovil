package com.emprendesoftcr.web.command;

import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.Utils.Utils;
import com.emprendesoftcr.fisco.MapEnums;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.sqlNativo.HaciendaNative;
import com.emprendesoftcr.modelo.sqlNativo.HaciendaNativeByEmpresaAndFechaAndCliente;

public class HaciendaNativeCommand {

	private Long		id;

	private String	fechaEmisor;

	private String	tipoDoc;

	private Double	totalReceptor;

	private String	consecutivo;
	private String	estado;

	private Empresa	empresa;

	private String	nombreReceptor;
	
	
	

	public HaciendaNativeCommand(HaciendaNative haciendaNative) {
		super();
		this.id = haciendaNative.getId();
		this.fechaEmisor =! haciendaNative.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_COMPRA_SIMPLIFICADA)?  Utils.getFechaGeneraHacienda(haciendaNative.getFechaEmisor()):Utils.getFechaGeneraHacienda(haciendaNative.getCreated_at());
		this.tipoDoc = MapEnums.ENUM_TIPO_DOC.get(haciendaNative.getTipoDoc());
		this.totalReceptor = haciendaNative.getTotalReceptor();
		this.consecutivo = haciendaNative.getConsecutivo();
		this.estado = MapEnums.ENUM_ESTADO_HACIENTA.get(haciendaNative.getEstado());
		this.empresa = haciendaNative.getEmpresa();
		this.nombreReceptor = haciendaNative.getNombreReceptor();

	}
	
	
	

	public HaciendaNativeCommand(HaciendaNativeByEmpresaAndFechaAndCliente haciendaNativeByEmpresaAndFechaAndCliente) {
		super();
		this.id = haciendaNativeByEmpresaAndFechaAndCliente.getId();
		this.fechaEmisor = Utils.getFechaGeneraHacienda(haciendaNativeByEmpresaAndFechaAndCliente.getFechaEmisor());
		this.tipoDoc = MapEnums.ENUM_TIPO_DOC.get(haciendaNativeByEmpresaAndFechaAndCliente.getTipoDoc());
		this.totalReceptor = haciendaNativeByEmpresaAndFechaAndCliente.getTotalReceptor();
		this.consecutivo = haciendaNativeByEmpresaAndFechaAndCliente.getConsecutivo();
		this.estado = MapEnums.ENUM_ESTADO_HACIENTA.get(haciendaNativeByEmpresaAndFechaAndCliente.getEstado());
		this.empresa = haciendaNativeByEmpresaAndFechaAndCliente.getEmpresa();
		this.nombreReceptor = haciendaNativeByEmpresaAndFechaAndCliente.getNombreReceptor();
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	
	public String getFechaEmisor() {
		return fechaEmisor;
	}

	
	public void setFechaEmisor(String fechaEmisor) {
		this.fechaEmisor = fechaEmisor;
	}

	public String getTipoDoc() {
		return tipoDoc;
	}

	public void setTipoDoc(String tipoDoc) {
		this.tipoDoc = tipoDoc;
	}

	public Double getTotalReceptor() {
		return totalReceptor;
	}

	public void setTotalReceptor(Double totalReceptor) {
		this.totalReceptor = totalReceptor;
	}

	public String getConsecutivo() {
		return consecutivo;
	}

	public void setConsecutivo(String consecutivo) {
		this.consecutivo = consecutivo;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public String getNombreReceptor() {
		return nombreReceptor;
	}

	public void setNombreReceptor(String nombreReceptor) {
		this.nombreReceptor = nombreReceptor;
	}

}
