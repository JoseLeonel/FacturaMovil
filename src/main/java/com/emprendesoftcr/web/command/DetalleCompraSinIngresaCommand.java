package com.emprendesoftcr.web.command;


public class DetalleCompraSinIngresaCommand {
  private Long id;
  private Long idCompra;
  private String descripcion;
  private Double cantidad;
  
	
	private Double impuesto;
  private Integer estado;
  private Integer numero_linea;
  private String cod_invet;
  private String cod_proveedor;
  private Double costo_prove;
  private Double costo_inv;
  private Double ganancia;
  private Double precio_publico;
  private String cod_tarifa;
  private Double imp_art;
  
  public Double getCantidad() {
		return cantidad;
	}

	
	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	
	public String getDescripcion() {
		return descripcion;
	}

	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Double getImpuesto() {
		return impuesto;
	}
	
	public void setImpuesto(Double impuesto) {
		this.impuesto = impuesto;
	}
	
	public Integer getEstado() {
		return estado;
	}
	
	public void setEstado(Integer estado) {
		this.estado = estado;
	}
	
	public String getCod_invet() {
		return cod_invet;
	}
	
	public void setCod_invet(String cod_invet) {
		this.cod_invet = cod_invet;
	}
	
	public String getCod_proveedor() {
		return cod_proveedor;
	}
	
	public void setCod_proveedor(String cod_proveedor) {
		this.cod_proveedor = cod_proveedor;
	}
	
	public Double getCosto_prove() {
		return costo_prove;
	}
	
	public void setCosto_prove(Double costo_prove) {
		this.costo_prove = costo_prove;
	}
	
	public Double getCosto_inv() {
		return costo_inv;
	}
	
	public void setCosto_inv(Double costo_inv) {
		this.costo_inv = costo_inv;
	}
	
	public Double getGanancia() {
		return ganancia;
	}
	
	public void setGanancia(Double ganancia) {
		this.ganancia = ganancia;
	}
	
	public Double getPrecio_publico() {
		return precio_publico;
	}
	
	public void setPrecio_publico(Double precio_publico) {
		this.precio_publico = precio_publico;
	}
	

	
	
	public String getCod_tarifa() {
		return cod_tarifa;
	}


	
	public void setCod_tarifa(String cod_tarifa) {
		this.cod_tarifa = cod_tarifa;
	}


	public Double getImp_art() {
		return imp_art;
	}
	
	public void setImp_art(Double imp_art) {
		this.imp_art = imp_art;
	}


	
	public Long getIdCompra() {
		return idCompra;
	}


	
	public void setIdCompra(Long idCompra) {
		this.idCompra = idCompra;
	}


	
	public Integer getNumero_linea() {
		return numero_linea;
	}


	
	public void setNumero_linea(Integer numero_linea) {
		this.numero_linea = numero_linea;
	}
  
  
	
}
