package com.factura.FacturaElectronica.Dao.Impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.factura.FacturaElectronica.Dao.UsuarioCajaDao;
import com.factura.FacturaElectronica.Utils.Constantes;
import com.factura.FacturaElectronica.modelo.Empresa;
import com.factura.FacturaElectronica.modelo.Usuario;
import com.factura.FacturaElectronica.modelo.UsuarioCaja;

@Repository("usuarioCajaDao")
public class UsuarioCajaDaoImpl implements UsuarioCajaDao {

	@PersistenceContext
	EntityManager entityManager;

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
	 * @see com.factura.FacturaElectronica.Dao.UsuarioCajaDao#buscar(java.lang.Long)
	 */
	@Override
	public UsuarioCaja buscar(Integer id) {
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
	 * @see com.factura.FacturaElectronica.Dao.UsuarioCajaDao#findByUsuarioAndEstado(com.factura.FacturaElectronica.modelo.Usuario, java.lang.String)
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
	 * Actualizar Caja
	 * @see com.factura.FacturaElectronica.Dao.UsuarioCajaDao#actualizarCaja(com.factura.FacturaElectronica.modelo.UsuarioCaja, java.lang.Double, java.lang.Double, java.lang.Double, java.lang.Double, java.lang.Double)
	 */
	@Override
	public void actualizarCaja(UsuarioCaja usuarioCaja, Double totalEfectivo, Double totalTarjeta, Double totalBanco, Double totalCredito, Double totalAbono) {
		usuarioCaja.setTotalCredito(usuarioCaja.getTotalCredito() == null ? totalCredito : usuarioCaja.getTotalCredito() + totalCredito);
		usuarioCaja.setTotalBanco(usuarioCaja.getTotalBanco() == null ? totalBanco : usuarioCaja.getTotalBanco() + totalBanco);
		usuarioCaja.setTotalEfectivo(usuarioCaja.getTotalEfectivo() == null ? totalEfectivo : usuarioCaja.getTotalEfectivo() + totalEfectivo);
		usuarioCaja.setTotalTarjeta(usuarioCaja.getTotalTarjeta() == null ? totalTarjeta : usuarioCaja.getTotalTarjeta() + totalTarjeta);
		usuarioCaja.setTotalAbono(usuarioCaja.getTotalAbono() == null ? totalAbono : usuarioCaja.getTotalAbono() + totalAbono);
		usuarioCaja.setTotalNeto(usuarioCaja.getTotalNeto() == null ? totalBanco : usuarioCaja.getTotalNeto() + totalEfectivo + totalTarjeta + totalBanco+totalAbono);
		modificar(usuarioCaja);
	}

}