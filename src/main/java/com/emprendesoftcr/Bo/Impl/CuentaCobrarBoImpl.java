package com.emprendesoftcr.Bo.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.emprendesoftcr.Bo.CuentaCobrarBo;
import com.emprendesoftcr.Dao.CuentaCobrarDao;
import com.emprendesoftcr.modelo.CuentaCobrar;

/**
 * cuentas por cobrar que se debe cobrar a los clientes
 * CuentaCobrarBoImpl.
 * @author jose.
 * @since 25 mar. 2018
 */
@Lazy
@Transactional
@EnableTransactionManagement
@Service("cuentaCobrarBo")
public class CuentaCobrarBoImpl implements CuentaCobrarBo {

	@Lazy
	@Autowired
	CuentaCobrarDao cuentaCobrarDao;

	public void agregar(CuentaCobrar cuentaCobrar) {
		cuentaCobrarDao.agregar(cuentaCobrar);
	}

	public void modificar(CuentaCobrar cuentaCobrar) {
		cuentaCobrarDao.modificar(cuentaCobrar);
	}

	public void eliminar(CuentaCobrar cuentaCobrar) {
		cuentaCobrarDao.eliminar(cuentaCobrar);
	}

	/**
	 * Busca por Id
	 * @see com.factura.bo.CuentaCobrarBo#buscar(java.lang.Integer)
	 */
	@Override
	public CuentaCobrar buscar(Long id) {
		return cuentaCobrarDao.buscar(id);
	}
	
	/**
	 * 
	 * @param letraCambio
	 * @return
	 */
	@Override
	public CuentaCobrar buscarPorLetraCambio(String letraCambio){
		return cuentaCobrarDao.buscarPorLetraCambio(letraCambio);
	}
	
	/**
	 * Factura Manual
	 * @see com.factura.bo.CuentaCobrarBo#buscarByFacturaManual(java.lang.Integer)
	 */
	@Override
	public CuentaCobrar buscarPorFacturaManual(Integer facturaManual){
		return cuentaCobrarDao.buscarPorFacturaManual(facturaManual);
	}

}