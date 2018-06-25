package com.factura.FacturaElectronica.Bo;

import com.factura.FacturaElectronica.modelo.TipoCambio;

public interface TipoCambioBo {

	void agregar(TipoCambio tipoCambio);

	void modificar(TipoCambio tipoCambio);

	void eliminar(TipoCambio tipoCambio);

	TipoCambio buscar(Integer id);

}