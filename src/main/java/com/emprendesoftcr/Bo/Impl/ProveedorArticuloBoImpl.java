package com.emprendesoftcr.Bo.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.emprendesoftcr.Bo.ProveedorArticuloBo;
import com.emprendesoftcr.Dao.ProveedorArticuloDao;
import com.emprendesoftcr.modelo.Proveedor;
import com.emprendesoftcr.modelo.ProveedorArticulo;

@Lazy
@Transactional
@EnableTransactionManagement
@Service("proveedorArticuloBo")
public class ProveedorArticuloBoImpl implements ProveedorArticuloBo {

	@Lazy
	@Autowired
	ProveedorArticuloDao proveedorArticuloDao;

	@Override
	public void agregar(ProveedorArticulo proveedorArticulo) {
		proveedorArticuloDao.agregar(proveedorArticulo);

	}

	@Override
	public void modificar(ProveedorArticulo proveedorArticulo) {
		proveedorArticuloDao.modificar(proveedorArticulo);
	}

	@Override
	public void eliminar(ProveedorArticulo proveedorArticulo) {
		proveedorArticuloDao.eliminar(proveedorArticulo);
	}

	@Override
	public ProveedorArticulo findById(Long id) {

		return proveedorArticuloDao.findById(id);
	}
	public ProveedorArticulo findByCodigo(String codigo, Proveedor proveedor) {
		return proveedorArticuloDao.findByCodigo(codigo, proveedor);
	}

}
