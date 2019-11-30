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

@BaseNativeQuery(name = "listar_tfacturas", query = "SELECT empresas.nofactura_elec,"
		+ " fac.tipo_doc,"
		+ " fac.estado,"
		+ " fac.condicion_venta, "
		+ " fac.fecha_emision,"
		+ " if(fac.tipo_doc = '03' or fac.tipo_doc = '86' ,fac.total_comprobante * -1,fac.total_comprobante) AS total_comprobante, \n"
		+ " fac.id,"
		+ " fac.numero_consecutivo,"
		+ " fac.nombre_factura,"
		+ " clientes.nombre_completo,"
		+ " usuarios.nombre_usuario from facturas fac" 
+ " inner join clientes on clientes.id = fac.cliente_id " 
+ " inner join empresas on empresas.id = fac.empresa_id " 
+ " inner join usuarios on usuarios.id = fac.usuario_id " 
+ " where fac.empresa_id = :ID_EMPRESA "
+ " and fac.fecha_emision >=  :fechaInicial "
+ " and  fac.fecha_emision <=  :fechaFinal "
+ " and fac.cliente_id  "
+ " and fac.tipo_doc  "
+ " and fac.act_comercial "
+ " and fac.estado "
+ " and fac.usuario_id ")
@Entity
public class ListarFacturasTableNativa implements Serializable{
	

	
	private static final long serialVersionUID = -1307919090005602598L;

	@Id
	@Column(name = "id")
	private Long							id;
	
	@Column(name = "tipo_doc")
	private String						tipoDoc;

	
	@Column(name = "nombre_factura")
	private String						nombreFactura;

	@Column(name = "nombre_completo")
	private String						NombreCompleto;

	@Column(name = "nombre_usuario")
	private String						nombreUsuario;
	

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "fecha_emision")
	private Date							fechaEmision;
	
	@Column(name = "condicion_venta")
	private String						condicionVenta;

	@Column(name = "numero_consecutivo")
	private String						numeroConsecutivo;
	

	@Column(name = "estado")
	private Integer						estado;
	
	@Column(name = "nofactura_elec")
	private Integer						noFacturaElectronica;

	@Column(name = "total_comprobante", precision = 18, scale = 5)
	private Double						totalComprobante;

	
	public Long getId() {
		return id;
	}

	
	public void setId(Long id) {
		this.id = id;
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

	
	public Date getFechaEmision() {
		return fechaEmision;
	}

	public String getFechaEmisionSTR() {
		if (this.fechaEmision != null) {
			return Utils.getFechaGeneraReporte(this.getFechaEmision());
		}
		return Constantes.EMPTY;
	}
	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	
	public String getCondicionVenta() {
		return condicionVenta;
	}
	public String getCondicionVentaSTR() {
		return MapEnums.ENUM_CONDICION_VENTA.get(this.getCondicionVenta());
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

	
	public Integer getEstado() {
		return estado;
	}

	
	public String getEstadoSTR() {
		return MapEnums.ENUM_ESTADO_FACTURA.get(this.estado.toString());
	}

	
	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	
	
	public Integer getNoFacturaElectronica() {
		return noFacturaElectronica;
	}

	
	public void setNoFacturaElectronica(Integer noFacturaElectronica) {
		this.noFacturaElectronica = noFacturaElectronica;
	}

	
	public Double getTotalComprobante() {
			return totalComprobante;

	}
	
	public String gettotalComprobanteSTR() {
			return Utils.formateadorMiles(totalComprobante);
	}
	
	public void setTotalComprobante(Double totalComprobante) {
		this.totalComprobante = totalComprobante;
	}


	
	public String getTipoDoc() {
		return tipoDoc;
	}

	public String getTipoDocSTR() {
		return MapEnums.ENUM_TIPO_DOC.get(this.tipoDoc);
	}
	
	public void setTipoDoc(String tipoDoc) {
		this.tipoDoc = tipoDoc;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((NombreCompleto == null) ? 0 : NombreCompleto.hashCode());
		result = prime * result + ((condicionVenta == null) ? 0 : condicionVenta.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + ((fechaEmision == null) ? 0 : fechaEmision.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((noFacturaElectronica == null) ? 0 : noFacturaElectronica.hashCode());
		result = prime * result + ((nombreFactura == null) ? 0 : nombreFactura.hashCode());
		result = prime * result + ((nombreUsuario == null) ? 0 : nombreUsuario.hashCode());
		result = prime * result + ((numeroConsecutivo == null) ? 0 : numeroConsecutivo.hashCode());
		result = prime * result + ((tipoDoc == null) ? 0 : tipoDoc.hashCode());
		result = prime * result + ((totalComprobante == null) ? 0 : totalComprobante.hashCode());
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
		ListarFacturasTableNativa other = (ListarFacturasTableNativa) obj;
		if (NombreCompleto == null) {
			if (other.NombreCompleto != null)
				return false;
		} else if (!NombreCompleto.equals(other.NombreCompleto))
			return false;
		if (condicionVenta == null) {
			if (other.condicionVenta != null)
				return false;
		} else if (!condicionVenta.equals(other.condicionVenta))
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
		return true;
	}




	
	

}
