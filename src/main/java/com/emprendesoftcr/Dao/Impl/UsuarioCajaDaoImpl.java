package com.emprendesoftcr.Dao.Impl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.emprendesoftcr.Dao.UsuarioCajaDao;
import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.Utils.Utils;
import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.modelo.UsuarioCaja;

@Repository("usuarioCajaDao")
public class UsuarioCajaDaoImpl implements UsuarioCajaDao {

	@PersistenceContext
	EntityManager		entityManager;

	private Logger	log	= LoggerFactory.getLogger(this.getClass());

	public void agregar(UsuarioCaja marca) {
		entityManager.persist(marca);
	}

	public void modificar(UsuarioCaja marca) {
		entityManager.merge(marca);
	}

	public void eliminar(UsuarioCaja marca) {
		entityManager.remove(marca);
	}

	/**
	 * Buscar una UsuarioCaja
	 * @see com.emprendesoftcr.Dao.UsuarioCajaDao#buscar(java.lang.Long)
	 */
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
			Double resultadoTotalBanco = Constantes.ZEROS_DOUBLE;
			resultadoTotalBanco = usuarioCaja.getTotalBanco() == null ? Constantes.ZEROS_DOUBLE : usuarioCaja.getTotalBanco();
			resultadoTotalBanco = totalBanco + resultadoTotalBanco;

			Double resultadoTotalEfectivo = Constantes.ZEROS_DOUBLE;
			resultadoTotalEfectivo = usuarioCaja.getTotalEfectivo() != null ? usuarioCaja.getTotalEfectivo() : Constantes.ZEROS_DOUBLE;
			resultadoTotalEfectivo = totalEfectivo + usuarioCaja.getTotalEfectivo();

			Double resultadoAbono = Constantes.ZEROS_DOUBLE;
			resultadoAbono = usuarioCaja.getTotalAbono() != null ? usuarioCaja.getTotalAbono() : Constantes.ZEROS_DOUBLE;
			resultadoAbono = totalAbono + resultadoAbono ;

			Double resultadoTarjeta = Constantes.ZEROS_DOUBLE;
			resultadoTarjeta = usuarioCaja.getTotalTarjeta() != null ? usuarioCaja.getTotalTarjeta() : Constantes.ZEROS_DOUBLE;
			resultadoTarjeta = totalTarjeta + resultadoTarjeta;

			Double resultadoServicio = Constantes.ZEROS_DOUBLE;
			resultadoServicio = usuarioCaja.getTotalServicio() != null ? usuarioCaja.getTotalServicio() : Constantes.ZEROS_DOUBLE;
			resultadoServicio = totalServicio + resultadoServicio ;

			Double resultadoNeto = resultadoTotalEfectivo + resultadoTarjeta + resultadoTotalBanco + resultadoAbono + resultadoServicio;
			usuarioCaja.setTotalCredito(Constantes.ZEROS_DOUBLE);
			usuarioCaja.setTotalBanco(Utils.roundFactura(resultadoTotalBanco, 5));
			usuarioCaja.setTotalEfectivo(Utils.roundFactura(resultadoTotalEfectivo, 5));
			usuarioCaja.setTotalTarjeta(Utils.roundFactura(resultadoTarjeta, 5));
			usuarioCaja.setTotalAbono(Utils.roundFactura(resultadoAbono, 5));
			usuarioCaja.setTotalNeto(Utils.roundFactura(resultadoNeto, 5));
			usuarioCaja.setTotalServicio(Utils.roundFactura(resultadoServicio, 5));
			modificar(usuarioCaja);

		} catch (Exception e) {
			log.info("** Error  aplicar getTotalEfectivo : " + e.getMessage() + " fecha " + new Date());
			throw e;
		}

	}

}