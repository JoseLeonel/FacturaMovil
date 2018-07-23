package com.emprendesoftcr.Bo.Impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.emprendesoftcr.Bo.ClienteBo;
import com.emprendesoftcr.Dao.ClienteDao;
import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.modelo.Cliente;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Usuario;

/**
 * Clientes asociados a una empresa ClienteBoImpl.
 * @author jose.
 * @since 17 mar. 2018
 */
@Lazy
@Transactional
@EnableTransactionManagement
@Service("clienteBo")
public class ClienteBoImpl implements ClienteBo {

	@Lazy
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
	 * @see com.emprendesoftcr.Bo.ClienteBo#crearClienteFrecuente(com.emprendesoftcr.modelo.Empresa, com.emprendesoftcr.modelo.Usuario)
	 */
	@Override
	public Cliente crearClienteFrecuente(Empresa empresa,Usuario usuario) {
		Cliente cliente = new Cliente();
		cliente.setCedula(Constantes.CEDULA_CLIENTE_FRECUENTE);
		cliente.setTipoCedula(Constantes.TIPO_CEDULA_FISICA);
		cliente.setCodigoPais(506);
		cliente.setCorreoElectronico(Constantes.CORREO_CLIENTE_FRECUENTE);
		cliente.setNombreCompleto(Constantes.NOMBRE_CLIENTE_FRECUENTE);
		cliente.setCelular(Constantes.ZEROS);
		cliente.setTelefono(Constantes.ZEROS);
		cliente.setEmpresa(empresa);
		cliente.setDescuento(Constantes.ZEROS);
		cliente.setEstado(Constantes.ESTADO_ACTIVO);
		cliente.setOtraSena(Constantes.EMPTY);
		cliente.setCreated_at(new Date());
		cliente.setUpdated_at(new Date());
		cliente.setUsuario(usuario);
		cliente.setProvincia(Constantes.ZEROS.toString());
		cliente.setDistrito(Constantes.ZEROS.toString());
		cliente.setCanton(Constantes.ZEROS.toString());
		cliente.setBarrio(Constantes.ZEROS.toString());
		cliente.setIdentificacionExtranjero(Constantes.EMPTY);
		cliente.setNombreComercial(Constantes.EMPTY);
		agregar(cliente);
		return cliente;
	}

}