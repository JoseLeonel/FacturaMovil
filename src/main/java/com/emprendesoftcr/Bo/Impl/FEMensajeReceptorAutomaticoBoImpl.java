package com.emprendesoftcr.Bo.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.emprendesoftcr.Bo.IFEMensajeReceptorAutomaticoBo;
import com.emprendesoftcr.Dao.IFEMensajeReceptorAutomaticoDao;
import com.emprendesoftcr.modelo.FEMensajeReceptorAutomatico;

@EnableTransactionManagement
@Service("IFEMensajeReceptorAutomaticoBo")
public class FEMensajeReceptorAutomaticoBoImpl implements IFEMensajeReceptorAutomaticoBo {
	
	@Autowired
	private IFEMensajeReceptorAutomaticoDao _dao;

	@Override
	public void save(FEMensajeReceptorAutomatico entity) {
		_dao.save(entity);
	}

	@Override
	public FEMensajeReceptorAutomatico findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FEMensajeReceptorAutomatico> getAll(String estado, String identifiacionCliente) {
		return _dao.getAll(estado, identifiacionCliente);
	}

	@Override
	@Transactional
	public void updateEstado(String estado, Long id) {
		_dao.updateEstado(estado, id);
	}

}
