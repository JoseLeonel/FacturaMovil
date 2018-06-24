package com.factura.FacturaElectronica.Dao.Impl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.factura.FacturaElectronica.Dao.UsuarioCajaDao;
import com.factura.FacturaElectronica.modelo.Usuario;
import com.factura.FacturaElectronica.modelo.UsuarioCaja;

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
	 * @param usuarioCaja
	 * @param totalEfectivo
	 * @param totalTarjeta
	 * @param totalBanco
	 * @param totalCredito
	 * @param totalAbono
	 */
	@Override
	public void actualizarCaja(UsuarioCaja usuarioCaja, Double totalEfectivo, Double totalTarjeta, Double totalBanco, Double totalCredito, Double totalAbono) throws Exception {
		try {
			Double resultadoTotalBanco  = totalBanco + usuarioCaja.getTotalBanco();
			Double resultadoTotalEfectivo = totalEfectivo + usuarioCaja.getTotalEfectivo();
			Double resultadoTarjeta = totalTarjeta + usuarioCaja.getTotalTarjeta();
			Double resultadoAbono = totalAbono + usuarioCaja.getTotalAbono();
			Double resultadoNeto  = getTotalNeto(resultadoTotalEfectivo.doubleValue(),resultadoTarjeta.doubleValue(),resultadoTotalBanco.doubleValue(),resultadoAbono.doubleValue());
			usuarioCaja.setTotalCredito(sumarTotalCredito(totalCredito.doubleValue(),usuarioCaja.getTotalCredito().doubleValue()));
			usuarioCaja.setTotalBanco(resultadoTotalBanco);
			usuarioCaja.setTotalEfectivo(resultadoTotalEfectivo);
			usuarioCaja.setTotalTarjeta(resultadoTarjeta);
			usuarioCaja.setTotalAbono(resultadoAbono);
			usuarioCaja.setTotalNeto(resultadoNeto);
			modificar(usuarioCaja);

		} catch (Exception e) {
			log.info("** Error  aplicar getTotalEfectivo : " + e.getMessage() + " fecha " + new Date());
			throw e;
		}

	}
	
	private Double getTotalNeto( Double totalEfectivo, Double totalTarjeta, Double totalBanco,  Double totalAbono) {
		
		Double total  = totalEfectivo + totalTarjeta + totalBanco + totalAbono;
		Double totalNeto=  new Double(total.toString());
		
		return totalNeto;
		
	}
	
	private Double sumarTotalCredito(Double totalCredito,Double monto) {
		Double resultado = totalCredito + monto;
		
		return new Double(resultado.toString());
	}

}