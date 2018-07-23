package com.emprendesoftcr.Bo.Impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.emprendesoftcr.Bo.HaciendaBo;
import com.emprendesoftcr.Dao.HaciendaDao;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Hacienda;
import com.emprendesoftcr.service.FacturaXMLServices;

@Lazy
@Transactional
@EnableTransactionManagement
@Service("haciendaBo")
public class HaciendaBoImpl implements HaciendaBo {


	@Lazy
	@Autowired
	HaciendaDao					haciendaDao;

	@Lazy
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

	@Override
	public Collection<Hacienda> findByEstado( Integer estado,Integer estadoError) {

		return haciendaDao.findByEstado(estado,estadoError);
	}
}
