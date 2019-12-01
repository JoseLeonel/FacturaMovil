package com.emprendesoftcr.Bo.Impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.emprendesoftcr.Bo.ControlPagoBo;
import com.emprendesoftcr.modelo.ControlPago;
import com.emprendesoftcr.modelo.Empresa;

@EnableTransactionManagement
@Service("controlPagoBo")
public class ControlPagoBoImpl implements ControlPagoBo {

	@Override
	public void agregar(ControlPago controlPago) {
		// TODO Auto-generated method stub

	}

	@Override
	public void modificar(ControlPago controlPago) {
		// TODO Auto-generated method stub

	}

	@Override
	public void eliminar(ControlPago controlPago) {
		// TODO Auto-generated method stub

	}

	@Override
	public ControlPago buscar(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ControlPago findByEstadoAndEmpresa(Integer estado, Empresa empresa) {
		// TODO Auto-generated method stub
		return null;
	}

}
