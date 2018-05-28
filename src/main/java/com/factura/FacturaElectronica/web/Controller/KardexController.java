package com.factura.FacturaElectronica.web.Controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.util.WebUtils;

import com.factura.FacturaElectronica.Bo.InventarioBo;
import com.factura.FacturaElectronica.Bo.KardexBo;
import com.factura.FacturaElectronica.Bo.UsuarioBo;
import com.factura.FacturaElectronica.Utils.Constantes;
import com.factura.FacturaElectronica.Utils.RespuestaServiceValidator;
import com.factura.FacturaElectronica.modelo.Inventario;
import com.factura.FacturaElectronica.modelo.Kardex;
import com.factura.FacturaElectronica.modelo.Usuario;

/**
 * Registrar la entrada al inventario  y  Registrar una salida al inventario
 * KardexController.
 * @author jose.
 * @since 13 abr. 2018
 */
@Controller
public class KardexController {

	@Autowired
	private KardexBo			kardexBo;

	@Autowired
	private InventarioBo	inventarioBo;
	
	@Autowired
	private UsuarioBo	usuarioBo;
	
	

	/**
	 * Agregar una entrada al inventario de un articulo
	 * @param request
	 * @param model
	 * @param IdInventario
	 * @param kardex
	 * @param result
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("all")
	@RequestMapping(value = "/AgregarEntradaKardex.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator agregarEntrada(HttpServletRequest request, ModelMap model, @RequestParam("IdInventario") Integer IdInventario, @ModelAttribute Kardex kardex, BindingResult result, SessionStatus status) throws Exception {

		RespuestaServiceValidator respuestaServiceValidator = new RespuestaServiceValidator();
		try {
			Inventario inventario = inventarioBo.buscar(IdInventario);
			if (inventario == null) {
				result.rejectValue("codigo", "error.kardex.articulo.no.existe");
			}
			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
			}
			Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());
			kardexBo.entrada(inventario, kardex.getCantidadNueva(), kardex.getObservacion(), Constantes.CONSECUTIVO_INICIAL_INVENTARIO_NUEVO, Constantes.KARDEX_TIPO_ENTRADA, kardex.getMotivo(),usuarioSesion);
			inventario.setCantidad(inventarioBo.sumarCantidad(inventario, kardex.getCantidadNueva()));
			inventarioBo.modificar(inventario);

			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("kardex.agregar.entrada.correctamente", inventario);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	/**
	 * Registrar una salida
	 * @param request
	 * @param model
	 * @param IdInventario
	 * @param kardex
	 * @param result
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("all")
	@RequestMapping(value = "/AgregarSalidaKardex.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator agregarSalida(HttpServletRequest request, ModelMap model, @RequestParam("IdInventario") Integer IdInventario, @ModelAttribute Kardex kardex, BindingResult result, SessionStatus status) throws Exception {

		RespuestaServiceValidator respuestaServiceValidator = new RespuestaServiceValidator();
		try {
			Inventario inventario = inventarioBo.buscar(IdInventario);
			if (inventario == null) {
				result.rejectValue("codigo", "error.kardex.articulo.no.existe");
			}
			if(inventario.getCantidad() < kardex.getCantidadNueva()){
				result.rejectValue("cantidadNueva", "error.kardex.articulo.cantidad.mayor.cantidaDelIventario");
			}
			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
			}
			Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());
			kardexBo.salida(inventario, kardex.getCantidadNueva(), kardex.getObservacion(), Constantes.CONSECUTIVO_INICIAL_INVENTARIO_NUEVO, Constantes.KARDEX_TIPO_SALIDA, kardex.getMotivo(),usuarioSesion);
			inventario.setCantidad(inventarioBo.restarCantidad(inventario, kardex.getCantidadNueva()));
			inventarioBo.modificar(inventario);

			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("kardex.agregar.salida.correctamente", inventario);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	
}
