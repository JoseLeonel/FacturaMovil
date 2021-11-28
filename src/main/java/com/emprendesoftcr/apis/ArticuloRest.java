package com.emprendesoftcr.apis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.SessionStatus;

import com.emprendesoftcr.Bo.ArticuloBo;
import com.emprendesoftcr.Bo.ConsultasNativeBo;
import com.emprendesoftcr.Bo.UsuarioBo;
import com.emprendesoftcr.Bo.ValidateTokenBo;
import com.emprendesoftcr.modelo.Articulo;
import com.emprendesoftcr.modelo.Categoria;
import com.emprendesoftcr.modelo.ControlPrecioArticulo;
import com.emprendesoftcr.modelo.Marca;
import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.utils.DataTableDelimitador;
import com.emprendesoftcr.utils.RespuestaServiceDataTable;
import com.emprendesoftcr.utils.RespuestaServiceValidator;
import com.emprendesoftcr.web.command.CambiarPrecioArticuloCommand;
import com.emprendesoftcr.web.propertyEditor.ArticuloPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.CategoriaPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.MarcaPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.StringPropertyEditor;

@CrossOrigin
@RestController
@RequestMapping("/api/articulo")
public class ArticuloRest {

	@Autowired
	private ArticuloBo							articuloBo;

	@Autowired
	ConsultasNativeBo								consultasNativeBo;

	@Autowired
	private ArticuloPropertyEditor	articuloPropertyEditor;

	@Autowired
	private MarcaPropertyEditor			marcaPropertyEditor;

	@Autowired
	private CategoriaPropertyEditor	categoriaPropertyEditor;

	@Autowired
	private StringPropertyEditor		stringPropertyEditor;
	@Autowired
	private ValidateTokenBo					validateTokenBo;
	
	@Autowired
	private UsuarioBo usuarioBo;

	@InitBinder
	public void initBinder(WebDataBinder binder) {

		binder.registerCustomEditor(Articulo.class, articuloPropertyEditor);

		binder.registerCustomEditor(Marca.class, marcaPropertyEditor);
		binder.registerCustomEditor(Categoria.class, categoriaPropertyEditor);
		binder.registerCustomEditor(String.class, stringPropertyEditor);
	}



