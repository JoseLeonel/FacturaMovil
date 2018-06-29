package com.factura.FacturaElectronica.Dao;

import com.factura.FacturaElectronica.modelo.Empresa;
import com.factura.FacturaElectronica.modelo.TipoCambio;

public interface TipoCambioDao {

	void agregar(TipoCambio tipoCambio);

	void modificar(TipoCambio tipoCambio);

	void eliminar(TipoCambio tipoCambio);

	TipoCambio buscar(Integer id);
	TipoCambio findByEstadoAndEmpresa(String estado,Empresa empresa);

}