package com.emprendesoftcr.web.Controller;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

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

import com.emprendesoftcr.Bo.EmpresaBo;
import com.emprendesoftcr.Bo.RecepcionFacturaBo;
import com.emprendesoftcr.Bo.UsuarioBo;
import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.Utils.RespuestaServiceValidator;
import com.emprendesoftcr.Utils.Utils;
import com.emprendesoftcr.modelo.Cliente;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.RecepcionFactura;
import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.modelo.Vendedor;
import com.emprendesoftcr.web.command.RecepcionFacturaAceptacionCommand;
import com.emprendesoftcr.web.propertyEditor.ClientePropertyEditor;
import com.emprendesoftcr.web.propertyEditor.EmpresaPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.FechaPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.StringPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.VendedorPropertyEditor;

@Controller
public class RecepcionComprasController {

	@Autowired
	private UsuarioBo								usuarioBo;

	@Autowired
	private EmpresaBo								empresaBo;

	@Autowired
	private RecepcionFacturaBo			recepcionFacturaBo;

	@Autowired
	private EmpresaPropertyEditor		empresaPropertyEditor;

	@Autowired
	private ClientePropertyEditor		clientePropertyEditor;

	@Autowired
	private VendedorPropertyEditor	vendedorPropertyEditor;

	@Autowired
	private StringPropertyEditor		stringPropertyEditor;

