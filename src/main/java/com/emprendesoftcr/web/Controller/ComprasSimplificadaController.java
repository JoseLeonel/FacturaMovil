package com.emprendesoftcr.web.Controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.util.ByteArrayDataSource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jxls.template.SimpleExporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.emprendesoftcr.Bo.CompraSimplificadaBo;
import com.emprendesoftcr.Bo.ConsultasNativeBo;
import com.emprendesoftcr.Bo.CorreosBo;
import com.emprendesoftcr.Bo.DetalleCompraSimplificadaBo;
import com.emprendesoftcr.Bo.TipoCambioBo;
import com.emprendesoftcr.Bo.UsuarioBo;
import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.Utils.RespuestaServiceDataTable;
import com.emprendesoftcr.Utils.RespuestaServiceValidator;
import com.emprendesoftcr.Utils.Utils;
import com.emprendesoftcr.modelo.Attachment;
import com.emprendesoftcr.modelo.CompraSimplificada;
import com.emprendesoftcr.modelo.DetalleCompraSimplificada;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.ProveedorSimplificado;
import com.emprendesoftcr.modelo.TipoCambio;
import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.modelo.sqlNativo.CompraSimplificadaNative;
import com.emprendesoftcr.validator.CompraSimplificadaFormValidator;
import com.emprendesoftcr.web.command.CompraSimplificadaCommand;
import com.emprendesoftcr.web.command.CompraSimplificadaNativeCommand;
import com.emprendesoftcr.web.propertyEditor.EmpresaPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.ProveedorSimplificadoPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.StringPropertyEditor;

@Controller
public class ComprasSimplificadaController {

	@Autowired
	private UsuarioBo														usuarioBo;

	@Autowired
	private CorreosBo														correosBo;

	@Autowired
	private TipoCambioBo												tipoCambioBo;

	@Autowired
	private ConsultasNativeBo										consultasNativeBo;

	@Autowired
	private DetalleCompraSimplificadaBo					detalleCompraSimplificadaBo;

	@Autowired
	private CompraSimplificadaBo								compraSimplificadaBo;

	@Autowired
	private EmpresaPropertyEditor								empresaPropertyEditor;

	@Autowired
	private ProveedorSimplificadoPropertyEditor	proveedorSimplificadoPropertyEditor;

	@Autowired
	private StringPropertyEditor								stringPropertyEditor;

