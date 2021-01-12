package com.emprendesoftcr.Dao;

import java.util.Collection;
import java.util.Date;

import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.RecepcionFactura;
import com.emprendesoftcr.modelo.RecepcionFacturaDetalle;

public interface RecepcionFacturaDao {

	void agregar(RecepcionFactura recepcionFactura);

	void agregar(RecepcionFacturaDetalle recepcionFacturaDetalle);
	
	void modificar(RecepcionFactura recepcionFactura);
	
	RecepcionFactura findById(Long id);
	RecepcionFactura findByClaveAndCedulaEmisor(String clave, String cedula) throws Exception;

	RecepcionFactura findByConsecutivoAndEmpresa(String consecutivo, Empresa empresa) throws Exception;

	Collection<RecepcionFactura> findByEstadoFirma(Integer estadoFirma, Integer reEstadoFirma);	

	Collection<RecepcionFactura> findByClave(String cedulaEmisor, String clave);
	
	Collection<RecepcionFactura> findByFechaInicioAndFechaFinalAndCedulaEmisor(Date fechaInicio, Date fechaFin, Empresa empresa,  String cedula,Integer estado,Integer tipoGasto,String actividadEconocimica);
	Collection<RecepcionFacturaDetalle> findByDetalleAndFechaInicioAndFechaFinalAndCedulaEmisor(Date fechaInicio, Date fechaFin, Empresa empresa,  String cedula , Integer estado,Integer tipoGasto,String actividadEconocimica);
	Collection<RecepcionFacturaDetalle> findByIdRecepcionFactura(Long id);
}