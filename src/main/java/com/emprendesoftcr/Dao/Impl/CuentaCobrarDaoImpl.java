package com.emprendesoftcr.Dao.Impl;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.emprendesoftcr.Dao.CuentaCobrarDao;
import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.Utils.Utils;
import com.emprendesoftcr.modelo.Cliente;
import com.emprendesoftcr.modelo.CuentaCobrar;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Factura;
import com.emprendesoftcr.web.command.TotalCuentaPorCobrarCommand;

/**
 * Cuentas por cobrar de clientes CuentaCobrarDaoImpl.
 * @author jose.
 * @since 3 nov. 2018
 */
@Repository("cuentaCobrarDao")
public class CuentaCobrarDaoImpl implements CuentaCobrarDao {

	@PersistenceContext
	EntityManager		entityManager;
	private Logger	log	= LoggerFactory.getLogger(this.getClass());

	@Override
	public void agregar(CuentaCobrar cuentaCobrar) {
		entityManager.persist(cuentaCobrar);
	}

	@Override
	public void modificar(CuentaCobrar cuentaCobrar) {
		entityManager.merge(cuentaCobrar);
	}

	@Override
	public void eliminar(CuentaCobrar cuentaCobrar) {
		entityManager.remove(cuentaCobrar);
	}

	/**
	 * Busca por id y retorna la cuenta de cobrar
	 * @see com.factura.dao.CuentaCobrarDao#buscar(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public CuentaCobrar buscar(Long id) {
		Query query = entityManager.createQuery("select obj from CuentaCobrar obj where obj.id = :id");
		query.setParameter("id", id);
		List<CuentaCobrar> results = query.getResultList();
		if (!results.isEmpty()) {
			return (CuentaCobrar) results.get(0);
		} else {
			return null;
		}
	}

	/**
	 * Buscar la letra de cambio retorna la cuenta por cobrar
	 * @see com.factura.dao.CuentaCobrarDao#buscarByLetraCambio(java.lang.String)
	 */
	public CuentaCobrar buscarPorLetraCambio(String letraCambio) {
		Query query = entityManager.createQuery("select obj from CuentaCobrar obj where obj.letraCambio = :letraCambio");
		query.setParameter("letraCambio", letraCambio);
		List<CuentaCobrar> results = query.getResultList();
		if (!results.isEmpty()) {
			return (CuentaCobrar) results.get(0);
		} else {
			return null;
		}
	}

	/**
	 * Buscar factura manual
	 * @param facturaManual
	 * @return
	 */
	public CuentaCobrar buscarPorFacturaManual(Integer facturaManual) {
		Query query = entityManager.createQuery("select obj from CuentaCobrar obj where obj.facturaManual = :facturaManual");
		query.setParameter("facturaManual", facturaManual);
		List<CuentaCobrar> results = query.getResultList();
		if (!results.isEmpty()) {
			return (CuentaCobrar) results.get(0);
		} else {
			return null;
		}

	}

