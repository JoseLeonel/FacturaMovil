package com.emprendesoftcr.Bo.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.emprendesoftcr.Bo.RolBo;
import com.emprendesoftcr.Dao.RolDao;
import com.emprendesoftcr.modelo.Rol;

/**
 * Rol por usuario RolBoImpl.
 * @author jose.
 * @since 19 abr. 2018
 */
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