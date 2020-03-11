package com.emprendesoftcr.Bo.Impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.emprendesoftcr.Bo.SalidaEntradaDineroBo;
import com.emprendesoftcr.Dao.SalidaEntradaDineroDao;
import com.emprendesoftcr.modelo.SalidaEntradaDinero;
import com.emprendesoftcr.modelo.UsuarioCaja;

@EnableTransactionManagement
@Service("salidaEntradaDineroBo")
public class SalidaEntradaDineroBoImpl implements SalidaEntradaDineroBo {
	
	@Autowired
	SalidaEntradaDineroDao salidaEntradaDineroDao;

	@Override
	public void agregar(SalidaEntradaDinero salidaEntradaDinero) {
		salidaEntradaDineroDao.agregar(salidaEntradaDinero);

	}

	@Override
	public Collection<SalidaEntradaDinero> buscarPorUsuarioCaja(UsuarioCaja usuarioCaja) {
		
		return salidaEntradaDineroDao.buscarPorUsuarioCaja(usuarioCaja);
	}

	@Override
	public Collection<SalidaEntradaDinero> buscarPorUsuarioCajaAndTipo(UsuarioCaja usuarioCaja, Integer tipo) {
		
		return salidaEntradaDineroDao.buscarPorUsuarioCajaAndTipo(usuarioCaja, tipo);
	}

}
