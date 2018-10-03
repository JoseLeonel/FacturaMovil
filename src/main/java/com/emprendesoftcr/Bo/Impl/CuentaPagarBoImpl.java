package com.emprendesoftcr.Bo.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.emprendesoftcr.Bo.CuentaPagarBo;
import com.emprendesoftcr.Dao.CuentaPagarDao;
import com.emprendesoftcr.modelo.CuentaCobrar;
import com.emprendesoftcr.modelo.CuentaPagar;

/**
 * cuentas por cobrar que se debe cobrar a los clientes CuentaCobrarBoImpl.
 * @author jose.
 * @since 25 mar. 2018
 */
@Transactional
@EnableTransactionManagement
@Service("cuentaPagarBo")
public class CuentaPagarBoImpl implements CuentaPagarBo {

	@Autowired
	private CuentaPagarDao cuentaPagarDao;


	public void agregar(CuentaPagar cuentaPagar) {
		cuentaPagarDao.agregar(cuentaPagar);
	}

	public void modificar(CuentaPagar cuentaPagar) {
		cuentaPagarDao.modificar(cuentaPagar);
	}

	public void eliminar(CuentaPagar cuentaPagar) {
		cuentaPagarDao.eliminar(cuentaPagar);
	}

	/**
	 * Busca por Id
	 * @see com.factura.bo.CuentaCobrarBo#buscar(java.lang.Integer)
	 */
	@Override
	public CuentaPagar buscar(Long id) {
		return cuentaPagarDao.buscar(id);
	}

	

}