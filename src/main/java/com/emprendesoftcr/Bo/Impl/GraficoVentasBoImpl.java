package com.emprendesoftcr.Bo.Impl;


import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.emprendesoftcr.Bo.GraficoVentasBo;
import com.emprendesoftcr.Dao.GraficoVentasDao;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.GraficoVenta;


@EnableTransactionManagement
@Service("graficoVentasBo")
public class GraficoVentasBoImpl implements GraficoVentasBo {
	@Autowired
	private GraficoVentasDao						graficoVentasDao;
	
	@Override
	public Collection<GraficoVenta> findByAll() {
		
		return graficoVentasDao.findByAll();
	}

	@Override
	public Collection<GraficoVenta> findByAllEmpresaAndAnno(Empresa empresa, Integer anno) {
		
		return graficoVentasDao.findByAllEmpresaAndAnno(empresa, anno);
	}

	@Override
	public void actualizarGraficoVenta(Integer anno,Integer mes) throws Exception {
		graficoVentasDao.actualizarGraficoVenta(anno,mes);
		
	}

	@Override
	public GraficoVenta findByEmpresaAndAnno(Empresa empresa, Integer mes, Integer anno) throws Exception  {

		return graficoVentasDao.findByEmpresaAndAnno(empresa, mes, anno);
	}

	

}