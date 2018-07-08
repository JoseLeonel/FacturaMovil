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
import org.springframework.web.util.WebUtils;

import com.emprendesoftcr.Bo.DataTableBo;
import com.emprendesoftcr.Bo.UsuarioBo;
import com.emprendesoftcr.Bo.VendedorBo;
import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.Utils.DataTableDelimitador;
import com.emprendesoftcr.Utils.JqGridFilter;
import com.emprendesoftcr.Utils.RespuestaServiceDataTable;
import com.emprendesoftcr.Utils.RespuestaServiceValidator;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.modelo.Vendedor;
import com.emprendesoftcr.web.command.VendedorCommand;
import com.emprendesoftcr.web.componentes.EmpresaPropertyEditor;
import com.emprendesoftcr.web.componentes.StringPropertyEditor;
import com.emprendesoftcr.web.componentes.VendedorPropertyEditor;
import com.google.common.base.Function;

/**
 * controlar los vendedores de una empresa VendedorController.
 * @author jose.
 * @since 19 abr. 2018
 */
@Controller
public class VendedorController {

	private static final Function<Object, VendedorCommand>	TO_COMMAND	= new Function<Object, VendedorCommand>() {

																																				@Override
																																				public VendedorCommand apply(Object f) {
																																					return new VendedorCommand((Vendedor) f);
																																				};
																																			};

	@Autowired
	private DataTableBo																			dataTableBo;

	@Autowired
	private VendedorBo																			vendedorBo;
	
	@Autowired
	private UsuarioBo																			usuarioBo;

	@Autowired
	private VendedorPropertyEditor													vendedorPropertyEditor;

	@Autowired
	private EmpresaPropertyEditor														empresaPropertyEditor;

	@Autowired
	private StringPropertyEditor														stringPropertyEditor;

	@InitBinder
	public void initBinder(WebDataBinder binder) {

		binder.registerCustomEditor(Empresa.class, empresaPropertyEditor);
		binder.registerCustomEditor(Vendedor.class, vendedorPropertyEditor);
		binder.registerCustomEditor(String.class, stringPropertyEditor);
	}

