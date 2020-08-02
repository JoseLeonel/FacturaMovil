package com.emprendesoftcr.pdf;

/**
 * @author Wesker
 */
public class DetalleFacturaElectronica {

	private int			_linea;
	private String	_codigo;
	private String	_unidad;
	private double	_cantidad;
	private String	_descripcion;
	private double	_precioU;
	private double	_monto;
	private double	_descuento;
	private double	_subtotal;
	private double	_tarifaIva;
	private double	_impuesto1;
	private double	_impuesto;
	private double	_exento;
	private double	_total;
	private String	tipoDocumentoExoneracion;

	private String	numeroDocumentoExoneracion;

	private String	nombreInstitucionExoneracion;

	private String	fechaEmisionExoneracion;

	private Integer	porcentajeExoneracion;

	private Double	montoExoneracion;

	private String	tipoImpuesto;

	public String getUnidad() {
		return _unidad;
	}

	public void setUnidad(String _unidad) {
		this._unidad = _unidad;
	}

	public double getMonto() {
		return _monto;
	}

	public void setMonto(double _monto) {
		this._monto = _monto;
	}

	public double getSubtotal() {
		return _subtotal;
	}

	public void setSubtotal(double _subtotal) {
		this._subtotal = _subtotal;
	}

	public double getTarifaIva() {
		return _tarifaIva;
	}

	public void setTarifaIva(double _tarifaIva) {
		this._tarifaIva = _tarifaIva;
	}

	public double getExento() {
		return _exento;
	}

	public void setExento(double _exento) {
		this._exento = _exento;
	}

	public int getLinea() {
		return _linea;
	}

	public void setLinea(int _linea) {
		this._linea = _linea;
	}

	public String getCodigo() {
		return _codigo;
	}

	public void setCodigo(String _codigo) {
		this._codigo = _codigo;
	}

	public String getDescripcion() {
		return _descripcion;
	}

	public void setDescripcion(String _descripcion) {
		this._descripcion = _descripcion;
	}

	public double getPrecioU() {
		return _precioU;
	}

	public void setPrecioU(double _precioU) {
		this._precioU = _precioU;
	}

	public double getCantidad() {
		return _cantidad;
	}

	public void setCantidad(double _cantidad) {
		this._cantidad = _cantidad;
	}

	public double getDescuento() {
		return _descuento;
	}

	public void setDescuento(double _descuento) {
		this._descuento = _descuento;
	}

	public double getImpuesto() {
		return _impuesto;
	}

	public void setImpuesto(double _impuesto) {
		this._impuesto = _impuesto;
	}

	public double getTotal() {
		return _total;
	}

	public void setTotal(double _total) {
		this._total = _total;
	}

	public double get_impuesto1() {
		return _impuesto1;
	}

	public void set_impuesto1(double _impuesto1) {
		this._impuesto1 = _impuesto1;
	}

	public String getTipoDocumentoExoneracion() {
		return tipoDocumentoExoneracion;
	}

	public void setTipoDocumentoExoneracion(String tipoDocumentoExoneracion) {
		this.tipoDocumentoExoneracion = tipoDocumentoExoneracion;
	}

	public String getNumeroDocumentoExoneracion() {
		return numeroDocumentoExoneracion;
	}

	public void setNumeroDocumentoExoneracion(String numeroDocumentoExoneracion) {
		this.numeroDocumentoExoneracion = numeroDocumentoExoneracion;
	}

	public String getNombreInstitucionExoneracion() {
		return nombreInstitucionExoneracion;
	}

	public void setNombreInstitucionExoneracion(String nombreInstitucionExoneracion) {
		this.nombreInstitucionExoneracion = nombreInstitucionExoneracion;
	}

	public String getFechaEmisionExoneracion() {
		return fechaEmisionExoneracion;
	}

	public void setFechaEmisionExoneracion(String fechaEmisionExoneracion) {
		this.fechaEmisionExoneracion = fechaEmisionExoneracion;
	}

	public Integer getPorcentajeExoneracion() {
		return porcentajeExoneracion;
	}

	public void setPorcentajeExoneracion(Integer porcentajeExoneracion) {
		this.porcentajeExoneracion = porcentajeExoneracion;
	}

	public Double getMontoExoneracion() {
		return montoExoneracion;
	}

	public void setMontoExoneracion(Double montoExoneracion) {
		this.montoExoneracion = montoExoneracion;
	}

	public String getTipoImpuesto() {
		return tipoImpuesto;
	}

	public void setTipoImpuesto(String tipoImpuesto) {
		this.tipoImpuesto = tipoImpuesto;
	}

	public static DetalleFacturaElectronica Foo(int linea) {
		DetalleFacturaElectronica f = new DetalleFacturaElectronica();
		f.setLinea(linea);
		f.setCodigo("00" + linea);
		f.setDescripcion("Objeto de prueba " + linea);
		f.setPrecioU(1.386);
		f.setCantidad(9 + linea);
		f.setDescuento(99 - linea);
		f.setImpuesto(9 + linea);
		f.setTotal((f.getCantidad() * f.getPrecioU()) + f.getImpuesto() - f.getDescuento());
		return f;
	}

}