	@PostMapping("/AgregarArticuloAjax.do")
	public RespuestaServiceValidator<?> agregarLocal(HttpServletRequest request, ModelMap model, @RequestParam(value = "idPaquete", required = false) Integer idPaquete, @ModelAttribute Articulo articulo, BindingResult result, SessionStatus status) throws Exception {

		try {
			if (validateTokenBo.validarTokenApis(request) == false) {

				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("autenticacion.invalidad", result.getAllErrors());
			}
			if (articulo.getUnidadMedida() != null && articulo.getUnidadMedida().length() > 0) {
				articulo.setUnidadMedida(articulo.getUnidadMedida().length() > 20 ? articulo.getUnidadMedida().substring(0, 20) : articulo.getUnidadMedida());
			}
			articulo.setCantidadPaquete(idPaquete);
			return articuloBo.agregar(request, articulo, result);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	@SuppressWarnings("rawtypes")
	@PostMapping("/ModificarArticuloAjax.do")
	public RespuestaServiceValidator modificarLocal(HttpServletRequest request, ModelMap model, @ModelAttribute Articulo articulo, BindingResult result, SessionStatus status) throws Exception {

		try {
			if (validateTokenBo.validarTokenApis(request) == false) {

				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("autenticacion.invalidad", result.getAllErrors());
			}
			if (articulo.getUnidadMedida() != null && articulo.getUnidadMedida().length() > 0) {
				articulo.setUnidadMedida(articulo.getUnidadMedida().length() > 20 ? articulo.getUnidadMedida().substring(0, 20) : articulo.getUnidadMedida());
			}
			return articuloBo.modificar(request, articulo, result);

		} catch (Exception e) {
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
		}
	}

	@SuppressWarnings("rawtypes")
	@PostMapping("/CambiarPrecioAjax")
	public RespuestaServiceValidator cambiarPrecioLocal(HttpServletRequest request, HttpServletResponse response, ModelMap model, @ModelAttribute Articulo articulo, @RequestParam Double precioPublico, @RequestParam String codigo, @RequestParam String tipoImpuesto, @RequestParam Double impuesto, @RequestParam String descripcion, @RequestParam String tipoCodigo, String unidadMedida, BindingResult result, SessionStatus status) throws Exception {

		try {
			if (validateTokenBo.validarTokenApis(request) == false) {

				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("autenticacion.invalidad", result.getAllErrors());
			}
			return articuloBo.cambiarPrecio(request, response, articulo, precioPublico, codigo, tipoImpuesto, impuesto, descripcion, tipoCodigo, unidadMedida, result);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}

	}

	@SuppressWarnings("rawtypes")
	@PostMapping("/CambiarPrecioArticulo.do")
	public RespuestaServiceValidator cambiarPrecioArticuloLocal(HttpServletRequest request, HttpServletResponse response, ModelMap model, @ModelAttribute CambiarPrecioArticuloCommand cambiarPrecioArticuloCommand, BindingResult result, SessionStatus status) throws Exception {
		try {
			if (validateTokenBo.validarTokenApis(request) == false) {

				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("autenticacion.invalidad", result.getAllErrors());
			}
			return articuloBo.cambiarPrecioArticulo(request, response, model, cambiarPrecioArticuloCommand, result);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	@SuppressWarnings("rawtypes")
	@GetMapping("/CambiarPrecioAjax")
	public RespuestaServiceValidator cambiarPrecio(HttpServletRequest request, HttpServletResponse response, ModelMap model, @ModelAttribute Articulo articulo, @RequestParam Double precioPublico, @RequestParam String codigo, @RequestParam String tipoImpuesto, @RequestParam Double impuesto, @RequestParam String descripcion, @RequestParam String tipoCodigo, String unidadMedida, BindingResult result, SessionStatus status) throws Exception {

		try {
			if (articulo.getUnidadMedida() != null && articulo.getUnidadMedida().length() > 0) {
				articulo.setUnidadMedida(articulo.getUnidadMedida().length() > 20 ? articulo.getUnidadMedida().substring(0, 20) : articulo.getUnidadMedida());
			}
			return articuloBo.cambiarPrecio(request, response, articulo, precioPublico, codigo, tipoImpuesto, impuesto, descripcion, tipoCodigo, unidadMedida, result);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}

	}

	@SuppressWarnings("rawtypes")
	@GetMapping("/findArticuloByCodigojax")
	public RespuestaServiceValidator findArticuloByCodigojaxLocal(HttpServletRequest request, ModelMap model, @ModelAttribute Articulo articulo, HttpServletResponse response, @RequestParam String codigoArticulo, BindingResult result, SessionStatus status) {
		try {
			if (validateTokenBo.validarTokenApis(request) == false) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("autenticacion.invalidad", result.getAllErrors());
			}
			return articuloBo.findArticuloByCodigojax(request, articulo, response, codigoArticulo, result);
		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}
	
	@SuppressWarnings("rawtypes")
	@GetMapping("/findInventarioArticuloByCodigojax.do")
	public RespuestaServiceValidator findInventarioArticuloByCodigojax(HttpServletRequest request, ModelMap model, @ModelAttribute Articulo articulo, HttpServletResponse response, @RequestParam String codigoArticulo, BindingResult result, SessionStatus status) {
		try {
			
			return articuloBo.findArticuloByCodigojax(request, articulo, response, codigoArticulo, result);
		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}
	
	/**
	 * Listar el control de precio pendiente de aceptar
	 * @param request
	 * @param model
	 * @param controlPrecioArticulo
	 * @param response
	 * @param result
	 * @param status
	 * @return
	 */
	@SuppressWarnings({ "rawtypes" })
	@GetMapping("/listaControlPrecioPendienteAceptar")
	public RespuestaServiceDataTable listaControlPrecioPendienteAceptar(HttpServletRequest request, ModelMap model, @ModelAttribute ControlPrecioArticulo controlPrecioArticulo, HttpServletResponse response, BindingResult result, SessionStatus status) {
		Usuario usuario = usuarioBo.buscar(request.getUserPrincipal().getName());
		@SuppressWarnings("unused")
		DataTableDelimitador delimitadores = null;
		
		return articuloBo.listarByControlPrecioPendiente(request, response,  usuario);
	}
	@SuppressWarnings("all")
	@PostMapping("/ListarArticuloAjax.do")
	public RespuestaServiceDataTable listarLocalAjax(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "codigoArt", required = false) String codigoArt) throws IOException, ServletException {
		if (validateTokenBo.validarTokenApis(request) == false) {
			DataTableDelimitador delimitadores = null;
			delimitadores = new DataTableDelimitador(request, "Articulo");
			RespuestaServiceDataTable respuestaService = new RespuestaServiceDataTable();
			List<Object> solicitudList = new ArrayList<Object>();
			respuestaService.setRecordsTotal(0l);
			respuestaService.setRecordsFiltered(0l);
			respuestaService.setAaData(solicitudList);
			return respuestaService;
		}
		DataTableDelimitador delimitadores = null;
		String nombreUsuario = request.getUserPrincipal().getName();
		return articuloBo.listarByCodigoArticulo(request, response, codigoArt, nombreUsuario);
	}
}
