package com.emprendesoftcr.Dao.Impl;

import java.util.ArrayList;
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

import com.emprendesoftcr.Dao.UsuarioCajaDao;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.modelo.UsuarioCaja;
import com.emprendesoftcr.modelo.sqlNativo.BaseNativeQuery;
import com.emprendesoftcr.modelo.sqlNativo.UsuarioCajaCategoriaArticulo;
import com.emprendesoftcr.utils.Constantes;
import com.emprendesoftcr.utils.Utils;

@Repository("usuarioCajaDao")
public class UsuarioCajaDaoImpl implements UsuarioCajaDao {

	@PersistenceContext
	EntityManager		entityManager;

	private Logger	log	= LoggerFactory.getLogger(this.getClass());

	@Override
	public void agregar(UsuarioCaja usuarioCaja) {
		entityManager.persist(usuarioCaja);
	}

	@Override
	public void modificar(UsuarioCaja usuarioCaja) {
		entityManager.merge(usuarioCaja);
	}

	@Override
	public void eliminar(UsuarioCaja usuarioCaja) {
		entityManager.remove(usuarioCaja);
	}

	/**
	 * Buscar una UsuarioCaja
	 * @see com.emprendesoftcr.Dao.UsuarioCajaDao#buscar(java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public UsuarioCaja buscar(Long id) {
		Query query = entityManager.createQuery("select obj from UsuarioCaja obj where obj.id = :id");
		query.setParameter("id", id);
		List<UsuarioCaja> results = query.getResultList();
		if (!results.isEmpty()) {
			return (UsuarioCaja) results.get(0);
		} else {
			return null;
		}
	}

	/**
	 * Buscar por usuario y estado
	 * @see com.emprendesoftcr.Dao.UsuarioCajaDao#findByUsuarioAndEstado(com.emprendesoftcr.modelo.Usuario, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public UsuarioCaja findByUsuarioAndEstado(Usuario usuario, String estado) {

		Query query = entityManager.createQuery("select obj from UsuarioCaja obj where obj.usuario = :usuario and obj.estado = :estado");
		query.setParameter("usuario", usuario);
		query.setParameter("estado", estado);
		List<UsuarioCaja> results = query.getResultList();
		if (!results.isEmpty()) {
			return (UsuarioCaja) results.get(0);
		} else {
			return null;
		}

	}

	/**
	 * @param usuarioCaja
	 * @param totalEfectivo
	 * @param totalTarjeta
	 * @param totalBanco
	 * @param totalCredito
	 * @param totalAbono
	 */
	@Override
	public void actualizarCaja(UsuarioCaja usuarioCaja, Double totalEfectivo, Double totalTarjeta, Double totalBanco, Double totalCredito, Double totalAbono, Double totalServicio) throws Exception {
		try {

			Double resultadoAbono = Constantes.ZEROS_DOUBLE;
			resultadoAbono = usuarioCaja.getTotalAbono() != null ? usuarioCaja.getTotalAbono() : Constantes.ZEROS_DOUBLE;
			resultadoAbono = totalAbono + resultadoAbono;

			usuarioCaja.setTotalCredito(Constantes.ZEROS_DOUBLE);
			usuarioCaja.setTotalAbono(Utils.roundFactura(resultadoAbono, 5));
			modificar(usuarioCaja);

		} catch (Exception e) {
			log.info("** Error  aplicar getTotalEfectivo : " + e.getMessage() + " fecha " + new Date());
			throw e;
		}

	}

	@Override
	public void actualizarCaja(UsuarioCaja usuarioCaja) throws Exception {
		StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery(Constantes.SP_ACTUALIZA_CAJA);

		// set parametros entrada
		storedProcedure.registerStoredProcedureParameter(Constantes.SP_ACTUALIZA_CAJA_ID_CAJA_USUARIO, Long.class, ParameterMode.IN);

		// Valores de entrada
		storedProcedure.setParameter(Constantes.SP_ACTUALIZA_CAJA_ID_CAJA_USUARIO, usuarioCaja.getId());
		storedProcedure.execute();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<UsuarioCajaCategoriaArticulo> agrupaArticulosCategoria(Integer empresaId, Long usuarioCajaId) {
		String queryStr = getQueryBase(UsuarioCajaCategoriaArticulo.class);
		queryStr = queryStr.replaceAll(":ID_EMPRESA", empresaId.toString());
		queryStr = queryStr.replaceAll(":ID_CAJA_USUARIO", usuarioCajaId.toString());
		Query query = entityManager.createNativeQuery(queryStr, UsuarioCajaCategoriaArticulo.class);
		return (ArrayList<UsuarioCajaCategoriaArticulo>) query.getResultList();
	}

	private static <T> String getQueryBase(Class<T> claseObjecto) {
		return ((claseObjecto).getDeclaredAnnotationsByType(BaseNativeQuery.class))[0].query();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<UsuarioCaja> usuarioCajaBy(Empresa empresa, String estado) {
		Query query = entityManager.createQuery("select obj from UsuarioCaja obj where  obj.usuario.empresa.id = :idEmpresa and obj.estado = :estado");
		query.setParameter("idEmpresa", empresa.getId());
		query.setParameter("estado", estado);
		return query.getResultList();
	}

	@Override
	public void eliminarConteo(UsuarioCaja usuarioCaja,Integer tipo) throws Exception {
		StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery(Constantes.SP_ELIMINAR_CONTEO);
		// set parametros entrada
		storedProcedure.registerStoredProcedureParameter(Constantes.SP_ELIMINA_CAJA_ID_CAJA_USUARIO, Long.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter(Constantes.SP_ELIMINA_CONTEO_TIPO, Integer.class, ParameterMode.IN);

		// Valores de entrada
		storedProcedure.setParameter(Constantes.SP_ELIMINA_CAJA_ID_CAJA_USUARIO, usuarioCaja.getId());
		storedProcedure.setParameter(Constantes.SP_ELIMINA_CONTEO_TIPO, tipo);
		storedProcedure.execute();
		
	}

}