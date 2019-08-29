package com.emprendesoftcr.modelo.sqlNativo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@BaseNativeQuery(name = "listarimpServicio", query = "SELECT facturas.total_otros_cargos, facturas.total_servicio, empresas.nofactura_elec,facturas.tipo_doc,facturas.estado,facturas.consecutivo_proforma, facturas.condicion_venta, facturas.act_comercial,facturas.fecha_emision,clientes.cedula,facturas.total_impuesto,facturas.total_descuentos,facturas.id,facturas.tipo_cambio,facturas.numero_consecutivo,facturas.nombre_factura,clientes.nombre_completo,facturas.total_descuentos,facturas.total_impuesto,facturas.codigo_moneda,facturas.total_comprobante ,facturas.created_at,usuarios.nombre_usuario from facturas" 
+ " inner join clientes on clientes.id = facturas.cliente_id " 
+ " inner join empresas on empresas.id = facturas.empresa_id " 
+ " inner join usuarios on usuarios.id = facturas.usuario_id " + " where facturas.empresa_id = :ID_EMPRESA and facturas.created_at >=  :fechaInicial and  facturas.created_at <=  :fechaFinal and facturas.cliente_id  and facturas.tipo_doc  and facturas.act_comercial and facturas.estado and facturas.usuario_id and  facturas.ref_codigo !='01' order by facturas.created_at desc ")
@Entity
public class ListarFacturasImpuestoServicioNativa implements Serializable {

	


