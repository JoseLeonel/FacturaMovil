package com.emprendesoftcr.Bo.Impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.DoubleSummaryStatistics;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import com.emprendesoftcr.Bo.ConteoManualCajaBo;
import com.emprendesoftcr.Bo.CorreosBo;
import com.emprendesoftcr.Bo.SalidaEntradaDineroBo;
import com.emprendesoftcr.Bo.UsuarioCajaBo;
import com.emprendesoftcr.Dao.CajaDao;
import com.emprendesoftcr.Dao.UsuarioCajaDao;
import com.emprendesoftcr.Dao.UsuarioDao;
import com.emprendesoftcr.modelo.Attachment;
import com.emprendesoftcr.modelo.Caja;
import com.emprendesoftcr.modelo.ConteoManualCaja;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.SalidaEntradaDinero;
import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.modelo.UsuarioCaja;
import com.emprendesoftcr.modelo.sqlNativo.UsuarioCajaCategoriaArticulo;
import com.emprendesoftcr.utils.Constantes;
import com.emprendesoftcr.utils.DataTableDelimitador;
import com.emprendesoftcr.utils.JqGridFilter;
import com.emprendesoftcr.utils.RespuestaServiceDataTable;
import com.emprendesoftcr.utils.RespuestaServiceValidator;
import com.emprendesoftcr.utils.Utils;
import com.emprendesoftcr.web.command.ConteoManualCommand;
import com.emprendesoftcr.web.command.DenominacionCommand;
import com.emprendesoftcr.web.command.UsuarioCajaCommand;
import com.google.gson.Gson;

@EnableTransactionManagement
@Service("usuarioCajaBo")
public class UsuarioCajaBoImpl implements UsuarioCajaBo {

	@Autowired
	UsuarioCajaDao								usuarioCajaDao;

	@Autowired
	UsuarioDao										usuarioDao;

	@Autowired
	private CajaDao								cajaDao;
	@Autowired
	private ConteoManualCajaBo		conteoManualCajaBo;
	@Autowired
	private SalidaEntradaDineroBo	salidaEntradaDineroBo;
	@Autowired
	private CorreosBo correosBo;

	private Logger								log	= LoggerFactory.getLogger(this.getClass());

	@Transactional
	@Override
	public void agregar(UsuarioCaja usuarioCaja) {
		usuarioCajaDao.agregar(usuarioCaja);
	}

	@Transactional
	@Override
	public void modificar(UsuarioCaja usuarioCaja) {
		usuarioCajaDao.modificar(usuarioCaja);
	}

	@Transactional
	@Override
	public void eliminar(UsuarioCaja usuarioCaja) {
		usuarioCajaDao.eliminar(usuarioCaja);
	}

	/**
	 * Buscar por id
	 * @param id
	 * @return
	 */
	@Override
	public UsuarioCaja buscar(Long id) {
		return usuarioCajaDao.buscar(id);
	}

	/**
	 * Buscar por usuario y estado
	 * @see com.emprendesoftcr.Bo.UsuarioCajaBo#findByUsuarioAndEstado(com.emprendesoftcr.modelo.Usuario, java.lang.String)
	 */
	@Override
	public UsuarioCaja findByUsuarioAndEstado(Usuario usuario, String estado) {
		return usuarioCajaDao.findByUsuarioAndEstado(usuario, estado);

	}

