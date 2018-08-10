package com.emprendesoftcr.Bo.Impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.emprendesoftcr.Bo.AbonoBo;
import com.emprendesoftcr.Dao.AbonoDao;
import com.emprendesoftcr.modelo.Abono;

/**
 * Abonos aplicados en las cuentas por cobrar a los clientes AbonoBoImpl.
 * @author jose.
 * @since 19 abr. 2018
 */
@Transactional
@EnableTransactionManagement
@Service("abonoBo")
public class AbonoBoImpl implements AbonoBo {

	@Autowired
	AbonoDao abonoDao;

	public void agregar(Abono abono) {
		abonoDao.agregar(abono);
	}

	public void modificar(Abono abono) {
		abonoDao.modificar(abono);
	}

	public void eliminar(Abono abono) {
		abonoDao.eliminar(abono);
	}

	/**
	 * Busca por Id
	 * @see com.factura.bo.CuentaCobrarBo#buscar(java.lang.Integer)
	 */
	@Override
	public Abono buscar(Long id) {
		return abonoDao.buscar(id);
	}

	/**
	 * Listar los abonos por cuenta por cobrar
	 * @see com.factura.bo.AbonoBo#listarAbonosByCuentaCobrar()
	 */
	@Override
	public Collection<Abono> buscarPorCuentaCobrar() {
		return abonoDao.buscarPorCuentaCobrar();
	}

}