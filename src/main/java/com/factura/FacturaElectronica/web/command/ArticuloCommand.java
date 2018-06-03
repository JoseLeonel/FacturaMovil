package com.factura.FacturaElectronica.web.command;

import java.util.Date;

import com.factura.FacturaElectronica.modelo.Articulo;
import com.factura.FacturaElectronica.modelo.Categoria;
import com.factura.FacturaElectronica.modelo.Empresa;
import com.factura.FacturaElectronica.modelo.Marca;

public class ArticuloCommand {

	private Integer id;

	private String codigo;

	private String descripcion;
	private String serie;
	private String unidadMedida;
	private String contable;
	private Double costo;
	private Double cantidad;

	private Double iva;

	private Double precioPublico;

	private Double gananciaPrecioPublico;

	private Double precioMayorista;

	private Double gananciaPrecioMayorista;

	private Double precioEspecial;

	private Double gananciaPrecioEspecial;
	private String estado;

	private Date created_at;

	private Date updated_at;

	private Marca marca;

	private Categoria categoria;

	private Empresa empresa;

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
		this.iva = articulo.getIva()  ;
		this.serie = articulo.getSerie();
		this.unidadMedida = articulo.getUnidadMedida();
		this.contable = articulo.getContable();
		this.cantidad = 0d;
	}

	public ArticuloCommand() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
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

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getUnidadMedida() {
		return unidadMedida;
	}

	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Double getCosto() {
		return costo;
	}

	public void setCosto(Double costo) {
		this.costo = costo;
	}

	public Double getIva() {
		return iva;
	}

	public void setIva(Double iva) {
		this.iva = iva;
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

	public String getContable() {
		return contable;
	}

	public void setContable(String contable) {
		this.contable = contable;
	}

	
	public Double getCantidad() {
		return cantidad;
	}

	
	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}
	

}
