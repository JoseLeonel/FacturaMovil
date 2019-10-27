package com.emprendesoftcr.Dao;


import java.util.Collection;

import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.GraficoVenta;

public interface GraficoVentasDao {

	Collection<GraficoVenta> findByAllEmpresaAndAnno(Empresa empresa,Integer anno);
	
	Collection<GraficoVenta> findByAll();
	
	GraficoVenta findByEmpresaAndAnno(Empresa empresa,Integer mes,Integer anno);
	void actualizarGraficoVenta(Integer anno) throws Exception;

}