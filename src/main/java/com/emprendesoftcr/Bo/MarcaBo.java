package com.emprendesoftcr.Bo;

import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Marca;

public interface MarcaBo {

	void agregar(Marca marca);

	void modificar(Marca marca);

	void eliminar(Marca marca);

	Marca buscar(Integer id);

	Marca buscarPorDescripcionYEmpresa(String descripcion, Empresa empresa);

}