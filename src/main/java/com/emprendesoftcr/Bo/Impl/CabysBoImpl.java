package com.emprendesoftcr.Bo.Impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.emprendesoftcr.Bo.CabysBo;
import com.emprendesoftcr.Dao.CabysDao;
import com.emprendesoftcr.modelo.Cabys;
import com.emprendesoftcr.modelo.Empresa;

/**
 * categorias se va dividir los articulos de una empresa CategoriaBoImpl.
 * @author jose.
 * @since 17 mar. 2018
 */

@EnableTransactionManagement
@Service("cabysBo")
public class CabysBoImpl implements CabysBo {

	@Autowired
	private CabysDao	cabysDao;

	

	@Transactional
	@Override
	public void agregar(Cabys cabys) {
		cabysDao.agregar(cabys);
	}

	@Override
	@Transactional
	public void modificar(Cabys cabys) {
		cabysDao.modificar(cabys);
	}

	@Override
	@Transactional
	public void eliminar(Cabys cabys) {
		cabysDao.eliminar(cabys);
	}

	@Override
	public Cabys buscarPorDescripcionYEmpresa(String descripcion, Empresa empresa) {
		return cabysDao.buscarPorDescripcionYEmpresa(descripcion, empresa);
	}

	@Override
	public Cabys buscar(Long id) {
		return cabysDao.buscar(id);
	}

	@Override
	public Collection<Cabys> findByEmpresaAll(Integer idEmpresa) {
		return cabysDao.findByEmpresaAll(idEmpresa);
	}

	



	@Override
	public Cabys findByCodigo(String codigo, Empresa empresa) {
	
		return cabysDao.findByCodigo(codigo, empresa);
	}

}