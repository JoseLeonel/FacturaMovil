package com.emprendesoftcr.Dao.Impl;

import java.sql.Blob;
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

import com.emprendesoftcr.Dao.HaciendaDao;
import com.emprendesoftcr.fisco.FacturaElectronicaUtils;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Hacienda;
import com.emprendesoftcr.utils.Constantes;

/**
 * Hacienda de los xml que se envian a tributacion.
 * @author jose.
 * @since 3 nov. 2018
 */
@Repository("haciendaDao")
public class HaciendaDaoImpl implements HaciendaDao {

	@PersistenceContext
	EntityManager		entityManager;

	private Logger	log	= LoggerFactory.getLogger(this.getClass());

	@Override
	public void agregar(Hacienda hacienda) {
		entityManager.persist(hacienda);

	}

	@Override
	public void modificar(Hacienda hacienda) {
		entityManager.merge(hacienda);

	}

	public void eliminar(Hacienda hacienda) {
		entityManager.remove(hacienda);
	}
	@Override
	public Hacienda findById(Long id) {
		Query query = entityManager.createQuery("select obj from Hacienda obj where obj.id = :id");
		query.setParameter("id", id);
		@SuppressWarnings("unchecked")
		List<Hacienda> results = query.getResultList();
		if (!results.isEmpty()) {
			return (Hacienda) results.get(0);
		} else {
			return null;
		}
	}

	@Override
	public Hacienda findByClave(String clave) {
		Query query = entityManager.createQuery("select obj from Hacienda obj where obj.clave = :clave ");
		query.setParameter("clave", clave);
		@SuppressWarnings("unchecked")
		List<Hacienda> results = query.getResultList();
		if (!results.isEmpty()) {
			return (Hacienda) results.get(0);
		} else {
			return null;
		}
	}

