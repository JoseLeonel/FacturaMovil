package com.emprendesoftcr.Bo;

import java.util.Collection;
import java.util.Date;

import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.RecepcionFactura;
import com.emprendesoftcr.modelo.RecepcionFacturaDetalle;

public interface RecepcionFacturaBo {

	void agregar(RecepcionFactura recepcionFactura);

	void agregar(RecepcionFacturaDetalle recepcionFacturaDetalle);

	void modificar(RecepcionFactura recepcionFactura);

	RecepcionFactura findById(Long id);

	RecepcionFactura findByConsecutivoAndEmpresa(String consecutivo, Empresa empresa) throws Exception;

	Collection<RecepcionFactura> findByEstadoFirma(Integer estadoFirma, Integer reEstadoFirma);
	
	Collection<RecepcionFactura> findByClave(String cedulaEmisor, String clave);

	Collection<RecepcionFactura> findByFechaInicioAndFechaFinalAndCedulaEmisor(Date fechaInicio, Date fechaFin, Empresa empresa,  String cedula,Integer estado,String tipoGasto,String actividadEconocimica);
	
	Collection<RecepcionFacturaDetalle> findByDetalleAndFechaInicioAndFechaFinalAndCedulaEmisor(Date fechaInicio, Date fechaFin, Empresa empresa,  String cedula , Integer estado,String tipoGasto,String actividadEconocimica);
}