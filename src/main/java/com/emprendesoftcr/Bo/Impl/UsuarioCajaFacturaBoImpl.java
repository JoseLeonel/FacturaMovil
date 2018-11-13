package com.emprendesoftcr.Bo.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.emprendesoftcr.Bo.UsuarioCajaFacturaBo;
import com.emprendesoftcr.Dao.UsuarioCajaFacturaDao;
import com.emprendesoftcr.modelo.UsuarioCajaFactura;

@Repository("usuarioCajaFacturaBo")
public class UsuarioCajaFacturaBoImpl implements UsuarioCajaFacturaBo {

	@Autowired
	UsuarioCajaFacturaDao	usuarioCajaFacturaDao;

	
	@Override
	public UsuarioCajaFactura findById(Long id) {
		return usuarioCajaFacturaDao.findById(id);
	}

}