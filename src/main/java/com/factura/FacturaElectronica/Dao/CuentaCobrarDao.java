package com.factura.FacturaElectronica.Dao;

import com.factura.FacturaElectronica.modelo.CuentaCobrar;
import com.factura.FacturaElectronica.modelo.Factura;

public interface CuentaCobrarDao {

	void agregar(CuentaCobrar cuentaCobrar);

	void modificar(CuentaCobrar cuentaCobrar);

	void eliminar(CuentaCobrar cuentaCobrar);

	CuentaCobrar buscar(Integer id);
	CuentaCobrar buscarPorLetraCambio(String letraCambio);
	CuentaCobrar buscarPorFacturaManual(Integer facturaManual);
	
	void crearCuentaXCobrar(Factura factura);
}