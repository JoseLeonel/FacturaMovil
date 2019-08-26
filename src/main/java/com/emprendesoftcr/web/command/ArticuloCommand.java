package com.emprendesoftcr.web.command;

import java.math.BigDecimal;
import java.util.Date;

import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.Utils.Utils;
import com.emprendesoftcr.modelo.Articulo;
import com.emprendesoftcr.modelo.Categoria;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Marca;
import com.emprendesoftcr.modelo.Usuario;

public class ArticuloCommand {

	private Long				id;
	private String			codigo;
	private String			descripcion;
	private String			serie;
	private String			unidadMedida;
	private String			contable;
	private Double			costo;
	private String			costoSTR;
	private Double			impuesto;
	private Double			precioPublico;
	private Double			gananciaPrecioPublico;
	private String			precioPublicoSTR;
	private Double			precioMayorista;
	private Double			gananciaPrecioMayorista;
	private Double			precioEspecial;
	private Double			gananciaPrecioEspecial;
	private String			totalCostoSTR;
	private String			totalVentaSTR;
	private String			totalImpuestoSTR;
	private String			totalGananciaSTR;
	private Double			totalCosto;
	private Double			totalVenta;
	private Double			totalImpuesto;
	private Double			totalGanancia;

	private String			estado;
	private Double			minimo;
	private Double			maximo;
	private String			tipoImpuesto;
	private String			tipoCodigo;
	private Date				created_at;
	private Date				updated_at;
	private String			created_atSTR;
	private String			updated_atSTR;

	private Marca				marca;
	private Usuario			usuario;
	private Categoria		categoria;
	private Empresa			empresa;
	private BigDecimal	cantidad;
	private String			cantidadSTR;
	private Integer			comanda;
	private Integer			prioridad;
	private Double			pesoTransporte;

	private String			consecutivoCompra;

	private String			fechaUltimaCompra;

	private String			tipoImpuesto1;

	private Double			impuesto1;

	private String			codigoTarifa;
	private String			codigoTarifa1;
	private Integer						baseImponible;

	public ArticuloCommand(Articulo articulo) {
		super();
		this.id = articulo.getId();
		this.codigo = articulo.getCodigo();
		this.descripcion = articulo.getDescripcion();
		this.estado = articulo.getEstado();
		this.created_at = articulo.getCreated_at();
		this.updated_at = articulo.getUpdated_at();
		this.empresa = articulo.getEmpresa();
		this.marca = articulo.getMarca();
		this.categoria = articulo.getCategoria();
		this.precioPublico = articulo.getPrecioPublico();
		this.gananciaPrecioPublico = articulo.getGananciaPrecioPublico();
		this.precioMayorista = articulo.getPrecioMayorista();
		this.gananciaPrecioMayorista = articulo.getGananciaPrecioMayorista();
		this.precioEspecial = articulo.getPrecioEspecial();
		this.gananciaPrecioEspecial = articulo.getGananciaPrecioEspecial();
		this.costo = articulo.getCosto();
		this.costoSTR = Utils.formateadorMiles(articulo.getCosto());
		this.impuesto = articulo.getImpuesto();
		this.serie = articulo.getSerie();
		this.unidadMedida = articulo.getUnidadMedida();
		this.contable = articulo.getContable();
		Double resultado = articulo.getCantidad();
		this.cantidad = BigDecimal.valueOf(resultado != null ? resultado : Constantes.ZEROS_DOUBLE);
		this.tipoImpuesto = articulo.getTipoImpuesto();
		this.usuario = articulo.getUsuario();
		this.tipoCodigo = articulo.getTipoCodigo();
		this.minimo = articulo.getMinimo();
		this.maximo = articulo.getMaximo();
		this.comanda = articulo.getComanda();
		this.prioridad = articulo.getPrioridad();
		this.totalCostoSTR = Utils.formateadorMiles(articulo.getTotalCosto());
		this.totalVentaSTR = Utils.formateadorMiles(articulo.getTotalPrecioPublico());
		this.cantidadSTR = Utils.formateadorMiles(articulo.getCantidad());
		this.totalGananciaSTR = Utils.formateadorMiles(articulo.getGananciaTotalPublico());
		this.precioPublicoSTR = Utils.formateadorMiles(articulo.getPrecioPublico());
		this.totalImpuestoSTR = Utils.formateadorMiles(articulo.getTotalImpuesto());
		this.totalCosto = articulo.getTotalCosto();
		this.totalVenta = articulo.getTotalPrecioPublico();
		this.totalGanancia = articulo.getGananciaTotalPublico();
		this.precioPublico = articulo.getPrecioPublico();
		this.totalImpuesto = articulo.getTotalImpuesto();
		this.impuesto1 = articulo.getImpuesto1();
		this.fechaUltimaCompra = articulo.getFechaUltimaCompraSTR();
		this.consecutivoCompra = articulo.getConsecutivoCompra();
		this.pesoTransporte = articulo.getPesoTransporte();
		this.tipoImpuesto1 = articulo.getTipoImpuesto1();
		this.created_atSTR = articulo.getCreated_atSTR();
		this.updated_atSTR = articulo.getUpdated_atSTR();
		this.codigoTarifa = articulo.getCodigoTarifa();
		this.codigoTarifa1 = articulo.getCodigoTarifa1();
		this.baseImponible = articulo.getBaseImponible();

	}
	
	

	
	public Integer getBaseImponible() {
		return baseImponible;
	}



	
	public void setBaseImponible(Integer baseImponible) {
		this.baseImponible = baseImponible;
	}