	/**
	 * Crear cuenta por cobrar basada en una factura de credito
	 * @see com.emprendesoftcr.Dao.CuentaCobrarDao#crearCuentaXCobrar(com.emprendesoftcr.modelo.Factura)
	 */
	@Override
	public void crearCuentaXCobrar(Factura factura) {
		try {
			log.info("**Crear cuentas por cobrar: " + " fecha " + new Date());
			CuentaCobrar cuentaCobrar = new CuentaCobrar();
			cuentaCobrar.setNombreFactura(factura.getNombreFactura() == null ? Constantes.EMPTY : factura.getNombreFactura());
			cuentaCobrar.setCreated_at(new Date());
			cuentaCobrar.setUpdated_at(new Date());
			cuentaCobrar.setDescripcionArticulo(Constantes.CUENTA_POR_COBRAR_DESCRIPCION_AUTOMATICO);
			cuentaCobrar.setEmpresa(factura.getEmpresa());
			cuentaCobrar.setCliente(factura.getCliente());
			cuentaCobrar.setFactura(factura.getNumeroConsecutivo());
			cuentaCobrar.setFacturaManual(Constantes.ZEROS);
			cuentaCobrar.setCantidadPagos(Constantes.ZEROS_DOUBLE);
			cuentaCobrar.setEstado(Constantes.CUENTA_POR_COBRAR_ESTADO_PENDIENTE);
			cuentaCobrar.setFechaEntrega(factura.getFechaEmision());
			cuentaCobrar.setFechaPlazo(factura.getFechaCredito());
			cuentaCobrar.setLetraCambio(Constantes.EMPTY);
			cuentaCobrar.setMontoCouta(Constantes.ZEROS_DOUBLE);
			cuentaCobrar.setNota(factura.getNota() == null ? Constantes.CUENTA_POR_COBRAR_NOTA_AUTOMATICO : factura.getNota());
			cuentaCobrar.setRecibo(Constantes.EMPTY);
			cuentaCobrar.setTipo(Constantes.CUENTA_POR_COBRAR_TIPO_Automatica);
			cuentaCobrar.setTotal(Utils.roundFactura(factura.getTotalComprobante(), 2));
			cuentaCobrar.setTotalAbono(Constantes.ZEROS_DOUBLE);
			cuentaCobrar.setTotalComision(Constantes.ZEROS_DOUBLE);
			cuentaCobrar.setTotalSaldo(Utils.roundFactura(factura.getTotalComprobante(), 2));
			cuentaCobrar.setDescuento(Constantes.ZEROS_DOUBLE);
			cuentaCobrar.setUsuario(factura.getUsuarioCreacion());
			cuentaCobrar.setVendedor(factura.getVendedor());
			cuentaCobrar.setTipoCambio(factura.getTipoCambio());
			cuentaCobrar.setCodigoMoneda(factura.getCodigoMoneda());
			cuentaCobrar.setPlazoCredito(factura.getPlazoCredito());
			agregar(cuentaCobrar);

		} catch (Exception e) {
			log.error("** Error  crear cuentas por cobrar: " + e.getMessage() + " fecha " + new Date());
			throw e;
		}

	}

	@Override
	public CuentaCobrar buscarPorConsecutivo(Empresa empresa, String consecutivo) {
		Query query = entityManager.createQuery("select obj from CuentaCobrar obj where obj.factura = :consecutivo and obj.empresa = :empresa ");
		query.setParameter("consecutivo", consecutivo);
		query.setParameter("empresa", empresa);
		List<CuentaCobrar> results = query.getResultList();
		if (!results.isEmpty()) {
			return (CuentaCobrar) results.get(0);
		} else {
			return null;
		}

	}

