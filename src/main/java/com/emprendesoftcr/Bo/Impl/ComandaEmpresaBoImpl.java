package com.emprendesoftcr.Bo.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.emprendesoftcr.Bo.ComandaEmpresaBo;
import com.emprendesoftcr.Dao.ComandaEmpresaDao;
import com.emprendesoftcr.modelo.ComandaEmpresa;

@EnableTransactionManagement
@Service("comandaEmpresaBo")
public class ComandaEmpresaBoImpl implements ComandaEmpresaBo {

	@Autowired
	ComandaEmpresaDao comandaEmpresaDao;

	@Transactional
	@Override
	public void agregar(ComandaEmpresa comandaEmpresa) {
		comandaEmpresaDao.agregar(comandaEmpresa);
	}

	@Transactional
	@Override
	public void modificar(ComandaEmpresa comandaEmpresa) {
		comandaEmpresaDao.modificar(comandaEmpresa);
	}

	/**
	 * Busca por Id
	 * @see com.factura.bo.CuentaCobrarBo#buscar(java.lang.Integer)
	 */
	@Override
	public ComandaEmpresa buscar(Long id) {
		return comandaEmpresaDao.buscar(id);
	}

}