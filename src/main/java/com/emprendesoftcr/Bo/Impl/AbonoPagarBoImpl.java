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
 * Abonos aplicados en las cuentas por cobrar a los clientes AbonoBoImpl.
 * @author jose.
 * @since 19 abr. 2018
 */
@EnableTransactionManagement
@Service("abonoPagarBo")
public class AbonoPagarBoImpl implements AbonoPagarBo {

	@Autowired
	AbonoPagarDao abonoPagarDao;

	@Transactional
	@Override
	public void agregar(AbonoPagar abono) {
		abonoPagarDao.agregar(abono);
	}

	@Transactional
  @Override
	public void modificar(AbonoPagar abono) {
		abonoPagarDao.modificar(abono);
	}

	@Override
	@Transactional
	public void eliminar(AbonoPagar abono) {
		abonoPagarDao.eliminar(abono);
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