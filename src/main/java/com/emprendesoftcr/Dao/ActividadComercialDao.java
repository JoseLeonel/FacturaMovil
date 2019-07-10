package com.emprendesoftcr.Dao;

import com.emprendesoftcr.modelo.ActividadComercial;

public interface ActividadComercialDao {

	void agregar(ActividadComercial actividadComercial);

	void modificar(ActividadComercial actividadComercial);

	void eliminar(ActividadComercial actividadComercial);

	ActividadComercial buscar(Integer id);

	

}