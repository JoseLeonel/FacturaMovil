package com.emprendesoftcr.Bo.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.emprendesoftcr.Bo.ControlPagoBo;
import com.emprendesoftcr.Dao.ControlPagoDao;
import com.emprendesoftcr.modelo.Articulo;
import com.emprendesoftcr.modelo.ControlPago;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Usuario;

@EnableTransactionManagement
@Service("controlPagoBo")
public class ControlPagoBoImpl implements ControlPagoBo {
	@Autowired
	private ControlPagoDao controlPagoDao;
	
	@Transactional
	@Override
	public void agregar(ControlPago controlPago) {
		controlPagoDao.agregar(controlPago);

	}

	@Transactional
	@Override
	public void modificar(ControlPago controlPago) {
		controlPagoDao.modificar(controlPago);

	}

	@Transactional
	@Override
	public void eliminar(ControlPago controlPago) {
		controlPagoDao.eliminar(controlPago);

	}

	@Override
	public ControlPago buscar(Long id) {
		
		return controlPagoDao.buscar(id);
	}

	@Override
	public ControlPago findByEstadoAndEmpresa(Integer estado, Empresa empresa) {
		
		return controlPagoDao.findByEstadoAndEmpresa(estado, empresa);
	}

	

}
