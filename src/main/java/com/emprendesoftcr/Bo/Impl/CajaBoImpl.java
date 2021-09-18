package com.emprendesoftcr.Bo.Impl;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import com.emprendesoftcr.Bo.CajaBo;
import com.emprendesoftcr.Bo.UsuarioBo;
import com.emprendesoftcr.Dao.CajaDao;
import com.emprendesoftcr.modelo.Caja;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.utils.RespuestaServiceValidator;
import com.emprendesoftcr.web.command.CajaCommand;

/**
 * Caja es las diferentes cajas de una empresa lo importante es la terminal 00001 son 5 digitos
 * CajaBoImpl.
 * @author jose.
 * @since 3 nov. 2018
 */
@EnableTransactionManagement
@Service("cajaBo")
public class CajaBoImpl implements CajaBo {

	@Autowired
	private CajaDao cajaDao;

	@Autowired
	private UsuarioBo usuarioBo;
	@Transactional
	@Override
	public void agregar(Caja caja) {
		cajaDao.agregar(caja);
	}

	@Transactional
	@Override
	public void modificar(Caja caja) {
		cajaDao.modificar(caja);
	}

	@Transactional
	@Override
	public void eliminar(Caja caja) {
		cajaDao.eliminar(caja);
	}

	/**
	 * Buscar por id
	 * @param id
	 * @return
	 */
	@Override
	public Caja buscar(Long id) {
		return cajaDao.buscar(id);
	}

	/**
	 * Buscar por descripcion
	 * @see com.emprendesoftcr.Bo.CajaBo#findByDescripcionAndEmpresa(java.lang.String, com.emprendesoftcr.modelo.Empresa)
	 */
	@Override
	public Caja findByDescripcionAndEmpresa(String descripcion, Empresa empresa) {
		return cajaDao.findByDescripcionAndEmpresa(descripcion, empresa);
	};

	/**
	 * buscar por terminal
	 * @see com.emprendesoftcr.Bo.CajaBo#findByTerminalAndEmpresa(java.lang.String, com.emprendesoftcr.modelo.Empresa)
	 */
	@Override
	public Caja findByTerminalAndEmpresa(String terminal, Empresa empresa) {
		return cajaDao.findByTerminalAndEmpresa(terminal, empresa);
	}

	@Override
	public Caja buscarCajaActiva(Empresa empresa,Usuario usuario) {
		
		return cajaDao.buscarCajaActiva(empresa,usuario);
	}
	@Transactional
	@Override
	public RespuestaServiceValidator<?> agregar(HttpServletRequest request, Caja caja, BindingResult result) {
			try {
			Usuario usuario = usuarioBo.buscar(request.getUserPrincipal().getName());
			Caja cajaBd = findByDescripcionAndEmpresa(caja.getDescripcion(), usuario.getEmpresa());
			if (cajaBd != null) {
				result.rejectValue("descripcion", "error.caja.descripcion.existe");
			}

			cajaBd = findByTerminalAndEmpresa(caja.getTerminal(), usuario.getEmpresa());
			if (cajaBd != null) {
				result.rejectValue("terminal", "error.caja.terminal.existe");
			}

			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
			}
			caja.setEmpresa(usuario.getEmpresa());
			caja.setCreated_at(new Date());
			caja.setUpdated_at(new Date());

			caja.setUsuario(usuario);
			agregar(caja);
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("caja.agregar.correctamente", caja);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}
	@Transactional
	@Override
	public RespuestaServiceValidator<?> modificar(HttpServletRequest request, Caja caja, BindingResult result) {
		try {
			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("caja.no.modificado", result.getAllErrors());
			}
			Usuario usuario = usuarioBo.buscar(request.getUserPrincipal().getName());
			Caja cajaBD = buscar(caja.getId());

			if (cajaBD == null) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
			} else {
				Caja cajaValidar = null;
				if (!caja.getDescripcion().equals(cajaBD.getDescripcion())) {
					cajaValidar = findByDescripcionAndEmpresa(caja.getDescripcion(), usuario.getEmpresa());
					if (cajaValidar != null) {
						result.rejectValue("descripcion", "error.caja.descripcion.existe");
					}
				}
				if (!caja.getTerminal().equals(cajaBD.getTerminal())) {
					cajaValidar = findByTerminalAndEmpresa(caja.getTerminal(), usuario.getEmpresa());
					if (cajaValidar != null) {
						result.rejectValue("terminal", "error.caja.terminal.existe");
					}

				}

				if (result.hasErrors()) {
					return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
				}
				cajaBD.setDescripcion(caja.getDescripcion());
				cajaBD.setTerminal(caja.getTerminal());
				cajaBD.setUpdated_at(new Date());
				cajaBD.setEstado(caja.getEstado());
				modificar(cajaBD);
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("caja.modificado.correctamente", cajaBD);
			}

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	@Override
	public RespuestaServiceValidator<?> mostrar(HttpServletRequest request, Caja caja) {
		try {
			CajaCommand cajaCommand = new CajaCommand(buscar(caja.getId()));
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("mensaje.consulta.exitosa", cajaCommand);
		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}
	
	

}