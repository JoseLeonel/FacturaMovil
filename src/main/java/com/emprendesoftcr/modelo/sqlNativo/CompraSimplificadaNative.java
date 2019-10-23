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

@BaseNativeQuery(name = "compra_simpli", query = "Select ref_numero,compra_simpli.created_at,compra_simpli.clave,compra_simpli.numero_consecutivo,compra_simpli.total_descuentos,compra_simpli.total_impuesto,compra_simpli.total_comprobante,usuarios.nombre_usuario,"
+ "compra_simpli.fecha_emision, provee_simpli.nombre_completo,compra_simpli.total_descuentos, compra_simpli.act_comercial,compra_simpli.id,compra_simpli.empresa_id,compra_simpli.estado" 
+ " from compra_simpli" 
+ " inner join usuarios on usuarios.id = compra_simpli.usuario_id " 
+ " inner join provee_simpli on provee_simpli.id = compra_simpli.proveedorsimpl_id " 
+ "  where  compra_simpli.empresa_id = :ID_EMPRESA and compra_simpli.created_at >= :FECHAINICIAL and compra_simpli.created_at <= :FechaFinal and compra_simpli.estado = :Estado and compra_simpli.usuario_id =  and compra_simpli.proveedorsimpl_id =  ")
@Entity
public class CompraSimplificadaNative implements Serializable {

	
	private static final long serialVersionUID = 466521158014902869L;

	@Id
	@Column(name = "id")
	private Long		id;

	@Column(name = "numero_consecutivo", length = 20)
	private String	numeroConsecutivo;

	@Column(name = "clave", length = 50)
	private String	clave;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "fecha_emision")
	private Date		fechaEmision;

	@Column(name = "total_descuentos", precision = 18, scale = 5)
	private Double	totalDescuentos;

	@Column(name = "total_impuesto", precision = 18, scale = 5)
	private Double	totalImpuesto;

	@Column(name = "total_comprobante", precision = 18, scale = 5)
	private Double	totalComprobante;

	@Column(name = "estado")
	private Integer	estado;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "created_at")
	private Date		created_at;

	@Column(name = "nombre_completo")
	private String	nombreProveedor;

	@Column(name = "nombre_usuario")
	private String	nombreUsuario;

	@Column(name = "act_comercial", length = 6)
	private String	codigoActividad;
	
	@Column(name = "ref_numero", length = 20)
	private String								referenciaNumero;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumeroConsecutivo() {
		return numeroConsecutivo;
	}

	public void setNumeroConsecutivo(String numeroConsecutivo) {
		this.numeroConsecutivo = numeroConsecutivo;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
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
	public String getTotalImpuestoSTR() {
		return Utils.formateadorMiles(this.totalImpuesto);
	}
	public Double getTotalImpuesto() {
		return totalImpuesto;
	}

	public void setTotalImpuesto(Double totalImpuesto) {
		this.totalImpuesto = totalImpuesto;
	}

	public Double getTotalComprobante() {
		return totalComprobante;
	}

	public void setTotalComprobante(Double totalComprobante) {
		this.totalComprobante = totalComprobante;
	}

	public String getTotalComprobanteSTR() {
		return Utils.formateadorMiles(this.totalComprobante);
	}

	
	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public String getNombreProveedor() {
		return nombreProveedor;
	}

	public void setNombreProveedor(String nombreProveedor) {
		this.nombreProveedor = nombreProveedor;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getCodigoActividad() {
		return codigoActividad;
	}

	public void setCodigoActividad(String codigoActividad) {
		this.codigoActividad = codigoActividad;
	}

	
	public Double getTotalDescuentos() {
		return totalDescuentos;
	}

	
	public void setTotalDescuentos(Double totalDescuentos) {
		this.totalDescuentos = totalDescuentos;
	}

	public String getTotalDescuentoSTR() {
		return Utils.formateadorMiles(this.totalDescuentos);
	}
	
	

	
	public String getReferenciaNumero() {
		return referenciaNumero;
	}

	
	public void setReferenciaNumero(String referenciaNumero) {
		this.referenciaNumero = referenciaNumero;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((clave == null) ? 0 : clave.hashCode());
		result = prime * result + ((codigoActividad == null) ? 0 : codigoActividad.hashCode());
		result = prime * result + ((created_at == null) ? 0 : created_at.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + ((fechaEmision == null) ? 0 : fechaEmision.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nombreProveedor == null) ? 0 : nombreProveedor.hashCode());
		result = prime * result + ((nombreUsuario == null) ? 0 : nombreUsuario.hashCode());
		result = prime * result + ((numeroConsecutivo == null) ? 0 : numeroConsecutivo.hashCode());
		result = prime * result + ((referenciaNumero == null) ? 0 : referenciaNumero.hashCode());
		result = prime * result + ((totalComprobante == null) ? 0 : totalComprobante.hashCode());
		result = prime * result + ((totalDescuentos == null) ? 0 : totalDescuentos.hashCode());
		result = prime * result + ((totalImpuesto == null) ? 0 : totalImpuesto.hashCode());
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
		CompraSimplificadaNative other = (CompraSimplificadaNative) obj;
		if (clave == null) {
			if (other.clave != null)
				return false;
		} else if (!clave.equals(other.clave))
			return false;
		if (codigoActividad == null) {
			if (other.codigoActividad != null)
				return false;
		} else if (!codigoActividad.equals(other.codigoActividad))
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
		if (nombreProveedor == null) {
			if (other.nombreProveedor != null)
				return false;
		} else if (!nombreProveedor.equals(other.nombreProveedor))
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
		if (referenciaNumero == null) {
			if (other.referenciaNumero != null)
				return false;
		} else if (!referenciaNumero.equals(other.referenciaNumero))
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
		return true;
	}

	
	
	
	
	

}
