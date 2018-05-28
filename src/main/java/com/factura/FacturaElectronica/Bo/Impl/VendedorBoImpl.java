package com.factura.FacturaElectronica.Bo.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.factura.FacturaElectronica.Bo.VendedorBo;
import com.factura.FacturaElectronica.Dao.VendedorDao;
import com.factura.FacturaElectronica.modelo.Empresa;
import com.factura.FacturaElectronica.modelo.Vendedor;

/**
 * ClienteBoImpl.
 * @author jose.
 * @since 17 mar. 2018
 */
@Transactional
@EnableTransactionManagement
@Service("vendedorBo")
public class VendedorBoImpl implements VendedorBo {

	@Autowired
	VendedorDao vendedorDao;

	public void agregar(Vendedor vendedor) {
		vendedorDao.agregar(vendedor);
	}

	public void modificar(Vendedor vendedor) {
		vendedorDao.modificar(vendedor);
	}

	public void eliminar(Vendedor vendedor) {
		vendedorDao.eliminar(vendedor);
	}
/**
 * buscar por id
 * @see com.factura.bo.VendedorBo#buscar(java.lang.Integer)
 */
	@Override
	public Vendedor buscar(Integer id) {
		return vendedorDao.buscar(id);
	}
/**
 * Buscar por nombre completo y sucursal
 * @see com.factura.bo.VendedorBo#buscarByNombreCompletoAndSucursal(java.lang.String, com.factura.domain.Sucursal)
 */
	@Override
	public Vendedor buscarPorNombreCompletoYEmpresa(String nombreCompleto, Empresa empresa) {
		return vendedorDao.buscarPorNombreCompletoYEmpresa(nombreCompleto, empresa);
	}
/**
 * buscar por cedula y sucursal
 * @see com.factura.bo.VendedorBo#buscarByCedulaAndSucursal(java.lang.String, com.factura.domain.Sucursal)
 */
	@Override
	public Vendedor buscarPorCedulaYEmpresa(String cedula, Empresa empresa) {
		return vendedorDao.buscarPorCedulaYEmpresa(cedula, empresa);
	}

}