	@Override
	public Hacienda findByEmpresaAndClave(Empresa empresa, String clave) {
		Query query = entityManager.createQuery("select obj from Hacienda obj where obj.empresa = :empresa and obj.clave = :clave ");
		query.setParameter("empresa", empresa);
		query.setParameter("clave", clave);
		@SuppressWarnings("unchecked")
		List<Hacienda> results = query.getResultList();
		if (!results.isEmpty()) {
			return (Hacienda) results.get(0);
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Hacienda> findByEmpresaAndEstado(Empresa empresa, Integer estado) {
		Query query = entityManager.createQuery("select obj from Hacienda obj where obj.estado = :estado and obj.empresa = :empresa");
		query.setParameter("estado", estado);
		query.setParameter("empresa", empresa);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Hacienda> findByEstado(Integer estado, Integer estadoError) {
		Query query = entityManager.createQuery("select obj from Hacienda obj where obj.estado = :estado or obj.estado = :estadoError order by obj.empresa.id");
		query.setParameter("estado", estado);
		query.setParameter("estadoError", estadoError);
		query.setMaxResults(Constantes.BLOQUES_DOCUMENTOS_A_PROCESAR);

		List<Hacienda> results = query.getResultList();
		if (results != null) {
			if (!results.isEmpty()) {
				return results;
			} else {
				return null;
			}

		} else {
			return null;
		}

	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Collection<Hacienda> findByEmpresaAndEstadoAndFechas(Integer estado,Date fechaInicial, Date FechaFinal){
		Query query = entityManager.createQuery("select obj from Hacienda obj where  obj.estado = :estado and obj.created_at >= :fechaInicio and obj.created_at <= :fechaFin");
		query.setParameter("estado", estado);
		query.setParameter("fechaInicio", fechaInicial);
		query.setParameter("fechaFin", FechaFinal);
		query.setMaxResults(Constantes.BLOQUES_DOCUMENTOS_A_PROCESAR);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Hacienda> findByEstadoOrEstadoErrorAndEmpresa(Empresa empresa, Integer estado, Integer estadoError) {
		Query query = entityManager.createQuery("select obj from Hacienda obj where obj.estado in(:estado,:estadoError) and obj.empresa = :empresa ");
		query.setParameter("estado", estado);
		query.setParameter("estadoError", estadoError);
		query.setParameter("empresa", empresa);
		query.setMaxResults(Constantes.BLOQUES_DOCUMENTOS_A_PROCESAR);

		return query.getResultList();

	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Hacienda> findByEstadoAndNotificacion(Integer estado, Integer notificacion) {
		Query query = entityManager.createQuery("select obj from Hacienda obj where obj.estado = :estado and obj.notificacion = :notificacion ");
		query.setParameter("estado", estado);
		query.setParameter("notificacion", notificacion);
		query.setMaxResults(Constantes.BLOQUES_DOCUMENTOS_A_PROCESAR_CORREOS);

		return query.getResultList();
	}

	/**
	 * Actualizar hacienda desde SP
	 * @throws Exception 
	 * @see com.emprendesoftcr.Dao.HaciendaDao#findByClaveSP(java.lang.String)
	 */
	@Override
	public void findByClaveSP(Long idHacienda, Long numeroFactura,String clave, Integer estado, String xml, String mensajeHacienda) throws Exception {
		try {
			
			StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery(Constantes.SP_CALLBACK_HACIENDA);
			// set parametros entrada
			storedProcedure.registerStoredProcedureParameter(Constantes.SP_CALLBACK_HACIENDA_ID_HACIENDA, Long.class, ParameterMode.IN);
			storedProcedure.registerStoredProcedureParameter(Constantes.SP_CALLBACK_HACIENDA_ID_FACTURA, Long.class, ParameterMode.IN);

			storedProcedure.registerStoredProcedureParameter(Constantes.SP_CALLBACK_HACIENDA_CLAVE, String.class, ParameterMode.IN);
			storedProcedure.registerStoredProcedureParameter(Constantes.SP_CALLBACK_HACIENDA_ESTADO, Integer.class, ParameterMode.IN);
			storedProcedure.registerStoredProcedureParameter(Constantes.SP_CALLBACK_HACIENDA_XML, Blob.class, ParameterMode.IN);
			storedProcedure.registerStoredProcedureParameter(Constantes.SP_CALLBACK_HACIENDA_MENSAJE, Blob.class, ParameterMode.IN);
			// Valores de entrada Blob
			storedProcedure.setParameter(Constantes.SP_CALLBACK_HACIENDA_ID_HACIENDA, idHacienda);
			storedProcedure.setParameter(Constantes.SP_CALLBACK_HACIENDA_ID_FACTURA, numeroFactura);
			storedProcedure.setParameter(Constantes.SP_CALLBACK_HACIENDA_CLAVE, clave);
			storedProcedure.setParameter(Constantes.SP_CALLBACK_HACIENDA_ESTADO, estado);
			storedProcedure.setParameter(Constantes.SP_CALLBACK_HACIENDA_XML, FacturaElectronicaUtils.convertirStringToblod(xml));
			storedProcedure.setParameter(Constantes.SP_CALLBACK_HACIENDA_MENSAJE, FacturaElectronicaUtils.convertirStringToblod(mensajeHacienda));
			storedProcedure.execute();

		} catch (Exception e) {
			log.info("** Error callback actualizar el estado : " + e.getMessage() + " fecha " + new Date());
			throw e;
		}

	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Collection<Hacienda> findByEmpresaAndMigracionAndFechas(Integer migradoADisco, Date fechaInicial, Date FechaFinal,Integer cantidadMigrar) {
		Query query = entityManager.createQuery("select obj from Hacienda obj where  obj.status in (0,99)  and obj.fechaEmisor >= :fechaInicio and obj.fechaEmisor <= :fechaFin and obj.estado in (7,6) ");
		query.setParameter("fechaInicio", fechaInicial);
		query.setParameter("fechaFin", FechaFinal);
		query.setMaxResults(cantidadMigrar);
		return query.getResultList();
	}

}