	/**
	 * Cerrar la caja
	 * @see com.emprendesoftcr.Bo.UsuarioCajaBo#cierreCaja(com.emprendesoftcr.modelo.UsuarioCaja)
	 */
	@Transactional
	@Override
	public UsuarioCaja cierreCaja(UsuarioCaja usuarioCaja, ArrayList<DenominacionCommand> listaCoteo, Usuario usuario) throws Exception {
		try {

			usuarioCaja.setUpdated_at(new Date());
			usuarioCaja.setEstado(Constantes.ESTADO_INACTIVO);
			Double resultado = Constantes.ZEROS_DOUBLE;
			Double totalEfectivo = usuarioCaja.getTotalEfectivo() == null ? Constantes.ZEROS_DOUBLE : usuarioCaja.getTotalEfectivo();

			Double totalBanco = usuarioCaja.getTotalBanco() == null ? Constantes.ZEROS_DOUBLE : usuarioCaja.getTotalBanco();
			Double totalTarjeta = usuarioCaja.getTotalTarjeta() == null ? Constantes.ZEROS_DOUBLE : usuarioCaja.getTotalTarjeta();
			Double totalAbono = usuarioCaja.getTotalAbono() == null ? Constantes.ZEROS_DOUBLE : usuarioCaja.getTotalAbono();
			resultado = totalEfectivo + totalAbono + totalTarjeta + totalBanco;

			usuarioCaja.setTotalNeto(Utils.roundFactura(resultado, 2));

			usuarioCaja = agregarConteo(listaCoteo, usuarioCaja, usuario, 2);

			DoubleSummaryStatistics doubleSummaryStatistics = usuarioCaja.getConteoManualCajas().stream().filter(x -> x.getTipo().equals(Constantes.CONTEO_CIERRE_CAJA_TIPO)).collect(Collectors.summarizingDouble(ConteoManualCaja::getTotal));
			usuarioCaja.setTotalConversionColones(Utils.roundFactura(usuarioCaja.getConteoDolar() * usuarioCaja.getTipoCambio(), 2));
			Double cierreDataFono = usuarioCaja.getConteoTarjeta() == null ? Constantes.ZEROS_DOUBLE : usuarioCaja.getConteoTarjeta();
			usuarioCaja.setConteoManual(doubleSummaryStatistics.getSum());

			usuarioCaja.setTotalCierre(doubleSummaryStatistics.getSum() + cierreDataFono + usuarioCaja.getTotalConversionColones() + usuarioCaja.getSumaSalida());
			Double totalGeneral = usuarioCaja.getTotalNeto() + usuarioCaja.getSumaEntradas();
			Double conteManual = usuarioCaja.getConteoManual() == null ? Constantes.ZEROS_DOUBLE : usuarioCaja.getConteoManual();

			if (totalGeneral != null) {
				if (conteManual.equals(Constantes.ZEROS_DOUBLE)) {
					Double totalConteoManual = cierreDataFono + usuarioCaja.getTotalConversionColones() + usuarioCaja.getSumaSalida();

					usuarioCaja.setDiferencia(totalConteoManual - totalGeneral);
				} else {
					Double totalConteoManual = conteManual + cierreDataFono + usuarioCaja.getTotalConversionColones();
					totalConteoManual = totalConteoManual + usuarioCaja.getSumaSalida();
					usuarioCaja.setDiferencia(totalConteoManual - totalGeneral);
				}

			}

			usuarioCaja.setCierreCaja(new Date());
			modificar(usuarioCaja);

		} catch (Exception e) {
			log.info("** Error  aplicar cierreCaja : " + e.getMessage() + " fecha " + new Date());
			throw e;
		}

		return usuarioCaja;
	}

