package com.emprendesoftcr.Bo.Impl;

import java.util.ArrayList;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.emprendesoftcr.Bo.UsuarioCajaBo;
import com.emprendesoftcr.Dao.UsuarioCajaDao;
import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.Utils.Utils;
import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.modelo.UsuarioCaja;
import com.emprendesoftcr.modelo.sqlNativo.UsuarioCajaCategoriaArticulo;

@EnableTransactionManagement
@Service("usuarioCajaBo")
public class UsuarioCajaBoImpl implements UsuarioCajaBo {

	@Autowired
	UsuarioCajaDao usuarioCajaDao;

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Transactional
	@Override
	public void agregar(UsuarioCaja usuarioCaja) {
		usuarioCajaDao.agregar(usuarioCaja);
	}

	@Transactional
	@Override
	public void modificar(UsuarioCaja usuarioCaja) {
		usuarioCajaDao.modificar(usuarioCaja);
	}

	@Transactional
	@Override
	public void eliminar(UsuarioCaja usuarioCaja) {
		usuarioCajaDao.eliminar(usuarioCaja);
	}

	/**
	 * Buscar por id
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public UsuarioCaja buscar(Long id) {
		return usuarioCajaDao.buscar(id);
	}

	/**
	 * Buscar por usuario y estado
	 * 
	 * @see com.emprendesoftcr.Bo.UsuarioCajaBo#findByUsuarioAndEstado(com.emprendesoftcr.modelo.Usuario,
	 *      java.lang.String)
	 */
	@Override
	public UsuarioCaja findByUsuarioAndEstado(Usuario usuario, String estado) {
		return usuarioCajaDao.findByUsuarioAndEstado(usuario, estado);

	}

