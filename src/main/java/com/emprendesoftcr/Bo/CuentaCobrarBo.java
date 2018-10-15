package com.emprendesoftcr.Bo;

import java.util.Date;

import com.emprendesoftcr.modelo.Cliente;
import com.emprendesoftcr.modelo.CuentaCobrar;
import com.emprendesoftcr.web.command.TotalCuentaPorCobrarCommand;

public interface CuentaCobrarBo {

	void agregar(CuentaCobrar cuentaCobrar);

	void modificar(CuentaCobrar cuentaCobrar);

	void eliminar(CuentaCobrar cuentaCobrar);

	CuentaCobrar buscar(Long id);
	
	TotalCuentaPorCobrarCommand sumarCuentasPorCobrar(Date fechaInicio, Date fechaFinal, Integer idEmpresa, Cliente cliente);


}