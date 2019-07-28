package com.emprendesoftcr.web.Controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

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
import com.emprendesoftcr.Bo.ProveedorSimplificadoBo;
import com.emprendesoftcr.Bo.UsuarioBo;
import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.Utils.DataTableDelimitador;
import com.emprendesoftcr.Utils.JqGridFilter;
import com.emprendesoftcr.Utils.RespuestaServiceDataTable;
import com.emprendesoftcr.Utils.RespuestaServiceValidator;
import com.emprendesoftcr.Utils.Utils;
import com.emprendesoftcr.modelo.Cliente;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.ProveedorSimplificado;
import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.web.command.ProveedorSimplificadoCommand;
import com.emprendesoftcr.web.propertyEditor.ClientePropertyEditor;
import com.emprendesoftcr.web.propertyEditor.EmpresaPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.ProveedorSimplificadoPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.StringPropertyEditor;
import com.google.common.base.Function;

/**
 * Para el mantenimiento y consulta de los clientes asociados a una sucursal ClientesController.
 * @author jose.
 * @since 17 mar. 2018
 */
@Controller
public class ProveedorSimplificadoController {

	private static final Function<Object, ProveedorSimplificadoCommand>	TO_COMMAND	= new Function<Object, ProveedorSimplificadoCommand>() {

																																										@Override
																																										public ProveedorSimplificadoCommand apply(Object f) {
																																											return new ProveedorSimplificadoCommand((ProveedorSimplificado) f);
																																										};
																																									};

	
	@Autowired
	private ProveedorSimplificadoBo																			proveedorSimplificadoBo;

	
	@Autowired
	private UsuarioBo																										usuarioBo;

	@Autowired
	private DataTableBo																									dataTableBo;

	@Autowired
	private ClientePropertyEditor																				clientePropertyEditor;

	@Autowired
	private EmpresaPropertyEditor																				empresaPropertyEditor;

	@Autowired
	private StringPropertyEditor																				stringPropertyEditor;

