package com.emprendesoftcr.Bo;

import java.util.Collection;

import com.emprendesoftcr.modelo.Cabys;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.web.command.ListCabysHacienda;

public interface CabysBo {

	void agregar(Cabys cabys);

	void modificar(Cabys cabys);

	void eliminar(Cabys cabys);

	Cabys buscar(Long id);

	Cabys buscarPorDescripcionYEmpresa(String descripcion, Empresa empresa);
	
	Collection<Cabys> findByEmpresaAll(Integer idEmpresa);
	
	
	
	Cabys findByCodigo(String codigo, Empresa empresa);

}