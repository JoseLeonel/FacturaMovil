package com.emprendesoftcr.Bo.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.emprendesoftcr.Bo.TarifaIVAIBo;
import com.emprendesoftcr.Dao.TarifaIVAIDao;
import com.emprendesoftcr.modelo.TarifaIVAI;

/**
 * Marcas asociadas al articulo MarcaBoImpl.
 * @author jose.
 * @since 17 mar. 2018
 */
@EnableTransactionManagement
@Service("tarifaIVAIBo")
public class TarifaIVAIBoImpl implements TarifaIVAIBo {

	@Autowired
	TarifaIVAIDao tarifaIVAIDao;

	@Override
	@Transactional
	public void agregar(TarifaIVAI tarifaIVAI) {
		tarifaIVAIDao.agregar(tarifaIVAI);
	}

	@Override
	@Transactional
	public void modificar(TarifaIVAI tarifaIVAI) {
		tarifaIVAIDao.modificar(tarifaIVAI);
	}

	@Override
	@Transactional
	public void eliminar(TarifaIVAI tarifaIVAI) {
		tarifaIVAIDao.eliminar(tarifaIVAI);
	}

	@Override
	public TarifaIVAI buscar(Integer id) {
		return tarifaIVAIDao.buscar(id);
	}
	@Override
	public TarifaIVAI findByCodigoTarifa(String codigoTarifa) {
		return tarifaIVAIDao.findByCodigoTarifa(codigoTarifa);
	}
}