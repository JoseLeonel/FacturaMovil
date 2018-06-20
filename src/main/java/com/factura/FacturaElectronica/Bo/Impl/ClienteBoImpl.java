package com.factura.FacturaElectronica.Bo.Impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.factura.FacturaElectronica.Bo.ClienteBo;
import com.factura.FacturaElectronica.Dao.ClienteDao;
import com.factura.FacturaElectronica.Utils.Constantes;
import com.factura.FacturaElectronica.modelo.Cliente;
import com.factura.FacturaElectronica.modelo.Empresa;
import com.factura.FacturaElectronica.modelo.Usuario;
import com.factura.FacturaElectronica.modelo.Vendedor;

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
	/**
	 * Crear el cliente frecuente
	 * @see com.factura.FacturaElectronica.Bo.ClienteBo#crearClienteFrecuente(com.factura.FacturaElectronica.modelo.Empresa, com.factura.FacturaElectronica.modelo.Usuario)
	 */
	@Override
	public Cliente crearClienteFrecuente(Empresa empresa,Usuario usuario) {
		Cliente cliente = new Cliente();
		cliente.setCedula(Constantes.CEDULA_CLIENTE_FRECUENTE);
		cliente.setCorreoElectronico(Constantes.CORREO_CLIENTE_FRECUENTE);
		cliente.setNombreCompleto(Constantes.NOMBRE_CLIENTE_FRECUENTE);
		cliente.setCelular(Constantes.BLANK);
		cliente.setTelefono(BigInteger.ZERO);
		cliente.setEmpresa(empresa);
		cliente.setDescuento(BigDecimal.ZERO);
		cliente.setEstado(Constantes.ESTADO_ACTIVO);
		cliente.setOtraSena(Constantes.EMPTY);
		cliente.setCreated_at(new Date());
		cliente.setUpdated_at(new Date());
		cliente.setUsuario(usuario);
		cliente.setProvincia(Constantes.PROVINCIA_SAN_JOSE);
		agregar(cliente);
		return cliente;
	}

}