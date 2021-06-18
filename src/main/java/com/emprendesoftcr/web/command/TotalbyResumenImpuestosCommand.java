package com.emprendesoftcr.web.command;

import java.util.ArrayList;

public class TotalbyResumenImpuestosCommand {

	private Double	total_exo;
	private Double	total_otros_cargos;
	private Double	total_serv_gravados;
	private Double	total_serv_exentos;
	private Double	total_descuentos;
	private Double	total_impuesto;
	private Double	total_merc_exo;
	private Double	total_serv_exo;
	private Double	total_mercancias_gravadas;
	private Double	total_mercancias_exentas;
	private Double	total_exento;
	private Double	total_gravado;
	private Double	total_comprobante;
	private Double	imp_01;
	private Double	imp_02;
	private Double	imp_03;
	private Double	imp_04;
	private Double	imp_05;
	private Double	imp_06;
	private Double	imp_07;
	private Double	imp_08;
	
	private Double	venta_imp_01;
	private Double	venta_imp_02;
	private Double	venta_imp_03;
	private Double	venta_imp_04;
	private Double	venta_imp_05;
	private Double	venta_imp_06;
	private Double	venta_imp_07;
	private Double	venta_imp_08;
	private Double total_otros_impuestos;
	private Double total_otros_impuestos_v;
	private Double total_imp_cemento;
	private Double total_imp_cemento_v;
	
	public TotalbyResumenImpuestosCommand() {
		
	}
	public TotalbyResumenImpuestosCommand(ArrayList<TotalbyImpuestosCommand> listaDetallada) {
		
		this.total_comprobante =listaDetallada.stream().mapToDouble(l -> l.getTotal_comprobante()).sum();
		this.total_exo =listaDetallada.stream().mapToDouble(l -> l.getTotal_exo()).sum();
		this.total_otros_cargos =listaDetallada.stream().mapToDouble(l -> l.getTotal_otros_cargos()).sum();
		this.total_serv_gravados =listaDetallada.stream().mapToDouble(l -> l.getTotal_serv_gravados()).sum();
		this.total_serv_exentos =listaDetallada.stream().mapToDouble(l -> l.getTotal_serv_exentos()).sum();
		this.total_impuesto = listaDetallada.stream().mapToDouble(l -> l.getTotal_impuesto()).sum();
		this.total_merc_exo =listaDetallada.stream().mapToDouble(l -> l.getTotal_merc_exo()).sum();
		this.total_serv_exo =listaDetallada.stream().mapToDouble(l -> l.getTotal_serv_exo()).sum();
		this.total_mercancias_gravadas =listaDetallada.stream().mapToDouble(l -> l.getTotal_mercancias_gravadas()).sum();
		this.total_mercancias_exentas =listaDetallada.stream().mapToDouble(l -> l.getTotal_mercancias_exentas()).sum();
		this.total_exento =listaDetallada.stream().mapToDouble(l -> l.getTotal_exento()).sum();
		this.total_gravado =listaDetallada.stream().mapToDouble(l -> l.getTotal_gravado()).sum();
		this.total_descuentos =listaDetallada.stream().mapToDouble(l -> l.getTotal_descuentos()).sum();
		this.imp_01 =listaDetallada.stream().mapToDouble(l -> l.getImp_01()).sum();
		this.imp_02=listaDetallada.stream().mapToDouble(l -> l.getImp_02()).sum();
		this.imp_03=listaDetallada.stream().mapToDouble(l -> l.getImp_03()).sum();
		this.imp_04=listaDetallada.stream().mapToDouble(l -> l.getImp_04()).sum();
		this.imp_05=listaDetallada.stream().mapToDouble(l -> l.getImp_05()).sum();
		this.imp_06=listaDetallada.stream().mapToDouble(l -> l.getImp_06()).sum();
		this.imp_07=listaDetallada.stream().mapToDouble(l -> l.getImp_07()).sum();
		this.imp_08=listaDetallada.stream().mapToDouble(l -> l.getImp_08()).sum();
		this.venta_imp_01=listaDetallada.stream().mapToDouble(l -> l.getVenta_imp_01()).sum();
		this.venta_imp_02=listaDetallada.stream().mapToDouble(l -> l.getVenta_imp_02()).sum();
		this.venta_imp_03=listaDetallada.stream().mapToDouble(l -> l.getVenta_imp_03()).sum();
		this.venta_imp_04=listaDetallada.stream().mapToDouble(l -> l.getVenta_imp_04()).sum();
		this.venta_imp_05=listaDetallada.stream().mapToDouble(l -> l.getVenta_imp_05()).sum();
		this.venta_imp_06=listaDetallada.stream().mapToDouble(l -> l.getVenta_imp_06()).sum();
		this.venta_imp_07=listaDetallada.stream().mapToDouble(l -> l.getVenta_imp_07()).sum();
		this.venta_imp_08=listaDetallada.stream().mapToDouble(l -> l.getVenta_imp_08()).sum();
		this.total_otros_impuestos = listaDetallada.stream().mapToDouble(l -> l.getTotal_otros_impuestos()).sum();
		this.total_otros_impuestos_v	= listaDetallada.stream().mapToDouble(l -> l.getTotal_otros_impuestos_v()).sum();
		this.total_imp_cemento = listaDetallada.stream().mapToDouble(l -> l.getTotal_imp_cemento()).sum();
		this.total_imp_cemento_v=listaDetallada.stream().mapToDouble(l -> l.getTotal_imp_cemento_v()).sum(); 		
	}

	
	
	
	
