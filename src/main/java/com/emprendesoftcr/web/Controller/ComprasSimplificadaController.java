package com.emprendesoftcr.web.Controller;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

import com.emprendesoftcr.Bo.CompraSimplificadaBo;
import com.emprendesoftcr.Bo.DetalleCompraSimplificadaBo;
import com.emprendesoftcr.Bo.TipoCambioBo;
import com.emprendesoftcr.Bo.UsuarioBo;
import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.Utils.RespuestaServiceValidator;
import com.emprendesoftcr.modelo.CompraSimplificada;
import com.emprendesoftcr.modelo.DetalleCompraSimplificada;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.ProveedorSimplificado;
import com.emprendesoftcr.modelo.TipoCambio;
import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.modelo.UsuarioCaja;
import com.emprendesoftcr.validator.CompraSimplificadaFormValidator;
import com.emprendesoftcr.web.command.CompraSimplificadaCommand;
import com.emprendesoftcr.web.propertyEditor.EmpresaPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.ProveedorSimplificadoPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.StringPropertyEditor;


@Controller
public class ComprasSimplificadaController {

	@Autowired
	private UsuarioBo												usuarioBo;

	@Autowired
	private TipoCambioBo										tipoCambioBo;

	@Autowired
	private DetalleCompraSimplificadaBo			detalleCompraSimplificadaBo;

	@Autowired
	private CompraSimplificadaBo						compraSimplificadaBo;

	@Autowired
	private EmpresaPropertyEditor						empresaPropertyEditor;

	@Autowired
	private ProveedorSimplificadoPropertyEditor					proveedorSimplificadoPropertyEditor;

	@Autowired
	private StringPropertyEditor						stringPropertyEditor;

