package com.emprendesoftcr.fisco;

/**
 * Recepcion. Para la preparacion del XML hacia hacienda
 * @author jose.
 * @since 16 jul. 2018
 */
public class Recepcion {

	private String						clave;
	private String						fecha;
	private EmisorHacienda		emisor;
	private ReceptorHacienda	receptor;
	private String						comprobanteXml;

	
	

	public Recepcion(String clave, String fecha, EmisorHacienda emisor, ReceptorHacienda receptor, String comprobanteXml) {
		super();
		this.clave = clave;
		this.fecha = fecha;
		this.emisor = emisor;
		this.receptor = receptor;
		this.comprobanteXml = comprobanteXml;
	}

	public Recepcion() {
		super();
	}
	
	public String toString(){
    StringBuilder sb = new StringBuilder();
    sb.append("************************************");
    sb.append("\nclave: ").append(clave);
    sb.append("\nfecha: ").append(fecha);
    sb.append("\nemisor: ").append(emisor);
    sb.append("\nreceptor: ").append(receptor);
    sb.append("\ncomprobanteXML: ").append(comprobanteXml);
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

	public EmisorHacienda getEmisor() {
		return emisor;
	}

	public void setEmisor(EmisorHacienda emisor) {
		this.emisor = emisor;
	}

	public ReceptorHacienda getReceptor() {
		return receptor;
	}

	public void setReceptor(ReceptorHacienda receptor) {
		this.receptor = receptor;
	}

	
	public String getComprobanteXml() {
		return comprobanteXml;
	}

	
	public void setComprobanteXml(String comprobanteXml) {
		this.comprobanteXml = comprobanteXml;
	}

	

	

}
