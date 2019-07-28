package com.emprendesoftcr.Bo.Impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.emprendesoftcr.Bo.EmpresaBo;
import com.emprendesoftcr.Dao.EmpresaDao;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Factura;
import com.emprendesoftcr.modelo.RecepcionFactura;
import com.emprendesoftcr.modelo.Usuario;

/**
 * Empresa que realiza las ventas y compras EmpresaBoImpl.
 * 
 * @author jose.
 * @since 19 abr. 2018
 */
@EnableTransactionManagement
@Service("empresaBo")
public class EmpresaBoImpl implements EmpresaBo {

	@Autowired
	private EmpresaDao empresaDao;

	@Transactional
	@Override
	public void agregar(Empresa empresa) {
		empresaDao.agregar(empresa);
	}

	@Transactional
	@Override
	public void modificar(Empresa empresa) {
		empresaDao.modificar(empresa);
	}

	@Transactional
	@Override
	public void eliminar(Empresa empresa) {
		empresaDao.eliminar(empresa);
	}

	@Override
	public Empresa buscar(Integer id) {
		return empresaDao.buscar(id);
	}

	/**
	 * buscar por nombre
	 * 
	 * @see com.factura.bo.EmpresaBo#buscarPorNombre(java.lang.String)
	 */
	@Override
	public Empresa buscarPorNombre(String nombre) {
		return empresaDao.buscarPorNombre(nombre);
	}

	/**
	 * Buscar por nombre comercial
	 * 
	 * @see com.factura.bo.EmpresaBo#buscarPorNombreComercial(java.lang.String)
	 */
	@Override
	public Empresa buscarPorNombreComercial(String nombreComercial) {
		return empresaDao.buscarPorNombreComercial(nombreComercial);
	}

	/**
	 * buscar por cedula
	 * 
	 * @see com.factura.bo.EmpresaBo#buscarPorCedula(java.lang.String)
	 */
	@Override
	public Empresa buscarPorCedula(String cedula) {
		return empresaDao.buscarPorCedula(cedula);
	}
	
	/**
	 * Genera el consecutivo de la factura de una empresa
	 * @see com.emprendesoftcr.Bo.EmpresaBo#generarConsecutivoFactura(com.emprendesoftcr.modelo.Empresa)
	 */
	@Override
	@Transactional
	public String generarConsecutivoFactura(Empresa empresa,Usuario usuario,Factura factura)  throws Exception{
		return empresaDao.generarConsecutivoFactura(empresa,usuario,factura);
	}

	@Override
	@Transactional
	public String generarConsecutivoRecepcionFactura(Empresa empresa, Usuario usuario, RecepcionFactura recepcionFactura) throws Exception {
		return empresaDao.generarConsecutivoRecepcionFactura(empresa, usuario, recepcionFactura);
	}

	@Override
	@Transactional
	public String generaClaveFacturaTributacion(Empresa empresa, String consecutivoFactura, Integer comprobanteElectronico) throws Exception{
		return empresaDao.generaClaveFacturaTributacion(empresa, consecutivoFactura, comprobanteElectronico);
	}
	/**
	 * Lista de empresa por estado
	 * @see com.emprendesoftcr.Bo.EmpresaBo#findByEstado(java.lang.String)
	 */
	@Override
	public Collection<Empresa> findByEstado(String estado){
		return empresaDao.findByEstado(estado);
	}
	
	@Override
	public String generarConsecutivoProforma(Empresa empresa, Usuario usuario) throws Exception{
		return empresaDao.generarConsecutivoProforma(empresa, usuario);
	}

	@Override
	@Transactional
	public String generarConsecutivoCompraSimplificada(Empresa empresa, Usuario usuario) throws Exception {
		
		return empresaDao.generarConsecutivoCompraSimplificada(empresa, usuario);
	}
}