package com.factura.FacturaElectronica.Bo.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.factura.FacturaElectronica.Bo.FacturaBo;
import com.factura.FacturaElectronica.Dao.FacturaDao;
import com.factura.FacturaElectronica.modelo.Empresa;
import com.factura.FacturaElectronica.modelo.Factura;

@Transactional
@EnableTransactionManagement
@Service("facturaBo")
public class FacturaBoImpl implements FacturaBo {

	@Autowired
	FacturaDao				facturaDao;
	

	public void agregar(Factura factura) {
		facturaDao.agregar(factura);

	}

	
	/**
	 * Modificar una factura
	 * @see com.factura.FacturaElectronica.Bo.FacturaBo#modificar(com.factura.FacturaElectronica.modelo.Factura)
	 */
	@Override
	public void modificar(Factura factura) {
		facturaDao.modificar(factura);
	}

	/**
	 * Eliminar una factura
	 * @see com.factura.FacturaElectronica.Bo.FacturaBo#eliminar(com.factura.FacturaElectronica.modelo.Factura)
	 */
	@Override
	public void eliminar(Factura factura) {
		facturaDao.eliminar(factura);
	}

	/**
	 * Buscar por id
	 * @see com.factura.FacturaElectronica.Bo.FacturaBo#findById(java.lang.Integer)
	 */
	@Override
	public Factura findById(Integer id) {
		return facturaDao.findById(id);
	}

	/**
	 * Buscar por consecutivo y empresa
	 * @see com.factura.FacturaElectronica.Bo.FacturaBo#findByConsecutivoAndEmpresa(java.lang.String, com.factura.FacturaElectronica.modelo.Empresa)
	 */
	@Override
	public Factura findByConsecutivoAndEmpresa(String consecutivo, Empresa empresa) {
		return facturaDao.findByConsecutivoAndEmpresa(consecutivo, empresa);
	}
	
	
}