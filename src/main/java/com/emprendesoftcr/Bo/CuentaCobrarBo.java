package com.emprendesoftcr.Bo;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;

import com.emprendesoftcr.modelo.Cliente;
import com.emprendesoftcr.modelo.CuentaCobrar;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Factura;
import com.emprendesoftcr.web.command.TotalCuentaPorCobrarCommand;

public interface CuentaCobrarBo {

	void agregar(CuentaCobrar cuentaCobrar);

	void modificar(CuentaCobrar cuentaCobrar);

	void eliminar(CuentaCobrar cuentaCobrar);

	CuentaCobrar buscar(Long id);
	
	TotalCuentaPorCobrarCommand sumarCuentasPorCobrar(Date fechaInicio, Date fechaFinal, Integer idEmpresa, Cliente cliente);
	
	Collection<CuentaCobrar> cuentasPorCobrarbyFechasAndEmpresaAndClienteAndEstado(Date fechaInicio, Date fechaFin,Empresa empresa,Cliente cliente,String estado);	
	
	Collection<CuentaCobrar> cuentasPorCobrarbyFechasAndEmpresaAndClienteAndEstado(Empresa empresa,Cliente cliente,String estado);
	
	Collection<CuentaCobrar> cuentasPorCobrarbyEstado( String estado);
	void modificarCuentaXCobrarPorNotaCredito(Factura notaCredito,Factura facturaAplicar);
	void modificarCuentaXCobrarPorNotaDebito(Factura notaCredito, Factura facturaAplicar);
	
	ByteArrayInputStream createExcelCuentaCobrar(Collection<CuentaCobrar> cuentaCobrar,Empresa empresa, String fechaInicio,String fechaFinal,String estado, Cliente cliente)throws IOException;
}