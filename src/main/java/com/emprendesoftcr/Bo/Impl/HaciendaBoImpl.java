package com.emprendesoftcr.Bo.Impl;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.emprendesoftcr.Bo.HaciendaBo;
import com.emprendesoftcr.Dao.HaciendaDao;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Hacienda;
import com.emprendesoftcr.service.FacturaXMLServices;

@Transactional
@EnableTransactionManagement
@Service("haciendaBo")
public class HaciendaBoImpl implements HaciendaBo {

	private Logger			log	= LoggerFactory.getLogger(this.getClass());

	@Autowired
	HaciendaDao					haciendaDao;

	@Autowired
	FacturaXMLServices	facturaXMLServices;

	@Override
	public void agregar(Hacienda hacienda) {
		haciendaDao.agregar(hacienda);

	}

	@Override
	public void modificar(Hacienda hacienda) {
		haciendaDao.modificar(hacienda);

	}

	@Override
	public Hacienda findById(Integer id) {

		return haciendaDao.findById(id);
	}

	@Override
	public Collection<Hacienda> findByEmpresaAndEstado(Empresa empresa, Integer estado) {

		return haciendaDao.findByEmpresaAndEstado(empresa, estado);
	}

}
