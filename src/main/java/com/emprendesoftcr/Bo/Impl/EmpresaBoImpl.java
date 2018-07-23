package com.emprendesoftcr.Bo.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.emprendesoftcr.Bo.EmpresaBo;
import com.emprendesoftcr.Dao.EmpresaDao;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Factura;
import com.emprendesoftcr.modelo.Usuario;

/**
 * Empresa que realiza las ventas y compras EmpresaBoImpl.
 * 
 * @author jose.
 * @since 19 abr. 2018
 */
@Lazy
@Transactional
@EnableTransactionManagement
@Service("empresaBo")
public class EmpresaBoImpl implements EmpresaBo {

	@Lazy
	@Autowired
	EmpresaDao empresaDao;

	public void agregar(Empresa empresa) {
		empresaDao.agregar(empresa);
	}

	public void modificar(Empresa empresa) {
		empresaDao.modificar(empresa);
	}

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
	public String generarConsecutivoFactura(Empresa empresa,Usuario usuario,Factura factura)  throws Exception{
		return empresaDao.generarConsecutivoFactura(empresa,usuario,factura);
	}

}