	@Autowired
	private ProveedorSimplificadoPropertyEditor													proveedorSimplificadoPropertyEditor;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Empresa.class, empresaPropertyEditor);
		binder.registerCustomEditor(Cliente.class, clientePropertyEditor);
		binder.registerCustomEditor(String.class, stringPropertyEditor);

		binder.registerCustomEditor(ProveedorSimplificado.class, proveedorSimplificadoPropertyEditor);

	}

	@RequestMapping(value = "/ListarProveedorSimplificado.do", method = RequestMethod.GET)
	public String listar(ModelMap model) {
		return "views/proveedorSimplificado/ListarProveedorSimplificado";
	}

	@SuppressWarnings("all")
	@RequestMapping(value = "/ListarProveedorSimplificadoAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarAjax(HttpServletRequest request, HttpServletResponse response) {

		DataTableDelimitador delimitadores = null;
		delimitadores = new DataTableDelimitador(request, "ProveedorSimplificado");
		Usuario usuario = usuarioBo.buscar(request.getUserPrincipal().getName());
		if (!usuarioBo.isAdministrador_sistema(usuario)) {
			JqGridFilter dataTableFilter = usuarioBo.filtroPorEmpresa(usuario.getNombreUsuario());
			delimitadores.addFiltro(dataTableFilter);
		}
		JqGridFilter dataTableFilter = new JqGridFilter("cedula", "'" + Constantes.CEDULA_CLIENTE_FRECUENTE + "'", "<>");
		delimitadores.addFiltro(dataTableFilter);

		return UtilsForControllers.process(request, dataTableBo, delimitadores, TO_COMMAND);
	}

	@SuppressWarnings("all")
	@RequestMapping(value = "/ListarProveedorSimplificadoActivosAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarActivosAjax(HttpServletRequest request, HttpServletResponse response) {

		DataTableDelimitador delimitadores = null;
		delimitadores = new DataTableDelimitador(request, "ProveedorSimplificado");
		JqGridFilter dataTableFilter = new JqGridFilter("estado", "'" + Constantes.ESTADO_INACTIVO_PROVEEDOR_SIMPLIFICADO + "'", "<>");
		delimitadores.addFiltro(dataTableFilter);
		Usuario usuario = usuarioBo.buscar(request.getUserPrincipal().getName());
		delimitadores = new DataTableDelimitador(request, "ProveedorSimplificado");
		if (!usuarioBo.isAdministrador_sistema(usuario)) {
			String nombreUsuario = request.getUserPrincipal().getName();
			dataTableFilter = usuarioBo.filtroPorEmpresa(nombreUsuario);
			delimitadores.addFiltro(dataTableFilter);
		}
		Long total = dataTableBo.contar(delimitadores);
		Collection<Object> objetos = dataTableBo.listar(delimitadores);
		RespuestaServiceDataTable respuestaService = new RespuestaServiceDataTable();
		List<Object> solicitudList = new ArrayList<Object>();
		for (Iterator<Object> iterator = objetos.iterator(); iterator.hasNext();) {
			ProveedorSimplificado object = (ProveedorSimplificado) iterator.next();
			// no se carga el usuario del sistema el id -1
			if (object.getId().longValue() > 0L) {
				if (object.getEstado().equals(Constantes.ESTADO_ACTIVO_PROVEEDOR_SIMPLIFICADO)) {
					solicitudList.add(new ProveedorSimplificadoCommand(object));
				}

			}
		}

		respuestaService.setRecordsTotal(total);
		respuestaService.setRecordsFiltered(total);
		if (request.getParameter("draw") != null && !request.getParameter("draw").equals(" ")) {
			respuestaService.setDraw(Integer.parseInt(request.getParameter("draw")));
		}
		respuestaService.setAaData(solicitudList);
		return respuestaService;

	}

	@SuppressWarnings("all")
	@RequestMapping(value = "/AgregarProveedorSimplificadoAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator agregar(HttpServletRequest request, ModelMap model, @ModelAttribute ProveedorSimplificadoCommand proveedorSimplificadoCommand, BindingResult result, SessionStatus status) throws Exception {
		try {

			String nombreUsuario = request.getUserPrincipal().getName();
			Usuario usuarioSesion = usuarioBo.buscar(nombreUsuario);

			proveedorSimplificadoCommand.setCedula(proveedorSimplificadoCommand.getCedula() == null ? Constantes.EMPTY : proveedorSimplificadoCommand.getCedula());
			proveedorSimplificadoCommand.setNombreCompleto(proveedorSimplificadoCommand.getNombreCompleto() == null ? Constantes.EMPTY : proveedorSimplificadoCommand.getNombreCompleto());
			proveedorSimplificadoCommand.setCorreoElectronico(proveedorSimplificadoCommand.getCorreoElectronico() == null ? Constantes.EMPTY : proveedorSimplificadoCommand.getCorreoElectronico());

			if (proveedorSimplificadoCommand.getCedula().equals(Constantes.EMPTY)) {
				result.rejectValue("cedula", Constantes.KEY_REQUERIDO);
			}
			if (proveedorSimplificadoCommand.getNombreCompleto().equals(Constantes.EMPTY)) {
				result.rejectValue("nombreCompleto", Constantes.KEY_REQUERIDO);
			}
			if (proveedorSimplificadoCommand.getCorreoElectronico().equals(Constantes.EMPTY)) {
				result.rejectValue("correoElectronico", Constantes.KEY_REQUERIDO);
			}
			if (proveedorSimplificadoCommand.getTipoCedula().equals(Constantes.TIPO_CEDULA_FISICA)) {
				if (proveedorSimplificadoCommand.getCedula().length() > 9) {
					result.rejectValue("cedula", "error.proveedorSimplificado.cedula.fisica.tamano.incorrecto");
				}
			}

			if (proveedorSimplificadoCommand.getTipoCedula().equals(Constantes.TIPO_CEDULA_JURIDICA)) {

				if (proveedorSimplificadoCommand.getCedula().length() > 10) {
					result.rejectValue("cedula", "error.proveedorSimplificado.cedula.juridica.tamano.incorrecto");
				}
			}
			ProveedorSimplificado proveedorSimplificadoValidar = null;

			proveedorSimplificadoValidar = proveedorSimplificadoBo.buscarPorCedulaYEmpresa(proveedorSimplificadoCommand.getCedula().trim(), usuarioSesion.getEmpresa());
			if (proveedorSimplificadoValidar != null) {
				result.rejectValue("cedula", "error.proveedorSimplificado.existe.cedula");
			}

			if (Utils.validarCedulaDiferenteCaracter(proveedorSimplificadoCommand.getCedula()).equals(Boolean.FALSE)) {
				result.rejectValue("cedula", "error.proveedorSimplificado.cedula.tiene.mismo.digito");

			}

			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
			}
			ProveedorSimplificado proveedorSimplificado = new ProveedorSimplificado();
			proveedorSimplificado.setCedula(proveedorSimplificadoCommand.getCedula());
			proveedorSimplificado.setTipoCedula(proveedorSimplificadoCommand.getTipoCedula());
			proveedorSimplificado.setNombreCompleto(proveedorSimplificadoCommand.getNombreCompleto());
			proveedorSimplificado.setCorreoElectronico(proveedorSimplificadoCommand.getCorreoElectronico());
			proveedorSimplificado.setIdentificacionExtranjero(proveedorSimplificadoCommand.getIdentificacionExtranjero());
			proveedorSimplificado.setEstado(proveedorSimplificadoCommand.getEstado());
      proveedorSimplificado.setCodigoPais(proveedorSimplificadoCommand.getCodigoPais());
      proveedorSimplificado.setTelefono(proveedorSimplificadoCommand.getTelefono());
			proveedorSimplificado.setEmpresa(usuarioSesion.getEmpresa());
			proveedorSimplificado.setCreated_at(new Date());
			proveedorSimplificado.setUpdated_at(new Date());
			proveedorSimplificado.setUsuario(usuarioSesion);
			proveedorSimplificadoBo.agregar(proveedorSimplificado);
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("proveedorSimplificado.agregar.correctamente", proveedorSimplificado);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	
	@SuppressWarnings("all")
	@RequestMapping(value = "/ModificarProveedorSimplificadoAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator modificar(HttpServletRequest request, ModelMap model, @ModelAttribute ProveedorSimplificadoCommand proveedorSimplificadoCommand, BindingResult result, SessionStatus status) throws Exception {
		try {

			proveedorSimplificadoCommand.setCedula(proveedorSimplificadoCommand.getCedula() == null ? Constantes.EMPTY : proveedorSimplificadoCommand.getCedula());
			proveedorSimplificadoCommand.setNombreCompleto(proveedorSimplificadoCommand.getNombreCompleto() == null ? Constantes.EMPTY : proveedorSimplificadoCommand.getNombreCompleto());
			proveedorSimplificadoCommand.setCorreoElectronico(proveedorSimplificadoCommand.getCorreoElectronico() == null ? Constantes.EMPTY : proveedorSimplificadoCommand.getCorreoElectronico());

			if (proveedorSimplificadoCommand.getCedula().equals(Constantes.EMPTY)) {
				result.rejectValue("cedula", Constantes.KEY_REQUERIDO);
			}
			if (proveedorSimplificadoCommand.getNombreCompleto().equals(Constantes.EMPTY)) {
				result.rejectValue("nombreCompleto", Constantes.KEY_REQUERIDO);
			}
			if (proveedorSimplificadoCommand.getCorreoElectronico().equals(Constantes.EMPTY)) {
				result.rejectValue("correoElectronico", Constantes.KEY_REQUERIDO);
			}
			if (proveedorSimplificadoCommand.getTipoCedula().equals(Constantes.TIPO_CEDULA_FISICA)) {
				if (proveedorSimplificadoCommand.getCedula().length() > 9) {
					result.rejectValue("cedula", "error.proveedorSimplificado.cedula.fisica.tamano.incorrecto");
				}
			}
			if (proveedorSimplificadoCommand.getTipoCedula().equals(Constantes.TIPO_CEDULA_JURIDICA)) {
				if (proveedorSimplificadoCommand.getCedula().length() > 10) {
					result.rejectValue("cedula", "error.proveedorSimplificado.cedula.juridica.tamano.incorrecto");
				}
			}
			String nombreUsuario = request.getUserPrincipal().getName();
			Usuario usuarioSesion = usuarioBo.buscar(nombreUsuario);
			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("proveedorSimplificado.no.modificado", result.getAllErrors());
			}
			ProveedorSimplificado proveedorSimplificadoDB =proveedorSimplificadoBo.buscar(proveedorSimplificadoCommand.getId());

			if (proveedorSimplificadoDB == null) {
				return RESPONSES.ERROR.CLIENTE.NO_EXISTE;
			}
			ProveedorSimplificado proveedorSimplicifacoValidar = null;
//			if (!proveedorSimplificadoCommand.getCedula().equals(proveedorSimplificadoDB.getCedula())) {
//				Boolean verificarFacturas = false;				Collection<Factura> facturas = facturaBo.findByClienteAndEmpresa(proveedorSimplificadoDB, usuarioSesion.getEmpresa());
//				if (facturas != null) {
//					if (!facturas.isEmpty()) {
//						result.rejectValue("cedula", "error.cliente.cedula.tiene.facturas");
//						verificarFacturas = true;
//					}
//				}
//				if (verificarFacturas == false) {
//					proveedorSimplicifacoValidar = clienteBo.buscarPorCedulaYEmpresa(proveedorSimplificadoCommand.getCedula(), usuarioSesion.getEmpresa());
//					if (proveedorSimplicifacoValidar != null) {
//						result.rejectValue("cedula", "error.cliente.existe.cedula");
//					}
//
//				}
//			}

			if (Utils.validarCedulaDiferenteCaracter(proveedorSimplificadoCommand.getCedula()).equals(Boolean.FALSE)) {
				result.rejectValue("cedula", "error.proveedorSimplificado.cedula.tiene.mismo.digito");
			}

			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
			}

			proveedorSimplificadoDB.setCodigoPais(proveedorSimplificadoCommand.getCodigoPais());
			proveedorSimplificadoDB.setTelefono(proveedorSimplificadoCommand.getTelefono());


			proveedorSimplificadoDB.setCedula(proveedorSimplificadoCommand.getCedula());
			proveedorSimplificadoDB.setNombreCompleto(proveedorSimplificadoCommand.getNombreCompleto());
			proveedorSimplificadoDB.setCorreoElectronico(proveedorSimplificadoCommand.getCorreoElectronico());
			proveedorSimplificadoDB.setTipoCedula(proveedorSimplificadoCommand.getTipoCedula());
			proveedorSimplificadoDB.setNombreComercial(proveedorSimplificadoCommand.getNombreComercial());
			proveedorSimplificadoDB.setUpdated_at(new Date());
			proveedorSimplificadoDB.setEstado(proveedorSimplificadoCommand.getEstado());
			proveedorSimplificadoDB.setUsuario(usuarioSesion);
			proveedorSimplificadoDB.setIdentificacionExtranjero(proveedorSimplificadoCommand.getIdentificacionExtranjero());
			proveedorSimplificadoBo.modificar(proveedorSimplificadoDB);

			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("proveedorSimplificado.modificado.correctamente", proveedorSimplificadoDB);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	@SuppressWarnings("all")
	@RequestMapping(value = "/MostrarProveedorSimplificadoAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator mostrar(HttpServletRequest request, ModelMap model, @ModelAttribute ProveedorSimplificado proveedorSimplificado, BindingResult result, SessionStatus status) throws Exception {
		try {
			ProveedorSimplificadoCommand proveedorSimplificadoCommand = new ProveedorSimplificadoCommand(proveedorSimplificadoBo.buscar(proveedorSimplificado.getId()));
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("mensaje.consulta.exitosa", proveedorSimplificadoCommand);
		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}
	
	

	@SuppressWarnings("all")
	private static class RESPONSES {

		private static class OK {

			private static class CLIENTE {

				private static final RespuestaServiceValidator	AGREGADO		= RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("proveedorSimplificado.agregar.correctamente");
				private static final RespuestaServiceValidator	MODIFICADO	= RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("proveedorSimplificado.modificado.correctamente");
			}
		}

		private static class ERROR {

			private static class CLIENTE {

				private static final RespuestaServiceValidator NO_EXISTE = RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("error.proveedorSimplificado.noExiste");
			}
		}
	}

}
