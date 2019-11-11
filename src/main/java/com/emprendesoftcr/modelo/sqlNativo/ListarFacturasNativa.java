package com.emprendesoftcr.modelo.sqlNativo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.Utils.Utils;
import com.emprendesoftcr.fisco.MapEnums;

@BaseNativeQuery(name = "listar_facturas", query = "SELECT empresas.nofactura_elec,"
		+ " fac.tipo_doc,"
		+ " fac.estado,"
		+ " fac.consecutivo_proforma,"
		+ " fac.condicion_venta, "
		+ " fac.act_comercial,"
		+ " fac.fecha_emision,"
		+ " clientes.cedula,"
		+ " clientes.identificacion_Extranjero,"
		+ " if(fac.tipo_doc = '03' or fac.tipo_doc = '86' ,fac.total_impuesto * -1,fac.total_impuesto) AS total_impuesto, \n" 
		+ " if(fac.tipo_doc = '03' or fac.tipo_doc = '86' ,fac.total_descuentos * -1,fac.total_descuentos) AS total_descuentos, \n"
		+ " if(fac.tipo_doc = '03' or fac.tipo_doc = '86' ,fac.total_serv_exentos * -1,fac.total_serv_exentos) AS total_serv_exentos, \n"
		+ " if(fac.tipo_doc = '03' or fac.tipo_doc = '86' ,fac.total_mercancias_gravadas * -1,fac.total_mercancias_gravadas) AS total_mercancias_gravadas, \n"
		+ " if(fac.tipo_doc = '03' or fac.tipo_doc = '86' ,fac.total_mercancias_exentas * -1,fac.total_mercancias_exentas) AS total_mercancias_exentas, \n"
		+ " if(fac.tipo_doc = '03' or fac.tipo_doc = '86' ,fac.total_gravado * -1,fac.total_gravado) AS total_gravado, \n"
		+ " if(fac.tipo_doc = '03' or fac.tipo_doc = '86' ,fac.total_exento * -1,fac.total_exento) AS total_exento, \n"
		+ " if(fac.tipo_doc = '03' or fac.tipo_doc = '86' ,fac.total_exento * -1,fac.total_venta) AS total_venta, \n"
		+ " if(fac.tipo_doc = '03' or fac.tipo_doc = '86' ,fac.total_venta_neta * -1,fac.total_venta_neta) AS total_venta_neta, \n"
		+ " if(fac.tipo_doc = '03' or fac.tipo_doc = '86' ,fac.total_servicio * -1,fac.total_servicio) AS total_servicio, \n"
		+ " if(fac.tipo_doc = '03' or fac.tipo_doc = '86' ,fac.total_serv_gravados * -1,fac.total_serv_gravados) AS total_serv_gravados, \n"
		+ " if(fac.tipo_doc = '03' or fac.tipo_doc = '86' ,fac.total_otros_cargos * -1,fac.total_otros_cargos) AS total_otros_cargos, \n"
		+ " if(fac.tipo_doc = '03' or fac.tipo_doc = '86' ,fac.total_efectivo * -1,fac.total_efectivo) AS total_efectivo, \n"
		+ " if(fac.tipo_doc = '03' or fac.tipo_doc = '86' ,fac.total_tarjeta * -1,fac.total_tarjeta) AS total_tarjeta, \n"
		+ " if(fac.tipo_doc = '03' or fac.tipo_doc = '86' ,fac.total_banco * -1,fac.total_banco) AS total_banco, \n"
		+ " if(fac.tipo_doc = '03' or fac.tipo_doc = '86' ,fac.total_credito * -1,fac.total_credito) AS total_credito, \n"
		+ " if(fac.tipo_doc = '03' or fac.tipo_doc = '86' ,fac.monto_cambio * -1,fac.monto_cambio) AS monto_cambio, \n"
		+ " if(fac.tipo_doc = '03' or fac.tipo_doc = '86' ,fac.total_comprobante * -1,fac.total_comprobante) AS total_comprobante, \n"
		+ " fac.id,"
		+ " fac.tipo_cambio,"
		+ "(select facturas.numero_consecutivo from facturas where facturas.clave = fac.ref_numero and facturas.empresa_id = fac.empresa_id ) ref_numero , \n" 
		+ " fac.numero_consecutivo,"
		+ " fac.nombre_factura,"
		+ " clientes.nombre_completo,"
		+ " fac.codigo_moneda,"
		+ " fac.nota,"
		+ " fac.created_at,"
		+ " usuarios.nombre_usuario from facturas fac" 
+ " inner join clientes on clientes.id = fac.cliente_id " 
+ " inner join empresas on empresas.id = fac.empresa_id " 
+ " inner join usuarios on usuarios.id = fac.usuario_id " 
+ " where fac.empresa_id = :ID_EMPRESA "
+ " and fac.created_at >=  :fechaInicial "
+ " and  fac.created_at <=  :fechaFinal "
+ " and fac.cliente_id  "
+ " and fac.tipo_doc  "
+ " and fac.act_comercial "
+ " and fac.estado "
+ " and fac.usuario_id ")
@Entity
public class ListarFacturasNativa implements Serializable {

