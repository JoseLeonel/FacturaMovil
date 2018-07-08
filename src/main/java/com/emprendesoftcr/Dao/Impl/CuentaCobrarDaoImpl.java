package com.emprendesoftcr.Dao.Impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.emprendesoftcr.Dao.CuentaCobrarDao;
import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.modelo.CuentaCobrar;
import com.emprendesoftcr.modelo.Factura;

/**
 * Clientes por sucursal de empresa ClienteDaoImpl.
 * @author jose.
 * @since 18 mar. 2018
 */
@Repository("cuentaCobrarDao")
public class CuentaCobrarDaoImpl implements CuentaCobrarDao {

	@PersistenceContext
	EntityManager		entityManager;
	private Logger	log	= LoggerFactory.getLogger(this.getClass());

	public void agregar(CuentaCobrar cuentaCobrar) {
		entityManager.persist(cuentaCobrar);
	}

	public void modificar(CuentaCobrar cuentaCobrar) {
		entityManager.merge(cuentaCobrar);
	}

	public void eliminar(CuentaCobrar cuentaCobrar) {
		entityManager.remove(cuentaCobrar);
	}

	/**
	 * Busca por id y retorna la cuenta de cobrar
	 * @see com.factura.dao.CuentaCobrarDao#buscar(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	public CuentaCobrar buscar(Integer id) {
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
			cuentaCobrar.setNota(factura.getNota() == null?Constantes.CUENTA_POR_COBRAR_NOTA_AUTOMATICO:factura.getNota());
			cuentaCobrar.setRecibo(Constantes.EMPTY);
			cuentaCobrar.setTipo(Constantes.CUENTA_POR_COBRAR_TIPO_Automatica);
			cuentaCobrar.setTotal(factura.getTotalVentaNeta());
			cuentaCobrar.setTotalAbono(Constantes.ZEROS_DOUBLE);
			cuentaCobrar.setTotalComision(Constantes.ZEROS_DOUBLE);
			cuentaCobrar.setTotalSaldo(factura.getTotalVentaNeta());
			cuentaCobrar.setDescuento(Constantes.ZEROS_DOUBLE);
			cuentaCobrar.setUsuario(factura.getUsuarioCreacion());
			cuentaCobrar.setVendedor(factura.getVendedor());
			agregar(cuentaCobrar);

		} catch (Exception e) {
			log.error("** Error  crear cuentas por cobrar: " + e.getMessage() + " fecha " + new Date());
			throw e;
		}

	}

}