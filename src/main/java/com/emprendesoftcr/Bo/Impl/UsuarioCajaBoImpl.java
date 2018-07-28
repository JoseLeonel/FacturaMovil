package com.emprendesoftcr.Bo.Impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.emprendesoftcr.Bo.UsuarioCajaBo;
import com.emprendesoftcr.Dao.UsuarioCajaDao;
import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.modelo.UsuarioCaja;

@Lazy
@Transactional
@EnableTransactionManagement
@Service("usuarioCajaBo")
public class UsuarioCajaBoImpl implements UsuarioCajaBo {

	@Lazy
	@Autowired
	UsuarioCajaDao usuarioCajaDao;
	
	private Logger	log	= LoggerFactory.getLogger(this.getClass());

	public void agregar(UsuarioCaja usuarioCaja) {
		usuarioCajaDao.agregar(usuarioCaja);
	}

	public void modificar(UsuarioCaja usuarioCaja) {
		usuarioCajaDao.modificar(usuarioCaja);
	}

	public void eliminar(UsuarioCaja usuarioCaja) {
		usuarioCajaDao.eliminar(usuarioCaja);
	}

	/**
	 * Buscar por id
	 * @param id
	 * @return
	 */
	@Override
	public UsuarioCaja buscar(Long id) {
		return usuarioCajaDao.buscar(id);
	}

	/**
	 * Buscar por usuario y estado
	 * @see com.emprendesoftcr.Bo.UsuarioCajaBo#findByUsuarioAndEstado(com.emprendesoftcr.modelo.Usuario, java.lang.String)
	 */
	@Override
	public UsuarioCaja findByUsuarioAndEstado(Usuario usuario, String estado) {
		return usuarioCajaDao.findByUsuarioAndEstado(usuario, estado);

	}

	/**
	 * Cerrar la caja
	 * @see com.emprendesoftcr.Bo.UsuarioCajaBo#cierreCaja(com.emprendesoftcr.modelo.UsuarioCaja)
	 */
	@Override
	public void cierreCaja(UsuarioCaja usuarioCaja) throws Exception {
		try {
			usuarioCaja.setUpdated_at(new Date());
			usuarioCaja.setEstado(Constantes.ESTADO_INACTIVO);
			Double resultado = Constantes.ZEROS_DOUBLE;
			resultado = usuarioCaja.getTotalBanco() +  usuarioCaja.getTotalEfectivo() +  usuarioCaja.getTotalTarjeta() + usuarioCaja.getTotalFondoInicial();
			usuarioCaja.setTotalNeto(resultado);
			agregar(usuarioCaja);

		} catch (Exception e) {
			log.info("** Error  aplicar cierreCaja : " + e.getMessage() + " fecha " + new Date());
			throw e;
		}

	}

	/**
	 * Actualiza Caja activa
	 * @see com.emprendesoftcr.Bo.UsuarioCajaBo#actualizarCaja(java.lang.Double, java.lang.Double, java.lang.Double, java.lang.Double, java.lang.Double)
	 */
	@Override
	public void actualizarCaja(UsuarioCaja usuarioCaja, Double totalEfectivo, Double totalTarjeta, Double totalBanco, Double totalCredito, Double totalAbono) throws Exception {
		usuarioCajaDao.actualizarCaja(usuarioCaja, totalEfectivo, totalTarjeta, totalBanco, totalCredito, totalAbono);
	}

}