	@Transactional
	private UsuarioCaja agregarConteo(ArrayList<DenominacionCommand> listaCoteo, UsuarioCaja usuarioCaja, Usuario usuario, Integer tipo) {
		UsuarioCaja usuarioCajaTemp = usuarioCaja;
		try {
			for (DenominacionCommand denominacionCommand : listaCoteo) {
				if (denominacionCommand.getTotal() != null && denominacionCommand.getTotal() > 0) {
					ConteoManualCaja conteoManualCaja = new ConteoManualCaja();
					conteoManualCaja.setCantidad(denominacionCommand.getCantidad() == null ? Constantes.ZEROS_DOUBLE : denominacionCommand.getCantidad());
					conteoManualCaja.setDenominacion(denominacionCommand.getDenominacion() == null ? Constantes.EMPTY : denominacionCommand.getDenominacion());
					conteoManualCaja.setTipo(denominacionCommand.getTipo() == null ? Constantes.ZEROS : denominacionCommand.getTipo());
					conteoManualCaja.setTotal(denominacionCommand.getTotal() == null ? Constantes.ZEROS_DOUBLE : denominacionCommand.getTotal());
					conteoManualCaja.setMoneda(denominacionCommand.getMoneda());
					conteoManualCaja.setCreated_at(new Date());
					conteoManualCaja.setUpdated_at(new Date());
					conteoManualCaja.setUsuarioCaja(usuarioCaja);
					conteoManualCaja.setUsuarioCierra(tipo.equals(1) ? null : usuario);
					usuarioCajaTemp.addConteoManual(conteoManualCaja);
				}
			}

		} catch (Exception e) {
			log.info("** Error  agregarConteo: " + e.getMessage() + " fecha " + new Date());

			throw e;
		}
		return usuarioCajaTemp;
	}

	/**
	 * Actualiza Caja activa
	 * @see com.emprendesoftcr.Bo.UsuarioCajaBo#actualizarCaja(java.lang.Double, java.lang.Double, java.lang.Double, java.lang.Double, java.lang.Double)
	 */
	@Transactional
	@Override
	public void actualizarCaja(UsuarioCaja usuarioCaja, Double totalEfectivo, Double totalTarjeta, Double totalBanco, Double totalCredito, Double totalAbono, Double totalServicio) throws Exception {
		usuarioCajaDao.actualizarCaja(usuarioCaja, totalEfectivo, totalTarjeta, totalBanco, totalCredito, totalAbono, totalServicio);
	}

	@Transactional
	@Override
	public void actualizarCaja(UsuarioCaja usuarioCaja) throws Exception {
		usuarioCajaDao.actualizarCaja(usuarioCaja);
	}

	@Override
	public ArrayList<UsuarioCajaCategoriaArticulo> agrupaArticulosCategoria(Integer empresaId, Long usuarioCajaId) {
		return usuarioCajaDao.agrupaArticulosCategoria(empresaId, usuarioCajaId);
	}

	@Transactional
	@Override
	public UsuarioCaja aperturaCaja(ArrayList<DenominacionCommand> listaCoteo, Usuario usuario, Caja caja) throws Exception {
		UsuarioCaja usuarioCaja = new UsuarioCaja();
		try {

			usuarioCaja.setCreated_at(new Date());
			usuarioCaja.setCaja(caja);
			usuarioCaja.setUsuario(usuario);
			usuarioCaja.setUpdated_at(new Date());
			usuarioCaja.setEstado(Constantes.ESTADO_ACTIVO);
			usuarioCaja.setUsuario(usuario);
			usuarioCaja.setTotalBanco(Constantes.ZEROS_DOUBLE);
			usuarioCaja.setTotalEfectivo(Constantes.ZEROS_DOUBLE);
			usuarioCaja.setTotalNeto(Constantes.ZEROS_DOUBLE);
			usuarioCaja.setTotalTarjeta(Constantes.ZEROS_DOUBLE);
			usuarioCaja.setTotalAbono(Constantes.ZEROS_DOUBLE);
			usuarioCaja.setTotalCredito(Constantes.ZEROS_DOUBLE);
			usuarioCaja.setTotalServicio(Constantes.ZEROS_DOUBLE);
			usuarioCaja = agregarConteo(listaCoteo, usuarioCaja, usuario, 1);
			if (usuarioCaja.getConteoManualCajas() != null && usuarioCaja.getConteoManualCajas().size() > 0) {
				DoubleSummaryStatistics doubleSummaryStatistics = usuarioCaja.getConteoManualCajas().stream().filter(x -> x.getTipo().equals(Constantes.CONTEO_APERTURA_CAJA_TIPO)).collect(Collectors.summarizingDouble(ConteoManualCaja::getTotal));
				usuarioCaja.setTotalFondoInicial(doubleSummaryStatistics.getSum());

			} else {
				usuarioCaja.setTotalFondoInicial(Constantes.ZEROS_DOUBLE);
			}
			usuarioCaja.setConteoManual(Constantes.ZEROS_DOUBLE);
			usuarioCaja.setConteoTarjeta(Constantes.ZEROS_DOUBLE);

			usuarioCaja.setTotalDolares(Constantes.ZEROS_DOUBLE);
			usuarioCaja.setTipoCambio(Constantes.ZEROS_DOUBLE);
			usuarioCaja.setTotalConversionColones(Constantes.ZEROS_DOUBLE);
			usuarioCaja.setNotaCredito(Constantes.ZEROS_DOUBLE);
			usuarioCaja.setNotaDebito(Constantes.ZEROS_DOUBLE);
			usuarioCaja.setConteoDolar(Constantes.ZEROS_DOUBLE);
			usuarioCaja.setNotificacion(Constantes.ZEROS);

			agregar(usuarioCaja);

//			usuarioCajaDao.modificar(usuarioCaja);

		} catch (Exception e) {
			log.info("** Error  aplicar aperturaCaja : " + e.getMessage() + " fecha " + new Date());
			throw e;
		}
		return usuarioCaja;
	}

