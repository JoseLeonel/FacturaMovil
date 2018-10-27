package com.emprendesoftcr.Dao.Impl;

import java.util.Collection;
import java.util.Iterator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.emprendesoftcr.Dao.JqGridDao;
import com.emprendesoftcr.Utils.JqGridDelimitador;
import com.emprendesoftcr.Utils.JqGridFilter;

@Repository("jqGridDaoImpl")
public class JqGridDaoImpl implements JqGridDao {

	@PersistenceContext
	EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Object> listar(JqGridDelimitador delimitador) {
		Query query = generarQueryBusqueda(delimitador, false);
		if (!(delimitador.getInicio() == 0 && delimitador.getFin() == 1)) {
			query.setFirstResult(delimitador.getInicio());
			query.setMaxResults(delimitador.getFin() - delimitador.getInicio());
		}
		return query.getResultList();
	}

	@Override
	public Long contar(JqGridDelimitador delimitador) {
		Query query = generarQueryBusqueda(delimitador, true);
		return (Long) query.getSingleResult();
	}

	private Query generarQueryBusqueda(JqGridDelimitador delimitador, Boolean contar) {
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
		hql.append(" where 1 = 1 ");

		if (delimitador.getFiltros() != null) {
			for (Iterator<JqGridFilter> iterator = delimitador.getFiltros().iterator(); iterator.hasNext();) {
				JqGridFilter filter = (JqGridFilter) iterator.next();

				// Se buscar por el campo y valor indicado
				if (StringUtils.hasText(filter.getCampo()) && StringUtils.hasText(filter.getValor())) {
					if (filter.getOperadorBusqueda().equals("eq")) {
						hql.append(" and UPPER(obj." + filter.getCampo() + ") = '" + filter.getValor() + "'");
					} else if (filter.getOperadorBusqueda().equals("is null")) {
						hql.append(" and UPPER(obj." + filter.getCampo() + ") = " + filter.getValor());
					} else if (filter.getOperadorBusqueda().equals("is not null")) {
						hql.append(" and UPPER(obj." + filter.getCampo() + ") != " + filter.getValor());
					} else if (filter.getOperadorBusqueda().equals("ne")) {
						hql.append(" and UPPER(obj." + filter.getCampo() + ") != '" + filter.getValor() + "'");
					} else if (filter.getOperadorBusqueda().equals("in")) {
						hql.append(" and UPPER(obj." + filter.getCampo() + ") in (" + filter.getValor() + ")");
					} else {
						hql.append(" and UPPER(obj." + filter.getCampo() + ") like '%" + filter.getValor() + "%'");
					}
				}
			}
		}

		// Se define el ordenamiento
		if (StringUtils.hasText(delimitador.getTipoOrdenamiento()) && StringUtils.hasText(delimitador.getPropiedadOrdenamiento())) {
			hql.append(" order by obj.");
			hql.append(delimitador.getPropiedadOrdenamiento());
			hql.append(" ");
			hql.append(delimitador.getTipoOrdenamiento());
		} else {
			hql.append(" order by obj.id desc");
		}

		return entityManager.createQuery(hql.toString());
	}
}
