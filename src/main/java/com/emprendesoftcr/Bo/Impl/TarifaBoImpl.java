package com.emprendesoftcr.Bo.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.emprendesoftcr.Bo.TarifaBo;
import com.emprendesoftcr.Dao.TarifaDao;
import com.emprendesoftcr.modelo.Tarifa;

/**
 * Marcas asociadas al articulo MarcaBoImpl.
 * @author jose.
 * @since 17 mar. 2018
 */
@EnableTransactionManagement
@Service("tarifaBo")
public class TarifaBoImpl implements TarifaBo {

	@Autowired
	TarifaDao tarifaDao;

	@Override
	@Transactional
	public void agregar(Tarifa tarifa) {
		tarifaDao.agregar(tarifa);
	}

	@Override
	@Transactional
	public void modificar(Tarifa tarifa) {
		tarifaDao.modificar(tarifa);
	}

	@Override
	@Transactional
	public void eliminar(Tarifa tarifa) {
		tarifaDao.eliminar(tarifa);
	}

	@Override
	public Tarifa buscar(Long id) {
		return tarifaDao.buscar(id);
	}

}