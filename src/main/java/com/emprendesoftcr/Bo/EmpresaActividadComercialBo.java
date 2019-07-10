package com.emprendesoftcr.Bo;

import java.util.Collection;

import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.EmpresaActividadComercial;

public interface EmpresaActividadComercialBo {

	void agregar(EmpresaActividadComercial empresaActividadComercial);

	void modificar(EmpresaActividadComercial empresaActividadComercial);

	void eliminar(EmpresaActividadComercial empresaActividadComercial);

	EmpresaActividadComercial buscar(Integer id);

	EmpresaActividadComercial findByCodigo(String codigo,Empresa empresa);

	EmpresaActividadComercial findByPrincipal(Integer principal,Empresa empresa);
	
	Collection<EmpresaActividadComercial> findAll(Empresa empresa );

}