package com.factura.FacturaElectronica.Bo.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.factura.FacturaElectronica.Bo.ClienteBo;
import com.factura.FacturaElectronica.Dao.ClienteDao;
import com.factura.FacturaElectronica.modelo.Cliente;
import com.factura.FacturaElectronica.modelo.Empresa;

/**
 * Clientes asociados a una empresa ClienteBoImpl.
 * @author jose.
 * @since 17 mar. 2018
 */
@Transactional
@EnableTransactionManagement
@Service("clienteBo")
public class ClienteBoImpl implements ClienteBo {

	@Autowired
	ClienteDao clienteDao;

	public void agregar(Cliente cliente) {
		clienteDao.agregar(cliente);
	}

	public void modificar(Cliente cliente) {
		clienteDao.modificar(cliente);
	}

	public void eliminar(Cliente cliente) {
		clienteDao.eliminar(cliente);
	}

	/**
	 * @see com.factura.bo.ClienteBo#buscarPorNombreCompletoYEmpresa(java.lang.String, com.factura.domain.Empresa)
	 */
	@Override
	public Cliente buscarPorNombreCompletoYEmpresa(String nombreCompleto, Empresa empresa) {
		return clienteDao.buscarPorNombreCompletoYEmpresa(nombreCompleto, empresa);
	}

	/**
	 * @see com.factura.bo.ClienteBo#buscarPorCedulaYEmpresa(java.lang.String, com.factura.domain.Empresa)
	 */
	@Override
	public Cliente buscarPorCedulaYEmpresa(String cedula, Empresa empresa) {
		return clienteDao.buscarPorCedulaYEmpresa(cedula, empresa);
	}

	/**
	 * Buscar por id
	 * @see com.factura.bo.ClienteBo#buscar(java.lang.Integer)
	 */
	@Override
	public Cliente buscar(Integer id) {
		return clienteDao.buscar(id);
	}

}