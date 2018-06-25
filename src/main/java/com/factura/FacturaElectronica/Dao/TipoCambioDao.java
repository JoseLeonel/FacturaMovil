package com.factura.FacturaElectronica.Dao;

import com.factura.FacturaElectronica.modelo.TipoCambio;

public interface TipoCambioDao {

	void agregar(TipoCambio tipoCambio);

	void modificar(TipoCambio tipoCambio);

	void eliminar(TipoCambio tipoCambio);

	TipoCambio buscar(Integer id);

}