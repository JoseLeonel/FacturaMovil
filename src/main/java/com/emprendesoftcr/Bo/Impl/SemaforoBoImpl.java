package com.emprendesoftcr.Bo.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.emprendesoftcr.Bo.SemaforoBo;
import com.emprendesoftcr.Dao.SemaforoDao;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Semaforo;

@Transactional
@EnableTransactionManagement
@Service("semaforoBo")
public class SemaforoBoImpl implements SemaforoBo {
	
	@Autowired
	SemaforoDao semaforoDao;

	@Override
	public Semaforo findByEmpresa(Empresa empresa,Integer estado) {
		
		return semaforoDao.findByEmpresa(empresa,estado);
	}
	
	@Override
	public Semaforo findByEstado(Integer estado) {
		return semaforoDao.findByEstado(estado);
	}
	
	@Override
	public void modificar(Semaforo semaforo) {
		semaforoDao.modificar(semaforo);
	}

}
