package com.emprendesoftcr.Dao;

import java.util.Collection;

import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Hacienda;

public interface HaciendaDao {

	void agregar(Hacienda hacienda);

	void modificar(Hacienda hacienda);

	

	Hacienda findById(Integer id);	
	
	Collection<Hacienda> findByEmpresaAndEstado(Empresa empresa,Integer estado);

}