	/**
	 * Cerrar la caja
	 * 
	 * @see com.emprendesoftcr.Bo.UsuarioCajaBo#cierreCaja(com.emprendesoftcr.modelo.UsuarioCaja)
	 */
	@Transactional
	@Override
	public void cierreCaja(UsuarioCaja usuarioCaja) throws Exception {
		try {
			usuarioCaja.setUpdated_at(new Date());
			usuarioCaja.setEstado(Constantes.ESTADO_INACTIVO);
			Double resultado = Constantes.ZEROS_DOUBLE;
			Double totalEfectivo = usuarioCaja.getTotalEfectivo() == null ? Constantes.ZEROS_DOUBLE
					: usuarioCaja.getTotalEfectivo();
			Double totalBanco = usuarioCaja.getTotalBanco() == null ? Constantes.ZEROS_DOUBLE
					: usuarioCaja.getTotalBanco();
			Double totalTarjeta = usuarioCaja.getTotalTarjeta() == null ? Constantes.ZEROS_DOUBLE
					: usuarioCaja.getTotalTarjeta();
			Double totalAbono = usuarioCaja.getTotalAbono() == null ? Constantes.ZEROS_DOUBLE
					: usuarioCaja.getTotalAbono();
			resultado = totalEfectivo + totalAbono + totalTarjeta + totalBanco;
			usuarioCaja.setTotalNeto(Utils.roundFactura(resultado, 2));
			
			usuarioCaja.setBillete50000( usuarioCaja.getBillete50000() == null ? Constantes.ZEROS_DOUBLE
					: usuarioCaja.getBillete50000());

			usuarioCaja.setBillete20000(usuarioCaja.getBillete20000() == null ? Constantes.ZEROS_DOUBLE
					: usuarioCaja.getBillete20000());

			
			usuarioCaja.setBillete10000(usuarioCaja.getBillete10000() == null ? Constantes.ZEROS_DOUBLE
					: usuarioCaja.getBillete10000());

			usuarioCaja.setBillete5000(usuarioCaja.getBillete5000() == null ? Constantes.ZEROS_DOUBLE
					: usuarioCaja.getBillete5000());

			usuarioCaja.setBillete2000( usuarioCaja.getBillete2000() == null ? Constantes.ZEROS_DOUBLE
					: usuarioCaja.getBillete2000());

			usuarioCaja.setBillete1000( usuarioCaja.getBillete1000() == null ? Constantes.ZEROS_DOUBLE
					: usuarioCaja.getBillete1000());

			usuarioCaja.setMoneda500(usuarioCaja.getMoneda500() == null ? Constantes.ZEROS_DOUBLE
					: usuarioCaja.getMoneda500());

			usuarioCaja.setMoneda100( usuarioCaja.getMoneda100() == null ? Constantes.ZEROS_DOUBLE
					: usuarioCaja.getMoneda100());
			
			usuarioCaja.setMoneda50(usuarioCaja.getMoneda50() == null ? Constantes.ZEROS_DOUBLE
					: usuarioCaja.getMoneda50() );

			usuarioCaja.setMoneda25( usuarioCaja.getMoneda25() == null ? Constantes.ZEROS_DOUBLE
					: usuarioCaja.getMoneda25());

			usuarioCaja.setMoneda10(usuarioCaja.getMoneda10() == null ? Constantes.ZEROS_DOUBLE
					: usuarioCaja.getMoneda10());
			
			usuarioCaja.setMoneda5(usuarioCaja.getMoneda5() == null ? Constantes.ZEROS_DOUBLE
					: usuarioCaja.getMoneda5() );
			
			usuarioCaja.setConteoTarjeta(usuarioCaja.getConteoTarjeta() == null ? Constantes.ZEROS_DOUBLE
					: usuarioCaja.getConteoTarjeta());

			usuarioCaja.setConteoDolar( usuarioCaja.getConteoDolar() == null ? Constantes.ZEROS_DOUBLE
					: usuarioCaja.getConteoDolar());

			usuarioCaja.setConteoManual(Constantes.ZEROS_DOUBLE);
			
			usuarioCaja.setConteoManual(usuarioCaja.getBillete5000() + usuarioCaja.getBillete20000() + usuarioCaja.getBillete10000() + usuarioCaja.getBillete5000() + usuarioCaja.getBillete2000() + usuarioCaja.getBillete1000() + usuarioCaja.getMoneda500() + usuarioCaja.getMoneda100() + usuarioCaja.getMoneda50() + usuarioCaja.getMoneda25() + usuarioCaja.getMoneda10() + usuarioCaja.getMoneda5() );
			usuarioCaja.setTotalConversionColones(Utils.roundFactura(usuarioCaja.getConteoDolar() * usuarioCaja.getTipoCambio(),2));
			
			usuarioCaja.setConteoManual(Utils.roundFactura(usuarioCaja.getConteoManual() + usuarioCaja.getTotalConversionColones(),2));
			usuarioCaja.setDiferencia(usuarioCaja.getConteoManual()  - usuarioCaja.getTotalNeto());
			
			

			modificar(usuarioCaja);

		} catch (Exception e) {
			log.info("** Error  aplicar cierreCaja : " + e.getMessage() + " fecha " + new Date());
			throw e;
		}

	}

	/**
	 * Actualiza Caja activa
	 * 
	 * @see com.emprendesoftcr.Bo.UsuarioCajaBo#actualizarCaja(java.lang.Double,
	 *      java.lang.Double, java.lang.Double, java.lang.Double, java.lang.Double)
	 */
	@Transactional
	@Override
	public void actualizarCaja(UsuarioCaja usuarioCaja, Double totalEfectivo, Double totalTarjeta, Double totalBanco,
			Double totalCredito, Double totalAbono, Double totalServicio) throws Exception {
		usuarioCajaDao.actualizarCaja(usuarioCaja, totalEfectivo, totalTarjeta, totalBanco, totalCredito, totalAbono,
				totalServicio);
	}

	@Transactional
	@Override
	public void actualizarCaja(UsuarioCaja usuarioCaja) throws Exception {
		usuarioCajaDao.actualizarCaja(usuarioCaja);
	}

	@Override
	public ArrayList<UsuarioCajaCategoriaArticulo> agrupaArticulosCategoria(Integer empresaId, Long usuarioCajaId) {
		return usuarioCajaDao.agrupaArticulosCategoria(empresaId, usuarioCajaId);
	}

}