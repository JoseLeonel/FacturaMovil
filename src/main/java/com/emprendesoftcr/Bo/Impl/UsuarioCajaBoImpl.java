package com.emprendesoftcr.Bo.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.DoubleSummaryStatistics;
import java.util.stream.Collectors;

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
import com.emprendesoftcr.modelo.Caja;
import com.emprendesoftcr.modelo.ConteoManualCaja;
import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.modelo.UsuarioCaja;
import com.emprendesoftcr.modelo.sqlNativo.UsuarioCajaCategoriaArticulo;
import com.emprendesoftcr.web.command.DenominacionCommand;

@EnableTransactionManagement
@Service("usuarioCajaBo")
public class UsuarioCajaBoImpl implements UsuarioCajaBo {

	@Autowired
	UsuarioCajaDao	usuarioCajaDao;

	private Logger	log	= LoggerFactory.getLogger(this.getClass());

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
	@Transactional
	@Override
	public UsuarioCaja cierreCaja(UsuarioCaja usuarioCaja, ArrayList<DenominacionCommand> listaCoteo, Usuario usuario) throws Exception {
		try {

			usuarioCaja.setUpdated_at(new Date());
			usuarioCaja.setEstado(Constantes.ESTADO_INACTIVO);
			Double resultado = Constantes.ZEROS_DOUBLE;
			Double totalEfectivo = usuarioCaja.getTotalEfectivo() == null ? Constantes.ZEROS_DOUBLE : usuarioCaja.getTotalEfectivo();

			Double totalBanco = usuarioCaja.getTotalBanco() == null ? Constantes.ZEROS_DOUBLE : usuarioCaja.getTotalBanco();
			Double totalTarjeta = usuarioCaja.getTotalTarjeta() == null ? Constantes.ZEROS_DOUBLE : usuarioCaja.getTotalTarjeta();
			Double totalAbono = usuarioCaja.getTotalAbono() == null ? Constantes.ZEROS_DOUBLE : usuarioCaja.getTotalAbono();
			resultado = totalEfectivo + totalAbono + totalTarjeta + totalBanco;

			usuarioCaja.setTotalNeto(Utils.roundFactura(resultado, 2));

			usuarioCaja = agregarConteo(listaCoteo, usuarioCaja, usuario, 2);

			DoubleSummaryStatistics doubleSummaryStatistics = usuarioCaja.getConteoManualCajas().stream().filter(x -> x.getTipo().equals(Constantes.CONTEO_CIERRE_CAJA_TIPO)).collect(Collectors.summarizingDouble(ConteoManualCaja::getTotal));
			usuarioCaja.setTotalConversionColones(Utils.roundFactura(usuarioCaja.getConteoDolar() * usuarioCaja.getTipoCambio(), 2));
			usuarioCaja.setConteoManual(doubleSummaryStatistics.getSum() + usuarioCaja.getTotalConversionColones());

			usuarioCaja.setTotalConversionColones(Utils.roundFactura(usuarioCaja.getConteoDolar() * usuarioCaja.getTipoCambio(), 2));

			usuarioCaja.setCierreCaja(new Date());
			modificar(usuarioCaja);

		} catch (Exception e) {
			log.info("** Error  aplicar cierreCaja : " + e.getMessage() + " fecha " + new Date());
			throw e;
		}

		return usuarioCaja;
	}

	@Transactional
	private UsuarioCaja agregarConteo(ArrayList<DenominacionCommand> listaCoteo, UsuarioCaja usuarioCaja, Usuario usuario, Integer tipo) {
		UsuarioCaja usuarioCajaTemp = usuarioCaja;
		try {
			for (DenominacionCommand denominacionCommand : listaCoteo) {
				if (denominacionCommand.getTotal() != null && denominacionCommand.getTotal() > 0) {
					ConteoManualCaja conteoManualCaja = new ConteoManualCaja();
					conteoManualCaja.setCantidad(denominacionCommand.getCantidad() == null ? Constantes.ZEROS_DOUBLE : denominacionCommand.getCantidad());
					conteoManualCaja.setDenominacion(denominacionCommand.getDenominacion() == null ? Constantes.EMPTY : denominacionCommand.getDenominacion());
					conteoManualCaja.setTipo(denominacionCommand.getTipo() == null ? Constantes.ZEROS : denominacionCommand.getTipo());
					conteoManualCaja.setTotal(denominacionCommand.getTotal() == null ? Constantes.ZEROS_DOUBLE : denominacionCommand.getTotal());
					conteoManualCaja.setMoneda(denominacionCommand.getMoneda());
					conteoManualCaja.setCreated_at(new Date());
					conteoManualCaja.setUpdated_at(new Date());
					conteoManualCaja.setUsuarioCaja(usuarioCaja);
					conteoManualCaja.setUsuarioCierra(tipo.equals(1) ? null : usuario);
					usuarioCajaTemp.addConteoManual(conteoManualCaja);
				}
			}

		} catch (Exception e) {
			log.info("** Error  agregarConteo: " + e.getMessage() + " fecha " + new Date());

			throw e;
		}
		return usuarioCajaTemp;
	}