	@Override
	public Collection<UsuarioCaja> usuarioCajaBy(Empresa empresa, String estado) {

		return usuarioCajaDao.usuarioCajaBy(empresa, estado);
	}

	@Override
	public void eliminarConteo(UsuarioCaja usuarioCaja, Integer tipo) throws Exception {
		usuarioCajaDao.eliminarConteo(usuarioCaja, tipo);

	}

	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public RespuestaServiceDataTable<Object> listarUsuariosCajasActivasAjax(HttpServletRequest request, HttpServletResponse response) throws Exception {
		DataTableDelimitador delimitadores = null;
		delimitadores = new DataTableDelimitador(request, "UsuarioCaja");

		JqGridFilter dataTableFilter = null;
		Usuario usuario = usuarioDao.buscar(request.getUserPrincipal().getName());
		UsuarioCaja usuarioCajaBd = findByUsuarioAndEstado(usuario, Constantes.ESTADO_ACTIVO);
		if (usuarioCajaBd != null) {
			actualizarCaja(usuarioCajaBd);
		}

		if (usuarioDao.isUsuario_Vendedor(usuario) || usuarioDao.isUsuario_Cajero(usuario) || usuarioDao.isUsuario_Mesero(usuario) || usuarioDao.isUsuario_SuperDario(usuario)) {
			dataTableFilter = new JqGridFilter("usuario.id", "'" + usuario.getId().toString() + "'", "=");
			delimitadores.addFiltro(dataTableFilter);
		}
		// Se incluye la empresa
		dataTableFilter = new JqGridFilter("caja.empresa.id", "'" + usuario.getEmpresa().getId().toString() + "'", "=");
		delimitadores.addFiltro(dataTableFilter);

		dataTableFilter = new JqGridFilter("estado", "'" + Constantes.ESTADO_ACTIVO.toString() + "'", "=");
		delimitadores.addFiltro(dataTableFilter);
		List<Object> solicitudList = new ArrayList<Object>();
		Collection<UsuarioCaja> objetos = usuarioCajaBy(usuario.getEmpresa(), Constantes.ESTADO_ACTIVO);
		if (objetos != null) {
			for (UsuarioCaja usuarioCaja : objetos) {
				if (usuarioCaja.getId().longValue() > 0L) {
					if (usuarioDao.isUsuario_SuperDario(usuario)) {
						if (usuarioCaja.getUsuario().getId().equals(usuario.getId())) {
							usuarioCaja.setTotalNeto(Constantes.ZEROS_DOUBLE);
							// usuarioCaja.settotal
							solicitudList.add(new UsuarioCajaCommand(usuarioCaja));
						}

					} else {
						if (usuarioDao.isAdministrador_cajero(usuario) || usuarioDao.isAdministrador_empresa(usuario) || usuarioDao.isAdministrador_restaurante(usuario)) {
							solicitudList.add(new UsuarioCajaCommand(usuarioCaja));
						} else {
							if (usuarioCaja.getUsuario().getId().equals(usuario.getId())) {
								usuarioCaja.setTotalNeto(Constantes.ZEROS_DOUBLE);
								// usuarioCaja.settotal
								solicitudList.add(new UsuarioCajaCommand(usuarioCaja));
							}
						}

					}

				}
			}

		}
		@SuppressWarnings("rawtypes")
		RespuestaServiceDataTable respuestaService = new RespuestaServiceDataTable();

		respuestaService.setRecordsTotal((long) solicitudList.size());
		respuestaService.setRecordsFiltered((long) solicitudList.size());
		if (request.getParameter("draw") != null && !request.getParameter("draw").equals(" ")) {
			respuestaService.setDraw(Integer.parseInt(request.getParameter("draw")));
		}
		respuestaService.setAaData(solicitudList);
		return respuestaService;
	}

