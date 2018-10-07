package com.emprendesoftcr.Dao;

import com.emprendesoftcr.modelo.CuentaCobrar;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Factura;

public interface CuentaCobrarDao {

	void agregar(CuentaCobrar cuentaCobrar);

	void modificar(CuentaCobrar cuentaCobrar);

	void eliminar(CuentaCobrar cuentaCobrar);

	CuentaCobrar buscar(Long id);
	CuentaCobrar buscarPorConsecutivo(Empresa empresa,String consecutivo);
	void crearCuentaXCobrar(Factura factura);

}