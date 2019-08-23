package com.emprendesoftcr.modelo.sqlNativo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

//@BaseNativeQuery(name = "listarFacturas", query = "SELECT facturas.id,facturas.numero_consecutivo,facturas.nombre_factura,clientes.nombre_completo,facturas.total_descuentos,facturas.total_impuesto,facturas.codigo_moneda,facturas.total_comprobante ,facturas.created_at from facturas" + " inner join clientes on clientes.id = facturas.cliente_id " + " inner join usuarios on usuarios.id = facturas.usuario_id " + " where facturas.empresa_id = :ID_EMPRESA and facturas.created_at >=  :fechaInicial and  facturas.created_at <=  :fechaFinal and facturas.cliente_id = :idCliente and facturas.tipo_doc :listaTipoDoc and facturas.act_comercial = :idActividadComercial and facturas.estado = :estados ")
//@Entity
public class ListarFacturas {

	@Id
	@Column(name = "id")
	private Long		id;

	@Column(name = "total_comprobante", precision = 18, scale = 5)
	private Double	totalComprobante;
	@Column(name = "tipo_doc")
	private String	tipoDoc;

	@Column(name = "codigo_moneda")
	private String	codigoMoneda;

	@Column(name = "estado")
	private Integer	estado;

	@Column(name = "act_comercial", length = 6)
	private String	codigoActividad;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "created_at")
	private Date		created_at;

	@Column(name = "condicion_venta")
	private String	condicionVenta;

	@Column(name = "numero_consecutivo")
	private String	numeroConsecutivo;

	@Column(name = "consecutivo_proforma")
	private String	consecutivoProforma;

}