	/**
	 * Comentario para <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1019942655002912158L;

	@Id
	@Column(name = "id")
	private Long							id;

	@Column(name = "tipo_doc")
	private String						tipoDoc;

	@Column(name = "codigo_moneda")
	private String						codigoMoneda;

	@Column(name = "estado")
	private Integer						estado;

	@Column(name = "act_comercial", length = 6)
	private String						codigoActividad;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "created_at")
	private Date							created_at;

	@Column(name = "condicion_venta")
	private String						condicionVenta;

	@Column(name = "numero_consecutivo")
	private String						numeroConsecutivo;

	@Column(name = "consecutivo_proforma")
	private String						consecutivoProforma;

	@Column(name = "nombre_factura")
	private String						nombreFactura;

	@Column(name = "nombre_completo")
	private String						NombreCompleto;

	@Column(name = "cedula")
	private String						cedula;

	@Column(name = "nombre_usuario")
	private String						nombreUsuario;

	@Column(name = "tipo_cambio", precision = 18, scale = 5)
	private Double						tipoCambio;
	@Column(name = "total_comprobante", precision = 18, scale = 5)
	private Double						totalComprobante;

	@Column(name = "total_descuentos", precision = 18, scale = 5)
	private Double						totalDescuentos;

	@Column(name = "total_impuesto", precision = 18, scale = 5)
	private Double						totalImpuesto;
	
//Impuesto del servicio cuando aplica
	@Column(name = "total_servicio", precision = 18, scale = 5)
	private Double						totalImpuestoServicio;

	@Column(name = "total_otros_cargos", columnDefinition = "Decimal(10,5) default '0.00'")
	private Double						totalOtrosCargos;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "fecha_emision")
	private Date							fechaEmision;
	
	@Column(name = "nofactura_elec")
	private Integer						noFacturaElectronica;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public Double getTotalImpuesto() {
		return totalImpuesto;
	}

	public void setTotalImpuesto(Double totalImpuesto) {
		this.totalImpuesto = totalImpuesto;
	}

	public Double getTotalDescuentos() {
		return totalDescuentos;
	}

	public Date getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public void setTotalDescuentos(Double totalDescuentos) {
		this.totalDescuentos = totalDescuentos;
	}

	public String getTipoDoc() {
		return tipoDoc;
	}

	public void setTipoDoc(String tipoDoc) {
		this.tipoDoc = tipoDoc;
	}

	public String getCodigoMoneda() {
		return codigoMoneda;
	}

	public void setCodigoMoneda(String codigoMoneda) {
		this.codigoMoneda = codigoMoneda;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public String getCodigoActividad() {
		return codigoActividad;
	}

	public void setCodigoActividad(String codigoActividad) {
		this.codigoActividad = codigoActividad;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public String getCondicionVenta() {
		return condicionVenta;
	}

	public void setCondicionVenta(String condicionVenta) {
		this.condicionVenta = condicionVenta;
	}

	public String getNumeroConsecutivo() {
		return numeroConsecutivo;
	}

	public void setNumeroConsecutivo(String numeroConsecutivo) {
		this.numeroConsecutivo = numeroConsecutivo;
	}

	public String getConsecutivoProforma() {
		return consecutivoProforma;
	}

	public void setConsecutivoProforma(String consecutivoProforma) {
		this.consecutivoProforma = consecutivoProforma;
	}

	public String getNombreFactura() {
		return nombreFactura;
	}

	public void setNombreFactura(String nombreFactura) {
		this.nombreFactura = nombreFactura;
	}

	public String getNombreCompleto() {
		return NombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		NombreCompleto = nombreCompleto;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public Double getTipoCambio() {
		return tipoCambio;
	}

	public void setTipoCambio(Double tipoCambio) {
		this.tipoCambio = tipoCambio;
	}

	public Double getTotalComprobante() {
		return totalComprobante;
	}

	public void setTotalComprobante(Double totalComprobante) {
		this.totalComprobante = totalComprobante;
	}

	
	
	
	public Integer getNoFacturaElectronica() {
		return noFacturaElectronica;
	}

	
	public void setNoFacturaElectronica(Integer noFacturaElectronica) {
		this.noFacturaElectronica = noFacturaElectronica;
	}
	
	
	

	
	public Double getTotalImpuestoServicio() {
		return totalImpuestoServicio;
	}

	
	public void setTotalImpuestoServicio(Double totalImpuestoServicio) {
		this.totalImpuestoServicio = totalImpuestoServicio;
	}

	
	public Double getTotalOtrosCargos() {
		return totalOtrosCargos;
	}

	
	public void setTotalOtrosCargos(Double totalOtrosCargos) {
		this.totalOtrosCargos = totalOtrosCargos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((NombreCompleto == null) ? 0 : NombreCompleto.hashCode());
		result = prime * result + ((cedula == null) ? 0 : cedula.hashCode());
		result = prime * result + ((codigoActividad == null) ? 0 : codigoActividad.hashCode());
		result = prime * result + ((codigoMoneda == null) ? 0 : codigoMoneda.hashCode());
		result = prime * result + ((condicionVenta == null) ? 0 : condicionVenta.hashCode());
		result = prime * result + ((consecutivoProforma == null) ? 0 : consecutivoProforma.hashCode());
		result = prime * result + ((created_at == null) ? 0 : created_at.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + ((fechaEmision == null) ? 0 : fechaEmision.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((noFacturaElectronica == null) ? 0 : noFacturaElectronica.hashCode());
		result = prime * result + ((nombreFactura == null) ? 0 : nombreFactura.hashCode());
		result = prime * result + ((nombreUsuario == null) ? 0 : nombreUsuario.hashCode());
		result = prime * result + ((numeroConsecutivo == null) ? 0 : numeroConsecutivo.hashCode());
		result = prime * result + ((tipoCambio == null) ? 0 : tipoCambio.hashCode());
		result = prime * result + ((tipoDoc == null) ? 0 : tipoDoc.hashCode());
		result = prime * result + ((totalComprobante == null) ? 0 : totalComprobante.hashCode());
		result = prime * result + ((totalDescuentos == null) ? 0 : totalDescuentos.hashCode());
		result = prime * result + ((totalImpuesto == null) ? 0 : totalImpuesto.hashCode());
		result = prime * result + ((totalImpuestoServicio == null) ? 0 : totalImpuestoServicio.hashCode());
		result = prime * result + ((totalOtrosCargos == null) ? 0 : totalOtrosCargos.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ListarFacturasImpuestoServicioNativa other = (ListarFacturasImpuestoServicioNativa) obj;
		if (NombreCompleto == null) {
			if (other.NombreCompleto != null)
				return false;
		} else if (!NombreCompleto.equals(other.NombreCompleto))
			return false;
		if (cedula == null) {
			if (other.cedula != null)
				return false;
		} else if (!cedula.equals(other.cedula))
			return false;
		if (codigoActividad == null) {
			if (other.codigoActividad != null)
				return false;
		} else if (!codigoActividad.equals(other.codigoActividad))
			return false;
		if (codigoMoneda == null) {
			if (other.codigoMoneda != null)
				return false;
		} else if (!codigoMoneda.equals(other.codigoMoneda))
			return false;
		if (condicionVenta == null) {
			if (other.condicionVenta != null)
				return false;
		} else if (!condicionVenta.equals(other.condicionVenta))
			return false;
		if (consecutivoProforma == null) {
			if (other.consecutivoProforma != null)
				return false;
		} else if (!consecutivoProforma.equals(other.consecutivoProforma))
			return false;
		if (created_at == null) {
			if (other.created_at != null)
				return false;
		} else if (!created_at.equals(other.created_at))
			return false;
		if (estado == null) {
			if (other.estado != null)
				return false;
		} else if (!estado.equals(other.estado))
			return false;
		if (fechaEmision == null) {
			if (other.fechaEmision != null)
				return false;
		} else if (!fechaEmision.equals(other.fechaEmision))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (noFacturaElectronica == null) {
			if (other.noFacturaElectronica != null)
				return false;
		} else if (!noFacturaElectronica.equals(other.noFacturaElectronica))
			return false;
		if (nombreFactura == null) {
			if (other.nombreFactura != null)
				return false;
		} else if (!nombreFactura.equals(other.nombreFactura))
			return false;
		if (nombreUsuario == null) {
			if (other.nombreUsuario != null)
				return false;
		} else if (!nombreUsuario.equals(other.nombreUsuario))
			return false;
		if (numeroConsecutivo == null) {
			if (other.numeroConsecutivo != null)
				return false;
		} else if (!numeroConsecutivo.equals(other.numeroConsecutivo))
			return false;
		if (tipoCambio == null) {
			if (other.tipoCambio != null)
				return false;
		} else if (!tipoCambio.equals(other.tipoCambio))
			return false;
		if (tipoDoc == null) {
			if (other.tipoDoc != null)
				return false;
		} else if (!tipoDoc.equals(other.tipoDoc))
			return false;
		if (totalComprobante == null) {
			if (other.totalComprobante != null)
				return false;
		} else if (!totalComprobante.equals(other.totalComprobante))
			return false;
		if (totalDescuentos == null) {
			if (other.totalDescuentos != null)
				return false;
		} else if (!totalDescuentos.equals(other.totalDescuentos))
			return false;
		if (totalImpuesto == null) {
			if (other.totalImpuesto != null)
				return false;
		} else if (!totalImpuesto.equals(other.totalImpuesto))
			return false;
		if (totalImpuestoServicio == null) {
			if (other.totalImpuestoServicio != null)
				return false;
		} else if (!totalImpuestoServicio.equals(other.totalImpuestoServicio))
			return false;
		if (totalOtrosCargos == null) {
			if (other.totalOtrosCargos != null)
				return false;
		} else if (!totalOtrosCargos.equals(other.totalOtrosCargos))
			return false;
		return true;
	}

	
		
	

}
