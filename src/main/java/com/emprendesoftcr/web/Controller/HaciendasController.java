package com.emprendesoftcr.web.Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.emprendesoftcr.Bo.DataTableBo;
import com.emprendesoftcr.Bo.FacturaBo;
import com.emprendesoftcr.Bo.HaciendaBo;
import com.emprendesoftcr.Bo.UsuarioBo;
import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.Utils.DataTableDelimitador;
import com.emprendesoftcr.Utils.JqGridFilter;
import com.emprendesoftcr.Utils.RespuestaServiceDataTable;
import com.emprendesoftcr.Utils.RespuestaServiceValidator;
import com.emprendesoftcr.Utils.Utils;
import com.emprendesoftcr.fisco.FacturaElectronicaUtils;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Factura;
import com.emprendesoftcr.modelo.Hacienda;
import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.service.ProcesoHaciendaService;
import com.emprendesoftcr.web.command.HaciendaCommand;
import com.google.common.base.Function;

/**
 * Manejo de las cuentas por cobrar por los clientes , se controla las cuentas por cobrar manuales y automaticas CuentaCobrarController.
 * @author jose.
 * @since 25 mar. 2018
 */
@Controller
public class HaciendasController {

	private static final Function<Object, HaciendaCommand>	TO_COMMAND	= new Function<Object, HaciendaCommand>() {

																																				@Override
																																				public HaciendaCommand apply(Object f) {
																																					return new HaciendaCommand((Hacienda) f);
																																				};
																																			};

	private Logger																					log					= LoggerFactory.getLogger(this.getClass());

	@Lazy
	@Autowired
	DataTableBo																							dataTableBo;

	@Lazy
	@Autowired
	FacturaBo																								facturaBo;

	@Lazy
	@Autowired
	ProcesoHaciendaService																	procesoHaciendaService;

	@Lazy
	@Autowired
	HaciendaBo																							haciendaBo;

	@Lazy
	@Autowired
	UsuarioBo																								usuarioBo;

	@InitBinder
	public void initBinder(WebDataBinder binder) {

	}

	@RequestMapping(value = "/ListarHacienda", method = RequestMethod.GET)
	public String listaFacturas(ModelMap model) {
		return "views/hacienda/ListarHacienda";
	}