	/**
	 * Listar JSP de vendedores
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/ListarVendedores", method = RequestMethod.GET)
	public String listar(ModelMap model) {
		return "views/vendedores/ListarVendedores";

	}

	/**
	 * Listar metodo ajax de los vendedores
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("all")
	@RequestMapping(value = "/ListarVendedoresAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarAjax(HttpServletRequest request, HttpServletResponse response) {

		DataTableDelimitador delimitadores = null;
		delimitadores = new DataTableDelimitador(request, "Vendedor");
		if (!request.isUserInRole(Constantes.ROL_ADMINISTRADOR_SISTEMA)) {
			String nombreUsuario = request.getUserPrincipal().getName();
			JqGridFilter dataTableFilter = usuarioBo.filtroPorEmpresa(nombreUsuario);
			delimitadores.addFiltro(dataTableFilter);
		}

		return UtilsForControllers.process(request, dataTableBo, delimitadores, TO_COMMAND);
	}

	/**
	 * Listar metodo de ajax de los vendedores activos
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("all")
	@RequestMapping(value = "/ListarVendedoresActivosAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarActivosAjax(HttpServletRequest request, HttpServletResponse response) {

		DataTableDelimitador delimitadores = null;
		delimitadores = new DataTableDelimitador(request, "Vendedor");
		JqGridFilter dataTableFilter = new JqGridFilter("estado", "'" + Constantes.ESTADO_ACTIVO.toString() + "'", "=");
		delimitadores.addFiltro(dataTableFilter);
		
		if (!request.isUserInRole(Constantes.ROL_ADMINISTRADOR_SISTEMA)) {
			String nombreUsuario = request.getUserPrincipal().getName();
			dataTableFilter = usuarioBo.filtroPorEmpresa(nombreUsuario);
			delimitadores.addFiltro(dataTableFilter);
		}

		return UtilsForControllers.process(request, dataTableBo, delimitadores, TO_COMMAND);
	}

	/**
	 * Agregar un vendedor
	 * @param request
	 * @param model
	 * @param vendedor
	 * @param result
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("all")
	@RequestMapping(value = "/AgregarVendedorAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator agregar(HttpServletRequest request, ModelMap model, @ModelAttribute Vendedor vendedor, BindingResult result, SessionStatus status) throws Exception {
		try {
			String nombreUsuario = request.getUserPrincipal().getName();
			Usuario usuarioSesion = usuarioBo.buscar(nombreUsuario);
			Vendedor vendedorValidar = null;

			vendedorValidar = vendedorBo.buscarPorCedulaYEmpresa(vendedor.getCedula(), usuarioSesion.getEmpresa());
			if (vendedorValidar != null) {
				result.rejectValue("cedula", "error.vendedor.existe.cedula");
			}

		
			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
			}
			vendedor.setEmpresa(usuarioSesion.getEmpresa());

			vendedor.setCreated_at(new Date());
			vendedor.setUpdated_at(new Date());
			vendedor.setUsuario(usuarioSesion);
			vendedorBo.agregar(vendedor);
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("vendedor.agregar.correctamente", vendedor);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	/**
	 * Modificar el metodo de ajax de los vendedores
	 * @param request
	 * @param model
	 * @param vendedor
	 * @param result
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("all")
	@RequestMapping(value = "/ModificarVendedorAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator modificar(HttpServletRequest request, ModelMap model, @ModelAttribute Vendedor vendedor, BindingResult result, SessionStatus status) throws Exception {
		try {
			String nombreUsuario = request.getUserPrincipal().getName();
			Usuario usuarioSesion = usuarioBo.buscar(nombreUsuario);

			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("vendedor.no.modificado", result.getAllErrors());
			}
			Vendedor vendedorBD = vendedorBo.buscar(vendedor.getId());

			if (vendedorBD == null) {
				return RESPONSES.ERROR.VENDEDOR.NO_EXISTE;
			}
			Vendedor vendedorValidar = null;
			if (!vendedor.getCedula().equals(vendedorBD.getCedula())) {
				vendedorValidar = vendedorBo.buscarPorCedulaYEmpresa(vendedor.getCedula(), usuarioSesion.getEmpresa());
				if (vendedorValidar != null) {
					result.rejectValue("cedula", "error.vendedor.existe.cedula");
				}
			}

			
			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
			}

			vendedorBD.setCedula(vendedor.getCedula());
			vendedorBD.setNombreCompleto(vendedor.getNombreCompleto());
			vendedorBD.setCorreoElectronico(vendedor.getCorreoElectronico());
			vendedorBD.setDescuento(vendedor.getDescuento());
			vendedorBD.setOtraSena(vendedor.getOtraSena());
			vendedorBD.setDescuento(vendedor.getDescuento());
			vendedorBD.setUpdated_at(new Date());
			vendedorBD.setEstado(vendedor.getEstado());
			vendedorBD.setTelefono(vendedor.getTelefono());
			vendedorBD.setCelular(vendedor.getCelular());
			vendedorBD.setUsuario(usuarioSesion);
			vendedorBD.setPorcentajeComision(vendedor.getPorcentajeComision());
			vendedorBo.modificar(vendedorBD);

			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("vendedor.modificado.correctamente", vendedorBD);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	/**
	 * Mostrar un cliente
	 * @param request
	 * @param model
	 * @param vendedor
	 * @param result
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("all")
	@RequestMapping(value = "/MostrarVendedorAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator mostrar(HttpServletRequest request, ModelMap model, @ModelAttribute Vendedor vendedor, BindingResult result, SessionStatus status) throws Exception {
		try {
			VendedorCommand vendedorCommand = new VendedorCommand(vendedorBo.buscar(vendedor.getId()));
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("mensaje.consulta.exitosa", vendedorCommand);
		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	@SuppressWarnings("all")
	private static class RESPONSES {

		private static class OK {

			private static class VENDEDOR {

				private static final RespuestaServiceValidator	AGREGADO		= RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("vendedor.agregar.correctamente");
				private static final RespuestaServiceValidator	MODIFICADO	= RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("vendedor.modificado.correctamente");
			}
		}

		private static class ERROR {

			private static class VENDEDOR {

				private static final RespuestaServiceValidator NO_EXISTE = RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("error.vendedor.noExiste");
			}
		}
	}

}
