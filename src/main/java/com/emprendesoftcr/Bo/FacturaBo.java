package com.emprendesoftcr.Bo;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emprendesoftcr.modelo.Cliente;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Factura;
import com.emprendesoftcr.modelo.TipoCambio;
import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.modelo.UsuarioCaja;
import com.emprendesoftcr.modelo.sqlNativo.ListarFacturasNativa;
import com.emprendesoftcr.utils.RespuestaServiceDataTable;
import com.emprendesoftcr.web.command.DetalleFacturaCommand;
import com.emprendesoftcr.web.command.FacturaCommand;
import com.emprendesoftcr.web.command.TotalFacturaCommand;

public interface FacturaBo {

	void agregar(Factura factura);

	void modificar(Factura factura);

	void eliminar(Factura factura);

	Factura findById(Long id);

	Factura findByConsecutivoAndEmpresa(String consecutivo, Empresa empresa) throws Exception;

	Factura findByConsecutivoProformaAndEmpresa(String consecutivo, Empresa empresa) throws Exception;

	Factura findByClaveAndEmpresa(String clave, Empresa empresa) throws Exception;

	Factura crearFactura(FacturaCommand facturaCommand, Usuario usuario, UsuarioCaja usuarioCaja, TipoCambio tipoCambio, ArrayList<DetalleFacturaCommand> detallesFacturaCommand, ArrayList<DetalleFacturaCommand> detallesNotaCredito) throws Exception;

	Double getTotalEfectivo(FacturaCommand facturaCommand) throws Exception;

	void eliminarDetalleFacturaPorSP(Factura factura) throws Exception;

	Collection<Factura> findByEstadoFirma(Integer estadoFirma, Integer reEstadoFirma);

	TotalFacturaCommand sumarFacturas(Date fechaInicio, Date fechaFinal, Integer idEmpresa, Integer estado, String actividadEconomica);

	TotalFacturaCommand sumarFacturasNoElectronica(Date fechaInicio, Date fechaFinal, Integer idEmpresa, Integer estado, String actividadEconomica);

	Collection<Factura> facturasRangoEstado(Integer estado, Date fechaInicio, Date fechaFin, Integer idEmpresa, String actividadEconomica);

	Collection<Factura> findByClienteAndEmpresa(Cliente cliente, Empresa empresa);

	Collection<Object[]> proformasByState(Integer estado, Integer idEmpresa);

	ArrayList<DetalleFacturaCommand> formaDetallesCommand(FacturaCommand facturaCommand) throws Exception;

	Collection<Factura> findByEnvioCorreoSimplificado(Integer estadoFirma);

	ArrayList<String> listaCorreosAsociadosFactura(Factura factura);

	Collection<Factura> findBySinNotificarCorreo();

	ByteArrayInputStream createExcelFacturas(Collection<ListarFacturasNativa> facturas, Empresa empresa, String fechaInicio, String fechaFinal, Integer estado, Cliente cliente) throws IOException;

	ByteArrayInputStream createExcelFacturasTotalMensual(Collection<Factura> facturas, Empresa empresa, String fechaInicio, String fechaFinal, Integer estado, String actividadEconomica) throws IOException;

//	Map<String, Object> generarPDFIVAMensual(Date fechaInicial, Date fechaFinal, Integer estado, String actividadEconomica,Empresa empresa);
	RespuestaServiceDataTable<?> listarEsperaActivasAjax(HttpServletRequest request, HttpServletResponse response);
	
}