	public Double getTotal_imp_cemento() {
		return total_imp_cemento;
	}
	
	public void setTotal_imp_cemento(Double total_imp_cemento) {
		this.total_imp_cemento = total_imp_cemento;
	}
	
	public Double getTotal_imp_cemento_v() {
		return total_imp_cemento_v;
	}
	
	public void setTotal_imp_cemento_v(Double total_imp_cemento_v) {
		this.total_imp_cemento_v = total_imp_cemento_v;
	}
	public Double getTotal_otros_impuestos_v() {
		return total_otros_impuestos_v;
	}
	
	public void setTotal_otros_impuestos_v(Double total_otros_impuestos_v) {
		this.total_otros_impuestos_v = total_otros_impuestos_v;
	}
	public Double getTotal_exo() {
		return total_exo;
	}
	
	public void setTotal_exo(Double total_exo) {
		this.total_exo = total_exo;
	}
	
	public Double getTotal_otros_cargos() {
		return total_otros_cargos;
	}
	
	public void setTotal_otros_cargos(Double total_otros_cargos) {
		this.total_otros_cargos = total_otros_cargos;
	}
	
	public Double getTotal_serv_gravados() {
		return total_serv_gravados;
	}
	
	public void setTotal_serv_gravados(Double total_serv_gravados) {
		this.total_serv_gravados = total_serv_gravados;
	}
	
	public Double getTotal_serv_exentos() {
		return total_serv_exentos;
	}
	
	public void setTotal_serv_exentos(Double total_serv_exentos) {
		this.total_serv_exentos = total_serv_exentos;
	}
	
	public Double getTotal_impuesto() {
		return total_impuesto;
	}
	
	public void setTotal_impuesto(Double total_impuesto) {
		this.total_impuesto = total_impuesto;
	}
	
	public Double getTotal_merc_exo() {
		return total_merc_exo;
	}
	
	public void setTotal_merc_exo(Double total_merc_exo) {
		this.total_merc_exo = total_merc_exo;
	}
	
	public Double getTotal_serv_exo() {
		return total_serv_exo;
	}
	
	public void setTotal_serv_exo(Double total_serv_exo) {
		this.total_serv_exo = total_serv_exo;
	}
	
	public Double getTotal_mercancias_gravadas() {
		return total_mercancias_gravadas;
	}
	
	public void setTotal_mercancias_gravadas(Double total_mercancias_gravadas) {
		this.total_mercancias_gravadas = total_mercancias_gravadas;
	}
	
	public Double getTotal_mercancias_exentas() {
		return total_mercancias_exentas;
	}
	
