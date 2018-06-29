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
import com.factura.FacturaElectronica.Bo.MotivoSalidaBo;
import com.factura.FacturaElectronica.Bo.UsuarioBo;
import com.factura.FacturaElectronica.Utils.Constantes;
import com.factura.FacturaElectronica.Utils.DataTableDelimitador;
import com.factura.FacturaElectronica.Utils.JqGridFilter;
import com.factura.FacturaElectronica.Utils.RespuestaServiceDataTable;
import com.factura.FacturaElectronica.Utils.RespuestaServiceValidator;
import com.factura.FacturaElectronica.modelo.Empresa;
import com.factura.FacturaElectronica.modelo.MotivoSalida;
import com.factura.FacturaElectronica.modelo.Usuario;
import com.factura.FacturaElectronica.web.command.MotivoSalidaCommand;
import com.factura.FacturaElectronica.web.componentes.EmpresaPropertyEditor;
import com.factura.FacturaElectronica.web.componentes.MotivoSalidaPropertyEditor;
import com.factura.FacturaElectronica.web.componentes.StringPropertyEditor;
import com.google.common.base.Function;

/**
 * Controlar los motivos de salidas por almacen MotivoSalidasController.
 * @author jose.
 * @since 19 abr. 2018
 */
@Controller
public class MotivoSalidasController {

	private static final Function<Object, MotivoSalidaCommand>	TO_COMMAND	= new Function<Object, MotivoSalidaCommand>() {

																																						@Override
																																						public MotivoSalidaCommand apply(Object f) {
																																							return new MotivoSalidaCommand((MotivoSalida) f);
																																						};
																																					};

	@Autowired
	private DataTableBo																					dataTableBo;

	@Autowired
	private MotivoSalidaBo																			motivoSalidaBo;

	@Autowired
	private EmpresaBo																						empresaBo;

	@Autowired
	private UsuarioBo																						usuarioBo;

	@Autowired
	private MotivoSalidaPropertyEditor													motivoSalidaPropertyEditor;

	@Autowired
	private EmpresaPropertyEditor																empresaPropertyEditor;

	@Autowired
	private StringPropertyEditor																stringPropertyEditor;

	@InitBinder
	public void initBinder(WebDataBinder binder) {

		binder.registerCustomEditor(MotivoSalida.class, motivoSalidaPropertyEditor);
		binder.registerCustomEditor(Empresa.class, empresaPropertyEditor);
		binder.registerCustomEditor(String.class, stringPropertyEditor);
	}