	@Transactional
	@Override
	public RespuestaServiceValidator<?> agregarCaja(HttpServletRequest request, ConteoManualCommand conteoManualCommand, BindingResult result) throws Exception {

		@SuppressWarnings({ "rawtypes", "unused" })
		RespuestaServiceValidator respuestaServiceValidator = new RespuestaServiceValidator();
		try {
			Usuario usuario = usuarioDao.buscar(request.getUserPrincipal().getName());
			UsuarioCaja usuarioCajaBd = findByUsuarioAndEstado(usuario, Constantes.ESTADO_ACTIVO);
			if (usuarioCajaBd != null) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("error.usuarioCaja.totalFondoInicial.existe.activo", result.getAllErrors());
			}

			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
			}
			ArrayList<DenominacionCommand> listaCoteo = new ArrayList<>();
			listaCoteo = denominacionCommand(conteoManualCommand);
			Caja caja = cajaDao.buscarCajaActiva(usuario.getEmpresa(), usuario);
			UsuarioCaja usuarioCaja = aperturaCaja(listaCoteo, usuario, caja);
			UsuarioCaja usuarioCajaBd1 = buscar(usuarioCaja.getId());
			UsuarioCajaCommand usuarioCajaCommand = new UsuarioCajaCommand(usuarioCajaBd1);
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("usuarioCaja.agregar.correctamente", usuarioCajaCommand);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	private ArrayList<DenominacionCommand> denominacionCommand(ConteoManualCommand conteoManualCommand) throws Exception {
		JSONObject json = null;
		ArrayList<DenominacionCommand> denominacionCommands = new ArrayList<>();
		try {
			json = (JSONObject) new JSONParser().parse(conteoManualCommand.getDenominacion());
			// Agregar Lineas
			JSONArray jsonArrayDetalleFactura = (JSONArray) json.get("data");
			Gson gson = new Gson();
			if (jsonArrayDetalleFactura != null) {
				for (int i = 0; i < jsonArrayDetalleFactura.size(); i++) {
					DenominacionCommand denominacionCommand = gson.fromJson(jsonArrayDetalleFactura.get(i).toString(), DenominacionCommand.class);
					denominacionCommands.add(denominacionCommand);
				}
			}
		} catch (org.json.simple.parser.ParseException e) {
			throw e;
		}
		return denominacionCommands;
	}

	@Transactional
	@Override
	public RespuestaServiceValidator<?> cerrarCajaCajero(HttpServletRequest request, ConteoManualCommand conteoManualCommand, BindingResult result) throws Exception {
		@SuppressWarnings({ "unused", "rawtypes" })
		RespuestaServiceValidator respuestaServiceValidator = new RespuestaServiceValidator();
		try {
			Usuario usuario = usuarioDao.buscar(request.getUserPrincipal().getName());
			UsuarioCaja usuarioCajaBd = buscar(conteoManualCommand.getId());

			if (usuarioCajaBd == null) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("error.usuarioCaja.noExiste", result.getAllErrors());
			}

			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
			}

