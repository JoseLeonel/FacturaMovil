package com.emprendesoftcr.Bo.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.emprendesoftcr.Bo.FEMensajeReceptorAutomaticoBo;
import com.emprendesoftcr.Dao.FEMensajeReceptorAutomaticoDao;
import com.emprendesoftcr.modelo.FEMensajeReceptorAutomatico;

@EnableTransactionManagement
@Service("fEMensajeReceptorAutomaticoBo")
public class FEMensajeReceptorAutomaticoBoImpl1 implements FEMensajeReceptorAutomaticoBo {
	@Autowired
	private FEMensajeReceptorAutomaticoDao fEMensajeReceptorAutomaticoDao;
	@Transactional
	@Override
	public void agregar(FEMensajeReceptorAutomatico fEMensajeReceptorAutomatico) {
		fEMensajeReceptorAutomaticoDao.agregar(fEMensajeReceptorAutomatico);

	}
	@Transactional
	@Override
	public void modificar(FEMensajeReceptorAutomatico fEMensajeReceptorAutomatico) {
		fEMensajeReceptorAutomaticoDao.modificar(fEMensajeReceptorAutomatico);
	}
	@Transactional
	@Override
	public void eliminar(FEMensajeReceptorAutomatico fEMensajeReceptorAutomatico) {
		fEMensajeReceptorAutomaticoDao.eliminar(fEMensajeReceptorAutomatico);

	}

	@Override
	public FEMensajeReceptorAutomatico buscar(Long id) {
		// TODO Auto-generated method stub
		return fEMensajeReceptorAutomaticoDao.buscar(id);
	}

}
