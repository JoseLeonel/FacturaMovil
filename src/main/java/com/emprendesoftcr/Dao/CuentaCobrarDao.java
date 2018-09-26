package com.emprendesoftcr.Dao;

import com.emprendesoftcr.modelo.CuentaCobrar;
import com.emprendesoftcr.modelo.Factura;

public interface CuentaCobrarDao {

	void agregar(CuentaCobrar cuentaCobrar);

	void modificar(CuentaCobrar cuentaCobrar);

	void eliminar(CuentaCobrar cuentaCobrar);

	CuentaCobrar buscar(Long id);
	CuentaCobrar buscarPorLetraCambio(String letraCambio);
	CuentaCobrar buscarPorFacturaManual(Integer facturaManual);
	
	void crearCuentaXCobrar(Factura factura);
}