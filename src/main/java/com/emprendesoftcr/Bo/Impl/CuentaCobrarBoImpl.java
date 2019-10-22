package com.emprendesoftcr.Bo.Impl;

import java.util.Collection;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.emprendesoftcr.Bo.CuentaCobrarBo;
import com.emprendesoftcr.Dao.AbonoDao;
import com.emprendesoftcr.Dao.CuentaCobrarDao;
import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.Utils.Utils;
import com.emprendesoftcr.modelo.Abono;
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

@EnableTransactionManagement
@Service("cuentaCobrarBo")
public class CuentaCobrarBoImpl implements CuentaCobrarBo {

	@Autowired
	private CuentaCobrarDao	cuentaCobrarDao;

	@Autowired
	private AbonoDao				abonoDao;
	
	private Logger								log	= LoggerFactory.getLogger(this.getClass());

	@Transactional
	@Override
	public void agregar(CuentaCobrar cuentaCobrar) {
		cuentaCobrarDao.agregar(cuentaCobrar);
	}

	@Transactional
	@Override
	public void modificar(CuentaCobrar cuentaCobrar) {
		cuentaCobrarDao.modificar(cuentaCobrar);
	}

	@Transactional
	@Override
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
		return cuentaCobrarDao.sumarCuentasPorCobrar(fechaInicio, fechaFinal, idEmpresa, cliente);
	}

	/**
	 * Genera lista de cuentas por cobrar de clientes
	 * @see com.emprendesoftcr.Bo.CuentaCobrarBo#cuentasPorCobrarbyFechasAndEmpresaAndClienteAndEstado(java.util.Date, java.util.Date, com.emprendesoftcr.modelo.Empresa, com.emprendesoftcr.modelo.Cliente, java.lang.Integer)
	 */
	@Override
	public Collection<CuentaCobrar> cuentasPorCobrarbyFechasAndEmpresaAndClienteAndEstado(Date fechaInicio, Date fechaFin, Empresa empresa, Cliente cliente, String estado) {
		return cuentaCobrarDao.cuentasPorCobrarbyFechasAndEmpresaAndClienteAndEstado(fechaInicio, fechaFin, empresa, cliente, estado);

	}

	@Override
	public Collection<CuentaCobrar> cuentasPorCobrarbyFechasAndEmpresaAndClienteAndEstado(Empresa empresa, Cliente cliente, String estado) {
		return cuentaCobrarDao.cuentasPorCobrarbyFechasAndEmpresaAndClienteAndEstado(empresa, cliente, estado);

	}

	@Override
	public Collection<CuentaCobrar> cuentasPorCobrarbyEstado(String estado) {

		return cuentaCobrarDao.cuentasPorCobrarbyEstado(Constantes.CUENTA_POR_COBRAR_ESTADO_PENDIENTE);
	}

	@Transactional
	@Override
	public void modificarCuentaXCobrarPorNotaCredito(Factura notaCredito, Factura facturaAplicar) {

		try {
			CuentaCobrar cuentaCobrar = cuentaCobrarDao.buscarPorConsecutivo(notaCredito.getEmpresa(), facturaAplicar.getNumeroConsecutivo());
			Double saldo = cuentaCobrar.getTotalSaldo() == null ? Constantes.ZEROS_DOUBLE : cuentaCobrar.getTotalSaldo();
			saldo = Utils.roundFactura(saldo,2) - Utils.roundFactura(notaCredito.getTotalComprobante(),2);
			String estado = Constantes.CUENTA_POR_COBRAR_ESTADO_PENDIENTE;
			if (saldo <= Constantes.ZEROS_DOUBLE) {
				estado = Constantes.CUENTA_POR_COBRAR_ESTADO_CERRADO;
			}
			cuentaCobrar.setTotalSaldo(saldo);
			cuentaCobrar.setTotalAbono(Utils.roundFactura(cuentaCobrar.getTotalAbono(),2) + Utils.roundFactura(notaCredito.getTotalComprobante(),2));
			cuentaCobrar.setEstado(estado);
			modificar(cuentaCobrar);
			Abono abono = new Abono();
			abono.setCreated_at(new Date());
			abono.setUpdated_at(new Date());
			abono.setCuentaCobrar(cuentaCobrar);
			abono.setEstado(Constantes.ABONO_PAGAR_ESTADO_PAGADO);
			abono.setRecibo("NC:"+facturaAplicar.getNumeroConsecutivo());
			abono.setNota("Por concepto de nota credito,factura referencia:" + facturaAplicar.getNumeroConsecutivo());
			abono.setTotal(Utils.roundFactura(notaCredito.getTotalComprobante(),2));
			abono.setTotalBanco(Constantes.ZEROS_DOUBLE);
			abono.setTotalEfectivo(Utils.roundFactura(notaCredito.getTotalComprobante(),2));
			abono.setTotalTarjeta(Constantes.ZEROS_DOUBLE);
			abono.setTransferencia(Constantes.EMPTY);
			abono.setUsuario(notaCredito.getUsuarioCreacion());
			abono.setFechaPago(new Date());
			abonoDao.agregar(abono);

		} catch (Exception e) {
			log.error("** Error  crear la factura: " + e.getMessage() + " fecha " + new Date());
			throw e;
		}

	}

}