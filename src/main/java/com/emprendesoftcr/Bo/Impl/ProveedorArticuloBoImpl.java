package com.emprendesoftcr.Bo.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.emprendesoftcr.Bo.ProveedorArticuloBo;
import com.emprendesoftcr.Dao.ProveedorArticuloDao;
import com.emprendesoftcr.modelo.Articulo;
import com.emprendesoftcr.modelo.Proveedor;
import com.emprendesoftcr.modelo.ProveedorArticulo;

@EnableTransactionManagement
@Service("proveedorArticuloBo")
public class ProveedorArticuloBoImpl implements ProveedorArticuloBo {

	@Autowired
	ProveedorArticuloDao proveedorArticuloDao;

	@Transactional
	@Override
	public void agregar(ProveedorArticulo proveedorArticulo) {
		proveedorArticuloDao.agregar(proveedorArticulo);

	}

	@Transactional
	@Override
	public void modificar(ProveedorArticulo proveedorArticulo) {
		proveedorArticuloDao.modificar(proveedorArticulo);
	}

	@Override
	@Transactional
	public void eliminar(ProveedorArticulo proveedorArticulo) {
		proveedorArticuloDao.eliminar(proveedorArticulo);
	}

	@Override
	public ProveedorArticulo findById(Long id) {

		return proveedorArticuloDao.findById(id);
	}

	public ProveedorArticulo findByCodigo(Articulo articulo, Proveedor proveedor) {
		return proveedorArticuloDao.findByCodigo(articulo, proveedor);
	}

}
