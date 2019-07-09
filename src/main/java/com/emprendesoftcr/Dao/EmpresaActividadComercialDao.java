package com.emprendesoftcr.Dao;

import com.emprendesoftcr.modelo.EmpresaActividadComercial;

public interface EmpresaActividadComercialDao {

	void agregar(EmpresaActividadComercial empresaActividadComercial);

	void modificar(EmpresaActividadComercial empresaActividadComercial);

	void eliminar(EmpresaActividadComercial empresaActividadComercial);

	EmpresaActividadComercial buscar(Integer id);

	

}