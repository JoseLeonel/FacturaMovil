package com.emprendesoftcr.Bo.Impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.emprendesoftcr.Bo.GraficoVentasBo;
import com.emprendesoftcr.Dao.GraficoVentasDao;
import com.emprendesoftcr.modelo.GraficoVenta;


@EnableTransactionManagement
@Service("graficoVentasBo")
public class GraficoVentasBoImpl implements GraficoVentasBo {
	@Autowired
	private GraficoVentasDao						graficoVentasDao;
	
	@Override
	public Collection<GraficoVenta> findByAll() {
		// TODO Auto-generated method stub
		return graficoVentasDao.findByAll();
	}

	

}