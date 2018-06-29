package com.factura.FacturaElectronica.Bo.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.factura.FacturaElectronica.Bo.TipoCambioBo;
import com.factura.FacturaElectronica.Dao.TipoCambioDao;
import com.factura.FacturaElectronica.modelo.Empresa;
import com.factura.FacturaElectronica.modelo.TipoCambio;

/**
 * TipoCambios aplicados en las cuentas por cobrar a los clientes TipoCambioBoImpl.
 * @author jose.
 * @since 19 abr. 2018
 */
@Transactional
@EnableTransactionManagement
@Service("tipoCambioBo")
public class TipoCambioBoImpl implements TipoCambioBo {

	@Autowired
	TipoCambioDao tipoCambioDao;

	public void agregar(TipoCambio tipoCambio) {
		tipoCambioDao.agregar(tipoCambio);
	}

	public void modificar(TipoCambio tipoCambio) {
		tipoCambioDao.modificar(tipoCambio);
	}

	public void eliminar(TipoCambio tipoCambio) {
		tipoCambioDao.eliminar(tipoCambio);
	}

	/**
	 * Busca por Id
	 * @see com.factura.bo.CuentaCobrarBo#buscar(java.lang.Integer)
	 */
	@Override
	public TipoCambio buscar(Integer id) {
		return tipoCambioDao.buscar(id);
	}

	@Override
	public TipoCambio findByEstadoAndEmpresa(String estado,Empresa empresa){
		return tipoCambioDao.findByEstadoAndEmpresa(estado,empresa);
	}
	
}