package com.emprendesoftcr.Bo;

import com.emprendesoftcr.modelo.TarifaIVAI;

public interface TarifaIVAIBo {

	void agregar(TarifaIVAI tarifaIVAI);

	void modificar(TarifaIVAI tarifaIVAI);

	void eliminar(TarifaIVAI tarifaIVAI);

	TarifaIVAI buscar(Integer id);
	
	TarifaIVAI findByCodigoTarifa(String codigoTarifa);

	

}