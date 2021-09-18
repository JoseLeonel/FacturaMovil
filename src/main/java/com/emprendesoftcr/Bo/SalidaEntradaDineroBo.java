package com.emprendesoftcr.Bo;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.BindingResult;

import com.emprendesoftcr.modelo.SalidaEntradaDinero;
import com.emprendesoftcr.modelo.UsuarioCaja;
import com.emprendesoftcr.utils.RespuestaServiceValidator;
import com.emprendesoftcr.web.command.SalidaEntradaDineroCommand;

public interface SalidaEntradaDineroBo {

	void agregar(SalidaEntradaDinero salidaEntradaDinero);
	void eliminar(SalidaEntradaDinero salidaEntradaDinero);
	SalidaEntradaDinero findById(Long id) ;

	Collection<SalidaEntradaDinero> buscarPorUsuarioCaja(UsuarioCaja usuarioCaja);
	
	Collection<SalidaEntradaDinero> buscarPorUsuarioCajaAndTipo(UsuarioCaja usuarioCaja, Integer tipo);
	
	RespuestaServiceValidator<?> agregar(HttpServletRequest request, SalidaEntradaDineroCommand salidaEntradaDineroCommand, BindingResult result);

}
