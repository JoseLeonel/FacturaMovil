package com.emprendesoftcr.Bo;

import com.emprendesoftcr.modelo.Caja;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Usuario;

public interface CajaBo {

	void agregar(Caja caja);

	void modificar(Caja caja);

	void eliminar(Caja caja);

	Caja buscar(Long id);
	
	Caja findByDescripcionAndEmpresa(String descripcion, Empresa empresa);

	Caja findByTerminalAndEmpresa(String terminal, Empresa empresa);
	Caja buscarCajaActiva(Empresa empresa,Usuario usuario); 

}