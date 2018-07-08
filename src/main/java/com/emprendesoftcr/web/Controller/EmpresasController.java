package com.emprendesoftcr.web.Controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

import com.emprendesoftcr.Bo.DataTableBo;
import com.emprendesoftcr.Bo.EmpresaBo;
import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.Utils.DataTableDelimitador;
import com.emprendesoftcr.Utils.JqGridFilter;
import com.emprendesoftcr.Utils.RespuestaServiceDataTable;
import com.emprendesoftcr.Utils.RespuestaServiceValidator;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.web.command.EmpresaCommand;
import com.google.common.base.Function;

/**
 * EmpresasController. Empresas asociadas al sistema para la facturacion
 * @author jose.
 * @since 16 mar. 2018
 */
@Controller
public class EmpresasController {

	private static final Function<Object, EmpresaCommand>	TO_COMMAND	= new Function<Object, EmpresaCommand>() {

																																			@Override
																																			public EmpresaCommand apply(Object f) {
																																				return new EmpresaCommand((Empresa) f);
																																			};

																																		};

	@Autowired
	private EmpresaBo																			empresaBo;

	@Autowired
	private DataTableBo																		dataTableBo;

	/**
	 * Mostrar el html de la lista de empresa
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/ListarEmpresas", method = RequestMethod.GET)
	public String listar(ModelMap model) {

		return "views/empresa/ListarEmpresas";
	}

	/**
	 * Metodo json para ser llamado de un ajax
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/ListarEmpresasAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarAjax(HttpServletRequest request, HttpServletResponse response) {

		DataTableDelimitador delimitadores = null;

		delimitadores = new DataTableDelimitador(request, "Empresa");

		return UtilsForControllers.process(request, dataTableBo, delimitadores, TO_COMMAND);
	}

	/**
	 * Lista de las empresas activas
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/ListarEmpresasActivasAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarEmpresasActivasAjax(HttpServletRequest request, HttpServletResponse response) {

		DataTableDelimitador delimitadores = new DataTableDelimitador(request, "Empresa");
		JqGridFilter dataTableFilter = new JqGridFilter("estado", "'" + Constantes.ESTADO_ACTIVO.toString() + "'", "=");
		delimitadores.addFiltro(dataTableFilter);

		return UtilsForControllers.process(request, dataTableBo, delimitadores, TO_COMMAND);
	}
/**
 * Agregar Empresa
 * @param request
 * @param model
 * @param empresa
 * @param result
 * @param status
 * @return
 * @throws Exception
 */
	@RequestMapping(value = "/AgregarEmpresaAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator agregarEmpresaAjax(HttpServletRequest request, ModelMap model, @ModelAttribute Empresa empresa, BindingResult result, SessionStatus status) throws Exception {
		try {
			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("empresa.no.modificado", result.getAllErrors());
			}
			Empresa empresaValidar = empresaBo.buscarPorNombre(empresa.getNombre());
			if (empresaValidar != null) {
				result.rejectValue("nombre", "error.empresa.existe.nombre");
			}
			empresaValidar = empresaBo.buscarPorNombreComercial(empresa.getNombreComercial());
			if (empresaValidar != null) {
				result.rejectValue("nombreComercial", "error.empresa.existe.nombreComercial");
			}
			empresaValidar = empresaBo.buscarPorCedula(empresa.getCedula());
			if (empresaValidar != null) {
				result.rejectValue("cedula", "error.empresa.existe.cedula");
			}

			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
			}
			empresa.setUpdated_at(new Date());
			empresa.setCreated_at(new Date());
			empresa.setEstado(Constantes.ESTADO_ACTIVO);

			empresaBo.agregar(empresa);
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("empresa.modificado.correctamente", empresa);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	/**
	 * Modificar una empresa
	 * @param request
	 * @param model
	 * @param empresa
	 * @param result
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/ModificarEmpresaAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator modificar(HttpServletRequest request, ModelMap model, @ModelAttribute Empresa empresa, BindingResult result, SessionStatus status) throws Exception {
		try {
			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("empresa.no.modificado", result.getAllErrors());
			}
			Empresa empresaBD = empresaBo.buscar(empresa.getId());

			if (empresaBD == null) {
				return RESPONSES.ERROR.EMPRESA.NO_EXISTE;
			} else {

				Empresa empresaValidar = null;
				if (!empresaBD.getNombre().equals(empresa.getNombre())) {
					empresaValidar = empresaBo.buscarPorNombre(empresa.getNombre());
					if (empresaValidar != null) {
						result.rejectValue("nombre", "error.empresa.existe.nombre");
					}
				}
				if (!empresaBD.getNombreComercial().equals(empresa.getNombreComercial())) {
					empresaValidar = empresaBo.buscarPorNombreComercial(empresa.getNombreComercial());
					if (empresaValidar != null) {
						result.rejectValue("nombreComercial", "error.empresa.existe.nombreComercial");
					}

				}
				if (!empresaBD.getCedula().equals(empresa.getCedula())) {
					empresaValidar = empresaBo.buscarPorCedula(empresa.getCedula());
					if (empresaValidar != null) {
						result.rejectValue("cedula", "error.empresa.existe.cedula");
					}

				}

				if (result.hasErrors()) {
					return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
				}
				empresaBD.setTipoCedula(empresa.getTipoCedula());
				empresaBD.setCedula(empresa.getCedula());
				empresaBD.setNombre(empresa.getNombre());
				empresaBD.setNombreComercial(empresa.getNombreComercial());
				empresaBD.setClave(empresa.getClave());
				empresaBD.setCodigoPais(empresa.getCodigoPais());
				empresaBD.setCorreoElectronico(empresa.getCorreoElectronico());
				empresaBD.setProvincia(empresa.getProvincia());
				empresaBD.setDistrito(empresa.getDistrito());
				empresaBD.setBarrio(empresa.getBarrio());
				empresaBD.setCanton(empresa.getCanton());
				empresaBD.setCazaMatriz(empresa.getCazaMatriz());
        empresaBD.setNumeroConsecutivo(empresa.getNumeroConsecutivo());
				empresaBD.setRepresentante(empresa.getRepresentante());
				empresaBD.setTelefono(empresa.getTelefono());
				empresaBD.setOtraSenas(empresa.getOtraSenas());
				empresaBD.setUpdated_at(new Date());
				empresaBD.setClaveLlaveCriptografica(empresa.getClaveLlaveCriptografica());
				empresaBD.setNombreLlaveCriptografica(empresa.getNombreLlaveCriptografica());
				empresaBo.modificar(empresaBD);
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("empresa.modificado.correctamente", empresaBD);
			}

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	/**
	 * Mostrar por codigo de la empresa
	 * @param request
	 * @param model
	 * @param empresa
	 * @param result
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/MostrarEmpresaAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator mostrar(HttpServletRequest request, ModelMap model, @ModelAttribute Empresa empresa, BindingResult result, SessionStatus status) throws Exception {
		try {
			EmpresaCommand empresaCommand = new EmpresaCommand(empresaBo.buscar(empresa.getId()));
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("mensaje.consulta.exitosa", empresaCommand);
		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	private static class RESPONSES {

		private static class OK {

			private static class EMPRESA {

				private static final RespuestaServiceValidator	AGREGADO		= RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("empresa.agregar.correctamente");
				private static final RespuestaServiceValidator	MODIFICADO	= RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("empresa.modificado.correctamente");
			}
		}

		private static class ERROR {

			private static class EMPRESA {

				private static final RespuestaServiceValidator NO_EXISTE = RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("error.empresa.noExiste");
			}
		}
	}
}
