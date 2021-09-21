package com.emprendesoftcr.web.command;

import java.math.BigDecimal;
import java.util.Date;

import com.emprendesoftcr.modelo.Articulo;
import com.emprendesoftcr.modelo.Categoria;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Marca;
import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.modelo.sqlNativo.ArticuloMinimoNative;
import com.emprendesoftcr.utils.Constantes;
import com.emprendesoftcr.utils.Utils;

public class ArticuloCommand {

	private Long				id;
	private String			codigo;
	private String			codigoSecundario;
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

	private String			tipoImpuestoMag;

	private Double			impuestoMag;

	private String			codigoTarifa;
	private String			codigoTarifaMag;
	private Integer						baseImponible;
	private Double cantidad1;
	private String						codigoCabys;
	private Integer						cantidadPaquete;
	private Double cantidaProducto;
	private Double montoTotalLinea;

	
	

	public ArticuloCommand(ArticuloMinimoNative articuloMinimoNative) {
		super();
		this.id = articuloMinimoNative.getId();
		this.codigo = articuloMinimoNative.getCodigo();
		this.descripcion = articuloMinimoNative.getDescripcion();
				this.minimo = articuloMinimoNative.getMinimo();
				this.cantidad1 =articuloMinimoNative.getCantidad();
				this.cantidaProducto =Constantes.ZEROS_DOUBLE;
		
	}
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
		this.impuestoMag = articulo.getImpuestoMag();
		this.fechaUltimaCompra = articulo.getFechaUltimaCompraSTR();
		this.consecutivoCompra = articulo.getConsecutivoCompra();
		this.pesoTransporte = articulo.getPesoTransporte();
		this.tipoImpuestoMag = articulo.getTipoImpuestoMag();
		this.created_atSTR = articulo.getCreated_atSTR();
		this.updated_atSTR = articulo.getUpdated_atSTR();
		this.codigoTarifa = articulo.getCodigoTarifa();
		this.codigoTarifaMag = articulo.getCodigoTarifaMag();
		this.baseImponible = articulo.getBaseImponible();
		this.codigoCabys = articulo.getCodigoCabys();
		this.codigoSecundario = articulo.getCodigoSecundario();
		this.cantidadPaquete = articulo.getCantidadPaquete();
		
		this.cantidaProducto =Constantes.ZEROS_DOUBLE;

	}
	

	
	
	public String getCodigoCabys() {
		return codigoCabys;
	}
	
	public void setCodigoCabys(String codigoCabys) {
		this.codigoCabys = codigoCabys;
	}
	public Integer getBaseImponible() {
		return baseImponible;
	}



	
	
	public Double getCantidad1() {
		return cantidad1;
	}
	
	public void setCantidad1(Double cantidad1) {
		this.cantidad1 = cantidad1;
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

	public String getCodigoTarifaMag() {
		return codigoTarifaMag;
	}

	public void setCodigoTarifaMag(String codigoTarifaMag) {
		this.codigoTarifaMag = codigoTarifaMag;
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
	public String getPrecioPublicoformato() {
		return  this.precioPublico != null ?this.precioPublico > 1d ? Utils.formateadorMiles(this.precioPublico) : this.precioPublico+"":"0";
		
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

	public String getTipoImpuestoMag() {
		return tipoImpuestoMag;
	}

	public void setTipoImpuestoMag(String tipoImpuestoMag) {
		this.tipoImpuestoMag = tipoImpuestoMag;
	}

	public Double getImpuestoMag() {
		return impuestoMag;
	}

	public void setImpuestoMag(Double impuestoMag) {
		this.impuestoMag = impuestoMag;
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
	
	public String getCodigoSecundario() {
		return codigoSecundario;
	}
	
	public void setCodigoSecundario(String codigoSecundario) {
		this.codigoSecundario = codigoSecundario;
	}
	
	public Integer getCantidadPaquete() {
		return cantidadPaquete;
	}
	
	public void setCantidadPaquete(Integer cantidadPaquete) {
		this.cantidadPaquete = cantidadPaquete;
	}
	
	
	
	
	public Double getCantidaProducto() {
		return cantidaProducto == null?Constantes.ZEROS_DOUBLE:cantidaProducto;
	}
	
	public void setCantidaProducto(Double cantidaProducto) {
		this.cantidaProducto = cantidaProducto;
	}
	
	public Double getMontoTotalLinea() {
		return montoTotalLinea;
	}
	
	public void setMontoTotalLinea(Double montoTotalLinea) {
		this.montoTotalLinea = montoTotalLinea;
	}
	
	

}
