package com.emprendesoftcr.Bo.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.emprendesoftcr.Bo.RolBo;
import com.emprendesoftcr.Dao.RolDao;
import com.emprendesoftcr.modelo.Rol;

/**
 * Rol por usuario RolBoImpl.
 * @author jose.
 * @since 19 abr. 2018
 */
@Lazy
@Transactional
@EnableTransactionManagement
@Service("rolBo")
public class RolBoImpl implements RolBo {

	@Lazy
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