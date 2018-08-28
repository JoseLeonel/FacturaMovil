package com.emprendesoftcr.Bo.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.emprendesoftcr.Bo.MarcaBo;
import com.emprendesoftcr.Dao.MarcaDao;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Marca;

/**
 * Marcas asociadas al articulo
 * MarcaBoImpl.
 * @author jose.
 * @since 17 mar. 2018
 */
@Transactional
@EnableTransactionManagement
@Service("marcaBo")
public class MarcaBoImpl implements MarcaBo {

	@Autowired
	MarcaDao marcaDao;

	public void agregar(Marca marca) {
		marcaDao.agregar(marca);
	}

	public void modificar(Marca marca) {
		marcaDao.modificar(marca);
	}

	public void eliminar(Marca marca) {
		marcaDao.eliminar(marca);
	}

/**
 * Busca la Marca por descripcion y empresa
 * @see com.factura.bo.MarcaBo#buscarPorDescripcionYEmpresa(java.lang.String, com.factura.domain.Empresa)
 */
	@Override
	public Marca buscarPorDescripcionYEmpresa(String descripcion, Empresa empresa) {
		return marcaDao.buscarPorDescripcionYEmpresa(descripcion, empresa);
	}

	/**
	 * Buscar por id
	 * @see com.factura.bo.MarcaBo#buscar(java.lang.Integer)
	 */
	@Override
	public Marca buscar(Long id) {
		return marcaDao.buscar(id);
	}

}