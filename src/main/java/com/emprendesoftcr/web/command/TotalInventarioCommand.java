package com.emprendesoftcr.web.command;

import com.emprendesoftcr.Utils.Utils;

public class TotalInventarioCommand {

	private Double	totalCosto;
	private Double	totalPublico;
	private Double	totalMayorista;
	private Double	totalEspecial;

	public TotalInventarioCommand() {
		super();
	}

	public TotalInventarioCommand(Double totalCosto,Double totalMayorista,Double totalEspecial,Double totalPublico) {
		super();
		this.totalCosto = totalCosto;
		this.totalEspecial = totalEspecial;
		this.totalMayorista = totalMayorista;
		this.totalPublico = totalPublico;
	}

	
	public Double getTotalCosto() {
		return totalCosto;
	}

	public String getTotalCostoSTR() {
		return Utils.formateadorMiles(totalCosto);
	}

	
	public void setTotalCosto(Double totalCosto) {
		this.totalCosto = totalCosto;
	}

	
	public String getTotalPublicoSTR() {
		return Utils.formateadorMiles(totalPublico);
	}

	public Double getTotalPublico() {
		return totalPublico;
	}
	
	public void setTotalPublico(Double totalPublico) {
		this.totalPublico = totalPublico;
	}

	
	public Double getTotalMayorista() {
		return totalMayorista;
	}

	public String getTotalMayoristaSTR() {
		return Utils.formateadorMiles(totalMayorista) ;
	}

	
	public void setTotalMayorista(Double totalMayorista) {
		this.totalMayorista = totalMayorista;
	}

	
	public Double getTotalEspecial() {
		return totalEspecial;
	}


	public String getTotalEspecialSTR() {
		return Utils.formateadorMiles(totalEspecial) ;
	}

	
	public void setTotalEspecial(Double totalEspecial) {
		this.totalEspecial = totalEspecial;
	}

	

}
