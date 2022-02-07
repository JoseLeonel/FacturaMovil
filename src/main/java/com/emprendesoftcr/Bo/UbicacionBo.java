package com.emprendesoftcr.Bo;

import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Ubicacion;

public interface UbicacionBo {

	void agregar(Ubicacion ubicacion);

	void modificar(Ubicacion ubicacion);

	void eliminar(Ubicacion ubicacion);

	Ubicacion buscar(Long id);

	Ubicacion buscarPorDescripcionYEmpresa(String descripcion, Empresa empresa);

}