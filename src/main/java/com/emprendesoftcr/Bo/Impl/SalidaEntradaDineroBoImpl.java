package com.emprendesoftcr.Bo.Impl;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import com.emprendesoftcr.Bo.SalidaEntradaDineroBo;
import com.emprendesoftcr.Bo.UsuarioBo;
import com.emprendesoftcr.Bo.UsuarioCajaBo;
import com.emprendesoftcr.Dao.SalidaEntradaDineroDao;
import com.emprendesoftcr.modelo.SalidaEntradaDinero;
import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.modelo.UsuarioCaja;
import com.emprendesoftcr.utils.Constantes;
import com.emprendesoftcr.utils.RespuestaServiceValidator;
import com.emprendesoftcr.web.command.SalidaEntradaDineroCommand;

@EnableTransactionManagement
@Service("salidaEntradaDineroBo")
public class SalidaEntradaDineroBoImpl implements SalidaEntradaDineroBo {

	@Autowired
	SalidaEntradaDineroDao salidaEntradaDineroDao;
	@Autowired
	UsuarioCajaBo usuarioCajaBo;
	@Autowired
	UsuarioBo usuarioBo;

	@Transactional
	@Override
	public void eliminar(SalidaEntradaDinero salidaEntradaDinero) {
		salidaEntradaDineroDao.eliminar(salidaEntradaDinero);
	}

	@Override
	public void agregar(SalidaEntradaDinero salidaEntradaDinero) {
		salidaEntradaDineroDao.agregar(salidaEntradaDinero);

	}

	@Override
	public Collection<SalidaEntradaDinero> buscarPorUsuarioCaja(UsuarioCaja usuarioCaja) {

		return salidaEntradaDineroDao.buscarPorUsuarioCaja(usuarioCaja);
	}

	@Override
	public Collection<SalidaEntradaDinero> buscarPorUsuarioCajaAndTipo(UsuarioCaja usuarioCaja, Integer tipo) {

		return salidaEntradaDineroDao.buscarPorUsuarioCajaAndTipo(usuarioCaja, tipo);
	}

	@Override
	public SalidaEntradaDinero findById(Long id) {

		return salidaEntradaDineroDao.findById(id);
	}

	@Transactional
	@Override
	public RespuestaServiceValidator<?> agregar(HttpServletRequest request,
			SalidaEntradaDineroCommand salidaEntradaDineroCommand, BindingResult result) {
	
		try {
			Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());

			UsuarioCaja usuarioCaja = usuarioCajaBo.findByUsuarioAndEstado(usuarioSesion, Constantes.ESTADO_ACTIVO);
			if (usuarioCaja == null) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("factura.error.factura.no.hay.cajas.abierta",
						result.getAllErrors());
			}
			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion",
						result.getAllErrors());
			}
			SalidaEntradaDinero salidaEntradaDinero = new SalidaEntradaDinero();

			salidaEntradaDinero.setCreated_at(new Date());
			salidaEntradaDinero.setTipo(salidaEntradaDineroCommand.getTipo());
			salidaEntradaDinero.setDescripcion(salidaEntradaDineroCommand.getDescripcion());
			salidaEntradaDinero.setTotal(salidaEntradaDineroCommand.getTotal());
			salidaEntradaDinero.setUsuariocaja(usuarioCaja);
			salidaEntradaDinero.setUsuarioResponsable(usuarioSesion);
			usuarioCaja.addSalidaEntradaDinero(salidaEntradaDinero);
			usuarioCajaBo.agregar(usuarioCaja);
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("salidaEntradaDinero.agregar.correctamente",
					salidaEntradaDinero);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

}
