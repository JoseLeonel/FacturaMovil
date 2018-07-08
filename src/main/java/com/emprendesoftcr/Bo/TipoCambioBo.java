package com.emprendesoftcr.Bo;

import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.TipoCambio;

public interface TipoCambioBo {

	void agregar(TipoCambio tipoCambio);

	void modificar(TipoCambio tipoCambio);

	void eliminar(TipoCambio tipoCambio);

	TipoCambio buscar(Integer id);
	TipoCambio findByEstadoAndEmpresa(String estado,Empresa empresa);
	
	Double conversionMoneda(Double total,TipoCambio tipoCambio);

}