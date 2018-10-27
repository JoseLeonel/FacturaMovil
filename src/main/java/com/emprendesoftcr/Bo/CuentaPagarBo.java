package com.emprendesoftcr.Bo;

import com.emprendesoftcr.modelo.CuentaPagar;

public interface CuentaPagarBo {

	void agregar(CuentaPagar cuentaPagar);

	void modificar(CuentaPagar cuentaPagar);

	void eliminar(CuentaPagar cuentaPagar);

	CuentaPagar buscar(Long id);

}