package com.emprendesoftcr.Bo;

import java.util.Collection;
import java.util.Date;

import com.emprendesoftcr.modelo.Detalle;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Factura;
import com.emprendesoftcr.web.command.TotalDetallesCommand;

public interface DetalleBo {
	

	void agregar(Detalle detalle);

	void modificar(Detalle detalle);

	void eliminar(Detalle detalle);
	
	Integer eliminarDetalleFactura(Factura factura)throws Exception;
	Collection<Detalle> facturasRangoEstado(Integer estado, Date fechaInicio, Date fechaFin);
	Collection<Detalle> findByFactura(Factura factura);
	
	
	TotalDetallesCommand totalVentasPorDetalle(Empresa empresa , Date fechaInicio,Date FechaFinal);

}