	/**
	 * Actualiza Caja activa
	 * @see com.emprendesoftcr.Bo.UsuarioCajaBo#actualizarCaja(java.lang.Double, java.lang.Double, java.lang.Double, java.lang.Double, java.lang.Double)
	 */
	@Transactional
	@Override
	public void actualizarCaja(UsuarioCaja usuarioCaja, Double totalEfectivo, Double totalTarjeta, Double totalBanco, Double totalCredito, Double totalAbono, Double totalServicio) throws Exception {
		usuarioCajaDao.actualizarCaja(usuarioCaja, totalEfectivo, totalTarjeta, totalBanco, totalCredito, totalAbono, totalServicio);
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

	@Transactional
	@Override
	public UsuarioCaja aperturaCaja(ArrayList<DenominacionCommand> listaCoteo, Usuario usuario, Caja caja) throws Exception {
		UsuarioCaja usuarioCaja = new UsuarioCaja();
		try {

			usuarioCaja.setCreated_at(new Date());
			usuarioCaja.setCaja(caja);
			usuarioCaja.setUsuario(usuario);
			usuarioCaja.setUpdated_at(new Date());
			usuarioCaja.setEstado(Constantes.ESTADO_ACTIVO);
			usuarioCaja.setUsuario(usuario);
			usuarioCaja.setTotalBanco(Constantes.ZEROS_DOUBLE);
			usuarioCaja.setTotalEfectivo(Constantes.ZEROS_DOUBLE);
			usuarioCaja.setTotalNeto(Constantes.ZEROS_DOUBLE);
			usuarioCaja.setTotalTarjeta(Constantes.ZEROS_DOUBLE);
			usuarioCaja.setTotalAbono(Constantes.ZEROS_DOUBLE);
			usuarioCaja.setTotalCredito(Constantes.ZEROS_DOUBLE);
			usuarioCaja.setTotalServicio(Constantes.ZEROS_DOUBLE);
			usuarioCaja = agregarConteo(listaCoteo, usuarioCaja, usuario, 1);
			if (usuarioCaja.getConteoManualCajas() != null && usuarioCaja.getConteoManualCajas().size() > 0) {
				DoubleSummaryStatistics doubleSummaryStatistics = usuarioCaja.getConteoManualCajas().stream().filter(x -> x.getTipo().equals(Constantes.CONTEO_APERTURA_CAJA_TIPO)).collect(Collectors.summarizingDouble(ConteoManualCaja::getTotal));
				usuarioCaja.setTotalFondoInicial(doubleSummaryStatistics.getSum());

			} else {
				usuarioCaja.setTotalFondoInicial(Constantes.ZEROS_DOUBLE);
			}
			usuarioCaja.setConteoManual(Constantes.ZEROS_DOUBLE);
			usuarioCaja.setConteoTarjeta(Constantes.ZEROS_DOUBLE);

			usuarioCaja.setTotalDolares(Constantes.ZEROS_DOUBLE);
			usuarioCaja.setTipoCambio(Constantes.ZEROS_DOUBLE);
			usuarioCaja.setTotalConversionColones(Constantes.ZEROS_DOUBLE);
			usuarioCaja.setNotaCredito(Constantes.ZEROS_DOUBLE);
			usuarioCaja.setNotaDebito(Constantes.ZEROS_DOUBLE);

			agregar(usuarioCaja);

//			usuarioCajaDao.modificar(usuarioCaja);

		} catch (Exception e) {
			log.info("** Error  aplicar aperturaCaja : " + e.getMessage() + " fecha " + new Date());
			throw e;
		}
		return usuarioCaja;
	}

}