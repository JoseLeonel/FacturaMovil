package com.emprendesoftcr.Bo.Impl;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.emprendesoftcr.Bo.CuentaCobrarBo;
import com.emprendesoftcr.Dao.CuentaCobrarDao;
import com.emprendesoftcr.modelo.Cliente;
import com.emprendesoftcr.modelo.CuentaCobrar;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Factura;
import com.emprendesoftcr.web.command.TotalCuentaPorCobrarCommand;

/**
 * cuentas por cobrar que se debe cobrar a los clientes CuentaCobrarBoImpl.
 * @author jose.
 * @since 25 mar. 2018
 */
@Transactional
@EnableTransactionManagement
@Service("cuentaCobrarBo")
public class CuentaCobrarBoImpl implements CuentaCobrarBo {

	@Autowired
	private CuentaCobrarDao cuentaCobrarDao;


	public void agregar(CuentaCobrar cuentaCobrar) {
		cuentaCobrarDao.agregar(cuentaCobrar);
	}

	public void modificar(CuentaCobrar cuentaCobrar) {
		cuentaCobrarDao.modificar(cuentaCobrar);
	}

	public void eliminar(CuentaCobrar cuentaCobrar) {
		cuentaCobrarDao.eliminar(cuentaCobrar);
	}

	/**
	 * Busca por Id
	 * @see com.factura.bo.CuentaCobrarBo#buscar(java.lang.Integer)
	 */
	@Override
	public CuentaCobrar buscar(Long id) {
		return cuentaCobrarDao.buscar(id);
	}

	@Override
	public TotalCuentaPorCobrarCommand sumarCuentasPorCobrar(Date fechaInicio, Date fechaFinal, Integer idEmpresa, Cliente cliente) {
		return cuentaCobrarDao.sumarCuentasPorCobrar(fechaInicio, fechaFinal, idEmpresa,cliente);
	}
	
	/**
	 * Genera lista de cuentas por cobrar de clientes
	 * @see com.emprendesoftcr.Bo.CuentaCobrarBo#cuentasPorCobrarbyFechasAndEmpresaAndClienteAndEstado(java.util.Date, java.util.Date, com.emprendesoftcr.modelo.Empresa, com.emprendesoftcr.modelo.Cliente, java.lang.Integer)
	 */
//	@Override
//	public Collection<Factura> cuentasPorCobrarbyFechasAndEmpresaAndClienteAndEstado(Date fechaInicio, Date fechaFin,Empresa empresa,Cliente cliente,String estado){
//		return cuentaCobrarDao.cuentasPorCobrarbyFechasAndEmpresaAndClienteAndEstado(fechaInicio, fechaFin, empresa, cliente, estado);
//		
//	}
	

}