package com.emprendesoftcr.Bo;

import com.emprendesoftcr.modelo.ComandaEmpresa;

public interface ComandaEmpresaBo {

	void agregar(ComandaEmpresa comandaMesa);

	void modificar(ComandaEmpresa comandaMesa);
	
	ComandaEmpresa buscar(Long id);

}