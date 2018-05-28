package com.factura.FacturaElectronica.Dao;

import com.factura.FacturaElectronica.modelo.CuentaCobrar;

public interface CuentaCobrarDao {

	void agregar(CuentaCobrar cuentaCobrar);

	void modificar(CuentaCobrar cuentaCobrar);

	void eliminar(CuentaCobrar cuentaCobrar);

	CuentaCobrar buscar(Integer id);
	CuentaCobrar buscarPorLetraCambio(String letraCambio);
	CuentaCobrar buscarPorFacturaManual(Integer facturaManual);
}