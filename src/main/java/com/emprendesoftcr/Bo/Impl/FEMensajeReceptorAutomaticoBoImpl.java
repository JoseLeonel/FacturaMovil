package com.emprendesoftcr.Bo.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emprendesoftcr.Bo.IFEMensajeReceptorAutomaticoBo;
import com.emprendesoftcr.modelo.FEMensajeReceptorAutomatico;
import com.emprendesoftcr.repository.IFEMensajeReceptorAutomaticoRepository;


@Service("ifEMensajeReceptorAutomaticoBo")
public class FEMensajeReceptorAutomaticoBoImpl implements IFEMensajeReceptorAutomaticoBo {
	
	@Autowired
	private IFEMensajeReceptorAutomaticoRepository ifEMensajeReceptorAutomaticoRepository;

	@Override
	public void save(FEMensajeReceptorAutomatico entity) {
		ifEMensajeReceptorAutomaticoRepository.save(entity);
	}

	@Override
	public FEMensajeReceptorAutomatico findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FEMensajeReceptorAutomatico> getAll(String estado, String identifiacionCliente) {
		return ifEMensajeReceptorAutomaticoRepository.getAll(estado, identifiacionCliente);
	}

	@Override
	@Transactional
	public void updateEstado(String estado, Long id) {
		ifEMensajeReceptorAutomaticoRepository.updateEstado(estado, id);
	}

	@Transactional
	@Override
	public void updateEstadoPorIdentificion(String estado, String cedulaEmisor) {
		ifEMensajeReceptorAutomaticoRepository.updateEstadoPorIdentificion(estado, cedulaEmisor);
		
	}
	
	

}
