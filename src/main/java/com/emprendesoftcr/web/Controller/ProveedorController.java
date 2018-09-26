package com.emprendesoftcr.web.Controller;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

import com.emprendesoftcr.Bo.DataTableBo;
import com.emprendesoftcr.Bo.ProveedorBo;
import com.emprendesoftcr.Bo.UsuarioBo;
import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.Utils.DataTableDelimitador;
import com.emprendesoftcr.Utils.JqGridFilter;
import com.emprendesoftcr.Utils.RespuestaServiceDataTable;
import com.emprendesoftcr.Utils.RespuestaServiceValidator;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Proveedor;
import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.web.command.ProveedorCommand;
import com.emprendesoftcr.web.propertyEditor.EmpresaPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.ProveedorPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.StringPropertyEditor;
import com.google.common.base.Function;

/**
 * Controlar los proveedores por empresa ProveedorController.
 * @author jose.
 * @since 19 abr. 2018
 */
@Controller
public class ProveedorController {

	private static final Function<Object, ProveedorCommand>	TO_COMMAND	= new Function<Object, ProveedorCommand>() {

																																				@Override
																																				public ProveedorCommand apply(Object f) {
																																					return new ProveedorCommand((Proveedor) f);
																																				};
																																			};

	@Autowired
	private ProveedorBo																			proveedorBo;

	@Autowired
	private UsuarioBo																				usuarioBo;

	@Autowired
	private DataTableBo																			dataTableBo;

	@Autowired
	private ProveedorPropertyEditor													proveedorPropertyEditor;

	@Autowired
	private EmpresaPropertyEditor														empresaPropertyEditor;

	@Autowired
	private StringPropertyEditor														stringPropertyEditor;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Empresa.class, empresaPropertyEditor);
		binder.registerCustomEditor(Proveedor.class, proveedorPropertyEditor);
		binder.registerCustomEditor(String.class, stringPropertyEditor);
	}

	/**
	 * Listar JSP de los proveedores
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/ListarProveedores", method = RequestMethod.GET)
	public String listar(ModelMap model) {
		return "views/proveedores/ListarProveedores";
	}

	/**
	 * Listar los proveedores ajax
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("all")
	@RequestMapping(value = "/ListarProveedoresAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarAjax(HttpServletRequest request, HttpServletResponse response) {

		DataTableDelimitador delimitadores = null;
		delimitadores = new DataTableDelimitador(request, "Proveedor");
		if (!request.isUserInRole(Constantes.ROL_ADMINISTRADOR_SISTEMA)) {
			String nombreUsuario = request.getUserPrincipal().getName();
			JqGridFilter dataTableFilter = usuarioBo.filtroPorEmpresa(nombreUsuario);
			delimitadores.addFiltro(dataTableFilter);
		}

		return UtilsForControllers.process(request, dataTableBo, delimitadores, TO_COMMAND);
	}

	/**
	 * Agregar metodo ajax para registrar un proveedor
	 * @param request
	 * @param model
	 * @param proveedor
	 * @param result
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("all")
	@RequestMapping(value = "/AgregarProveedorAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator agregar(HttpServletRequest request, ModelMap model, @ModelAttribute Proveedor proveedor, BindingResult result, SessionStatus status) throws Exception {
		try {
			Proveedor proveedorValidar = null;
			Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());
			proveedorValidar = proveedorBo.buscarPorCedulaYEmpresa(proveedor.getCedula(), usuarioSesion.getEmpresa());
			if (proveedorValidar != null) {
				result.rejectValue("cedula", "error.proveedor.cedula.existe");
			}

			proveedorValidar = proveedorBo.buscarPorNombreCompletoYEmpresa(proveedor.getNombreCompleto(), proveedor.getEmpresa());
			if (proveedorValidar != null) {
				result.rejectValue("nombreCompleto", "error.proveedor.nombreCompleto.existe");
			}

			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
			}
      proveedor.setEmpresa(usuarioSesion.getEmpresa());
			proveedor.setCreated_at(new Date());
			proveedor.setUpdated_at(new Date());
			proveedorBo.agregar(proveedor);
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("proveedor.agregar.correctamente", proveedor);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	/**
	 * Modificar una sucursal de una empresa
	 * @param request
	 * @param model
	 * @param proveedor
	 * @param result
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("all")
	@RequestMapping(value = "/ModificarProveedorAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator modificar(HttpServletRequest request, ModelMap model, @ModelAttribute Proveedor proveedor, BindingResult result, SessionStatus status) throws Exception {
		try {
			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("proveedor.no.modificado", result.getAllErrors());
			}
			Proveedor proveedorBD = proveedorBo.buscar(proveedor.getId());
			Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());
			if (proveedorBD == null) {
				return RESPONSES.ERROR.PROVEEDOR.NO_EXISTE;
			}
			Proveedor proveedorValidar = null;
			if (!proveedor.getCedula().equals(proveedorBD.getCedula())) {
				proveedorValidar = proveedorBo.buscarPorCedulaYEmpresa(proveedor.getCedula(), usuarioSesion.getEmpresa());
				if (proveedorValidar != null) {
					result.rejectValue("cedula", "error.proveedor.cedula.existe");
				}
			}

			if (!proveedor.getNombreCompleto().equals(proveedorBD.getNombreCompleto())) {
				proveedorValidar = proveedorBo.buscarPorNombreCompletoYEmpresa(proveedor.getNombreCompleto(), usuarioSesion.getEmpresa());
				if (proveedorValidar != null) {
					result.rejectValue("nombreCompleto", "error.proveedor.nombreCompleto.existe");
				}
			}
			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
			}

			proveedorBD.setCedula(proveedor.getCedula());
			proveedorBD.setNombreCompleto(proveedor.getNombreCompleto());
			proveedorBD.setEmail(proveedor.getEmail());
			proveedorBD.setEmpresa(usuarioSesion.getEmpresa());
			proveedorBD.setMovil(proveedor.getMovil());
			proveedorBD.setDireccion(proveedor.getDireccion());
			proveedorBD.setRazonSocial(proveedor.getRazonSocial());
			proveedorBD.setRepresentante(proveedor.getRepresentante());
			proveedorBD.setUpdated_at(new Date());
			proveedorBD.setEstado(proveedor.getEstado());
			proveedorBD.setTelefono(proveedor.getTelefono());
			proveedorBo.modificar(proveedorBD);

			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("proveedor.modificado.correctamente", proveedorBD);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	/**
	 * Buscar por id el proveedor para mostrar
	 * @param request
	 * @param model
	 * @param proveedor
	 * @param result
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("all")
	@RequestMapping(value = "/MostrarProveedorAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator mostrar(HttpServletRequest request, ModelMap model, @ModelAttribute Proveedor proveedor, BindingResult result, SessionStatus status) throws Exception {
		try {
			ProveedorCommand proveedorCommand = new ProveedorCommand(proveedorBo.buscar(proveedor.getId()));
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("mensaje.consulta.exitosa", proveedorCommand);
		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	@SuppressWarnings("all")
	private static class RESPONSES {

		private static class OK {

			private static class PROVEEDOR {

				private static final RespuestaServiceValidator	AGREGADO		= RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("proveedor.agregar.correctamente");
				private static final RespuestaServiceValidator	MODIFICADO	= RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("proveedor.modificado.correctamente");
			}
		}

		private static class ERROR {

			private static class PROVEEDOR {

				private static final RespuestaServiceValidator NO_EXISTE = RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("error.proveedor.noExiste");
			}
		}
	}

}