	/**
	 * Totales de cuentas por cobrar
	 * @see com.emprendesoftcr.Dao.CuentaCobrarDao#sumarCuentasPorCobrar(java.util.Date, java.util.Date, java.lang.Integer, com.emprendesoftcr.modelo.Cliente)
	 */
	@Override
	public TotalCuentaPorCobrarCommand sumarCuentasPorCobrar(Date fechaInicio, Date fechaFinal, Integer idEmpresa, Cliente cliente) {
		StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery(Constantes.SP_TOTAL_CUENTA_COBRAR);

		// set parametros entrada
		storedProcedure.registerStoredProcedureParameter(Constantes.SP_TOTAL_CUENTA_COBRAR_IN_FECHA_INICIO, Date.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter(Constantes.SP_TOTAL_CUENTA_COBRAR_IN_FECHA_FIN, Date.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter(Constantes.SP_TOTAL_CUENTA_COBRAR_IN_ID_EMPRESA, Integer.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter(Constantes.SP_TOTAL_CUENTA_COBRAR_IN_ID_CLIENTE, Integer.class, ParameterMode.IN);

		// set parametros salida
		storedProcedure.registerStoredProcedureParameter(Constantes.SP_TOTAL_CUENTA_COBRAR_OUT_TOTAL, Double.class, ParameterMode.OUT);
		storedProcedure.registerStoredProcedureParameter(Constantes.SP_TOTAL_CUENTA_COBRAR_OUT_SALDO, Double.class, ParameterMode.OUT);
		storedProcedure.registerStoredProcedureParameter(Constantes.SP_TOTAL_CUENTA_COBRAR_OUT_ABONO, Double.class, ParameterMode.OUT);

		// Valores de entrada
		storedProcedure.setParameter(Constantes.SP_TOTAL_CUENTA_COBRAR_IN_FECHA_INICIO, fechaInicio);
		storedProcedure.setParameter(Constantes.SP_TOTAL_CUENTA_COBRAR_IN_FECHA_FIN, fechaFinal);
		storedProcedure.setParameter(Constantes.SP_TOTAL_CUENTA_COBRAR_IN_ID_EMPRESA, idEmpresa);
		storedProcedure.setParameter(Constantes.SP_TOTAL_CUENTA_COBRAR_IN_ID_CLIENTE, cliente.getId());
		storedProcedure.execute();

		// Se toma la respuesta
		return new TotalCuentaPorCobrarCommand((Double) storedProcedure.getOutputParameterValue(Constantes.SP_TOTAL_CUENTA_COBRAR_OUT_TOTAL), (Double) storedProcedure.getOutputParameterValue(Constantes.SP_TOTAL_CUENTA_COBRAR_OUT_SALDO), (Double) storedProcedure.getOutputParameterValue(Constantes.SP_TOTAL_CUENTA_COBRAR_OUT_ABONO));
	}

	/**
	 * Listado de cuentas por cobrar de un cliente
	 * @see com.emprendesoftcr.Dao.CuentaCobrarDao#cuentasPorCobrarbyFechasAndEmpresaAndClienteAndEstado(java.util.Date, java.util.Date, com.emprendesoftcr.modelo.Empresa, com.emprendesoftcr.modelo.Cliente, java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Collection<CuentaCobrar> cuentasPorCobrarbyFechasAndEmpresaAndClienteAndEstado(Date fechaInicio, Date fechaFin, Empresa empresa, Cliente cliente, String estado) {
		StringBuilder hql = new StringBuilder();
		hql.append("select obj from CuentaCobrar obj");
		hql.append(" where obj.empresa = :empresa ");
		if (estado != null) {
			if (!estado.equals(Constantes.COMBO_TODOS)) {
				hql.append(" and obj.estado = :estado ");

			}
		}
		if (cliente != null) {
			if (!cliente.equals(Constantes.COMBO_TODOS)) {
				hql.append("and obj.cliente = :cliente ");

			}
		}
		hql.append("and obj.created_at >= :fechaInicio and obj.created_at <= :fechaFin ");
		Query query = entityManager.createQuery(hql.toString());
		if (estado != null) {
			if (!estado.equals(Constantes.COMBO_TODOS)) {
				query.setParameter("estado", estado);

			}
		}
		if (cliente != null) {
			if (!cliente.equals(Constantes.COMBO_TODOS)) {
				query.setParameter("cliente", cliente);

			}
		}

		query.setParameter("empresa", empresa);
		query.setParameter("fechaInicio", fechaInicio);
		query.setParameter("fechaFin", fechaFin);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<CuentaCobrar> cuentasPorCobrarbyFechasAndEmpresaAndClienteAndEstado(Empresa empresa, Cliente cliente, String estado) {
		StringBuilder hql = new StringBuilder();
		hql.append("select obj from CuentaCobrar obj");
		hql.append(" where obj.empresa = :empresa ");
		if (estado != null) {
			if (!estado.equals(Constantes.COMBO_TODOS)) {
				hql.append(" and obj.estado = :estado ");

			}
		}
		if (cliente != null) {
			if (!cliente.equals(Constantes.COMBO_TODOS)) {
				hql.append("and obj.cliente = :cliente ");

			}
		}
		Query query = entityManager.createQuery(hql.toString());
		if (estado != null) {
			if (!estado.equals(Constantes.COMBO_TODOS)) {
				query.setParameter("estado", estado);

			}
		}
		if (cliente != null) {
			if (!cliente.equals(Constantes.COMBO_TODOS)) {
				query.setParameter("cliente", cliente);

			}
		}
		query.setParameter("empresa", empresa);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<CuentaCobrar> cuentasPorCobrarbyEstado(String estado) {
		StringBuilder hql = new StringBuilder();
		hql.append("select obj from CuentaCobrar obj");
		hql.append(" where ");
		if (estado != null) {
			if (!estado.equals(Constantes.COMBO_TODOS)) {
				hql.append(" obj.estado = :estado ");

			}
		}
		Query query = entityManager.createQuery(hql.toString());
		query.setParameter("estado", estado);
		return query.getResultList();
	}

}