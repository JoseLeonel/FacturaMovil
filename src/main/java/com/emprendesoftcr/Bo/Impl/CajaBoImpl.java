package com.emprendesoftcr.Bo.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.emprendesoftcr.Bo.CajaBo;
import com.emprendesoftcr.Dao.CajaDao;
import com.emprendesoftcr.modelo.Caja;
import com.emprendesoftcr.modelo.Empresa;

/**
 * Caja es las diferentes cajas de una empresa lo importante es la terminal 00001 son 5 digitos
 * CajaBoImpl.
 * @author jose.
 * @since 3 nov. 2018
 */
@EnableTransactionManagement
@Service("cajaBo")
public class CajaBoImpl implements CajaBo {

	@Autowired
	private CajaDao cajaDao;

	@Transactional
	@Override
	public void agregar(Caja caja) {
		cajaDao.agregar(caja);
	}

	@Transactional
	@Override
	public void modificar(Caja caja) {
		cajaDao.modificar(caja);
	}

	@Transactional
	@Override
	public void eliminar(Caja caja) {
		cajaDao.eliminar(caja);
	}

	/**
	 * Buscar por id
	 * @param id
	 * @return
	 */
	@Override
	public Caja buscar(Long id) {
		return cajaDao.buscar(id);
	}

	/**
	 * Buscar por descripcion
	 * @see com.emprendesoftcr.Bo.CajaBo#findByDescripcionAndEmpresa(java.lang.String, com.emprendesoftcr.modelo.Empresa)
	 */
	@Override
	public Caja findByDescripcionAndEmpresa(String descripcion, Empresa empresa) {
		return cajaDao.findByDescripcionAndEmpresa(descripcion, empresa);
	};

	/**
	 * buscar por terminal
	 * @see com.emprendesoftcr.Bo.CajaBo#findByTerminalAndEmpresa(java.lang.String, com.emprendesoftcr.modelo.Empresa)
	 */
	@Override
	public Caja findByTerminalAndEmpresa(String terminal, Empresa empresa) {
		return cajaDao.findByTerminalAndEmpresa(terminal, empresa);
	}

}