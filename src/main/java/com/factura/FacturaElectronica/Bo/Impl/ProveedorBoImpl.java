package com.factura.FacturaElectronica.Bo.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.factura.FacturaElectronica.Bo.ProveedorBo;
import com.factura.FacturaElectronica.Dao.ProveedorDao;
import com.factura.FacturaElectronica.modelo.Empresa;
import com.factura.FacturaElectronica.modelo.Proveedor;

/**
 * ProveedorBoImpl.
 * @author jose.
 * @since 17 mar. 2018
 */
@Transactional
@EnableTransactionManagement
@Service("proveedorBo")
public class ProveedorBoImpl implements ProveedorBo {

	@Autowired
	ProveedorDao proveedorDao;

	public void agregar(Proveedor proveedor) {
		proveedorDao.agregar(proveedor);
	}

	public void modificar(Proveedor proveedor) {
		proveedorDao.modificar(proveedor);
	}

	public void eliminar(Proveedor proveedor) {
		proveedorDao.eliminar(proveedor);
	}

	/**
	 * Buscar por nombre completo y Empresa
	 * @see com.factura.bo.ProveedorBo#buscarByNombreCompletoAndEmpresa(java.lang.String, com.factura.domain.Empresa)
	 */
	@Override
	public Proveedor buscarPorNombreCompletoYEmpresa(String nombreCompleto, Empresa empresa) {
		return proveedorDao.buscarPorNombreCompletoYEmpresa(nombreCompleto, empresa);
	}

	/**
	 * Buscar por cedula y empresa
	 * @see com.factura.bo.ProveedorBo#buscarByCedulaAndEmpresa(java.lang.String, com.factura.domain.Empresa)
	 */
	@Override
	public Proveedor buscarPorCedulaYEmpresa(String cedula, Empresa empresa) {
		return proveedorDao.buscarPorCedulaYEmpresa(cedula, empresa);
	}

	/**
	 * Buscar por id
	 * @see com.factura.bo.ProveedorBo#buscar(java.lang.Integer)
	 */
	@Override
	public Proveedor buscar(Integer id) {
		return proveedorDao.buscar(id);
	}

}