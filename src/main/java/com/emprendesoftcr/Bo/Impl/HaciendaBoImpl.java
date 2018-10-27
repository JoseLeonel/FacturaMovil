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

@Service("haciendaBo")
@EnableTransactionManagement
public class HaciendaBoImpl implements HaciendaBo {


	@Autowired
	private HaciendaDao					haciendaDao;



	@Transactional
	@Override
	public void agregar(Hacienda hacienda) {
		haciendaDao.agregar(hacienda);

	}

	@Transactional
	@Override
	public void modificar(Hacienda hacienda) {
		haciendaDao.modificar(hacienda);

	}

	@Override
	public Hacienda findById(Long id) {

		return haciendaDao.findById(id);
	}

	@Override
	public Hacienda findByEmpresaAndClave(Empresa empresa,String clave) {
		return haciendaDao.findByEmpresaAndClave(empresa, clave);
	}
	

	@Override
	public Collection<Hacienda> findByEmpresaAndEstado(Empresa empresa, Integer estado) {

		return haciendaDao.findByEmpresaAndEstado(empresa, estado);
	}
	@Override
	public Collection<Hacienda> findByEstado( Integer estado,Integer estadoError) {

		return haciendaDao.findByEstado(estado,estadoError);
	}
	@Override
	
	public Collection<Hacienda> findByEstadoAndNotificacion(Integer estado, Integer notificacion){
		return haciendaDao.findByEstadoAndNotificacion(estado,notificacion);
	}
}
