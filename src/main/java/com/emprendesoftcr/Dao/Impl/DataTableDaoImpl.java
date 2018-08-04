package com.emprendesoftcr.Dao.Impl;

import java.util.Collection;
import java.util.Iterator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.emprendesoftcr.Dao.DataTableDao;
import com.emprendesoftcr.Utils.DataTableDelimitador;
import com.emprendesoftcr.Utils.JqGridFilter;

/**
 * Clase para realizar listados con el objeto enviado es una clase generica
 * DataTableDaoImpl.
 * @author Leonel Hernandez Chaverri.
 * @since 8 mar. 2018
 */
@Repository("dataTableDao")
public class DataTableDaoImpl implements DataTableDao {

	@PersistenceContext
	EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public Collection<Object> listar(DataTableDelimitador delimitador) {
		Query query = generarQueryBusqueda(delimitador, false);
		if (delimitador.getLength() > 0) {
			query.setFirstResult(delimitador.getStart());
			query.setMaxResults(delimitador.getLength());

		}
		return query.getResultList();
	}

	public Long contar(DataTableDelimitador delimitador) {
		Query query = generarQueryBusqueda(delimitador, true);
		return (Long) query.getSingleResult();
	}

	/**
	 * Ejecucion del HSQL
	 * @param delimitador
	 * @param contar
	 * @return
	 */
	private Query generarQueryBusqueda(DataTableDelimitador delimitador, Boolean contar) {
		StringBuilder hql = null;
		if (contar) {
			hql = new StringBuilder("select count(obj) from " + delimitador.getTabla() + " obj ");

		} else {
			hql = new StringBuilder("from " + delimitador.getTabla() + " obj ");

			if (delimitador.getListaLeftJoin() != null) {
				for (Iterator<String> iterator = delimitador.getListaLeftJoin().iterator(); iterator.hasNext();) {
					String tablaLeft = (String) iterator.next();
					if (tablaLeft.contains(".")) {
						hql.append(" left join fetch " + tablaLeft + " " + tablaLeft.replace(".", "_"));

					} else {
						hql.append(" left join fetch obj." + tablaLeft + " " + tablaLeft);
					}
				}
			}
		}
		// validar si estamos consultando una empresa systema o una empresa especial
		hql.append(" where 1 = 1 ");

		if (delimitador.getFiltros() != null) {
			for (Iterator<JqGridFilter> iterator = delimitador.getFiltros().iterator(); iterator.hasNext();) {
				JqGridFilter filter = (JqGridFilter) iterator.next();
				// Se buscar por el campo y valor indicado
				if (StringUtils.hasText(filter.getCampo()) && StringUtils.hasText(filter.getValor())) {
					String toAppend = " and UPPER(obj." + filter.getCampo() + ") " + valorConOperador(filter.getOperadorBusqueda(), filter.getValor());

					hql.append(toAppend);
				}
			}
		}
		// Se define el ordenamiento
		if (StringUtils.hasText(delimitador.getColumnOrderDir()) && StringUtils.hasText(delimitador.getColumnData())) {
			hql.append(" order by obj.");
			hql.append(delimitador.getColumnData());
			hql.append(" ");
			hql.append(delimitador.getColumnOrderDir());
		}

		// else {
		hql.append(" order by obj.id asc");
		// }
		return entityManager.createQuery(hql.toString());
	}

	/**
	 * Asigna el operador a consultar
	 * @param op
	 * @param val
	 * @return
	 */
	private static String valorConOperador(String op, String val) {

		if (op.equals("like")) {
			return op + " '%" + val + "%'"; // like
		}
		if (op.equals("date<=") || op.equals("date=<") || op.equals("date>=") || op.equals("date=>")) {
			return dateCondition(op, val); // Para rangos de fechas
		}
		if (op.equals("dateFinal<=") || op.equals("dateFinal=<") || op.equals("dateFinal>=") || op.equals("dateFinal=>")) {
			return dateConditionFinal(op, val); // Para rangos de fechas
		}

		return op + " " + val;

	}

	/**
	 * Para consultar una fecha Inicial se usa para rangos de fechas
	 * @param op
	 * @param val
	 * @return
	 */
	private static String dateCondition(String op, String val) {
		String fecha = "'" + val + "'";
		return op.substring(4) + fecha  ;
	}

	/**
	 * Para concsultar una fecha final se usa para rangos de fechas
	 * @param op
	 * @param val
	 * @return
	 */
	private static String dateConditionFinal(String op, String val) {
		String fecha = "'" + val + "'";
		String dateCondition = op.substring(9) + " " + fecha ;
		return dateCondition;
	}
}