	private static final long	serialVersionUID	= 4176688239523741663L;

	@Id
	@Column(name = "id")
	private Long							id;

	@Column(name = "tipo_doc")
	private String						tipoDoc;

	@Column(name = "codigo_moneda")
	private String						codigoMoneda;

	@Column(name = "estado")
	private Integer						estado;

	@Column(name = "act_comercial")
	private String						codigoActividad;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "created_at")
	private Date							created_at;

	@Column(name = "condicion_venta")
	private String						condicionVenta;

	@Column(name = "numero_consecutivo")
	private String						numeroConsecutivo;
	
	@Column(name = "ref_numero")
	private String						referenciaNumero;


	@Column(name = "consecutivo_proforma")
	private String						consecutivoProforma;

	@Column(name = "nombre_factura")
	private String						nombreFactura;

	@Column(name = "nombre_completo")
	private String						NombreCompleto;

	@Column(name = "identificacion_Extranjero")
	private String						identificacionExtranjero;
	
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
	
	@Column(name = "total_serv_exentos", precision = 18, scale = 5)
	private Double						totalServExentos;

	@Column(name = "total_mercancias_gravadas", precision = 18, scale = 5)
	private Double						totalMercanciasGravadas;

	@Column(name = "total_mercancias_exentas", precision = 18, scale = 5)
	private Double						totalMercanciasExentas;

	@Column(name = "total_gravado", precision = 18, scale = 5)
	private Double						totalGravado;

	@Column(name = "total_exento", precision = 18, scale = 5)
	private Double						totalExento;

	@Column(name = "total_venta", precision = 18, scale = 5)
	private Double						totalVenta;

	
	@Column(name = "total_venta_neta", precision = 18, scale = 5)
	private Double						totalVentaNeta;
	@Column(name = "total_otros_cargos", precision = 18, scale = 5)
	private Double						totalOtrosCargos;

	
	@Column(name = "total_efectivo", precision = 18, scale = 5)
	private Double						totalEfectivo;

	@Column(name = "total_tarjeta", precision = 18, scale = 5)
	private Double						totalTarjeta;

	@Column(name = "total_banco", precision = 18, scale = 5)
	private Double						totalBanco;

	@Column(name = "total_credito", precision = 18, scale = 5)
	private Double						totalCredito;

	@Column(name = "monto_cambio", precision = 18, scale = 5)
	private Double						montoCambio;

	@Column(name = "nota")
	private String						nota;

	
