package com.emprendesoftcr.Bo;


import java.util.Collection;
import java.util.Date;

import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.GraficoVenta;

public interface GraficoVentasBo {
	
	Collection<GraficoVenta> findByAllEmpresaAndAnno(Empresa empresa,Integer anno);
	Collection<GraficoVenta> findByAll();
	void actualizarGraficoVenta(Integer anno,Empresa empresa) throws Exception ;
	GraficoVenta findByEmpresaAndAnno(Empresa empresa, Integer mes,Integer anno) throws Exception;
}