	@Autowired
	private CompraSimplificadaFormValidator	compraSimplificadaFormValidator;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(ProveedorSimplificado.class, proveedorSimplificadoPropertyEditor);
		binder.registerCustomEditor(Empresa.class, empresaPropertyEditor);
		binder.registerCustomEditor(String.class, stringPropertyEditor);
	}

	@RequestMapping(value = "/comprasSimplificadas", method = RequestMethod.GET)
	public String crearCompras(ModelMap model) {
		return "/views/simplificado/crearCompraSimplificado";
	}

	@RequestMapping(value = "/CrearCompraSimplificadaAjax", method = RequestMethod.POST, headers = "Accept=application/json")
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
			compraSimplificadaCommand.setTotalBanco(compraSimplificadaCommand.getTotalBanco() == null ? Constantes.ZEROS_DOUBLE : compraSimplificadaCommand.getTotalBanco());
			compraSimplificadaCommand.setTotalEfectivo(compraSimplificadaCommand.getTotalEfectivo() == null ? Constantes.ZEROS_DOUBLE : compraSimplificadaCommand.getTotalEfectivo());
			compraSimplificadaCommand.setTotalTarjeta(compraSimplificadaCommand.getTotalTarjeta() == null ? Constantes.ZEROS_DOUBLE : compraSimplificadaCommand.getTotalTarjeta());
			compraSimplificadaCommand.setTotalTransporte(compraSimplificadaCommand.getTotalTransporte() == null ? Constantes.ZEROS_DOUBLE : compraSimplificadaCommand.getTotalTransporte());
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
			UsuarioCaja usuarioCajaBd = null;
			// Si esta en estado facturada en base de datos se retorna un mensaje que ya fue procesada
			if (compraSimplificadaCommand != null) {
				if (compraSimplificadaCommand.getId() != null) {
					if (compraSimplificadaCommand.getId() > Constantes.ZEROS_LONG) {
						CompraSimplificada compraSimplificadaRevision = compraSimplificadaBo.findById(compraSimplificadaCommand.getId());
						if (compraSimplificadaRevision != null) {
							if (compraSimplificadaRevision.getEstado() != null) {
								if (compraSimplificadaRevision.getEstado().equals(Constantes.FACTURA_ESTADO_FACTURADO)) {
									return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("factura.error.ya.esta.procesada", result.getAllErrors());
								}
							}

						}

					}
				}
			}
			if (compraSimplificadaCommand.getTipoDoc() == null) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("factura.error.tipo.doc", result.getAllErrors());
			}
			if (compraSimplificadaCommand.getTipoDoc() == null) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("factura.error.tipo.doc", result.getAllErrors());
			}

			if (compraSimplificadaCommand.getTipoDoc().equals(Constantes.EMPTY)) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("factura.error.tipo.doc", result.getAllErrors());
			}

			if (compraSimplificadaCommand.getCodigoActividad() == null) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("error.factura.actividad.comercial.no.existe", result.getAllErrors());
			} else if (compraSimplificadaCommand.getCodigoActividad().equals(Constantes.EMPTY)) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("error.factura.actividad.comercial.no.existe", result.getAllErrors());
			}

			if (!compraSimplificadaCommand.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_PROFORMAS) && !compraSimplificadaCommand.getCondicionVenta().equals(Constantes.FACTURA_CONDICION_VENTA_CREDITO)) {
				if (compraSimplificadaCommand.getEstado().equals(Constantes.FACTURA_ESTADO_FACTURADO)) {
					if (compraSimplificadaCommand.getTotalBanco().equals(Constantes.ZEROS_DOUBLE) && compraSimplificadaCommand.getTotalEfectivo().equals(Constantes.ZEROS_DOUBLE) && compraSimplificadaCommand.getTotalTarjeta().equals(Constantes.ZEROS_DOUBLE)) {
						return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("factura.error.factura.no.hay.ingreso.dinero", result.getAllErrors());
					}

				}
			}
			TipoCambio tipoCambio = null;
			if (!compraSimplificadaCommand.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_PROFORMAS)) {
				tipoCambio = tipoCambioBo.findByEstadoAndEmpresa(Constantes.ESTADO_ACTIVO, usuario.getEmpresa());
				if (tipoCambio == null) {
					return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("factura.error.factura.no.hay.tipo.cambio.dolar.activo", result.getAllErrors());

				}
				compraSimplificadaCommand.setTipoCambioMoneda(tipoCambio.getTotal());
			}

			if (compraSimplificadaCommand.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_ELECTRONICA) && !usuario.getEmpresa().getNoFacturaElectronica().equals(Constantes.NO_APLICA_FACTURA_ELECTRONICA)) {
				if (compraSimplificadaCommand.getProveedorSimplificado().getNombreCompleto().equals(Constantes.NOMBRE_CLIENTE_FRECUENTE)) {
					return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("error.factura.tipo.documento.factura", result.getAllErrors());
				}
				if (compraSimplificadaCommand.getProveedorSimplificado().getCedula().equals(Constantes.CEDULA_CLIENTE_FRECUENTE)) {
					return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("error.factura.tipo.documento.factura", result.getAllErrors());
				}
			}

			// Validar el codigo de factura que se le va aplicar una nota de credito
			if (compraSimplificadaCommand.getReferenciaNumero() != null) {
				if (!compraSimplificadaCommand.getReferenciaNumero().equals(Constantes.EMPTY)) {
					CompraSimplificada compraSimplificadaReferenciaValidar = compraSimplificadaBo.findByConsecutivoAndEmpresa(compraSimplificadaCommand.getReferenciaNumero(), usuario.getEmpresa());
					if (compraSimplificadaReferenciaValidar.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO) && compraSimplificadaReferenciaValidar.getReferenciaCodigo().equals(Constantes.FACTURA_CODIGO_REFERENCIA_ANULA_DOCUMENTO)) {
						return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("factura.error.nota.credito.con.anulacion.completa", result.getAllErrors());
					}
					if (compraSimplificadaReferenciaValidar == null) {
						return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("factura.error.factura.aplicar.nota.credito.o.debito.no.existe", result.getAllErrors());
					} else {
						compraSimplificadaCommand.setReferenciaTipoDoc(compraSimplificadaReferenciaValidar.getTipoDoc());

					}
				}
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

			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("factura.agregar.correctamente", facturaCreada);

		} catch (Exception e) {
			respuestaServiceValidator.setStatus(HttpStatus.BAD_REQUEST.value());
			respuestaServiceValidator.setMessage(e.getMessage());
			return respuestaServiceValidator;
		}

	}

	





	static class RESPONSES {

		private static class OK {

			private static class COMPRASIMPLIFICADA {

				private static final RespuestaServiceValidator	AGREGADO		= RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("compra.agregar.correctamente");
				private static final RespuestaServiceValidator	MODIFICADO	= RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("compra.modificado.correctamente");
			}
		}

		private static class ERROR {

			private static class COMPRASIMPLIFICADA {

				private static final RespuestaServiceValidator NO_EXISTE = RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("error.categoria.noExiste");
			}
		}
	}

}
