package com.emprendesoftcr.Dao;

import java.util.Collection;
import java.util.Date;

import com.emprendesoftcr.modelo.Cliente;
import com.emprendesoftcr.modelo.CuentaCobrar;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Factura;
import com.emprendesoftcr.web.command.TotalCuentaPorCobrarCommand;

public interface CuentaCobrarDao {

	void agregar(CuentaCobrar cuentaCobrar);

	void modificar(CuentaCobrar cuentaCobrar);

	void eliminar(CuentaCobrar cuentaCobrar);

	CuentaCobrar buscar(Long id);

	CuentaCobrar buscarPorConsecutivo(Empresa empresa, String consecutivo);

	void crearCuentaXCobrar(Factura factura);

	TotalCuentaPorCobrarCommand sumarCuentasPorCobrar(Date fechaInicio, Date fechaFinal, Integer idEmpresa, Cliente cliente);
	
	Collection<Factura> cuentasPorCobrarbyFechasAndEmpresaAndClienteAndEstado(Date fechaInicio, Date fechaFin,Empresa empresa,Cliente cliente,String estado);

}