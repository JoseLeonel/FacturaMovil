package com.emprendesoftcr.Dao;

import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Mesa;

public interface MesaDao {

	void agregar(Mesa mesa);

	void modificar(Mesa mesa);

	void eliminar(Mesa mesa);

	Mesa buscar(Long id);

	Mesa findByDescripcionAndEmpresa(String descripcion, Empresa empresa);

	

}