package com.emprendesoftcr.Dao;

import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.TipoCambio;

public interface TipoCambioDao {

	void agregar(TipoCambio tipoCambio);

	void modificar(TipoCambio tipoCambio);

	void eliminar(TipoCambio tipoCambio);

	TipoCambio buscar(Long id);
	TipoCambio findByEstadoAndEmpresa(String estado,Empresa empresa);

}