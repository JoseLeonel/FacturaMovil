package com.emprendesoftcr.Bo;

import java.util.Collection;
import java.util.Date;

import com.emprendesoftcr.modelo.CuentaPagar;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Proveedor;

public interface CuentaPagarBo {

	void agregar(CuentaPagar cuentaPagar);

	void modificar(CuentaPagar cuentaPagar);

	void eliminar(CuentaPagar cuentaPagar);

	CuentaPagar buscar(Long id);
	Collection<CuentaPagar> cuentasPorPagarbyFechasAndEmpresaAndClienteAndEstado(Date fechaInicio, Date fechaFin, Empresa empresa, Proveedor proveedor, String estado);
	Collection<CuentaPagar> cuentasPorPagarbyEmpresaAndClienteAndEstado( Empresa empresa, Proveedor proveedor, String estado);

}