package com.emprendesoftcr.web.command;

import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.Utils.Utils;
import com.emprendesoftcr.fisco.MapEnums;
import com.google.gson.Gson;

public class VentasByCategoriasCommand {
	
	private Long id;
	private Integer estado;
	private String nom_cat;
	private String cod_tarifa;
  private Double impuesto;
  private String tipo_impuesto;
  private Double cantidad;
  private Double total_costo;
  private Double total_neto;
  private Double total_desc;
  private Double mont_total;
  private Double sub_tota;
  private Double total_linea;
  private Double mont_exon;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	
	public Integer getEstado() {
		return estado;
	}
	public String getEstadoSTR() {
		return estado != null ? MapEnums.ENUM_ESTADO_FACTURA.get(estado.toString()) : estado.toString();
	}

	
	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public String getNom_cat() {
		return nom_cat;
	}
	
	public String getDescrpcion() {
		String resultado = nom_cat == null?Constantes.EMPTY:nom_cat;
		
		return resultado.length() >24?resultado.substring(0,24)+ "...": resultado;
	}
	
	public void setNom_cat(String nom_cat) {
		this.nom_cat = nom_cat;
	}
	
	public String getCod_tarifa() {
		return cod_tarifa;
	}
	
	public void setCod_tarifa(String cod_tarifa) {
		this.cod_tarifa = cod_tarifa;
	}
	
	public String getCodigoTarifaSTR() {
		return cod_tarifa != null ? MapEnums.ENUM_TARIFAS_IMPUESTOS.get(cod_tarifa) : cod_tarifa;
	}
	
	public Double getImpuesto() {
		return impuesto;
	}
	
	public void setImpuesto(Double impuesto) {
		this.impuesto = impuesto;
	}
	
	public String getTipo_impuesto() {
		return tipo_impuesto;
	}
	public String getTipoImpuestoSTR() {
		return tipo_impuesto != null ? MapEnums.ENUM_TIPOS_IMPUESTOS.get(tipo_impuesto) : tipo_impuesto;
	}
	
	public void setTipo_impuesto(String tipo_impuesto) {
		this.tipo_impuesto = tipo_impuesto;
	}
	
	public Double getCantidad() {
		return cantidad;
	}
	
	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}
	
	public Double getTotal_costo() {
		return total_costo;
	}
	public String getTotalCostoSTR() {
		return Utils.formateadorMiles(this.total_costo);
	}
	public void setTotal_costo(Double total_costo) {
		this.total_costo = total_costo;
	}
	
	public Double getTotal_neto() {
		return total_neto;
	}
	public String getTotalNetoSTR() {
		return Utils.formateadorMiles(this.total_neto);
	}
	
	public void setTotal_neto(Double total_neto) {
		this.total_neto = total_neto;
	}
	
	public Double getTotal_desc() {
		return total_desc;
	}
	public String getTotalDesccuentoSTR() {
		return Utils.formateadorMiles(this.total_desc);
	}
	public void setTotal_desc(Double total_desc) {
		this.total_desc = total_desc;
	}
	
	public Double getMont_total() {
		return mont_total;
	}
	
	public String getMontoTotalSTR() {
		return Utils.formateadorMiles(this.mont_total);
	}
	public void setMont_total(Double mont_total) {
		this.mont_total = mont_total;
	}
	
	public Double getSub_tota() {
		return sub_tota;
	}
	public String getSubTotalSTR() {
		return Utils.formateadorMiles(this.sub_tota);
	}
	public void setSub_tota(Double sub_tota) {
		this.sub_tota = sub_tota;
	}
	
	public Double getTotal_linea() {
		return total_linea;
	}
	
	public String getTotalLineaSTR() {
		return Utils.formateadorMiles(this.total_linea);
	}
	
	public void setTotal_linea(Double total_linea) {
		this.total_linea = total_linea;
	}
	
	public Double getMont_exon() {
		return mont_exon;
	}
	
	public void setMont_exon(Double mont_exon) {
		this.mont_exon = mont_exon;
	}
	public String getMontoExoneracionSTR() {
		return Utils.formateadorMiles(this.mont_exon);
	}
	@Override
	public String toString() {
		 return new Gson().toJson(this);
	}
  
  
  
}
