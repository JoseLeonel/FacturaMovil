package com.emprendesoftcr.Dao;

import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Semaforo;

public interface SemaforoDao {

	
	
	Semaforo findByEmpresa(Empresa empresa,Integer estado);
	
	

}