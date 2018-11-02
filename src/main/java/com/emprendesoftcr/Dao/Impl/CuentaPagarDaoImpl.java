package com.emprendesoftcr.Dao.Impl;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.emprendesoftcr.Dao.CuentaPagarDao;
import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.modelo.CuentaCobrar;
import com.emprendesoftcr.modelo.CuentaPagar;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Proveedor;

/**
 * Clientes por sucursal de empresa ClienteDaoImpl.
 * @author jose.
 * @since 18 mar. 2018
 */
@Repository("cuentaPagarDao")
public class CuentaPagarDaoImpl implements CuentaPagarDao {

	@PersistenceContext
	EntityManager		entityManager;


	public void agregar(CuentaPagar cuentaCobrar) {
		entityManager.persist(cuentaCobrar);
	}

	public void modificar(CuentaPagar cuentaCobrar) {
		entityManager.merge(cuentaCobrar);
	}

	public void eliminar(CuentaPagar cuentaCobrar) {
		entityManager.remove(cuentaCobrar);
	}

	/**
	 * Busca por id y retorna la cuenta de cobrar
	 * @see com.factura.dao.CuentaCobrarDao#buscar(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	public CuentaPagar buscar(Long id) {
		Query query = entityManager.createQuery("select obj from CuentaPagar obj where obj.id = :id");
		query.setParameter("id", id);
		List<CuentaPagar> results = query.getResultList();
		if (!results.isEmpty()) {
			return (CuentaPagar) results.get(0);
		} else {
			return null;
		}
	}
	
	/**
	 * Listado de cuentas por cobrar de un cliente
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Collection<CuentaPagar> cuentasPorPagarbyFechasAndEmpresaAndClienteAndEstado(Date fechaInicio, Date fechaFin, Empresa empresa, Proveedor proveedor, String estado) {
		StringBuilder hql = new StringBuilder();
		hql.append("select obj from CuentaCobrar obj");
		hql.append(" where obj.empresa = :empresa ");
		if (estado != null) {
			if (!estado.equals(Constantes.COMBO_TODOS)) {
				hql.append(" and obj.estado = :estado ");

			}
		}
		if (proveedor != null) {
			if (!proveedor.equals(Constantes.COMBO_TODOS)) {
				hql.append("and obj.proveedor = :proveedor ");

			}
		}
		hql.append("and obj.created_at >= :fechaInicio and obj.created_at <= :fechaFin ");
		Query query = entityManager.createQuery(hql.toString());
		if (estado != null) {
			if (!estado.equals(Constantes.COMBO_TODOS)) {
				query.setParameter("estado", estado);

			}
		}
		if (proveedor != null) {
			if (!proveedor.equals(Constantes.COMBO_TODOS)) {
				query.setParameter("proveedor", proveedor);

			}
		}

		query.setParameter("empresa", empresa);
		query.setParameter("fechaInicio", fechaInicio);
		query.setParameter("fechaFin", fechaFin);
		return query.getResultList();
	}
	
	
}