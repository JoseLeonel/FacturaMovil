package com.emprendesoftcr.Bo.Impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.emprendesoftcr.Bo.VendedorBo;
import com.emprendesoftcr.Dao.VendedorDao;
import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.modelo.Vendedor;

/**
 * ClienteBoImpl.
 * @author jose.
 * @since 17 mar. 2018
 */
@EnableTransactionManagement
@Service("vendedorBo")
public class VendedorBoImpl implements VendedorBo {

	@Autowired
	VendedorDao vendedorDao;

	@Transactional
	@Override
	public void agregar(Vendedor vendedor) {
		vendedorDao.agregar(vendedor);
	}

	@Transactional
	@Override
	public void modificar(Vendedor vendedor) {
		vendedorDao.modificar(vendedor);
	}

	@Transactional
	@Override
	public void eliminar(Vendedor vendedor) {
		vendedorDao.eliminar(vendedor);
	}

	/**
	 * buscar por id
	 * @see com.factura.bo.VendedorBo#buscar(java.lang.Integer)
	 */
	@Override
	public Vendedor buscar(Long id) {
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

	/**
	 * Vendedor Frecuente
	 * @see com.emprendesoftcr.Bo.VendedorBo#crearVendedorFrecuente(com.emprendesoftcr.modelo.Empresa, com.emprendesoftcr.modelo.Usuario)
	 */
	@Transactional
	@Override
	public Vendedor crearVendedorFrecuente(Empresa empresa, Usuario usuario) {
		Vendedor vendedor = new Vendedor();
		vendedor.setCedula(Constantes.CEDULA_VENDEDOR_FRECUENTE);
		vendedor.setCorreoElectronico(Constantes.CORREO_VENDEDOR_FRECUENTE);
		vendedor.setNombreCompleto(Constantes.NOMBRE_VENDEDOR_FRECUENTE);
		vendedor.setCelular(Constantes.ZEROS);
		vendedor.setTelefono(Constantes.ZEROS);
		vendedor.setEmpresa(empresa);
		vendedor.setDescuento(Constantes.ZEROS_DOUBLE);
		vendedor.setEstado(Constantes.ESTADO_ACTIVO);
		vendedor.setOtraSena(Constantes.EMPTY);
		vendedor.setCreated_at(new Date());
		vendedor.setUpdated_at(new Date());
		vendedor.setUsuario(usuario);
		agregar(vendedor);
		return vendedor;
	}

}