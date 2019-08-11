package com.emprendesoftcr.Dao;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.emprendesoftcr.modelo.Cliente;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Factura;
import com.emprendesoftcr.web.command.TotalFacturaCommand;

public interface FacturaDao {

	void agregar(Factura factura);

	void modificar(Factura factura);

	void eliminar(Factura factura);

	Factura findById(Long id);

	Factura findByConsecutivoAndEmpresa(String consecutivo, Empresa empresa);
	
	Factura findByClaveAndEmpresa(String clave, Empresa empresa) throws Exception;
	
	void eliminarDetalleFacturaPorSP(Factura factura);
	
	Collection<Factura> findByEstadoFirma(Integer estadoFirma, Integer reEstadoFirma);
	
	TotalFacturaCommand sumarFacturas(Date fechaInicio, Date fechaFinal, Integer idEmpresa,Integer estado,String actividadEconomica);
	
	List<Object[]> proformasByState(Integer estado, Integer idEmpresa);
	
	
	Collection<Factura> facturasRangoEstado(Integer estado, Date fechaInicio, Date fechaFin, Integer idEmpresa,String actividadEconomica);
		
	Collection<Factura> findByClienteAndEmpresa(Cliente cliente ,Empresa empresa);
}