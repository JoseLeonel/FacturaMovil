package com.emprendesoftcr.Bo;

import com.emprendesoftcr.modelo.EmpresaActividadComercial;

public interface EmpresaActividadComercialBo {

	void agregar(EmpresaActividadComercial empresaActividadComercial);

	void modificar(EmpresaActividadComercial empresaActividadComercial);

	void eliminar(EmpresaActividadComercial empresaActividadComercial);

	EmpresaActividadComercial buscar(Integer id);

}