package com.emprendesoftcr.Bo.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.emprendesoftcr.Bo.MesaBo;
import com.emprendesoftcr.Dao.MesaDao;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Mesa;

@EnableTransactionManagement
@Service("mesaBo")
public class MesaBoImpl implements MesaBo {

	@Autowired
	MesaDao mesaDao;

	@Transactional
	@Override
	public void agregar(Mesa mesa) {
		mesaDao.agregar(mesa);
	}

	@Transactional
	@Override
	public void modificar(Mesa mesa) {
		mesaDao.modificar(mesa);

	}

	@Transactional
	@Override
	public void eliminar(Mesa mesa) {
		mesaDao.eliminar(mesa);
	}

	@Override
	public Mesa buscar(Long id) {
		return mesaDao.buscar(id);
	}

	@Override
	public Mesa findByDescripcionAndEmpresa(String descripcion, Empresa empresa) {
		return mesaDao.findByDescripcionAndEmpresa(descripcion, empresa);
	}

}
