package com.factura.FacturaElectronica.Bo.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.factura.FacturaElectronica.Bo.RolBo;
import com.factura.FacturaElectronica.Dao.RolDao;
import com.factura.FacturaElectronica.modelo.Rol;

/**
 * Rol por usuario RolBoImpl.
 * @author jose.
 * @since 19 abr. 2018
 */
@Transactional
@EnableTransactionManagement
@Service("rolBo")
public class RolBoImpl implements RolBo {

	@Autowired
	RolDao rolDao;

	/**
	 * Buscar por username
	 * @param username
	 * @return
	 */
	public Rol buscarPorUserName(String username) {
		return rolDao.buscarPorUserName(username);
	}

}