//Impuesto del servicio cuando aplica
	@Column(name = "total_servicio", precision = 18, scale = 5)
	private Double						totalImpuestoServicio;


	@Column(name = "total_serv_gravados", precision = 18, scale = 5)
	private Double						totalServGravados;
	
	


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
	
	

	
	public String getNota() {
		return nota;
	}

	
	public void setNota(String nota) {
		this.nota = nota;
	}

	public String getCedula() {
		return cedula;
	}
	
	public String getCedulaPrin() {
		if(this.cedula !=null) {
			if(!cedula.equals(Constantes.EMPTY)) {
				return cedula;
			}
		}
		if(this.identificacionExtranjero != null) {
		  if(!this.identificacionExtranjero.equals(Constantes.EMPTY)) {
		  	return this.identificacionExtranjero;
		  }	
		}	
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
	public String getFechaEmisionSTR() {
		if (this.fechaEmision != null) {
			return Utils.getFechaGeneraReporte(this.getFechaEmision());
		}
		return Constantes.EMPTY;
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
	
	public String getTipoDocSTR() {
		return MapEnums.ENUM_TIPO_DOC.get(this.tipoDoc);
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
	
	public String getCondicionVentaSTR() {
		return MapEnums.ENUM_CONDICION_VENTA.get(this.getCondicionVenta());
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
	
	

	
	public Double getTotalServExentos() {
		return totalServExentos;
	}

	
	public void setTotalServExentos(Double totalServExentos) {
		this.totalServExentos = totalServExentos;
	}
	
	
	
	
	public Double getTotalOtrosCargos() {
		return totalOtrosCargos;
	}

	
	public void setTotalOtrosCargos(Double totalOtrosCargos) {
		this.totalOtrosCargos = totalOtrosCargos;
	}

	
	public Double getTotalImpuestoServicio() {
		return totalImpuestoServicio;
	}

	
	public void setTotalImpuestoServicio(Double totalImpuestoServicio) {
		this.totalImpuestoServicio = totalImpuestoServicio;
	}

	
	public Double getTotalServGravados() {
		return totalServGravados;
	}

	
	public void setTotalServGravados(Double totalServGravados) {
		this.totalServGravados = totalServGravados;
	}

	public String getTipoCambioSTR() {
		return Utils.formateadorMiles(this.tipoCambio);
	}
	public Double getTotalEfectivoNC() {
		if (tipoDoc != null && tipoDoc.equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO)) {
			return this.totalEfectivo != null ? totalEfectivo * -1 : 0d;
		} else {
			return totalEfectivo;
		}
	}
	
	public Double getTotalTarjetaNC() {
		if (tipoDoc != null && tipoDoc.equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO)) {
			return this.totalTarjeta != null ? totalTarjeta * -1 : 0d;
		} else {
			return totalTarjeta;
		}
	}
	public Double getTotalBancoNC() {
		if (tipoDoc != null && tipoDoc.equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO)) {
			return this.totalBanco != null ? totalBanco * -1 : 0d;
		} else {
			return totalBanco;
		}
	}
	public Double getTotalCreditoNC() {
		if (tipoDoc != null && tipoDoc.equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO)) {
			return this.totalCredito != null ? totalCredito * -1 : 0d;
		} else {
			return totalCredito;
		}
	}
	public Double getMontoCambioNC() {
		if (tipoDoc != null && tipoDoc.equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO)) {
			return this.montoCambio != null ? montoCambio * -1 : 0d;
		} else {
			return montoCambio;
		}
	}
	public Double getTotalComprobanteNC() {
		if (tipoDoc != null && tipoDoc.equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO)) {
			return this.totalComprobante != null ? totalComprobante * -1 : 0d;
		} else {
			return totalComprobante;
		}
	}
	public Double getTotalOtrosCargosNC() {
		if (tipoDoc != null && tipoDoc.equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO)) {
			return this.totalOtrosCargos != null ? totalOtrosCargos * -1 : 0d;
		} else {
			return totalOtrosCargos;
		}
	}
	public Double getTotalServExentosNC() {
		if (tipoDoc != null && tipoDoc.equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO)) {
			return this.totalServExentos != null ? totalServExentos * -1 : 0d;
		} else {
			return totalServExentos;
		}
	}
	
	public Double getTotalColonesNC() {
		if (tipoDoc != null && tipoDoc.equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO)) {
			return this.totalComprobante != null ? (totalComprobante * tipoCambio)  : (totalComprobante * tipoCambio);
		} else {
			return (totalComprobante * tipoCambio);
		}
	}
	
	public Double getTotalImpuestoServicioNC() {
		if (tipoDoc != null && tipoDoc.equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO)) {
			return this.totalImpuestoServicio != null ? this.totalImpuestoServicio * -1 : this.totalImpuestoServicio;
		} else {
			return this.totalImpuestoServicio;
		}
	}

	
	public Double getTotalServGravadosNC() {
		if (tipoDoc != null && tipoDoc.equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO)) {
			return this.totalServGravados != null ? totalServGravados * -1 : 0d;
		} else {
			return totalServGravados;
		}
	}

	public Double getTotalMercanciasGravadas() {
		return totalMercanciasGravadas;
	}
	public Double getTotalMercanciasGravadasNC() {
		if (tipoDoc != null && tipoDoc.equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO)) {
			return this.totalMercanciasGravadas != null ? totalMercanciasGravadas * -1 : 0d;
		} else {
			return totalMercanciasGravadas;
		}
	}
	public Double getTotalDescuentosNC() {
		if (tipoDoc != null && tipoDoc.equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO)) {
			return this.totalDescuentos != null ? totalDescuentos * -1 : 0d;
		} else {
			return totalDescuentos;
		}
	}
	
	public void setTotalMercanciasGravadas(Double totalMercanciasGravadas) {
		this.totalMercanciasGravadas = totalMercanciasGravadas;
	}

	
	public Double getTotalMercanciasExentas() {
		return totalMercanciasExentas;
	}
	
	public Double getTotalVentaNetaNC() {
		if (tipoDoc != null && tipoDoc.equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO)) {
			return this.totalVentaNeta != null ? totalVentaNeta * -1 : 0d;
		} else {
			return totalVentaNeta;
		}
	}
	public Double getTotalImpuestoNC() {
		if (tipoDoc != null && tipoDoc.equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO)) {
			return this.totalImpuesto != null ? totalImpuesto * -1 : 0d;
		} else {
			return totalImpuesto;
		}
	}
	public Double getTotalMercanciasExentasNC() {
		if (tipoDoc != null && tipoDoc.equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO)) {
			return this.totalMercanciasExentas != null ? totalMercanciasExentas * -1 : 0d;
		} else {
			return totalMercanciasExentas;
		}
	}
	
	public void setTotalMercanciasExentas(Double totalMercanciasExentas) {
		this.totalMercanciasExentas = totalMercanciasExentas;
	}

	
	public Double getTotalGravado() {
		return totalGravado;
	}
	public Double getTotalGravadoNC() {
		if (tipoDoc != null && tipoDoc.equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO)) {
			return this.totalGravado != null ? totalGravado * -1 : 0d;
		} else {
			return totalGravado;
		}
	}
	
	public void setTotalGravado(Double totalGravado) {
		this.totalGravado = totalGravado;
	}

	
	public Double getTotalExento() {
		return totalExento;
	}
	public Double getTotalExentoNC() {
		if (tipoDoc != null && tipoDoc.equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO)) {
			return this.totalExento != null ? totalExento * -1 : 0d;
		} else {
			return totalExento;
		}
	}
	
	public void setTotalExento(Double totalExento) {
		this.totalExento = totalExento;
	}

	
	public Double getTotalVenta() {
		return totalVenta;
	}

	
	public void setTotalVenta(Double totalVenta) {
		this.totalVenta = totalVenta;
	}

	
	public Double getTotalVentaNeta() {
		return totalVentaNeta;
	}

	
	public void setTotalVentaNeta(Double totalVentaNeta) {
		this.totalVentaNeta = totalVentaNeta;
	}
	

	
	public String getReferenciaNumero() {
		return referenciaNumero;
	}

	
	public void setReferenciaNumero(String referenciaNumero) {
		this.referenciaNumero = referenciaNumero;
	}

	
	public Double getTotalEfectivo() {
		return totalEfectivo;
	}

	
	public void setTotalEfectivo(Double totalEfectivo) {
		this.totalEfectivo = totalEfectivo;
	}

	
	public Double getTotalTarjeta() {
		return totalTarjeta;
	}

	
	public void setTotalTarjeta(Double totalTarjeta) {
		this.totalTarjeta = totalTarjeta;
	}

	
	public Double getTotalBanco() {
		return totalBanco;
	}

	
	public void setTotalBanco(Double totalBanco) {
		this.totalBanco = totalBanco;
	}

	
	public Double getTotalCredito() {
		return totalCredito;
	}

	
	public void setTotalCredito(Double totalCredito) {
		this.totalCredito = totalCredito;
	}

	
	public Double getMontoCambio() {
		return montoCambio;
	}

	
	public void setMontoCambio(Double montoCambio) {
		this.montoCambio = montoCambio;
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
		result = prime * result + ((identificacionExtranjero == null) ? 0 : identificacionExtranjero.hashCode());
		result = prime * result + ((montoCambio == null) ? 0 : montoCambio.hashCode());
		result = prime * result + ((noFacturaElectronica == null) ? 0 : noFacturaElectronica.hashCode());
		result = prime * result + ((nombreFactura == null) ? 0 : nombreFactura.hashCode());
		result = prime * result + ((nombreUsuario == null) ? 0 : nombreUsuario.hashCode());
		result = prime * result + ((nota == null) ? 0 : nota.hashCode());
		result = prime * result + ((numeroConsecutivo == null) ? 0 : numeroConsecutivo.hashCode());
		result = prime * result + ((referenciaNumero == null) ? 0 : referenciaNumero.hashCode());
		result = prime * result + ((tipoCambio == null) ? 0 : tipoCambio.hashCode());
		result = prime * result + ((tipoDoc == null) ? 0 : tipoDoc.hashCode());
		result = prime * result + ((totalBanco == null) ? 0 : totalBanco.hashCode());
		result = prime * result + ((totalComprobante == null) ? 0 : totalComprobante.hashCode());
		result = prime * result + ((totalCredito == null) ? 0 : totalCredito.hashCode());
		result = prime * result + ((totalDescuentos == null) ? 0 : totalDescuentos.hashCode());
		result = prime * result + ((totalEfectivo == null) ? 0 : totalEfectivo.hashCode());
		result = prime * result + ((totalExento == null) ? 0 : totalExento.hashCode());
		result = prime * result + ((totalGravado == null) ? 0 : totalGravado.hashCode());
		result = prime * result + ((totalImpuesto == null) ? 0 : totalImpuesto.hashCode());
		result = prime * result + ((totalImpuestoServicio == null) ? 0 : totalImpuestoServicio.hashCode());
		result = prime * result + ((totalMercanciasExentas == null) ? 0 : totalMercanciasExentas.hashCode());
		result = prime * result + ((totalMercanciasGravadas == null) ? 0 : totalMercanciasGravadas.hashCode());
		result = prime * result + ((totalOtrosCargos == null) ? 0 : totalOtrosCargos.hashCode());
		result = prime * result + ((totalServExentos == null) ? 0 : totalServExentos.hashCode());
		result = prime * result + ((totalServGravados == null) ? 0 : totalServGravados.hashCode());
		result = prime * result + ((totalTarjeta == null) ? 0 : totalTarjeta.hashCode());
		result = prime * result + ((totalVenta == null) ? 0 : totalVenta.hashCode());
		result = prime * result + ((totalVentaNeta == null) ? 0 : totalVentaNeta.hashCode());
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
		ListarFacturasNativa other = (ListarFacturasNativa) obj;
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
		if (identificacionExtranjero == null) {
			if (other.identificacionExtranjero != null)
				return false;
		} else if (!identificacionExtranjero.equals(other.identificacionExtranjero))
			return false;
		if (montoCambio == null) {
			if (other.montoCambio != null)
				return false;
		} else if (!montoCambio.equals(other.montoCambio))
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
		if (nota == null) {
			if (other.nota != null)
				return false;
		} else if (!nota.equals(other.nota))
			return false;
		if (numeroConsecutivo == null) {
			if (other.numeroConsecutivo != null)
				return false;
		} else if (!numeroConsecutivo.equals(other.numeroConsecutivo))
			return false;
		if (referenciaNumero == null) {
			if (other.referenciaNumero != null)
				return false;
		} else if (!referenciaNumero.equals(other.referenciaNumero))
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
		if (totalBanco == null) {
			if (other.totalBanco != null)
				return false;
		} else if (!totalBanco.equals(other.totalBanco))
			return false;
		if (totalComprobante == null) {
			if (other.totalComprobante != null)
				return false;
		} else if (!totalComprobante.equals(other.totalComprobante))
			return false;
		if (totalCredito == null) {
			if (other.totalCredito != null)
				return false;
		} else if (!totalCredito.equals(other.totalCredito))
			return false;
		if (totalDescuentos == null) {
			if (other.totalDescuentos != null)
				return false;
		} else if (!totalDescuentos.equals(other.totalDescuentos))
			return false;
		if (totalEfectivo == null) {
			if (other.totalEfectivo != null)
				return false;
		} else if (!totalEfectivo.equals(other.totalEfectivo))
			return false;
		if (totalExento == null) {
			if (other.totalExento != null)
				return false;
		} else if (!totalExento.equals(other.totalExento))
			return false;
		if (totalGravado == null) {
			if (other.totalGravado != null)
				return false;
		} else if (!totalGravado.equals(other.totalGravado))
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
		if (totalMercanciasExentas == null) {
			if (other.totalMercanciasExentas != null)
				return false;
		} else if (!totalMercanciasExentas.equals(other.totalMercanciasExentas))
			return false;
		if (totalMercanciasGravadas == null) {
			if (other.totalMercanciasGravadas != null)
				return false;
		} else if (!totalMercanciasGravadas.equals(other.totalMercanciasGravadas))
			return false;
		if (totalOtrosCargos == null) {
			if (other.totalOtrosCargos != null)
				return false;
		} else if (!totalOtrosCargos.equals(other.totalOtrosCargos))
			return false;
		if (totalServExentos == null) {
			if (other.totalServExentos != null)
				return false;
		} else if (!totalServExentos.equals(other.totalServExentos))
			return false;
		if (totalServGravados == null) {
			if (other.totalServGravados != null)
				return false;
		} else if (!totalServGravados.equals(other.totalServGravados))
			return false;
		if (totalTarjeta == null) {
			if (other.totalTarjeta != null)
				return false;
		} else if (!totalTarjeta.equals(other.totalTarjeta))
			return false;
		if (totalVenta == null) {
			if (other.totalVenta != null)
				return false;
		} else if (!totalVenta.equals(other.totalVenta))
			return false;
		if (totalVentaNeta == null) {
			if (other.totalVentaNeta != null)
				return false;
		} else if (!totalVentaNeta.equals(other.totalVentaNeta))
			return false;
		return true;
	}

	
	
		
	

}
