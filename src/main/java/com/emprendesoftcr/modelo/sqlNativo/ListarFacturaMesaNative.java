package com.emprendesoftcr.modelo.sqlNativo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@BaseNativeQuery(name ="fact_mesas" ,query ="SELECT facturas.mesa_id,facturas.id,facturas.nombre_factura "
		+ "FROM `facturas` WHERE facturas.estado and facturas.mesa_id and facturas.empresa_id ")
@Entity
public class ListarFacturaMesaNative implements Serializable {

	private static final long serialVersionUID = -5131824064963800897L;
	@Id
	@Column(name = "id")
	private Long							id;
	
	@Column(name = "nombre_factura")
	private String		nombreFactura;
	

	@Column(name = "mesa_id")
	private Long  idMesa;


	
	
	
	
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


	
	public Long getIdMesa() {
		return idMesa;
	}


	
	public void setIdMesa(Long idMesa) {
		this.idMesa = idMesa;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((idMesa == null) ? 0 : idMesa.hashCode());
		result = prime * result + ((nombreFactura == null) ? 0 : nombreFactura.hashCode());
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
		ListarFacturaMesaNative other = (ListarFacturaMesaNative) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (idMesa == null) {
			if (other.idMesa != null)
				return false;
		} else if (!idMesa.equals(other.idMesa))
			return false;
		if (nombreFactura == null) {
			if (other.nombreFactura != null)
				return false;
		} else if (!nombreFactura.equals(other.nombreFactura))
			return false;
		return true;
	}
	
	

	
	

}
