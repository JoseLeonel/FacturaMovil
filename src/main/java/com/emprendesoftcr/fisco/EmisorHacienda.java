package com.emprendesoftcr.fisco;

/**
 * @author Luis Matamoros luis.matamoros@efika.solutions
 * @version 1.0
 */
public class EmisorHacienda {
	public static final String CEDULA = "000000000000";
	private String	tipoIdentificacion;
	private String	numeroIdentificacion;

	public EmisorHacienda(String tipoIdentificacion, String numeroIdentificacion) {
		super();
		this.tipoIdentificacion = tipoIdentificacion;
		this.numeroIdentificacion =  FacturaElectronicaUtils.replazarConZeros(numeroIdentificacion,CEDULA);
	}

	public EmisorHacienda() {
		super();
	}

	public String getTipoIdentificacion() {
		return tipoIdentificacion;
	}

	public void setTipoIdentificacion(String tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}

	public String getNumeroIdentificacion() {
		return numeroIdentificacion;
	}

	public void setNumeroIdentificacion(String numeroIdentificacion) {
		this.numeroIdentificacion = numeroIdentificacion;
	}

}
