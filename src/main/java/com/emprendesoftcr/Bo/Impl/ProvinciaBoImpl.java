package com.emprendesoftcr.Bo.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.emprendesoftcr.Bo.ProvinciaBo;
import com.emprendesoftcr.Dao.ProvinciaDao;
import com.emprendesoftcr.modelo.Canton;
import com.emprendesoftcr.modelo.Distrito;
import com.emprendesoftcr.modelo.Provincia;

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

	@Override
	public Canton findCantonByCodigo(Integer codigoProvincia, Integer codigoCanton) {
		return provinciaDao.findCantonByCodigo(codigoProvincia, codigoCanton);
	}

	@Override
	public Distrito findDistritoByCodigo(Integer codigoProvincia, Integer codigoCanton, Integer codigoDistrito) {
		return provinciaDao.findDistritoByCodigo(codigoProvincia, codigoCanton, codigoDistrito);
	}

}