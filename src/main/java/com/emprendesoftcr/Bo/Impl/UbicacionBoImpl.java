package com.emprendesoftcr.Bo.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.emprendesoftcr.Bo.UbicacionBo;
import com.emprendesoftcr.Dao.UbicacionDao;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Ubicacion;


@EnableTransactionManagement
@Service("ubicacionBo")
public class UbicacionBoImpl implements UbicacionBo {

	@Autowired
	UbicacionDao ubicacionDao;

	@Override
	@Transactional
	public void agregar(Ubicacion ubicacion) {
		ubicacionDao.agregar(ubicacion);
	}

	@Override
	@Transactional
	public void modificar(Ubicacion ubicacion) {
		ubicacionDao.modificar(ubicacion);
	}

	@Override
	@Transactional
	public void eliminar(Ubicacion ubicacion) {
		ubicacionDao.eliminar(ubicacion);
	}


	@Override
	public Ubicacion buscarPorDescripcionYEmpresa(String descripcion, Empresa empresa) {
		return ubicacionDao.buscarPorDescripcionYEmpresa(descripcion, empresa);
	}

	
	@Override
	public Ubicacion buscar(Long id) {
		return ubicacionDao.buscar(id);
	}

}