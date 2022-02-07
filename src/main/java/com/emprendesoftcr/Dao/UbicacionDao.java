package com.emprendesoftcr.Dao;

import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Ubicacion;

public interface UbicacionDao {

	void agregar(Ubicacion ubicacion);

	void modificar(Ubicacion ubicacion);

	void eliminar(Ubicacion ubicacion);

	Ubicacion buscar(Long id);

	Ubicacion buscarPorDescripcionYEmpresa(String descripcion, Empresa empresa);
	
	
}