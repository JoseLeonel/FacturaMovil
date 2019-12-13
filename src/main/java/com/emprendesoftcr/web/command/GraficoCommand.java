package com.emprendesoftcr.web.command;

import java.util.Date;

import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.GraficoVenta;

public class GraficoCommand {
	
	private Long							id;

	private Integer						anno;


	private Integer						mes;

	
	private Double						total;

	
	private Date							updated_at;
	
	
	private Empresa						empresa;


	
	public GraficoCommand(GraficoVenta graficoVenta) {
		super();
		this.id = graficoVenta.getId();
		this.empresa = graficoVenta.getEmpresa();
		this.mes = graficoVenta.getMes();
		this.anno = graficoVenta.getAnno();
		this.updated_at = graficoVenta.getUpdated_at();
		this.total = graficoVenta.getTotal();
	}



	public Long getId() {
		return id;
	}


	
	public void setId(Long id) {
		this.id = id;
	}


	
	public Integer getAnno() {
		return anno;
	}


	
	public void setAnno(Integer anno) {
		this.anno = anno;
	}


	
	public Integer getMes() {
		return mes;
	}


	
	public void setMes(Integer mes) {
		this.mes = mes;
	}


	
	public Double getTotal() {
		return total;
	}


	
	public void setTotal(Double total) {
		this.total = total;
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
	
	
	
}
