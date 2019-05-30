package com.emprendesoftcr.Bo;

import java.util.Collection;

import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Hacienda;

public interface HaciendaBo {

	void agregar(Hacienda hacienda);

	void modificar(Hacienda hacienda);

	Hacienda findById(Long id);
	Hacienda findByEmpresaAndClave(Empresa empresa,String clave);
	Hacienda findByClave(String clave);
	void findByClaveSP(String clave, Integer estado,String xml,String mensajeHacienda) throws Exception;

	Collection<Hacienda> findByEmpresaAndEstado(Empresa empresa, Integer estado);

	Collection<Hacienda> findByEstadoAndNotificacion(Integer estado, Integer notificacion);

	Collection<Hacienda> findByEstado(Integer estado, Integer estadoError);

	Collection<Hacienda>  findByEstadoOrEstadoErrorAndEmpresa(Empresa empresa ,Integer estado,Integer estadoError);
	
	
}
