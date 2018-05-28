package com.factura.FacturaElectronica.Bo;

import com.factura.FacturaElectronica.modelo.CuentaCobrar;

public interface CuentaCobrarBo {

	void agregar(CuentaCobrar cuentaCobrar);

	void modificar(CuentaCobrar cuentaCobrar);

	void eliminar(CuentaCobrar cuentaCobrar);

	CuentaCobrar buscar(Integer id);

	CuentaCobrar buscarPorLetraCambio(String letraCambio);

	CuentaCobrar buscarPorFacturaManual(Integer facturaManual);
}