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
	private String      precioPublicoSTR;
	private Double			precioMayorista;
	private Double			gananciaPrecioMayorista;
	private Double			precioEspecial;
	private Double			gananciaPrecioEspecial;
	private String			totalCosto;
	private String			totalVenta;
	private String			totalImpuesto;
	private String			totalGanancia;
	private String			estado;
	private Double			minimo;
	private Double			maximo;
	private String			tipoImpuesto;
	private String			tipoCodigo;
	private Date				created_at;
	private Date				updated_at;
	private Marca				marca;
	private Usuario			usuario;
	private Categoria		categoria;
	private Empresa			empresa;
	private BigDecimal	cantidad;
	private String	cantidadSTR;
	private Integer			comanda;
	private Integer			prioridad;

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
		this.totalCosto = Utils.formateadorMiles(articulo.getTotalCosto());
		this.totalVenta = Utils.formateadorMiles(articulo.getTotalPrecioPublico());
		this.cantidadSTR = Utils.formateadorMiles(articulo.getCantidad());
		this.totalGanancia = Utils.formateadorMiles(articulo.getGananciaTotalPublico());
		this.precioPublicoSTR = Utils.formateadorMiles(articulo.getPrecioPublico());
	}

	public Integer getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(Integer prioridad) {
		this.prioridad = prioridad;
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

	
	public String getTotalCosto() {
		return totalCosto;
	}

	
	public void setTotalCosto(String totalCosto) {
		this.totalCosto = totalCosto;
	}

	
	public String getTotalVenta() {
		return totalVenta;
	}

	
	public void setTotalVenta(String totalVenta) {
		this.totalVenta = totalVenta;
	}

	
	public String getTotalImpuesto() {
		return totalImpuesto;
	}

	
	public void setTotalImpuesto(String totalImpuesto) {
		this.totalImpuesto = totalImpuesto;
	}

	
	public String getTotalGanancia() {
		return totalGanancia;
	}

	
	public void setTotalGanancia(String totalGanancia) {
		this.totalGanancia = totalGanancia;
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
	

}
