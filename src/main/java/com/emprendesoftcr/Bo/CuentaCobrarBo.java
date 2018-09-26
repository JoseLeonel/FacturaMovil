package com.emprendesoftcr.Bo;

import com.emprendesoftcr.modelo.CuentaCobrar;

public interface CuentaCobrarBo {

	void agregar(CuentaCobrar cuentaCobrar);

	void modificar(CuentaCobrar cuentaCobrar);

	void eliminar(CuentaCobrar cuentaCobrar);

	CuentaCobrar buscar(Long id);

	CuentaCobrar buscarPorLetraCambio(String letraCambio);

	CuentaCobrar buscarPorFacturaManual(Integer facturaManual);
}