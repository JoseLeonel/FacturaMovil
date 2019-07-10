package com.emprendesoftcr.Dao;

import java.util.Collection;
import java.util.Date;

import com.emprendesoftcr.modelo.Detalle;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.EmpresaActividadComercial;

public interface EmpresaActividadComercialDao {

	void agregar(EmpresaActividadComercial empresaActividadComercial);

	void modificar(EmpresaActividadComercial empresaActividadComercial);

	void eliminar(EmpresaActividadComercial empresaActividadComercial);

	EmpresaActividadComercial buscar(Integer id);
	
	EmpresaActividadComercial findByCodigo(String codigo,Empresa empresa);

	EmpresaActividadComercial findByPrincipal(Integer principal,Empresa empresa);

	Collection<EmpresaActividadComercial> findAll(Empresa empresa );

}