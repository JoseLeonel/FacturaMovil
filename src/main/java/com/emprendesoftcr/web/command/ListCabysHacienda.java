package com.emprendesoftcr.web.command;

import java.util.List;

public class ListCabysHacienda {
	private Double total;
	private Integer cantidad;
	private List<CabysHaciendaCommand> cabys;
	
	public Double getTotal() {
		return total;
	}
	
	public void setTotal(Double total) {
	}
	
	public Integer getCantidad() {
		return cantidad;
	}
	
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	
	public List<CabysHaciendaCommand> getCabys() {
		return cabys;
	}
	
	public void setCabys(List<CabysHaciendaCommand> cabys) {
		this.cabys = cabys;
	}
	
	
	

}