	/**
	 * Listar JSP para mostrar las salidas
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/ListarMotivoSalidas", method = RequestMethod.GET)
	public String listar(ModelMap model) {
		return "views/motivoSalida/ListarMotivoSalidas";
	}

	/**
	 * Listar metodo ajax para mostrar los motivos de salidas del inventario
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/ListarMotivoSalidasAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarAjax(HttpServletRequest request, HttpServletResponse response) {

		DataTableDelimitador delimitadores = null;
		delimitadores = new DataTableDelimitador(request, "MotivoSalida");
		if (!request.isUserInRole(Constantes.ROL_ADMINISTRADOR_SISTEMA)) {
			String nombreUsuario = request.getUserPrincipal().getName();
			JqGridFilter dataTableFilter = usuarioBo.filtroPorEmpresa(nombreUsuario);
			delimitadores.addFiltro(dataTableFilter);
		}

		return UtilsForControllers.process(request, dataTableBo, delimitadores, TO_COMMAND);
	}

	/**
	 * Lista de motivos de salidas por empresa
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/ListarMotivoSalidasActivasAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listaraActivasAjax(HttpServletRequest request, HttpServletResponse response, @RequestParam Integer idEmpresa) {
		Empresa empresa = idEmpresa != null ? empresaBo.buscar(idEmpresa) : null;
		DataTableDelimitador delimitadores = null;
		if (empresa != null) {
			delimitadores = new DataTableDelimitador(request, "MotivoSalida");
			JqGridFilter dataTableFilter = new JqGridFilter("estado", "'" + Constantes.ESTADO_ACTIVO.toString() + "'", "=");
			delimitadores.addFiltro(dataTableFilter);
			dataTableFilter = new JqGridFilter("empresa.id", "'" + empresa.getId().toString() + "'", "=");
			delimitadores.addFiltro(dataTableFilter);

		}

		return UtilsForControllers.process(request, dataTableBo, delimitadores, TO_COMMAND);
	}

	/**
	 * Agregar metodo ajax para los motivos de salidas
	 * @param request
	 * @param model
	 * @param motivoSalida
	 * @param result
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/AgregarMotivoSalidaAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator agregar(HttpServletRequest request, ModelMap model, @ModelAttribute MotivoSalida motivoSalida, BindingResult result, SessionStatus status) throws Exception {

		RespuestaServiceValidator respuestaServiceValidator = new RespuestaServiceValidator();
		try {
			Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());

			MotivoSalida motivoSalidaBd = motivoSalidaBo.buscarPorDescripcionYEmpresa(motivoSalida.getDescripcion(), motivoSalida.getEmpresa());
			if (motivoSalidaBd != null) {
				result.rejectValue("descripcion", "error.motivoSalida.descripcion.existe");
			}

			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
			}
			motivoSalida.setCreated_at(new Date());
			motivoSalida.setUpdated_at(new Date());
			motivoSalida.setEstado(Constantes.ESTADO_ACTIVO);
			motivoSalida.setUsuario(usuarioSesion);
			motivoSalidaBo.agregar(motivoSalida);
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("motivoSalida.agregar.correctamente", motivoSalida);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	/**
	 * Modificar una motivoSalida
	 * @param request
	 * @param model
	 * @param motivoSalida
	 * @param result
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/ModificarMotivoSalidaAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator modificar(HttpServletRequest request, ModelMap model, @ModelAttribute MotivoSalida motivoSalida, BindingResult result, SessionStatus status) throws Exception {
		try {
			Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());

			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("motivoSalida.no.modificado", result.getAllErrors());
			}
			MotivoSalida motivoSalidaBD = motivoSalidaBo.buscar(motivoSalida.getId());

			if (motivoSalidaBD == null) {
				return RESPONSES.ERROR.MOTIVOSALIDA.NO_EXISTE;
			} else {
				MotivoSalida motivoSalidaValidar = null;
				if (!motivoSalida.getDescripcion().equals(motivoSalidaBD.getDescripcion())) {
					motivoSalidaValidar = motivoSalidaBo.buscarPorDescripcionYEmpresa(motivoSalida.getDescripcion(), motivoSalida.getEmpresa());
					if (motivoSalidaValidar != null) {
						result.rejectValue("descripcion", "error.motivoSalida.descripcion.existe");
					}
				}

				if (result.hasErrors()) {
					return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
				}
				motivoSalidaBD.setDescripcion(motivoSalida.getDescripcion());
				motivoSalidaBD.setUpdated_at(new Date());
				motivoSalidaBo.modificar(motivoSalidaBD);
				motivoSalida.setUsuario(usuarioSesion);
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("motivoSalida.modificado.correctamente", motivoSalidaBD);
			}

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	/**
	 * Mostrar la motivoSalida
	 * @param request
	 * @param model
	 * @param motivoSalida
	 * @param result
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/MostrarMotivoSalidaAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator mostrar(HttpServletRequest request, ModelMap model, @ModelAttribute MotivoSalida motivoSalida, BindingResult result, SessionStatus status) throws Exception {
		try {
			MotivoSalidaCommand motivoSalidaCommand = new MotivoSalidaCommand(motivoSalidaBo.buscar(motivoSalida.getId()));
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("mensaje.consulta.exitosa", motivoSalidaCommand);
		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	private static class RESPONSES {

		private static class OK {

			private static class MOTIVOSALIDA {

				private static final RespuestaServiceValidator	AGREGADO		= RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("motivoSalida.agregar.correctamente");
				private static final RespuestaServiceValidator	MODIFICADO	= RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("motivoSalida.modificado.correctamente");
			}
		}

		private static class ERROR {

			private static class MOTIVOSALIDA {

				private static final RespuestaServiceValidator NO_EXISTE = RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("error.motivoSalida.noExiste");
			}
		}
	}

}
