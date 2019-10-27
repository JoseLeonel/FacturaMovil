package com.emprendesoftcr.Dao.Impl;


import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;

import org.springframework.stereotype.Repository;

import com.emprendesoftcr.Dao.GraficoVentasDao;
import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.GraficoVenta;

@Repository("graficoVentasDao")
public class GraficoVentasDaoImpl implements GraficoVentasDao {

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public Collection<GraficoVenta> findByAll() {
		StringBuilder hql = new StringBuilder();
		hql.append("select obj from GraficoVenta obj");
		Query query = entityManager.createQuery(hql.toString());
		return query.getResultList();
	}

	

	@Override
	public Collection<GraficoVenta> findByAllEmpresaAndAnno(Empresa empresa,Integer anno) {
		StringBuilder hql = new StringBuilder();
		hql.append("select obj from GraficoVenta obj");
		hql.append(" where obj.empresa.id = :idEmpresa ");
		Query query = entityManager.createQuery(hql.toString());
		query.setParameter("idEmpresa", empresa.getId());
		query.setParameter("anno", anno);
		return query.getResultList();
	}



	@Override
	public void actualizarGraficoVenta(Integer anno) throws Exception  {
		try {
			StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery(Constantes.SP_GRAFICO_VENTAS);
			
			// set parametros entrada
			storedProcedure.registerStoredProcedureParameter(Constantes.SP_GRAFICO_VENTAS_ANNO_IN, Integer.class, ParameterMode.IN);
		
			storedProcedure.setParameter(Constantes.SP_GRAFICO_VENTAS_ANNO_IN, anno);
			
			storedProcedure.execute();
		
			
		} catch (Exception e) {
			 e.printStackTrace();
		}
		

		
	}



	@Override
	public GraficoVenta findByEmpresaAndAnno(Empresa empresa, Integer mes,Integer anno) {
			Query query = entityManager.createQuery("select obj from GraficoVenta obj where obj.mes = :mes and obj.anno =  :anno and obj.empresa.id = :idEmpresa");
			query.setParameter("mes",mes );
			query.setParameter("anno",anno );
			query.setParameter("idEmpresa",empresa.getId() );
			return (GraficoVenta) query.getSingleResult();
	}


	
	
}