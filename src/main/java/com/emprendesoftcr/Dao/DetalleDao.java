package com.emprendesoftcr.Dao;

import java.util.Collection;
import java.util.Date;

import com.emprendesoftcr.modelo.Detalle;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Factura;
import com.emprendesoftcr.web.command.TotalDetallesCommand;

public interface DetalleDao {

	void agregar(Detalle detalle);

	void modificar(Detalle detalle);

	void eliminar(Detalle detalle);

	Integer eliminarDetalleFactura(Factura factura) throws Exception;

	Collection<Detalle> facturasRangoEstado(Integer estado, Date fechaInicio, Date fechaFin, Empresa empresa);

	Collection<Detalle> facturasRango(Integer estado, Date fechaInicio, Date fechaFin, Empresa empresa, String tipoImpuesto, String actividadEconomica);

	Collection<Detalle> findByFactura(Factura factura);

	TotalDetallesCommand totalVentasPorDetalle(Empresa empresa, Date fechaInicio, Date FechaFinal, String tipoImpuesto, Integer estado, String actividadEconomica);

	Detalle findByCodigoAndEmpresa(String codigo, Empresa empresa);

	Detalle findById(Long idDetalle);
	
	Collection<Detalle>  findbyIdFactura (Long idFactura);
	
	Collection<Detalle>  findbyConsecutivoAndEmpresa (String consecutivo,Empresa empresa);

}