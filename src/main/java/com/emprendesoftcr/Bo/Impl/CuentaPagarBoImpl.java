package com.emprendesoftcr.Bo.Impl;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.emprendesoftcr.Bo.CuentaPagarBo;
import com.emprendesoftcr.Dao.CuentaPagarDao;
import com.emprendesoftcr.modelo.CuentaPagar;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Proveedor;

/**
 * cuentas por pagar de los proveedores.
 * @author jose.
 * @since 25 mar. 2018
 */
@EnableTransactionManagement
@Service("cuentaPagarBo")
public class CuentaPagarBoImpl implements CuentaPagarBo {

	@Autowired
	private CuentaPagarDao cuentaPagarDao;

	@Transactional
	@Override
	public void agregar(CuentaPagar cuentaPagar) {
		cuentaPagarDao.agregar(cuentaPagar);
	}

	@Transactional
	@Override
	public void modificar(CuentaPagar cuentaPagar) {
		cuentaPagarDao.modificar(cuentaPagar);
	}

	@Transactional
	@Override
	public void eliminar(CuentaPagar cuentaPagar) {
		cuentaPagarDao.eliminar(cuentaPagar);
	}

	/**
	 * Busca por Id
	 * @see com.factura.bo.CuentaCobrarBo#buscar(java.lang.Integer)
	 */
	@Override
	public CuentaPagar buscar(Long id) {
		return cuentaPagarDao.buscar(id);
	}

	@Override
	public Collection<CuentaPagar> cuentasPorPagarbyFechasAndEmpresaAndClienteAndEstado(Date fechaInicio, Date fechaFin, Empresa empresa, Proveedor proveedor, String estado) {

		return cuentaPagarDao.cuentasPorPagarbyFechasAndEmpresaAndClienteAndEstado(fechaInicio, fechaFin, empresa, proveedor, estado);
	}
	
	@Override
	public Collection<CuentaPagar> cuentasPorPagarbyEmpresaAndClienteAndEstado( Empresa empresa, Proveedor proveedor, String estado) {

		return cuentaPagarDao.cuentasPorPagarbyEmpresaAndClienteAndEstado(empresa, proveedor, estado);
	}

}