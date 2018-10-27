package com.emprendesoftcr.Bo;

import com.emprendesoftcr.modelo.Detalle;
import com.emprendesoftcr.modelo.Factura;

public interface DetalleBo {
	

	void agregar(Detalle detalle);

	void modificar(Detalle detalle);

	void eliminar(Detalle detalle);
	
	Integer eliminarDetalleFactura(Factura factura)throws Exception;

}
