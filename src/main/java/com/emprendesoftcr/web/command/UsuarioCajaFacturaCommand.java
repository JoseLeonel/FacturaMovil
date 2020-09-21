package com.emprendesoftcr.web.command;

import com.emprendesoftcr.modelo.UsuarioCajaFactura;
import com.emprendesoftcr.utils.Constantes;

public class UsuarioCajaFacturaCommand {

	private Long		id;
	private String	fechaEmision;
	private String	nombreUsuario;
	private String	numeroFactura;
	private String	nombreCliente;
	private String	total;
	private String	totalEfectivo;
	private String	totalTarjeta;
	private String	totalBanco;
	private String	tipoDoc;

	public UsuarioCajaFacturaCommand(UsuarioCajaFactura usuarioCajaFactura) {
		super();
		this.nombreUsuario = usuarioCajaFactura.getFactura().getUsuarioCreacion().getNombreUsuario();
		this.numeroFactura = usuarioCajaFactura.getFactura().getNumeroConsecutivo();
		this.total = usuarioCajaFactura.getFactura().getTotalComprobanteSTR();
		this.totalEfectivo = usuarioCajaFactura.getFactura().getTotalEfectivoSTR();
		this.totalBanco = usuarioCajaFactura.getFactura().getTotalBancoSTR();
		this.nombreCliente = usuarioCajaFactura.getFactura().getCliente().getCedula().equals(Constantes.CEDULA_CLIENTE_FRECUENTE )? usuarioCajaFactura.getFactura().getNombreCliente() : usuarioCajaFactura.getFactura().getCliente().getNombreCompleto();
		this.fechaEmision = usuarioCajaFactura.getFactura().getFechaEmisionSTR();
		this.tipoDoc = usuarioCajaFactura.getFactura().getTipoDoc();
		this.id =  usuarioCajaFactura.getFactura().getId();
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getNumeroFactura() {
		return numeroFactura;
	}

	public void setNumeroFactura(String numeroFactura) {
		this.numeroFactura = numeroFactura;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getTotalEfectivo() {
		return totalEfectivo;
	}

	public void setTotalEfectivo(String totalEfectivo) {
		this.totalEfectivo = totalEfectivo;
	}

	public String getTotalTarjeta() {
		return totalTarjeta;
	}

	public void setTotalTarjeta(String totalTarjeta) {
		this.totalTarjeta = totalTarjeta;
	}

	public String getTotalBanco() {
		return totalBanco;
	}

	public void setTotalBanco(String totalBanco) {
		this.totalBanco = totalBanco;
	}

	public String getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(String fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public String getTipoDoc() {
		return tipoDoc;
	}

	public void setTipoDoc(String tipoDoc) {
		this.tipoDoc = tipoDoc;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
