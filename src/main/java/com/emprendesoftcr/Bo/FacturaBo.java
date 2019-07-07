package com.emprendesoftcr.Bo;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.emprendesoftcr.modelo.Cliente;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Factura;
import com.emprendesoftcr.modelo.TipoCambio;
import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.modelo.UsuarioCaja;
import com.emprendesoftcr.web.command.FacturaCommand;

import com.emprendesoftcr.web.command.TotalFacturaCommand;

public interface FacturaBo {

	void agregar(Factura factura);

	void modificar(Factura factura);

	void eliminar(Factura factura);

	Factura findById(Long id);

	Factura findByConsecutivoAndEmpresa(String consecutivo, Empresa empresa) throws Exception;
	
	Factura crearFactura(FacturaCommand facturaCommand,Usuario usuario,UsuarioCaja usuarioCaja,TipoCambio tipoCambio) throws Exception;

	Double  getTotalEfectivo(FacturaCommand facturaCommand) throws Exception;
	
	void eliminarDetalleFacturaPorSP(Factura factura)throws Exception;
	
	Collection<Factura> findByEstadoFirma(Integer estadoFirma, Integer reEstadoFirma);
	
	TotalFacturaCommand sumarFacturas(Date fechaInicio, Date fechaFinal, Integer idEmpresa);
	
	Collection<Factura> facturasRangoEstado(Integer estado, Date fechaInicio, Date fechaFin, Integer idEmpresa);
	
	Collection<Factura> findByClienteAndEmpresa(Cliente cliente ,Empresa empresa);
	Collection<Object[]> proformasByState(Integer estado, Integer idEmpresa);
}	