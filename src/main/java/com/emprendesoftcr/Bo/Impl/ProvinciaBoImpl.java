package com.emprendesoftcr.Bo.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.emprendesoftcr.Bo.ProvinciaBo;
import com.emprendesoftcr.Dao.ProvinciaDao;
import com.emprendesoftcr.modelo.Provincia;

@Lazy
@Transactional
@EnableTransactionManagement
@Service("provinciaBo")
public class ProvinciaBoImpl implements ProvinciaBo {

	@Autowired
	ProvinciaDao provinciaDao;



	@Override
	public Provincia findByCodigo(Integer codigo) {
		return provinciaDao.findByCodigo(codigo);
	}

	
}