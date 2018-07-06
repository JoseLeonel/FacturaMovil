package com.factura.FacturaElectronica.web.Controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

import com.factura.FacturaElectronica.Bo.DataTableBo;
import com.factura.FacturaElectronica.Bo.EmpresaBo;
import com.factura.FacturaElectronica.Bo.MarcaBo;
import com.factura.FacturaElectronica.Bo.UsuarioBo;
import com.factura.FacturaElectronica.Utils.Constantes;
import com.factura.FacturaElectronica.Utils.DataTableDelimitador;
import com.factura.FacturaElectronica.Utils.DataTableFilter;
import com.factura.FacturaElectronica.Utils.JqGridFilter;
import com.factura.FacturaElectronica.Utils.RespuestaServiceDataTable;
import com.factura.FacturaElectronica.Utils.RespuestaServiceValidator;
import com.factura.FacturaElectronica.modelo.Empresa;
import com.factura.FacturaElectronica.modelo.Marca;
import com.factura.FacturaElectronica.modelo.Usuario;
import com.factura.FacturaElectronica.web.command.MarcaCommand;
import com.factura.FacturaElectronica.web.componentes.EmpresaPropertyEditor;
import com.factura.FacturaElectronica.web.componentes.MarcaPropertyEditor;
import com.factura.FacturaElectronica.web.componentes.StringPropertyEditor;
import com.google.common.base.Function;

/**
 * Marcas de los articulos MarcasController.
 * @author jose.
 * @since 19 abr. 2018
 */
@Controller
public class MarcasController {

	private static final Function<Object, MarcaCommand>	TO_COMMAND	= new Function<Object, MarcaCommand>() {

																																		@Override
																																		public MarcaCommand apply(Object f) {
																																			return new MarcaCommand((Marca) f);
																																		};
																																	};

	@Autowired
	private DataTableBo																	dataTableBo;

	@Autowired
	private MarcaBo																			marcaBo;

	@Autowired
	private EmpresaBo																		empresaBo;

	@Autowired
	private UsuarioBo																		usuarioBo;

	@Autowired
	private MarcaPropertyEditor													marcaPropertyEditor;

	@Autowired
	private EmpresaPropertyEditor												empresaPropertyEditor;

	@Autowired
	private StringPropertyEditor												stringPropertyEditor;

	@InitBinder
	public void initBinder(WebDataBinder binder) {

		binder.registerCustomEditor(Marca.class, marcaPropertyEditor);
		binder.registerCustomEditor(Empresa.class, empresaPropertyEditor);
		binder.registerCustomEditor(String.class, stringPropertyEditor);
	}

	/**
	 * Mostrar el JSP de las marcas
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/ListarMarcas", method = RequestMethod.GET)
	public String listar(ModelMap model) {
		return "views/marca/ListarMarcas";
	}

	/**
	 * Listar metodo ajax para mostrar las lista de las masrcas
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/ListarMarcasAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarAjax(HttpServletRequest request, HttpServletResponse response) {

		DataTableDelimitador delimitadores = null;
		delimitadores = new DataTableDelimitador(request, "Marca");
		if (!request.isUserInRole(Constantes.ROL_ADMINISTRADOR_SISTEMA)) {
			String nombreUsuario = request.getUserPrincipal().getName();
			JqGridFilter dataTableFilter = usuarioBo.filtroPorEmpresa(nombreUsuario);
			delimitadores.addFiltro(dataTableFilter);
		}

		return UtilsForControllers.process(request, dataTableBo, delimitadores, TO_COMMAND);
	}

	/**
	 * Listar metodo ajax para mostrar las marcas activas
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/ListarMarcasActivasAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarActivasAjax(HttpServletRequest request, HttpServletResponse response) {
		DataTableDelimitador delimitadores = null;
		delimitadores = new DataTableDelimitador(request, "Marca");
		JqGridFilter dataTableFilter = new JqGridFilter("estado", "'" + Constantes.ESTADO_ACTIVO.toString() + "'", "=");
		delimitadores.addFiltro(dataTableFilter);
		String nombreUsuario = request.getUserPrincipal().getName();
		dataTableFilter = usuarioBo.filtroPorEmpresa(nombreUsuario);
		delimitadores.addFiltro(dataTableFilter);
		return UtilsForControllers.process(request, dataTableBo, delimitadores, TO_COMMAND);
	}

	/**
	 * Agregar una marca
	 * @param request
	 * @param model
	 * @param marca
	 * @param result
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/AgregarMarcaAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator agregar(HttpServletRequest request, ModelMap model, @ModelAttribute Marca marca, BindingResult result, SessionStatus status) throws Exception {

		RespuestaServiceValidator respuestaServiceValidator = new RespuestaServiceValidator();
		try {
			Usuario usuario = usuarioBo.buscar(request.getUserPrincipal().getName());
			Marca marcaBd = marcaBo.buscarPorDescripcionYEmpresa(marca.getDescripcion(), usuario.getEmpresa());
			if (marcaBd != null) {
				result.rejectValue("descripcion", "error.marca.descripcion.existe");
			}

			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
			}
			
			marca.setEmpresa(usuario.getEmpresa());
			marca.setCreated_at(new Date());
			marca.setUpdated_at(new Date());
			marca.setEstado(Constantes.ESTADO_ACTIVO);
			marcaBo.agregar(marca);
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("marca.agregar.correctamente", marca);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	/**
	 * Modificar una Marca
	 * @param request
	 * @param model
	 * @param marca
	 * @param result
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/ModificarMarcaAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator modificar(HttpServletRequest request, ModelMap model, @ModelAttribute Marca marca, BindingResult result, SessionStatus status) throws Exception {
		try {
			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("marca.no.modificado", result.getAllErrors());
			}
			Marca marcaBD = marcaBo.buscar(marca.getId());
			Usuario usuario = usuarioBo.buscar(request.getUserPrincipal().getName());
			if (marcaBD == null) {
				return RESPONSES.ERROR.MARCA.NO_EXISTE;
			} else {
				Marca marcaValidar = null;
				if (!marca.getDescripcion().equals(marcaBD.getDescripcion())) {
					marcaValidar = marcaBo.buscarPorDescripcionYEmpresa(marca.getDescripcion(), usuario.getEmpresa());
					if (marcaValidar != null) {
						result.rejectValue("descripcion", "error.marca.descripcion.existe");
					}
				}

				if (result.hasErrors()) {
					return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
				}
				marcaBD.setDescripcion(marca.getDescripcion());
				marcaBD.setUpdated_at(new Date());
				marcaBo.modificar(marcaBD);
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("marca.modificado.correctamente", marcaBD);
			}

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	/**
	 * Mostrar una marca
	 * @param request
	 * @param model
	 * @param marca
	 * @param result
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/MostrarMarcaAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator mostrar(HttpServletRequest request, ModelMap model, @ModelAttribute Marca marca, BindingResult result, SessionStatus status) throws Exception {
		try {
			MarcaCommand marcaCommand = new MarcaCommand(marcaBo.buscar(marca.getId()));
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("mensaje.consulta.exitosa", marcaCommand);
		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	@SuppressWarnings("all")
	private static class RESPONSES {

		private static class OK {

			private static class MARCA {

				private static final RespuestaServiceValidator	AGREGADO		= RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("marca.agregar.correctamente");
				private static final RespuestaServiceValidator	MODIFICADO	= RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("marca.modificado.correctamente");
			}
		}

		private static class ERROR {

			private static class MARCA {

				private static final RespuestaServiceValidator NO_EXISTE = RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("error.marca.noExiste");
			}
		}
	}

}
