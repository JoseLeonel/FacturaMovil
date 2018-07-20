package com.emprendesoftcr.Bo;

import java.util.Collection;

import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Hacienda;

public interface HaciendaBo {

	void agregar(Hacienda hacienda);

	void modificar(Hacienda hacienda);

	Hacienda findById(Integer id);

	Collection<Hacienda> findByEmpresaAndEstado(Empresa empresa, Integer estado);
	
	Collection<Hacienda> findByEstado( Integer estado,Integer estadoError);

}
