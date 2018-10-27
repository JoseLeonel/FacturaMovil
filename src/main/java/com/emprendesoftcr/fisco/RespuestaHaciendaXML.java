package com.emprendesoftcr.fisco;

public class RespuestaHaciendaXML {

	private String	clave;
	private String	fecha;
	private String	indEstado;

	private String	nombreEmisor;

	private String	tipoIdentificacionEmisor;

	private String	numeroCedulaEmisor;

	private String	nombreReceptor;

	private String	tipoIdentificacionReceptor;

	private String	numeroCedulaReceptor;

	private String	mensaje;

	private String	detalleMensaje;

	private Double	montoTotalImpuesto;

	private Double	totalFactura;

	public RespuestaHaciendaXML(String clave, String fecha, String indEstado, String nombreEmisor, String tipoIdentificacionEmisor, String numeroCedulaEmisor, String nombreReceptor, String tipoIdentificacionReceptor, String numeroCedulaReceptor, String mensaje, String detalleMensaje, Double montoTotalImpuesto, Double totalFactura) {
		super();
		this.clave = clave;
		this.fecha = fecha;
		this.indEstado = indEstado;
		this.nombreEmisor = nombreEmisor;
		this.tipoIdentificacionEmisor = tipoIdentificacionEmisor;
		this.numeroCedulaEmisor = numeroCedulaEmisor;
		this.nombreReceptor = nombreReceptor;
		this.tipoIdentificacionReceptor = tipoIdentificacionReceptor;
		this.numeroCedulaReceptor = numeroCedulaReceptor;
		this.mensaje = mensaje;
		this.detalleMensaje = detalleMensaje;
		this.montoTotalImpuesto = montoTotalImpuesto;
		this.totalFactura = totalFactura;
	}

	public RespuestaHaciendaXML() {
		super();
	}

	
	public String toString(){
    StringBuilder sb = new StringBuilder();
    sb.append("************************************");
    sb.append("\nClave: ").append(clave);
    sb.append("\nNombreEmisor: ").append(nombreEmisor);
    sb.append("\nTipoIdentificacionEmisor: ").append(tipoIdentificacionEmisor);
    sb.append("\nNumeroCedulaEmisor: ").append(numeroCedulaEmisor);
    sb.append("\nNombreReceptor: ").append(nombreReceptor);
    sb.append("\nTipoIdentificacionReceptor: ").append(tipoIdentificacionReceptor);
    sb.append("\nNumeroCedulaReceptor: ").append(numeroCedulaReceptor);
    sb.append("\nMensaje: ").append(mensaje);
    sb.append("\nDetalleMensaje: ").append(detalleMensaje);
    sb.append("\nMontoTotalImpuesto: ").append(montoTotalImpuesto);
    sb.append("\nTotalFactura: ").append(totalFactura);
    sb.append("\n************************************");
    return sb.toString();
}
	
	public String getClave() {
		return clave;
	}

	
	public void setClave(String clave) {
		this.clave = clave;
	}

	
	public String getFecha() {
		return fecha;
	}

	
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	
	public String getIndEstado() {
		return indEstado;
	}

	
	public void setIndEstado(String indEstado) {
		this.indEstado = indEstado;
	}

	
	public String getNombreEmisor() {
		return nombreEmisor;
	}

	
	public void setNombreEmisor(String nombreEmisor) {
		this.nombreEmisor = nombreEmisor;
	}

	
	public String getTipoIdentificacionEmisor() {
		return tipoIdentificacionEmisor;
	}

	
	public void setTipoIdentificacionEmisor(String tipoIdentificacionEmisor) {
		this.tipoIdentificacionEmisor = tipoIdentificacionEmisor;
	}

	
	public String getNumeroCedulaEmisor() {
		return numeroCedulaEmisor;
	}

	
	public void setNumeroCedulaEmisor(String numeroCedulaEmisor) {
		this.numeroCedulaEmisor = numeroCedulaEmisor;
	}

	
	public String getNombreReceptor() {
		return nombreReceptor;
	}

	
	public void setNombreReceptor(String nombreReceptor) {
		this.nombreReceptor = nombreReceptor;
	}

	
	public String getTipoIdentificacionReceptor() {
		return tipoIdentificacionReceptor;
	}

	
	public void setTipoIdentificacionReceptor(String tipoIdentificacionReceptor) {
		this.tipoIdentificacionReceptor = tipoIdentificacionReceptor;
	}

	
	public String getNumeroCedulaReceptor() {
		return numeroCedulaReceptor;
	}

	
	public void setNumeroCedulaReceptor(String numeroCedulaReceptor) {
		this.numeroCedulaReceptor = numeroCedulaReceptor;
	}

	
	public String getMensaje() {
		return mensaje;
	}

	
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	
	public String getDetalleMensaje() {
		return detalleMensaje;
	}

	
	public void setDetalleMensaje(String detalleMensaje) {
		this.detalleMensaje = detalleMensaje;
	}

	
	public Double getMontoTotalImpuesto() {
		return montoTotalImpuesto;
	}

	
	public void setMontoTotalImpuesto(Double montoTotalImpuesto) {
		this.montoTotalImpuesto = montoTotalImpuesto;
	}

	
	public Double getTotalFactura() {
		return totalFactura;
	}

	
	public void setTotalFactura(Double totalFactura) {
		this.totalFactura = totalFactura;
	}
	
	

}
