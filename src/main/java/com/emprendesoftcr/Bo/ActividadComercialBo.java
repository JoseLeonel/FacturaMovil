package com.emprendesoftcr.Bo;

import com.emprendesoftcr.modelo.ActividadComercial;

public interface ActividadComercialBo {

	void agregar(ActividadComercial actividadComercial);

	void modificar(ActividadComercial actividadComercial);

	void eliminar(ActividadComercial actividadComercial);

	ActividadComercial buscar(Integer id);

	

}