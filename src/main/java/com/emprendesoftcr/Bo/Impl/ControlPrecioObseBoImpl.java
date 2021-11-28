package com.emprendesoftcr.Bo.Impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.emprendesoftcr.Bo.ControlPrecioObseBo;
import com.emprendesoftcr.Dao.ControlPrecioObseDao;
import com.emprendesoftcr.modelo.ControlPrecioObse;

@EnableTransactionManagement
@Service("controlPrecioObseBo")
public class ControlPrecioObseBoImpl  implements ControlPrecioObseBo{
	
	@Autowired
	private ControlPrecioObseDao controlPrecioObseDao;
  @Transactional
	@Override
	public void agregar(ControlPrecioObse controlPrecioObse) {
		controlPrecioObseDao.agregar(controlPrecioObse);
		
	}
  @Transactional
	@Override
	public void modificar(ControlPrecioObse controlPrecioObse) {
		controlPrecioObseDao.modificar(controlPrecioObse);
		
	}
  @Transactional
	@Override
	public void eliminar(ControlPrecioObse controlPrecioObse) {
		controlPrecioObseDao.eliminar(controlPrecioObse);
		
	}
  @Transactional
	@Override
	public ControlPrecioObse buscar(Long id) {
		

		return controlPrecioObseDao.buscar(id);
	}

}
