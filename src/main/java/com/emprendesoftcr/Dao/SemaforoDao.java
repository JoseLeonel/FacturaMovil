package com.emprendesoftcr.Dao;

import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Semaforo;

public interface SemaforoDao {

	void modificar(Semaforo semaforo);

	Semaforo findByEmpresa(Empresa empresa, Integer estado);

	Semaforo findByEstado(Integer estado);
	Semaforo findByEstadoAndID(Integer estado,Integer id);

}