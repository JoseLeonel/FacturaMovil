package com.emprendesoftcr.Bo.Impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.emprendesoftcr.Bo.CategoriaBo;
import com.emprendesoftcr.Dao.CategoriaDao;
import com.emprendesoftcr.modelo.Categoria;
import com.emprendesoftcr.modelo.Empresa;

/**
 * categorias se va dividir los articulos de una empresa CategoriaBoImpl.
 * @author jose.
 * @since 17 mar. 2018
 */

@EnableTransactionManagement
@Service("categoriaBo")
public class CategoriaBoImpl implements CategoriaBo {

	@Autowired
	private CategoriaDao categoriaDao;

	@Transactional
	@Override
	public void agregar(Categoria categoria) {
		categoriaDao.agregar(categoria);
	}

	@Override
	@Transactional
	public void modificar(Categoria categoria) {
		categoriaDao.modificar(categoria);
	}

	@Override
	@Transactional
	public void eliminar(Categoria categoria) {
		categoriaDao.eliminar(categoria);
	}

	/**
	 * Buscar por descripcion
	 * @see com.factura.bo.CategoriaBo#buscarPorDescripcionYEmpresa(java.lang.String, com.factura.domain.Empresa)
	 */
	@Override
	public Categoria buscarPorDescripcionYEmpresa(String descripcion, Empresa empresa) {
		return categoriaDao.buscarPorDescripcionYEmpresa(descripcion, empresa);
	}

	/**
	 * Buscar por id
	 * @see com.factura.bo.CategoriaBo#buscar(java.lang.Integer)
	 */
	@Override
	public Categoria buscar(Long id) {
		return categoriaDao.buscar(id);
	}

	@Override
	public Collection<Categoria> findByEmpresaAll(Integer idEmpresa) {
		return categoriaDao.findByEmpresaAll(idEmpresa);
	}

}