	@Autowired
	private FechaPropertyEditor			fechaPropertyEditor;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Cliente.class, clientePropertyEditor);
		binder.registerCustomEditor(Vendedor.class, vendedorPropertyEditor);
		binder.registerCustomEditor(Empresa.class, empresaPropertyEditor);
		binder.registerCustomEditor(String.class, stringPropertyEditor);
		binder.registerCustomEditor(Date.class, fechaPropertyEditor);
	}

	@RequestMapping(value = "/recepcionFactura", method = RequestMethod.GET)
	public String recepcionFactura(ModelMap model) {
		return "views/facturas/recepcionFactura";
	}

	/**
	 * Listado de facturas anuladas y facturadas
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/ListaRecepcionFacturas", method = RequestMethod.GET)
	public String listaRecepcionFacturas(ModelMap model) {
		return "views/facturas/listaRecepcionFacturas";
	}

	/**
	 * Recibir factura de otro emisor
	 * @param request
	 * @param model
	 * @param recepcionFactura
	 * @param result
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("all")
	@RequestMapping(value = "/AgregarRecepcionFacturaAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator agregarRecepcionFactura(HttpServletRequest request, ModelMap model, @ModelAttribute RecepcionFacturaAceptacionCommand recepcionFactura, BindingResult result, SessionStatus status) throws Exception {
		try {

			String nombreUsuario = request.getUserPrincipal().getName();
			Usuario usuarioSesion = usuarioBo.buscar(nombreUsuario);
//			// Se validan los datos
//			if (recepcionFactura.getMensaje() != null && (!recepcionFactura.getMensaje().equals(Constantes.RECEPCION_FACTURA_MENSAJE_ACEPTADO) && !recepcionFactura.getMensaje().equals(Constantes.RECEPCION_FACTURA_MENSAJE_ACEPTADO_PARCIAL) && !recepcionFactura.getMensaje().equals(Constantes.RECEPCION_FACTURA_MENSAJE_RECHAZADO))) {
//				result.rejectValue("mensaje", "error.recepcionFactura.mensaje.requerido");
//			} else if (!usuarioSesion.getEmpresa().getCedula().trim().toUpperCase().equals(recepcionFactura.getReceptorCedula().trim().toUpperCase())) {
//				result.rejectValue("receptorCedula", "error.recepcionFactura.factura.otro.receptor");
//			} else {
//				Collection<RecepcionFactura> resultado = recepcionFacturaBo.findByClave(recepcionFactura.getEmisorCedula(), recepcionFactura.getFacturaClave());
//				if (resultado != null && resultado.size() > 0) {
//					result.rejectValue("facturaClave", "error.recepcionFactura.ya.exite");
//				}
//			}
			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
			}
			RecepcionFactura recepcionFacturaAgregar = new RecepcionFactura();

			// Se prepara el objeto para almacenarlo
			recepcionFacturaAgregar.setNumeroConsecutivoReceptor(empresaBo.generarConsecutivoRecepcionFactura(usuarioSesion.getEmpresa(), usuarioSesion, recepcionFacturaAgregar));
			recepcionFacturaAgregar.setEstadoFirma(Constantes.FACTURA_ESTADO_FIRMA_PENDIENTE);
			recepcionFacturaAgregar.setEmpresa(usuarioSesion.getEmpresa());
			recepcionFacturaAgregar.setTipoDoc(Utils.obtenerTipoDocumentoConsecutivo(recepcionFactura.getFacturaConsecutivo()));
			recepcionFacturaAgregar.setCreated_at(new Date());
			recepcionFacturaAgregar.setUpdated_at(new Date());
			recepcionFacturaAgregar.setMensaje(recepcionFactura.getMensaje());
			recepcionFacturaAgregar.setDetalleMensaje(recepcionFactura.getDetalleMensaje());
			recepcionFacturaAgregar.setNumeroConsecutivoReceptor(recepcionFactura.getNumeroConsecutivoReceptor());
			recepcionFacturaAgregar.setEstadoFirma(recepcionFactura.getEstadoFirma());
			recepcionFacturaAgregar.setEmisorCedula(recepcionFactura.getEmisorCedula());
			recepcionFacturaAgregar.setEmisorNombre(recepcionFactura.getEmisorNombre());
			recepcionFacturaAgregar.setEmisorTipoCedula(recepcionFactura.getEmisorTipoCedula());
			recepcionFacturaAgregar.setEmisorCorreo(recepcionFactura.getEmisorCorreo());
			recepcionFacturaAgregar.setEmisorTelefono(recepcionFactura.getEmisorTelefono());
			recepcionFacturaAgregar.setEmisorCodigoProvincia(recepcionFactura.getEmisorCodigoProvincia());
			recepcionFacturaAgregar.setEmisorProvincia(recepcionFactura.getEmisorProvincia());
			recepcionFacturaAgregar.setEmisorCanton(recepcionFactura.getEmisorCanton());
			recepcionFacturaAgregar.setEmisorDistrito(recepcionFactura.getEmisorDistrito());
			recepcionFacturaAgregar.setEmisorCodigoDistrito(recepcionFactura.getEmisorCodigoDistrito());
			recepcionFacturaAgregar.setEmisorOtraSena(recepcionFactura.getEmisorOtraSena());
			recepcionFacturaAgregar.setReceptorNombre(recepcionFactura.getReceptorNombre());
			recepcionFacturaAgregar.setReceptorCedula(recepcionFactura.getReceptorCedula());
			recepcionFacturaAgregar.setReceptorTipoCedula(recepcionFactura.getReceptorTipoCedula());
			recepcionFacturaAgregar.setReceptorCorreo(recepcionFactura.getReceptorCorreo());

			recepcionFacturaAgregar.setReceptorProvincia(recepcionFactura.getReceptorProvincia());
			recepcionFacturaAgregar.setReceptorCodigoProvincia(recepcionFactura.getReceptorCodigoProvincia());
			recepcionFacturaAgregar.setReceptorCanton(recepcionFactura.getReceptorCanton());
			recepcionFacturaAgregar.setReceptorCodigoCanton(recepcionFactura.getReceptorCodigoCanton());
			recepcionFacturaAgregar.setReceptorDistrito(recepcionFactura.getReceptorDistrito());
			recepcionFacturaAgregar.setReceptorCodigoDistrito(recepcionFactura.getReceptorCodigoDistrito());
			recepcionFacturaAgregar.setReceptorOtraSena(recepcionFactura.getReceptorOtraSena());
			recepcionFacturaAgregar.setReceptorTelefono(recepcionFactura.getReceptorTelefono());
			recepcionFacturaAgregar.setReceptorNombreComercial(recepcionFactura.getReceptorNombreComercial() == null?Constantes.EMPTY:recepcionFactura.getReceptorNombreComercial());
			recepcionFacturaAgregar.setFacturaConsecutivo(recepcionFactura.getFacturaConsecutivo());
			recepcionFacturaAgregar.setFacturaClave(recepcionFactura.getFacturaClave() ==null?Constantes.EMPTY:recepcionFactura.getFacturaClave());
			recepcionFacturaAgregar.setFacturaFechaEmision(recepcionFactura.getFacturaFechaEmision());
			recepcionFacturaAgregar.setFacturaCondicionVenta(recepcionFactura.getFacturaCondicionVenta() ==null?Constantes.EMPTY:recepcionFactura.getFacturaCondicionVenta());
			recepcionFacturaAgregar.setFacturaMedioPago(recepcionFactura.getFacturaMedioPago() ==null?Constantes.EMPTY:recepcionFactura.getFacturaMedioPago());
			recepcionFacturaAgregar.setFacturaCodigoMoneda(recepcionFactura.getFacturaCodigoMoneda()==null?Constantes.EMPTY:recepcionFactura.getFacturaCodigoMoneda());
			recepcionFacturaAgregar.setFacturaTipoCambio(recepcionFactura.getFacturaTipoCambio());
			recepcionFacturaAgregar.setFacturaTotalServExentos(recepcionFactura.getFacturaTotalServExentos() == null ? Constantes.ZEROS_DOUBLE : recepcionFactura.getFacturaTotalServExentos());
			recepcionFacturaAgregar.setFacturaTotalExento(recepcionFactura.getFacturaTotalExento() == null ? Constantes.ZEROS_DOUBLE : recepcionFactura.getFacturaTotalExento());
			recepcionFacturaAgregar.setFacturaTotalVenta(recepcionFactura.getFacturaTotalVenta() == null ? Constantes.ZEROS_DOUBLE : recepcionFactura.getFacturaTotalVenta());
			recepcionFacturaAgregar.setFacturaTotalVentaNeta(recepcionFactura.getFacturaTotalVentaNeta() == null ? Constantes.ZEROS_DOUBLE : recepcionFactura.getFacturaTotalVentaNeta());
			recepcionFacturaAgregar.setFacturaTotalComprobante(recepcionFactura.getFacturaTotalComprobante() == null ? Constantes.ZEROS_DOUBLE : recepcionFactura.getFacturaTotalComprobante());
			recepcionFacturaAgregar.setFacturaTotalImpuestos(recepcionFactura.getFacturaTotalImpuestos() == null ? Constantes.ZEROS_DOUBLE : recepcionFactura.getFacturaTotalImpuestos());
			recepcionFacturaAgregar.setUpdated_at(new Date());
			recepcionFacturaAgregar.setCreated_at(new Date());
			recepcionFacturaAgregar.setTipoDoc(Utils.obtenerTipoDocumentoConsecutivo(recepcionFactura.getTipoDoc()));
			recepcionFacturaAgregar.setCodigoActividad(recepcionFactura.getCodigoActividad() == null ? Constantes.EMPTY : recepcionFactura.getCodigoActividad());
			recepcionFacturaAgregar.setFacturaTipoDocumentoOtroCargo(recepcionFactura.getFacturaTipoDocumentoOtroCargo() == null ? Constantes.EMPTY : recepcionFactura.getFacturaTipoDocumentoOtroCargo());
			recepcionFacturaAgregar.setFacturaDetalleOtroCargo(recepcionFactura.getFacturaDetalleOtroCargo() == null ? Constantes.EMPTY : recepcionFactura.getFacturaDetalleOtroCargo());
			recepcionFacturaAgregar.setFacturaMontoCargoOtroCargo(recepcionFactura.getFacturaMontoCargoOtroCargo() == null ? Constantes.ZEROS_DOUBLE : recepcionFactura.getFacturaMontoCargoOtroCargo());
			recepcionFacturaAgregar.setFacturaTotalServGravados(recepcionFactura.getFacturaTotalServGravados() == null ? Constantes.ZEROS_DOUBLE : recepcionFactura.getFacturaTotalServGravados());
			recepcionFacturaAgregar.setFacturaTotalMercanciasExentas(recepcionFactura.getFacturaTotalMercanciasExentas() == null ? Constantes.ZEROS_DOUBLE : recepcionFactura.getFacturaTotalMercanciasExentas());
			recepcionFacturaAgregar.setFacturaTotalMercanciasGravadas(recepcionFactura.getFacturaTotalMercanciasGravadas() == null ? Constantes.ZEROS_DOUBLE : recepcionFactura.getFacturaTotalMercanciasGravadas());
			recepcionFacturaAgregar.setFacturaTotalMercExonerada(recepcionFactura.getFacturaTotalMercExonerada() == null ? Constantes.ZEROS_DOUBLE : recepcionFactura.getFacturaTotalMercExonerada());
			recepcionFacturaAgregar.setFacturaTotalGravado(recepcionFactura.getFacturaTotalGravado() == null ? Constantes.ZEROS_DOUBLE : recepcionFactura.getFacturaTotalGravado());
			recepcionFacturaAgregar.setFacturaTotalExonerado(recepcionFactura.getFacturaTotalExonerado() == null ? Constantes.ZEROS_DOUBLE : recepcionFactura.getFacturaTotalExonerado());
			recepcionFacturaAgregar.setFacturaTotalDescuentos(recepcionFactura.getFacturaTotalDescuentos() == null ? Constantes.ZEROS_DOUBLE : recepcionFactura.getFacturaTotalDescuentos());
			recepcionFacturaAgregar.setFacturaTotalIVADevuelto(recepcionFactura.getFacturaTotalIVADevuelto() == null ? Constantes.ZEROS_DOUBLE : recepcionFactura.getFacturaTotalIVADevuelto());
			recepcionFacturaAgregar.setFacturaTotalOtrosCargos(recepcionFactura.getFacturaTotalOtrosCargos() == null ? Constantes.ZEROS_DOUBLE : recepcionFactura.getFacturaTotalOtrosCargos());
			recepcionFacturaAgregar.setEmpresa(usuarioSesion.getEmpresa());

			recepcionFacturaBo.agregar(recepcionFacturaAgregar);
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("recepcionFactura.agregar.correctamente", recepcionFacturaAgregar);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}

	}

}
