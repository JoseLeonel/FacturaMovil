package com.emprendesoftcr.Dao;

import com.emprendesoftcr.modelo.CuentaPagar;

public interface CuentaPagarDao {

	void agregar(CuentaPagar cuentaPagar);

	void modificar(CuentaPagar cuentaPagar);

	void eliminar(CuentaPagar cuentaCobrar);

	CuentaPagar buscar(Long id);
	
	
	

}