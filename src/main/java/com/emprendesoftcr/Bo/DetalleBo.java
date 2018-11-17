package com.emprendesoftcr.Bo;

import java.util.Collection;
import java.util.Date;

import com.emprendesoftcr.modelo.Cliente;
import com.emprendesoftcr.modelo.Detalle;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Factura;
import com.emprendesoftcr.modelo.Usuario;

public interface DetalleBo {
	

	void agregar(Detalle detalle);

	void modificar(Detalle detalle);

	void eliminar(Detalle detalle);
	
	Integer eliminarDetalleFactura(Factura factura)throws Exception;
	Collection<Detalle> facturasRangoEstado(Integer estado, Date fechaInicio, Date fechaFin, String codigo, String tipoDocumento, Cliente cliente, Empresa empresa, Usuario usuario);

}
