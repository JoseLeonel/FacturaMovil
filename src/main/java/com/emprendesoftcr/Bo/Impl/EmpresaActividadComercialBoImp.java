package com.emprendesoftcr.Bo.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.emprendesoftcr.Bo.EmpresaActividadComercialBo;
import com.emprendesoftcr.Dao.EmpresaActividadComercialDao;
import com.emprendesoftcr.modelo.EmpresaActividadComercial;

@EnableTransactionManagement
@Service("empresaActividadComercialBo")
public class EmpresaActividadComercialBoImp implements EmpresaActividadComercialBo {

	@Autowired
	EmpresaActividadComercialDao empresaActividadComercialDao;
	
	@Override
	public void agregar(EmpresaActividadComercial empresaActividadComercial) {
		empresaActividadComercialDao.agregar(empresaActividadComercial);

	}

	@Override
	public void modificar(EmpresaActividadComercial empresaActividadComercial) {
		empresaActividadComercialDao.modificar(empresaActividadComercial);

	}

	@Override
	public void eliminar(EmpresaActividadComercial empresaActividadComercial) {
		empresaActividadComercialDao.eliminar(empresaActividadComercial);

	}

	@Override
	public EmpresaActividadComercial buscar(Integer id) {
	
		return empresaActividadComercialDao.buscar(id);
	}

}
