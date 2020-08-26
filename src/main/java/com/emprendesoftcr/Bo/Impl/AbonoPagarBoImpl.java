package com.emprendesoftcr.Bo.Impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.emprendesoftcr.Bo.AbonoPagarBo;
import com.emprendesoftcr.Dao.AbonoPagarDao;
import com.emprendesoftcr.modelo.AbonoPagar;

/**
 * Abonos pagados a proveedores AbonoPagarBoImpl.
 * @author jose.
 * @since 3 nov. 2018
 */

@EnableTransactionManagement
@Service("abonoPagarBo")
public class AbonoPagarBoImpl implements AbonoPagarBo {

	@Autowired(required=true)
	AbonoPagarDao abonoPagarDao;

	@Transactional
	@Override
	public void agregar(AbonoPagar abonoPagar) {
		abonoPagarDao.agregar(abonoPagar);
	}

	@Transactional
	@Override
	public void modificar(AbonoPagar abonoPagar) {
		abonoPagarDao.modificar(abonoPagar);
	}

	@Override
	@Transactional
	public void eliminar(AbonoPagar abonoPagar) {
		abonoPagarDao.eliminar(abonoPagar);
	}

	@Transactional
	@Override
	public AbonoPagar buscar(Long id) {
		return abonoPagarDao.buscar(id);
	}

	@Override
	public Collection<AbonoPagar> buscarPorCuentaPagar() {
		return abonoPagarDao.buscarPorCuentaPagar();
	}

}