	public Integer getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(Integer prioridad) {
		this.prioridad = prioridad;
	}

	public String getCodigoTarifa() {
		return codigoTarifa;
	}

	public void setCodigoTarifa(String codigoTarifa) {
		this.codigoTarifa = codigoTarifa;
	}

	public String getCodigoTarifa1() {
		return codigoTarifa1;
	}

	public void setCodigoTarifa1(String codigoTarifa1) {
		this.codigoTarifa1 = codigoTarifa1;
	}

	public String getFechaUltimaCompra() {
		return fechaUltimaCompra;
	}

	public void setFechaUltimaCompra(String fechaUltimaCompra) {
		this.fechaUltimaCompra = fechaUltimaCompra;
	}

	public Integer getComanda() {
		return comanda;
	}

	public void setComanda(Integer comanda) {
		this.comanda = comanda;
	}

	public ArticuloCommand() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public String getUnidadMedida() {
		return unidadMedida;
	}

	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

	public String getContable() {
		return contable;
	}

	public void setContable(String contable) {
		this.contable = contable;
	}

	public Double getCosto() {
		return costo;
	}

	public void setCosto(Double costo) {
		this.costo = costo;
	}

	public Double getImpuesto() {
		return impuesto;
	}

	public void setImpuesto(Double impuesto) {
		this.impuesto = impuesto;
	}

	public Double getPrecioPublico() {
		return precioPublico;
	}

	public void setPrecioPublico(Double precioPublico) {
		this.precioPublico = precioPublico;
	}

	public Double getGananciaPrecioPublico() {
		return gananciaPrecioPublico;
	}

	public void setGananciaPrecioPublico(Double gananciaPrecioPublico) {
		this.gananciaPrecioPublico = gananciaPrecioPublico;
	}

	public Double getPrecioMayorista() {
		return precioMayorista;
	}

	public void setPrecioMayorista(Double precioMayorista) {
		this.precioMayorista = precioMayorista;
	}

	public Double getGananciaPrecioMayorista() {
		return gananciaPrecioMayorista;
	}

	public void setGananciaPrecioMayorista(Double gananciaPrecioMayorista) {
		this.gananciaPrecioMayorista = gananciaPrecioMayorista;
	}

	public Double getPrecioEspecial() {
		return precioEspecial;
	}

	public void setPrecioEspecial(Double precioEspecial) {
		this.precioEspecial = precioEspecial;
	}

	public Double getGananciaPrecioEspecial() {
		return gananciaPrecioEspecial;
	}

