package com.emprendesoftcr.Bo.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.emprendesoftcr.Bo.ActividadComercialBo;
import com.emprendesoftcr.Dao.ActividadComercialDao;
import com.emprendesoftcr.modelo.ActividadComercial;

@EnableTransactionManagement
@Service("actividadComercialBo")
public class ActividadComercialBoImpl implements ActividadComercialBo {

	@Autowired
	ActividadComercialDao actividadComercialDao;

	@Override
	@Transactional
	public void agregar(ActividadComercial actividadComercial) {
		actividadComercialDao.agregar(actividadComercial);
	}

	@Override
	@Transactional
	public void modificar(ActividadComercial actividadComercial) {
		actividadComercialDao.modificar(actividadComercial);
	}

	@Override
	@Transactional
	public void eliminar(ActividadComercial actividadComercial) {
		actividadComercialDao.eliminar(actividadComercial);
	}

	@Override
	public ActividadComercial buscar(Integer id) {
		return actividadComercialDao.buscar(id);
	}
	
	

}