	/**
	 * Listado de hacienda
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/ListarHaciendasAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarAjax(HttpServletRequest request, HttpServletResponse response, @RequestParam String fechaInicio, @RequestParam String fechaFin, @RequestParam String cedulaReceptor) {
		Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());
		DataTableDelimitador query = DelimitadorBuilder.get(request, fechaInicio, fechaFin, cedulaReceptor, usuarioSesion.getEmpresa());
		return UtilsForControllers.process(request, dataTableBo, query, TO_COMMAND);
	}

	/**
	 * Env
	 * @param request
	 * @param response
	 * @param fechaInicio
	 * @param fechaFin
	 * @param cedulaReceptor
	 * @return
	 */
	@RequestMapping(value = "/EnviarHaciendasAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable enviarHaciendaAjax(HttpServletRequest request, HttpServletResponse response, @RequestParam String fechaInicio, @RequestParam String fechaFin, @RequestParam String cedulaReceptor) {
		Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());
		DataTableDelimitador query = DelimitadorBuilder.get(request, fechaInicio, fechaFin, cedulaReceptor, usuarioSesion.getEmpresa());
		return UtilsForControllers.process(request, dataTableBo, query, TO_COMMAND);
	}

	/**
	 * Enviar aceptar el documento hacienda
	 * @param request
	 * @param response
	 * @param idFactura
	 * @return
	 */
	@RequestMapping(value = "/EnviarAceptarHaciendaAjax", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator mostrar(HttpServletRequest request, HttpServletResponse response, ModelMap model, @RequestParam Long idHacienda) {
		RespuestaServiceValidator respuestaServiceValidator = new RespuestaServiceValidator();
		try {
			respuestaServiceValidator.setStatus(HttpStatus.OK.value());
			respuestaServiceValidator.setMessage("");

			Hacienda haciendaBD = haciendaBo.findById(idHacienda);
			if (haciendaBD == null) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("hacienda.error.no.existe");
			}

			procesoHaciendaService.envioHacienda(haciendaBD);

			respuestaServiceValidator.setStatus(HttpStatus.OK.value());
			respuestaServiceValidator.setMessage(Constantes.RESOURCE_BUNDLE.getString("hacienda.enviado.exitoso"));

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
		return respuestaServiceValidator;
	}

	@RequestMapping(value = "/AceptarHaciendaAjax", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator mostrarAceptar(HttpServletRequest request, HttpServletResponse response, ModelMap model, @RequestParam Long idHacienda) {
		RespuestaServiceValidator respuestaServiceValidator = new RespuestaServiceValidator();
		try {
			respuestaServiceValidator.setStatus(HttpStatus.OK.value());
			respuestaServiceValidator.setMessage("");

			Hacienda haciendaBD = haciendaBo.findById(idHacienda);
			if (haciendaBD == null) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("hacienda.error.no.existe");
			}

			procesoHaciendaService.aceptarDocumento(haciendaBD);

			respuestaServiceValidator.setStatus(HttpStatus.OK.value());
			respuestaServiceValidator.setMessage(Constantes.RESOURCE_BUNDLE.getString("hacienda.aceptar.exitoso"));

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
		return respuestaServiceValidator;
	}

	/**
	 * Enviar el correo al emisor y al cliente si es una factura(nota credito o debito) , tiquete solo al emisor
	 * @param request
	 * @param response
	 * @param model
	 * @param idHacienda
	 * @return
	 */
	@RequestMapping(value = "/EnviarCorreoClienteAndEmisorAjax", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator enviarCorreoEmisorAndReceptor(HttpServletRequest request, HttpServletResponse response, ModelMap model, @RequestParam Long idHacienda) {
		RespuestaServiceValidator respuestaServiceValidator = new RespuestaServiceValidator();
		try {
			respuestaServiceValidator.setStatus(HttpStatus.OK.value());
			respuestaServiceValidator.setMessage("");

			Hacienda haciendaBD = haciendaBo.findById(idHacienda);
			if (haciendaBD == null) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("hacienda.error.no.existe");
			}

			Factura factura = facturaBo.findByConsecutivoAndEmpresa(haciendaBD.getConsecutivo(), haciendaBD.getEmpresa());

			if (factura == null) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("hacienda.error.factura.no.existe");
			}
			procesoHaciendaService.enviarCorreos(factura, haciendaBD);

			respuestaServiceValidator.setStatus(HttpStatus.OK.value());
			respuestaServiceValidator.setMessage(Constantes.RESOURCE_BUNDLE.getString("hacienda.envio.correo.exitoso"));

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
		return respuestaServiceValidator;
	}

	/**
	 * Baja Comprobante XML
	 * @param request
	 * @param response
	 * @param model
	 * @param idHacienda
	 * @return
	 * @throws SQLException
	 * @throws SerialException
	 */
	@RequestMapping(value = "/bajarXMLComprobanteAjax",method = RequestMethod.GET, headers = "Accept=application/json")
	public void bajarXMLComprobante(HttpServletRequest request, HttpServletResponse response, ModelMap model, @RequestParam Long idHacienda) throws IOException, SerialException, SQLException {
		try {
			Hacienda haciendaBD = haciendaBo.findById(idHacienda);
			String fileName = haciendaBD.getTipoDoc() + "-" + haciendaBD.getConsecutivo();
			if (haciendaBD.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_TIQUETE)) {
				fileName = "TiqueteXML_" + haciendaBD.getTipoDoc() + "-" + haciendaBD.getConsecutivo();
			}

			if (haciendaBD.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_ELECTRONICA)) {
				fileName = "FacturaXML_" + haciendaBD.getTipoDoc() + "-" + haciendaBD.getConsecutivo();
			}

			response.setContentType("text/plain");
			response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xml");
			ServletOutputStream out = response.getOutputStream();
			out.println(FacturaElectronicaUtils.convertirBlodToString(haciendaBD.getComprobanteXML()));
			out.flush();
			out.close();

		} catch (Exception e) {
			log.info("** Error  bajarXMLComprobante: " + e.getMessage() + " fecha " + new Date());
			throw e;
		}
	}

	private static class DelimitadorBuilder {

		static DataTableDelimitador get(HttpServletRequest request, String inicio, String fin, String cedula, Empresa empresa) {
			// Consulta por fechas
			DataTableDelimitador delimitador = new DataTableDelimitador(request, "Hacienda");
			Date fechaInicio = new Date();
			Date fechaFinal = new Date();

			delimitador.addFiltro(new JqGridFilter("empresa.id", "'" + empresa.getId().toString() + "'", "="));

			if (cedula != null) {
				if (!cedula.equals("0")) {
					delimitador.addFiltro(new JqGridFilter("cedulaReceptor", "'" + cedula.toString() + "'", "="));
				}

			}
			if (!inicio.equals(Constantes.EMPTY) && !fin.equals(Constantes.EMPTY)) {
				fechaInicio = Utils.parseDate(inicio);
				fechaFinal = Utils.parseDate(fin);
				if (fechaFinal == null) {
					fechaFinal = new Date(System.currentTimeMillis());
				}
				if (fechaFinal != null && fechaFinal != null) {
					fechaFinal = Utils.sumarDiasFecha(fechaFinal, 1);
				}

				DateFormat dateFormat = new SimpleDateFormat(Constantes.DATE_FORMAT5);

				inicio = dateFormat.format(fechaInicio);
				fin = dateFormat.format(fechaFinal);

				delimitador.addFiltro(new JqGridFilter("fechaEmisor", inicio, "date>="));
				delimitador.addFiltro(new JqGridFilter("fechaEmisor", fin, "dateFinal<="));
			}
			return delimitador;
		}
	}

	static class RESPONSES {

		private static class OK {

			private static class FACTURA {

				private static final RespuestaServiceValidator	AGREGADO		= RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("factura.agregar.correctamente");
				private static final RespuestaServiceValidator	MODIFICADO	= RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("factura.modificado.correctamente");
			}
		}

		private static class ERROR {

			private static class FACTURA {

				private static final RespuestaServiceValidator NO_EXISTE = RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("error.factura.noExiste");
			}
		}
	}

}
