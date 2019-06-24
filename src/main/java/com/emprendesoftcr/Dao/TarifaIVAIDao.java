package com.emprendesoftcr.Dao;

import com.emprendesoftcr.modelo.TarifaIVAI;

public interface TarifaIVAIDao {

	void agregar(TarifaIVAI tarifaIVAI);

	void modificar(TarifaIVAI tarifaIVAI);

	void eliminar(TarifaIVAI tarifaIVAI);

	TarifaIVAI buscar(Integer id);

	

}