package com.emprendesoftcr.Bo.Impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.emprendesoftcr.Bo.ConteoManualCajaBo;
import com.emprendesoftcr.Dao.ConteoManualCajaDao;
import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.modelo.ConteoManualCaja;
import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.modelo.UsuarioCaja;
import com.emprendesoftcr.web.command.DenominacionCommand;

@EnableTransactionManagement
@Service("conteoManualCajaBo")
public class ConteoManualCajaBoImpl implements ConteoManualCajaBo {

	@Autowired
	private ConteoManualCajaDao	conteoManualCajaDao;

	private Logger							log	= LoggerFactory.getLogger(this.getClass());

	@Transactional
	@Override
	public void agregar(ConteoManualCaja conteoManualCaja) {
		conteoManualCajaDao.agregar(conteoManualCaja);

	}

	@Transactional
	@Override
	public void modificar(ConteoManualCaja conteoManualCaja) {
		conteoManualCajaDao.modificar(conteoManualCaja);

	}

	@Transactional
	@Override
	public void eliminar(ConteoManualCaja conteoManualCaja) {
		conteoManualCajaDao.eliminar(conteoManualCaja);
	}

	@Override
	public Collection<ConteoManualCaja> buscarPorUsuarioCaja(UsuarioCaja usuarioCaja) {

		return conteoManualCajaDao.buscarPorUsuarioCaja(usuarioCaja);
	}

	@Transactional
	@Override
	public void agregarConteo(ArrayList<DenominacionCommand> listaCoteo, UsuarioCaja usuarioCaja, Usuario usuarioCierra) {

		try {
			for (DenominacionCommand denominacionCommand : listaCoteo) {
				if (denominacionCommand.getTotal() != null && denominacionCommand.getTotal() > 0) {
					ConteoManualCaja conteoManualCaja = new ConteoManualCaja();
					conteoManualCaja.setCantidad(denominacionCommand.getCantidad() == null ? Constantes.ZEROS_DOUBLE : denominacionCommand.getCantidad());
					conteoManualCaja.setDenominacion(denominacionCommand.getDenominacion() == null ? Constantes.EMPTY : denominacionCommand.getDenominacion());
					conteoManualCaja.setTipo(denominacionCommand.getTipo() == null ? Constantes.ZEROS : denominacionCommand.getTipo());
					conteoManualCaja.setTotal(denominacionCommand.getTotal() == null ? Constantes.ZEROS_DOUBLE : denominacionCommand.getTotal());
					conteoManualCaja.setCreated_at(new Date());
					conteoManualCaja.setUpdated_at(new Date());
					conteoManualCaja.setUsuarioCaja(usuarioCaja);
					conteoManualCaja.setUsuarioCierra(usuarioCierra );
					conteoManualCajaDao.agregar(conteoManualCaja);
				}
			}

		} catch (Exception e) {
			log.info("** Error  agregarConteo: " + e.getMessage() + " fecha " + new Date());

			throw e;
		}

	}

	@Override
	public ConteoManualCaja buscar(Long id) {
		return conteoManualCajaDao.buscar(id);
	}

}
