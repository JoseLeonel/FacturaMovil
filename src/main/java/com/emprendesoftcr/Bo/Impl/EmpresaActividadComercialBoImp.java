package com.emprendesoftcr.Bo.Impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.emprendesoftcr.Bo.EmpresaActividadComercialBo;
import com.emprendesoftcr.Dao.EmpresaActividadComercialDao;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.EmpresaActividadComercial;

@EnableTransactionManagement
@Service("empresaActividadComercialBo")
public class EmpresaActividadComercialBoImp implements EmpresaActividadComercialBo {

	@Autowired
	EmpresaActividadComercialDao empresaActividadComercialDao;

	@Transactional
	@Override
	public void agregar(EmpresaActividadComercial empresaActividadComercial) {
		empresaActividadComercialDao.agregar(empresaActividadComercial);

	}

	@Transactional
	@Override
	public void modificar(EmpresaActividadComercial empresaActividadComercial) {
		empresaActividadComercialDao.modificar(empresaActividadComercial);

	}

	@Transactional
	@Override
	public void eliminar(EmpresaActividadComercial empresaActividadComercial) {
		empresaActividadComercialDao.eliminar(empresaActividadComercial);

	}

	@Override
	public EmpresaActividadComercial buscar(Integer id) {

		return empresaActividadComercialDao.buscar(id);
	}

	@Override
	public EmpresaActividadComercial findByCodigo(String codigo, Empresa empresa) {
		return empresaActividadComercialDao.findByCodigo(codigo,empresa);
	}

	@Override
	public EmpresaActividadComercial findByPrincipal(Integer principal, Empresa empresa) {
		return empresaActividadComercialDao.findByPrincipal(principal,empresa);
	}
	@Override
	public Collection<EmpresaActividadComercial> findAll(Empresa empresa ){
		return empresaActividadComercialDao.findAll(empresa);
	}

}