			ArrayList<DenominacionCommand> listaCoteo = new ArrayList<>();
			listaCoteo = denominacionCommand(conteoManualCommand);

			eliminarConteo(usuarioCajaBd, 2);
			// Se acutalizan los registros
			actualizarCaja(usuarioCajaBd);

			usuarioCajaBd.setConteoTarjeta(conteoManualCommand.getConteoTarjeta() == null ? Constantes.ZEROS_DOUBLE : conteoManualCommand.getConteoTarjeta());

			usuarioCajaBd.setConteoDolar(conteoManualCommand.getConteoDolar() == null ? Constantes.ZEROS_DOUBLE : conteoManualCommand.getConteoDolar());

			usuarioCajaBd.setTipoCambio(conteoManualCommand.getTipoCambio() == null ? Constantes.ZEROS_DOUBLE : conteoManualCommand.getTipoCambio());

			usuarioCajaBd.setConteoManual(Constantes.ZEROS_DOUBLE);

			// Se cierra la caja
			cierreCaja(usuarioCajaBd, listaCoteo, usuario);

			UsuarioCajaCommand usuarioCajaCommand = new UsuarioCajaCommand(usuarioCajaBd);

			enviarCorreoCierreCaja(usuarioCajaCommand, usuario);
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("usuarioCaja.cierre.correctamente", usuarioCajaCommand);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}


	private void enviarCorreoCierreCaja(UsuarioCajaCommand usuarioCajaCommand, Usuario usuario) throws Exception {

		Map<String, Object> modelEmail = new HashMap<>();
		ArrayList<String> listaCorreos = new ArrayList<>();

		if (usuario.getEmpresa().getCorreoCaja1() != null) {
			if (!usuario.getEmpresa().getCorreoCaja1().isEmpty()) {
				listaCorreos.add(usuario.getEmpresa().getCorreoCaja1());
			}

		}
		if (usuario.getEmpresa().getCorreoCaja2() != null) {
			if (!usuario.getEmpresa().getCorreoCaja2().isEmpty()) {
				listaCorreos.add(usuario.getEmpresa().getCorreoCaja2());
			}

		}

		if (!listaCorreos.isEmpty()) {

			UsuarioCaja usuarioCajaBd = buscar(usuarioCajaCommand.getId());
			UsuarioCajaCommand usuarioCajaCommand1 = new UsuarioCajaCommand(usuarioCajaBd);
			String nombreUsuario = usuarioCajaCommand1.getUsuario().getNombre() == null ? Constantes.EMPTY : usuarioCajaCommand1.getUsuario().getNombre().trim();
			String apellido1 = usuarioCajaCommand.getUsuario().getPrimerApellido() == null ? Constantes.EMPTY : usuarioCajaCommand.getUsuario().getPrimerApellido().trim();
			String apellido2 = usuarioCajaCommand.getUsuario().getSegundoApellido() == null ? Constantes.EMPTY : usuarioCajaCommand.getUsuario().getSegundoApellido().trim();
			modelEmail.put("usuarioResponsable", nombreUsuario + " " + apellido1 + " " + apellido2);
			modelEmail.put("fechaApertura", usuarioCajaCommand1.getCreated_atSTR());
			modelEmail.put("nombreComercial", usuarioCajaBd.getCaja().getEmpresa().getNombreComercial());
			modelEmail.put("nombreEmpresa", usuarioCajaBd.getCaja().getEmpresa().getNombre());
			modelEmail.put("cedula", usuarioCajaBd.getCaja().getEmpresa().getCedula());
			modelEmail.put("fondoInicial", usuarioCajaCommand1.getTotalFondoInicialSTR());
			modelEmail.put("fechaCierre", usuarioCajaCommand1.getCierreCajaSTR());
			modelEmail.put("conteoCierre", usuarioCajaCommand1.getConteoManualSTR());
			modelEmail.put("totalEfectivo", usuarioCajaCommand1.getTotalEfectivoSTR());
			modelEmail.put("totalTarjeta", usuarioCajaCommand1.getTotalTarjetaSTR());
			modelEmail.put("totalBanco", usuarioCajaCommand1.getTotalBancoSTR());
			modelEmail.put("totalAbonos", usuarioCajaCommand1.getTotalAbonoSTR());
			modelEmail.put("totalVentas", usuarioCajaCommand1.getTotalNetoSTR());
			modelEmail.put("totalEntradas", usuarioCajaCommand1.getSumaEntradasSTR());
			modelEmail.put("totalSalidas", usuarioCajaCommand1.getSumaSalidaSTR());
			modelEmail.put("datafonoSTR", usuarioCajaBd.getDatafonoSTR());
			modelEmail.put("conteoDolarConversionSTR", usuarioCajaCommand1.getConteoDolarConversionSTR());

			modelEmail.put("totalDolaresSTR", usuarioCajaCommand1.getTotalDolaresSTR());
			modelEmail.put("totalGeneralSTR", usuarioCajaCommand1.getTotalGeneralSTR());
			modelEmail.put("totalCierreSTR", usuarioCajaCommand1.getTotalCierreSTR());
			modelEmail.put("conteoManualSTR", usuarioCajaCommand1.getConteoManualSTR());
			modelEmail.put("diferenciaSTR", usuarioCajaCommand1.getDiferenciaSTR());
			modelEmail.put("diferenciaFinalSTR", usuarioCajaCommand1.getDiferenciaFinalSTR());
			modelEmail.put("totalDolaresSTR", usuarioCajaCommand1.getConteoDolarSTR());
			modelEmail.put("totalAbonoSTR", usuarioCajaCommand1.getTotalAbonoSTR());
			modelEmail.put("tipoCambio", usuarioCajaCommand1.getTipoCambioSTR());
			modelEmail.put("totalServicio", usuarioCajaCommand1.getTotalServicioSTR());
			modelEmail.put("idCaja", usuarioCajaCommand1.getId());
			modelEmail.put("tipoCambio", usuarioCajaCommand1.getTipoCambioSTR());
			Collection<ConteoManualCaja> conteoCierre = conteoManualCajaBo.buscarPorUsuarioCaja(usuarioCajaBd, Constantes.CONTEO_CIERRE_CAJA_TIPO);
			modelEmail.put("conteoCierres", conteoCierre);
			Collection<ConteoManualCaja> conteoApertura = conteoManualCajaBo.buscarPorUsuarioCaja(usuarioCajaBd, Constantes.CONTEO_APERTURA_CAJA_TIPO);

			modelEmail.put("conteoAperturas", conteoApertura);

			Collection<SalidaEntradaDinero> salidas = salidaEntradaDineroBo.buscarPorUsuarioCajaAndTipo(usuarioCajaBd, Constantes.ENTRADASALIDA_TIPO_SALIDA);
			Collection<SalidaEntradaDinero> entradas = salidaEntradaDineroBo.buscarPorUsuarioCajaAndTipo(usuarioCajaBd, Constantes.ENTRADASALIDA_TIPO_ENTRADA);

			modelEmail.put("entradas", entradas);
			modelEmail.put("salidas", salidas);

			Collection<Attachment> attachments = null;
			String from = "cierrecaja@facturaemprendesoftcr.com";
			String subject = "Cierre Caja-" + usuarioCajaBd.getCaja().getEmpresa().getAbreviaturaEmpresa() + " Apertura :" + usuarioCajaCommand.getCreated_atSTR() + " Cierre: " + usuarioCajaCommand.getCierreCajaSTR();

			correosBo.enviarConAttach(attachments, listaCorreos, from, subject, Constantes.PLANTILLA_CORREO_CIERRE_CAJA, modelEmail);
		}

	}

}