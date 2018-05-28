package com.factura.FacturaElectronica.Bo.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.factura.FacturaElectronica.Bo.CuentaCobrarBo;
import com.factura.FacturaElectronica.Dao.CuentaCobrarDao;
import com.factura.FacturaElectronica.modelo.CuentaCobrar;

/**
 * cuentas por cobrar que se debe cobrar a los clientes
 * CuentaCobrarBoImpl.
 * @author jose.
 * @since 25 mar. 2018
 */
@Transactional
@EnableTransactionManagement
@Service("cuentaCobrarBo")
public class CuentaCobrarBoImpl implements CuentaCobrarBo {

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
	public CuentaCobrar buscar(Integer id) {
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