package com.factura.FacturaElectronica.Bo.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.factura.FacturaElectronica.Bo.CajaBo;
import com.factura.FacturaElectronica.Dao.CajaDao;
import com.factura.FacturaElectronica.modelo.Caja;
import com.factura.FacturaElectronica.modelo.Empresa;

@Transactional
@EnableTransactionManagement
@Service("cajaBo")
public class CajaBoImpl implements CajaBo {

	@Autowired
	CajaDao cajaDao;

	public void agregar(Caja caja) {
		cajaDao.agregar(caja);
	}

	public void modificar(Caja caja) {
		cajaDao.modificar(caja);
	}

	public void eliminar(Caja caja) {
		cajaDao.eliminar(caja);
	}

	/**
	 * Buscar por id
	 * @param id
	 * @return
	 */
	@Override
	public Caja buscar(Integer id) {
		return cajaDao.buscar(id);
	}

	/**
	 * Buscar por descripcion
	 * @see com.factura.FacturaElectronica.Bo.CajaBo#findByDescripcionAndEmpresa(java.lang.String, com.factura.FacturaElectronica.modelo.Empresa)
	 */
	@Override
	public Caja findByDescripcionAndEmpresa(String descripcion, Empresa empresa) {
		return cajaDao.findByDescripcionAndEmpresa(descripcion, empresa);
	};

	/**
	 * buscar por terminal
	 * @see com.factura.FacturaElectronica.Bo.CajaBo#findByTerminalAndEmpresa(java.lang.String, com.factura.FacturaElectronica.modelo.Empresa)
	 */
	@Override
	public Caja findByTerminalAndEmpresa(String terminal, Empresa empresa) {
		return cajaDao.findByTerminalAndEmpresa(terminal, empresa);
	}
	
}