	public void setGananciaPrecioEspecial(Double gananciaPrecioEspecial) {
		this.gananciaPrecioEspecial = gananciaPrecioEspecial;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getTipoImpuesto() {
		return tipoImpuesto;
	}

	public void setTipoImpuesto(String tipoImpuesto) {
		this.tipoImpuesto = tipoImpuesto;
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

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public BigDecimal getCantidad() {
		return cantidad;
	}

	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}

	public String getTipoCodigo() {
		return tipoCodigo;
	}

	public void setTipoCodigo(String tipoCodigo) {
		this.tipoCodigo = tipoCodigo;
	}

	public Double getMinimo() {
		return minimo;
	}

	public void setMinimo(Double minimo) {
		this.minimo = minimo;
	}

	public Double getMaximo() {
		return maximo;
	}

	public void setMaximo(Double maximo) {
		this.maximo = maximo;
	}

	public String getCostoSTR() {
		return costoSTR;
	}

	public void setCostoSTR(String costoSTR) {
		this.costoSTR = costoSTR;
	}

	public String getCantidadSTR() {
		return cantidadSTR;
	}

	public void setCantidadSTR(String cantidadSTR) {
		this.cantidadSTR = cantidadSTR;
	}

	public String getPrecioPublicoSTR() {
		return precioPublicoSTR;
	}

	public void setPrecioPublicoSTR(String precioPublicoSTR) {
		this.precioPublicoSTR = precioPublicoSTR;
	}

	public String getTotalCostoSTR() {
		return totalCostoSTR;
	}

	public void setTotalCostoSTR(String totalCostoSTR) {
		this.totalCostoSTR = totalCostoSTR;
	}

	public String getTotalVentaSTR() {
		return totalVentaSTR;
	}

	public void setTotalVentaSTR(String totalVentaSTR) {
		this.totalVentaSTR = totalVentaSTR;
	}

	public String getTotalImpuestoSTR() {
		return totalImpuestoSTR;
	}

	public void setTotalImpuestoSTR(String totalImpuestoSTR) {
		this.totalImpuestoSTR = totalImpuestoSTR;
	}

	public String getTotalGananciaSTR() {
		return totalGananciaSTR;
	}

	public void setTotalGananciaSTR(String totalGananciaSTR) {
		this.totalGananciaSTR = totalGananciaSTR;
	}

	public Double getTotalCosto() {
		return totalCosto;
	}

	public void setTotalCosto(Double totalCosto) {
		this.totalCosto = totalCosto;
	}

	public Double getTotalVenta() {
		return totalVenta;
	}

	public void setTotalVenta(Double totalVenta) {
		this.totalVenta = totalVenta;
	}

	public Double getTotalImpuesto() {
		return totalImpuesto;
	}

	public void setTotalImpuesto(Double totalImpuesto) {
		this.totalImpuesto = totalImpuesto;
	}

	public Double getTotalGanancia() {
		return totalGanancia;
	}

	public void setTotalGanancia(Double totalGanancia) {
		this.totalGanancia = totalGanancia;
	}

	public Double getPesoTransporte() {
		return pesoTransporte;
	}

	public void setPesoTransporte(Double pesoTransporte) {
		this.pesoTransporte = pesoTransporte;
	}

	public String getConsecutivoCompra() {
		return consecutivoCompra;
	}

	public void setConsecutivoCompra(String consecutivoCompra) {
		this.consecutivoCompra = consecutivoCompra;
	}

	public String getTipoImpuesto1() {
		return tipoImpuesto1;
	}

	public void setTipoImpuesto1(String tipoImpuesto1) {
		this.tipoImpuesto1 = tipoImpuesto1;
	}

	public Double getImpuesto1() {
		return impuesto1;
	}

	public void setImpuesto1(Double impuesto1) {
		this.impuesto1 = impuesto1;
	}

	public String getCreated_atSTR() {
		return created_atSTR;
	}

	public void setCreated_atSTR(String created_atSTR) {
		this.created_atSTR = created_atSTR;
	}

	public String getUpdated_atSTR() {
		return updated_atSTR;
	}

	public void setUpdated_atSTR(String updated_atSTR) {
		this.updated_atSTR = updated_atSTR;
	}

}
