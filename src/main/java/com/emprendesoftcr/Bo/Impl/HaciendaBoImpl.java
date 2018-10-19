package com.emprendesoftcr.Bo.Impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.emprendesoftcr.Bo.HaciendaBo;
import com.emprendesoftcr.Dao.HaciendaDao;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Hacienda;

@Transactional
@EnableTransactionManagement
@Service("haciendaBo")
public class HaciendaBoImpl implements HaciendaBo {


	@Autowired
	private HaciendaDao					haciendaDao;



	@Override
	public void agregar(Hacienda hacienda) {
		haciendaDao.agregar(hacienda);

	}

	@Override
	public void modificar(Hacienda hacienda) {
		haciendaDao.modificar(hacienda);

	}

	@Transactional(readOnly = true)
	@Override
	public Hacienda findById(Long id) {

		return haciendaDao.findById(id);
	}

	@Override
	public Hacienda findByEmpresaAndClave(Empresa empresa,String clave) {
		return haciendaDao.findByEmpresaAndClave(empresa, clave);
	}
	
	@Transactional(readOnly = true)
	@Override
	public Collection<Hacienda> findByEmpresaAndEstado(Empresa empresa, Integer estado) {

		return haciendaDao.findByEmpresaAndEstado(empresa, estado);
	}
	@Transactional(readOnly = true)
	@Override
	public Collection<Hacienda> findByEstado( Integer estado,Integer estadoError) {

		return haciendaDao.findByEstado(estado,estadoError);
	}
	@Transactional(readOnly = true)
	@Override
	public Collection<Hacienda> findByEstadoAndNotificacion(Integer estado, Integer notificacion){
		return haciendaDao.findByEstadoAndNotificacion(estado,notificacion);
	}
}