	public void setTotal_mercancias_exentas(Double total_mercancias_exentas) {
		this.total_mercancias_exentas = total_mercancias_exentas;
	}
	
	public Double getTotal_exento() {
		return total_exento;
	}
	
	public void setTotal_exento(Double total_exento) {
		this.total_exento = total_exento;
	}
	
	public Double getTotal_gravado() {
		return total_gravado;
	}
	
	public void setTotal_gravado(Double total_gravado) {
		this.total_gravado = total_gravado;
	}
	
	public Double getTotal_comprobante() {
		return total_comprobante;
	}
	
	public void setTotal_comprobante(Double total_comprobante) {
		this.total_comprobante = total_comprobante;
	}
	
	public Double getImp_01() {
		return imp_01;
	}
	
	public void setImp_01(Double imp_01) {
		this.imp_01 = imp_01;
	}
	
	public Double getImp_02() {
		return imp_02;
	}
	
	public void setImp_02(Double imp_02) {
		this.imp_02 = imp_02;
	}
	
	public Double getImp_03() {
		return imp_03;
	}
	
	public void setImp_03(Double imp_03) {
		this.imp_03 = imp_03;
	}
	
	public Double getImp_04() {
		return imp_04;
	}
	
	public void setImp_04(Double imp_04) {
		this.imp_04 = imp_04;
	}
	
	public Double getImp_05() {
		return imp_05;
	}
	
	public void setImp_05(Double imp_05) {
		this.imp_05 = imp_05;
	}
	
	public Double getImp_06() {
		return imp_06;
	}
	
	public void setImp_06(Double imp_06) {
		this.imp_06 = imp_06;
	}
	
	public Double getImp_07() {
		return imp_07;
	}
	
	public void setImp_07(Double imp_07) {
		this.imp_07 = imp_07;
	}
	
	public Double getImp_08() {
		return imp_08;
	}
	
	public void setImp_08(Double imp_08) {
		this.imp_08 = imp_08;
	}
	
	public Double getTotal_descuentos() {
		return total_descuentos;
	}
	
	public void setTotal_descuentos(Double total_descuentos) {
		this.total_descuentos = total_descuentos;
	}
	
	public Double getVenta_imp_01() {
		return venta_imp_01;
	}
	
	public void setVenta_imp_01(Double venta_imp_01) {
		this.venta_imp_01 = venta_imp_01;
	}
	
	public Double getVenta_imp_02() {
		return venta_imp_02;
	}
	
	public void setVenta_imp_02(Double venta_imp_02) {
		this.venta_imp_02 = venta_imp_02;
	}
	
	public Double getVenta_imp_03() {
		return venta_imp_03;
	}
	
	public void setVenta_imp_03(Double venta_imp_03) {
		this.venta_imp_03 = venta_imp_03;
	}
	
	public Double getVenta_imp_04() {
		return venta_imp_04;
	}
	
	public void setVenta_imp_04(Double venta_imp_04) {
		this.venta_imp_04 = venta_imp_04;
	}
	
	public Double getVenta_imp_05() {
		return venta_imp_05;
	}
	
	public void setVenta_imp_05(Double venta_imp_05) {
		this.venta_imp_05 = venta_imp_05;
	}
	
	public Double getVenta_imp_06() {
		return venta_imp_06;
	}
	
	public void setVenta_imp_06(Double venta_imp_06) {
		this.venta_imp_06 = venta_imp_06;
	}
	
	public Double getVenta_imp_07() {
		return venta_imp_07;
	}
	
	public void setVenta_imp_07(Double venta_imp_07) {
		this.venta_imp_07 = venta_imp_07;
	}
	
	public Double getVenta_imp_08() {
		return venta_imp_08;
	}
	
	public void setVenta_imp_08(Double venta_imp_08) {
		this.venta_imp_08 = venta_imp_08;
	}
	
	public Double getTotal_otros_impuestos() {
		return total_otros_impuestos;
	}
	
	public void setTotal_otros_impuestos(Double total_otros_impuestos) {
		this.total_otros_impuestos = total_otros_impuestos;
	}





}
