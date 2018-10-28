package com.emprendesoftcr.Bo;

import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Semaforo;

public interface SemaforoBo {

	Semaforo findByEmpresa(Empresa empresa,Integer estado);
	
	Semaforo findByEstado(Integer estado);
	
	void modificar(Semaforo semaforo);
}