	@Autowired
	private CompraSimplificadaFormValidator			compraSimplificadaFormValidator;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(ProveedorSimplificado.class, proveedorSimplificadoPropertyEditor);
		binder.registerCustomEditor(Empresa.class, empresaPropertyEditor);
		binder.registerCustomEditor(String.class, stringPropertyEditor);
	}

	@RequestMapping(value = "/comprasSimplificadas.do", method = RequestMethod.GET)
	public String crearCompras(ModelMap model) {
		return "/views/simplificado/crearCompraSimplificado";
	}

	@RequestMapping(value = "/ListarComprasSimplificado", method = RequestMethod.GET)
	public String listarComprasSimplificadas(ModelMap model) {
		return "/views/simplificado/ListarComprasSimplificado";
	}
	
	@RequestMapping(value = "/ListarConsultaComprasSimplificado", method = RequestMethod.GET)
	public String listarComprasConsultaSimplificadas(ModelMap model) {
		return "/views/simplificado/ListarConsultaComprasSimplificado";
	}

	@RequestMapping(value = "/CrearCompraSimplificadaAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator crearFactura(HttpServletRequest request, ModelMap model, @ModelAttribute CompraSimplificadaCommand compraSimplificadaCommand, BindingResult result, SessionStatus status) {
		RespuestaServiceValidator respuestaServiceValidator = new RespuestaServiceValidator();
		try {

			Usuario usuario = usuarioBo.buscar(request.getUserPrincipal().getName());
			return this.crearFactura(compraSimplificadaCommand, result, usuario);
		} catch (Exception e) {
			respuestaServiceValidator.setStatus(HttpStatus.BAD_REQUEST.value());
			respuestaServiceValidator.setMessage(e.getMessage());
			return respuestaServiceValidator;
		}
	}

	private RespuestaServiceValidator<?> crearFactura(CompraSimplificadaCommand compraSimplificadaCommand, BindingResult result, Usuario usuario) {
		@SuppressWarnings("rawtypes")
		RespuestaServiceValidator respuestaServiceValidator = new RespuestaServiceValidator();
		try {
			compraSimplificadaCommand.setEstado(Constantes.FACTURA_ESTADO_FACTURADO);
			compraSimplificadaCommand.setTipoDoc(Constantes.FACTURA_TIPO_DOC_COMPRA_SIMPLIFICADA);
			compraSimplificadaCommand.setTotalBanco(compraSimplificadaCommand.getTotalBanco() == null ? Constantes.ZEROS_DOUBLE : compraSimplificadaCommand.getTotalBanco());
			compraSimplificadaCommand.setTotalEfectivo(compraSimplificadaCommand.getTotalEfectivo() == null ? Constantes.ZEROS_DOUBLE : compraSimplificadaCommand.getTotalEfectivo());
			compraSimplificadaCommand.setTotalTarjeta(compraSimplificadaCommand.getTotalTarjeta() == null ? Constantes.ZEROS_DOUBLE : compraSimplificadaCommand.getTotalTarjeta());
			compraSimplificadaCommand.setTotalDescuentos(compraSimplificadaCommand.getTotalDescuentos() == null ? Constantes.ZEROS_DOUBLE : compraSimplificadaCommand.getTotalDescuentos());
			compraSimplificadaCommand.setTotalExento(compraSimplificadaCommand.getTotalExento() == null ? Constantes.ZEROS_DOUBLE : compraSimplificadaCommand.getTotalExento());
			compraSimplificadaCommand.setTotalGravado(compraSimplificadaCommand.getTotalGravado() == null ? Constantes.ZEROS_DOUBLE : compraSimplificadaCommand.getTotalGravado());
			compraSimplificadaCommand.setTotalCambioPagar(compraSimplificadaCommand.getTotalCambioPagar() == null ? Constantes.ZEROS_DOUBLE : compraSimplificadaCommand.getTotalCambioPagar());
			compraSimplificadaCommand.setTotalMercanciasExentas(compraSimplificadaCommand.getTotalMercanciasExentas() == null ? Constantes.ZEROS_DOUBLE : compraSimplificadaCommand.getTotalMercanciasExentas());
			compraSimplificadaCommand.setTotalMercanciasGravadas(compraSimplificadaCommand.getTotalMercanciasGravadas() == null ? Constantes.ZEROS_DOUBLE : compraSimplificadaCommand.getTotalMercanciasGravadas());
			compraSimplificadaCommand.setTotalCredito(compraSimplificadaCommand.getTotalCredito() == null ? Constantes.ZEROS_DOUBLE : compraSimplificadaCommand.getTotalCredito());
			compraSimplificadaCommand.setTotalServExentos(compraSimplificadaCommand.getTotalServExentos() == null ? Constantes.ZEROS_DOUBLE : compraSimplificadaCommand.getTotalServExentos());
			compraSimplificadaCommand.setTotalServGravados(compraSimplificadaCommand.getTotalServGravados() == null ? Constantes.ZEROS_DOUBLE : compraSimplificadaCommand.getTotalServGravados());
			compraSimplificadaCommand.setTotalVenta(compraSimplificadaCommand.getTotalVenta() == null ? Constantes.ZEROS_DOUBLE : compraSimplificadaCommand.getTotalVenta());
			compraSimplificadaCommand.setTotalVentaNeta(compraSimplificadaCommand.getTotalVentaNeta() == null ? Constantes.ZEROS_DOUBLE : compraSimplificadaCommand.getTotalVentaNeta());
			compraSimplificadaCommand.setTipoDoc(compraSimplificadaCommand.getTipoDoc() != null ? compraSimplificadaCommand.getTipoDoc() : Constantes.EMPTY);
			compraSimplificadaCommand.setCodigoActividad(compraSimplificadaCommand.getProveedorSimplificado() != null ? compraSimplificadaCommand.getProveedorSimplificado().getCodigoActividad() : Constantes.EMPTY);

			// Si esta en estado facturada en base de datos se retorna un mensaje que ya fue procesada
			if (compraSimplificadaCommand != null) {
				if (compraSimplificadaCommand.getId() != null) {
					if (compraSimplificadaCommand.getId() > Constantes.ZEROS_LONG) {
						CompraSimplificada compraSimplificadaRevision = compraSimplificadaBo.findById(compraSimplificadaCommand.getId());
						if (compraSimplificadaRevision != null) {
							if (compraSimplificadaRevision.getEstado() != null) {
								if (compraSimplificadaRevision.getEstado().equals(Constantes.FACTURA_ESTADO_FACTURADO)) {
									return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("compraSimplificada.error.ya.esta.procesada", result.getAllErrors());
								}
							}

						}

					}
				}
			}
			if (compraSimplificadaCommand.getTipoDoc() == null) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("compraSimplificada.error.tipo.doc", result.getAllErrors());
			}
			if (compraSimplificadaCommand.getTipoDoc() == null) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("compraSimplificada.error.tipo.doc", result.getAllErrors());
			}

			if (compraSimplificadaCommand.getTipoDoc().equals(Constantes.EMPTY)) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("compraSimplificada.error.tipo.doc", result.getAllErrors());
			}

			if (compraSimplificadaCommand.getProveedorSimplificado() == null) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("error.compraSimplificada.proveedor.no.asociado", result.getAllErrors());
			}
			if (compraSimplificadaCommand.getCodigoActividad() == null) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("error.compraSimplificada.actividad.comercial.no.existe", result.getAllErrors());
			} else if (compraSimplificadaCommand.getCodigoActividad().equals(Constantes.EMPTY)) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("error.compraSimplificada.actividad.comercial.no.existe", result.getAllErrors());
			}

			TipoCambio tipoCambio = null;
			if (!compraSimplificadaCommand.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_PROFORMAS)) {
				tipoCambio = tipoCambioBo.findByEstadoAndEmpresa(Constantes.ESTADO_ACTIVO, usuario.getEmpresa());
				if (tipoCambio == null) {
					return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("factura.error.factura.no.hay.tipo.cambio.dolar.activo", result.getAllErrors());

				}
				compraSimplificadaCommand.setTipoCambioMoneda(tipoCambio.getTotal());
			}

			compraSimplificadaCommand.setEmpresa(usuario.getEmpresa());
			compraSimplificadaFormValidator.validate(compraSimplificadaCommand, result);
			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
			}
			if (!compraSimplificadaCommand.getCondicionVenta().equals(Constantes.FACTURA_CONDICION_VENTA_CREDITO)) {
				compraSimplificadaCommand.setFechaCredito(null);
			}
			CompraSimplificada compraSimplificadaBD = compraSimplificadaCommand.getId() == null || compraSimplificadaCommand.getId() == Constantes.ZEROS_LONG ? null : compraSimplificadaBo.findById(compraSimplificadaCommand.getId());
			// Eliminar detalles si existe
			if (compraSimplificadaBD != null) {
				compraSimplificadaBo.eliminarDetalleComprasPorSP(compraSimplificadaBD);
				Collection<DetalleCompraSimplificada> detalles = detalleCompraSimplificadaBo.findByCompraSimplificada(compraSimplificadaBD);
				for (DetalleCompraSimplificada detalleCompraSimplificada : detalles) {
					detalleCompraSimplificadaBo.eliminar(detalleCompraSimplificada);
				}

			}
			CompraSimplificada compraSimplificada = compraSimplificadaBo.crearCompraSimplificada(compraSimplificadaCommand, usuario, tipoCambio);
			if (compraSimplificada == null) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
			}
			CompraSimplificada facturaCreada = compraSimplificadaBo.findById(compraSimplificada.getId());

			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("compraSimplificada.agregar.correctamente", facturaCreada);

		} catch (Exception e) {
			respuestaServiceValidator.setStatus(HttpStatus.BAD_REQUEST.value());
			respuestaServiceValidator.setMessage(e.getMessage());
			return respuestaServiceValidator;
		}

	}

	@RequestMapping(value = "/ListarComprasSimplificadasAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable<Object> listarComprasNoAnuladasAjax(HttpServletRequest request, HttpServletResponse response, @RequestParam String fechaInicio, @RequestParam String fechaFin, @RequestParam Long idProveedor, @RequestParam Integer estado) {

		Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());

		Integer idUsuario = Constantes.ZEROS;
		RespuestaServiceDataTable<Object> respuestaService = new RespuestaServiceDataTable<Object>();
		List<Object> solicitudList = new ArrayList<Object>();
		if (!usuarioBo.isAdministrador_cajero(usuarioSesion) && !usuarioBo.isAdministrador_empresa(usuarioSesion) && !usuarioBo.isAdministrador_restaurante(usuarioSesion)) {
			idUsuario = usuarioSesion.getId();
		}
		Date fechaInicioP = Utils.parseDate(fechaInicio);
		Date fechaFinalP = Utils.parseDate(fechaFin);
		if (!fechaInicio.equals(Constantes.EMPTY) && !fechaFin.equals(Constantes.EMPTY)) {
			if (fechaFinalP != null) {
				fechaFinalP = Utils.sumarDiasFecha(fechaFinalP, 1);
			}

		}

		DateFormat dateFormat1 = new SimpleDateFormat(Constantes.DATE_FORMAT5);

		String inicio1 = dateFormat1.format(fechaInicioP);
		String fin1 = dateFormat1.format(fechaFinalP);
		Collection<CompraSimplificadaNative> objetos = consultasNativeBo.findComprasSimplificadasByFechaAndEstadoAndEmpresa(usuarioSesion.getEmpresa(), inicio1, fin1, idProveedor, estado, idUsuario);

		for (CompraSimplificadaNative compraSimplificadaNative : objetos) {

			// no se carga el usuario del sistema el id -1
			if (compraSimplificadaNative.getId().longValue() > 0L) {
				solicitudList.add(new CompraSimplificadaNativeCommand(compraSimplificadaNative));
			}
		}

		respuestaService.setRecordsTotal(0l);
		respuestaService.setRecordsFiltered(0l);
		if (request.getParameter("draw") != null && !request.getParameter("draw").equals(" ")) {
			respuestaService.setDraw(Integer.parseInt(request.getParameter("draw")));
		}
		respuestaService.setAaData(solicitudList);
		return respuestaService;
	}

	@RequestMapping(value = "/AnularCompraSimplificadaAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator anularCompraSimplificadaAjax(HttpServletRequest request, HttpServletResponse response, @RequestParam Long idFactura) {
		try {
			Usuario usuario = usuarioBo.buscar(request.getUserPrincipal().getName());
			CompraSimplificada compraSimplificada = compraSimplificadaBo.findById(idFactura);
			if (compraSimplificada != null) {
				compraSimplificada.setUsuarioCreacion(usuario);
				compraSimplificada.setUpdated_at(new Date());
				compraSimplificada.setEstado(Constantes.FACTURA_ESTADO_ANULADA);
				compraSimplificada.setEstadoFirma(Constantes.FACTURA_ESTADO_FIRMA_COMPLETO);
				compraSimplificadaBo.modificar(compraSimplificada);
			}

			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("compraSimplificada.anulado.correctamente", compraSimplificada);
		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

//Descarga de manuales de usuario de acuerdo con su perfil
	@RequestMapping(value = "/DescargarComprasSimplificadasAjax.do", method = RequestMethod.GET)
	public void descargarDetalleTotalFacturasAjax(HttpServletRequest request, HttpServletResponse response, @RequestParam String fechaInicio, @RequestParam String fechaFin, @RequestParam Long idProveedor, @RequestParam Integer estado) throws IOException, Exception {

		Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());

		Integer idUsuario = Constantes.ZEROS;
		if (!usuarioBo.isAdministrador_cajero(usuarioSesion) && !usuarioBo.isAdministrador_empresa(usuarioSesion) && !usuarioBo.isAdministrador_restaurante(usuarioSesion)) {
			idUsuario = usuarioSesion.getId();
		}
		Date fechaInicioP = Utils.parseDate(fechaInicio);
		Date fechaFinalP = Utils.parseDate(fechaFin);
		if (!fechaInicio.equals(Constantes.EMPTY) && !fechaFin.equals(Constantes.EMPTY)) {
			if (fechaFinalP != null) {
				fechaFinalP = Utils.sumarDiasFecha(fechaFinalP, 1);
			}
		}
		DateFormat dateFormat1 = new SimpleDateFormat(Constantes.DATE_FORMAT5);

		String inicio1 = dateFormat1.format(fechaInicioP);
		String fin1 = dateFormat1.format(fechaFinalP);
		List<CompraSimplificadaNativeCommand> solicitudList = new ArrayList<CompraSimplificadaNativeCommand>();
		Collection<CompraSimplificadaNative> objetos = consultasNativeBo.findComprasSimplificadasByFechaAndEstadoAndEmpresa(usuarioSesion.getEmpresa(), inicio1, fin1, idProveedor, estado, idUsuario);
		for (CompraSimplificadaNative compraSimplificadaNative : objetos) {

			// no se carga el usuario del sistema el id -1
			if (compraSimplificadaNative.getId().longValue() > 0L) {
				solicitudList.add(new CompraSimplificadaNativeCommand(compraSimplificadaNative));
			}
		}
		String nombreArchivo = "ComprasSimplificadas.xls";
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + nombreArchivo + "\"");
		// Se prepara el excell
		ByteArrayOutputStream baos = createExcelFacturas(solicitudList);
		ByteArrayInputStream inputStream = new ByteArrayInputStream(baos.toByteArray());

		int BUFFER_SIZE = 4096;
		byte[] buffer = new byte[BUFFER_SIZE];
		int bytesRead = -1;
		while ((bytesRead = inputStream.read(buffer)) != -1) {
			response.getOutputStream().write(buffer, 0, bytesRead);
		}
	}

	private ByteArrayOutputStream createExcelFacturas(Collection<CompraSimplificadaNativeCommand> compraSimplificadaNative) {
		// Se prepara el excell
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		List<String> headers = Arrays.asList("Fecha Emision", "# Documento", "Clave", "Proveedor", "Total Descuento", "Total Impuesto", "Total");
		new SimpleExporter().gridExport(headers, compraSimplificadaNative, "fechaEmisionSTR, numeroConsecutivo,clave, nombreProveedor,totalDescuentoSTR,totalImpuestoSTR, totalComprobanteSTR", baos);
		return baos;
	}

	@RequestMapping(value = "/EnvioCorreoComprasSimplificadaAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator envioCorreoComprasSimplificadaAjax(HttpServletRequest request, HttpServletResponse response, @RequestParam String fechaInicio, @RequestParam String fechaFin, @RequestParam Long idProveedor, @RequestParam Integer estado, @RequestParam String correoAlternativo, String totalDescuento, String totalImpuesto, String total) {
		RespuestaServiceValidator respuestaServiceValidator = new RespuestaServiceValidator();
		try {
			Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());

			Integer idUsuario = Constantes.ZEROS;
			if (!usuarioBo.isAdministrador_cajero(usuarioSesion) && !usuarioBo.isAdministrador_empresa(usuarioSesion) && !usuarioBo.isAdministrador_restaurante(usuarioSesion)) {
				idUsuario = usuarioSesion.getId();
			}
			Date fechaInicioP = Utils.parseDate(fechaInicio);
			Date fechaFinalP = Utils.parseDate(fechaFin);
			if (!fechaInicio.equals(Constantes.EMPTY) && !fechaFin.equals(Constantes.EMPTY)) {
				if (fechaFinalP != null) {
					fechaFinalP = Utils.sumarDiasFecha(fechaFinalP, 1);
				}
			}
			DateFormat dateFormat1 = new SimpleDateFormat(Constantes.DATE_FORMAT5);

			String inicio1 = dateFormat1.format(fechaInicioP);
			String fin1 = dateFormat1.format(fechaFinalP);
			List<CompraSimplificadaNativeCommand> solicitudList = new ArrayList<CompraSimplificadaNativeCommand>();
			Collection<CompraSimplificadaNative> objetos = consultasNativeBo.findComprasSimplificadasByFechaAndEstadoAndEmpresa(usuarioSesion.getEmpresa(), inicio1, fin1, idProveedor, estado, idUsuario);
			for (CompraSimplificadaNative compraSimplificadaNative : objetos) {

				// no se carga el usuario del sistema el id -1
				if (compraSimplificadaNative.getId().longValue() > 0L) {
					solicitudList.add(new CompraSimplificadaNativeCommand(compraSimplificadaNative));
				}
			}
			// Se prepara el excell
			ByteArrayOutputStream baos = createExcelFacturas(solicitudList);
			Collection<Attachment> attachments = createAttachments(attachment("CompraSimplificadas", ".xls", new ByteArrayDataSource(baos.toByteArray(), "text/plain")));

			// Se prepara el correo
			String from = "ComprasSimplificadasEmitidas@emprendesoftcr.com";
			if (usuarioSesion.getEmpresa().getAbreviaturaEmpresa() != null) {
				if (!usuarioSesion.getEmpresa().getAbreviaturaEmpresa().equals(Constantes.EMPTY)) {
					from = usuarioSesion.getEmpresa().getAbreviaturaEmpresa() + "_ComprasSimplificadas" + "_No_Reply@emprendesoftcr.com";
				}
			}
			String subject = "Compras Simplificadas dentro del rango de fechas: " + fechaInicio + " al " + fechaFin;

			ArrayList<String> listaCorreos = new ArrayList<>();
			if (correoAlternativo != null && correoAlternativo.length() > 0) {
				listaCorreos.add(correoAlternativo);
			} else {
				listaCorreos.add(usuarioSesion.getEmpresa().getCorreoElectronico());
			}

			Map<String, Object> modelEmail = new HashMap<>();
			modelEmail.put("nombreEmpresa", usuarioSesion.getEmpresa().getNombre());
			modelEmail.put("fechaInicial", fechaInicio);
			modelEmail.put("fechaFinal", fechaFin);
			modelEmail.put("total", total != null ? total : Constantes.ZEROS);
			modelEmail.put("totalDescuentos", totalDescuento == null ? Constantes.ZEROS : totalDescuento);
			modelEmail.put("totalImpuestos", totalImpuesto != null ? totalImpuesto : Constantes.ZEROS);

			correosBo.enviarConAttach(attachments, listaCorreos, from, subject, Constantes.PLANTILLA_CORREO_RESUMEN_COMPRAS_SIMPPLIFICADA_RANGO_FECHA, modelEmail);
			respuestaServiceValidator.setStatus(HttpStatus.OK.value());
			respuestaServiceValidator.setMessage(Constantes.RESOURCE_BUNDLE.getString("hacienda.envio.correo.exitoso"));

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
		return respuestaServiceValidator;
	}

	private Collection<Attachment> createAttachments(Attachment... attachments) {
		return Arrays.asList(attachments);
	}

	private Attachment attachment(String name, String ext, ByteArrayDataSource data) {
		return new Attachment(name + ext, data);
	}

	static class RESPONSES {

		private static class OK {

			private static class COMPRASIMPLIFICADA {

				private static final RespuestaServiceValidator	AGREGADO		= RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("compraSimplificada.agregar.correctamente");
				private static final RespuestaServiceValidator	MODIFICADO	= RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("compraSimplificada.modificado.correctamente");
			}
		}

		private static class ERROR {

			private static class COMPRASIMPLIFICADA {

				private static final RespuestaServiceValidator NO_EXISTE = RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("error.categoria.noExiste");
			}
		}
	}

}
