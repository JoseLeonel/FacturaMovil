package com.emprendesoftcr.Bo.Impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.emprendesoftcr.Bo.EmpresaBo;
import com.emprendesoftcr.Bo.FacturaBo;
import com.emprendesoftcr.Dao.ArticuloDao;
import com.emprendesoftcr.Dao.CuentaCobrarDao;
import com.emprendesoftcr.Dao.DetalleDao;
import com.emprendesoftcr.Dao.FacturaDao;
import com.emprendesoftcr.Dao.HaciendaDao;
import com.emprendesoftcr.Dao.KardexDao;
import com.emprendesoftcr.Dao.UsuarioCajaFacturaDao;
import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.Utils.Utils;
import com.emprendesoftcr.fisco.FacturaElectronicaUtils;
import com.emprendesoftcr.modelo.Articulo;
import com.emprendesoftcr.modelo.Cliente;
import com.emprendesoftcr.modelo.CuentaCobrar;
import com.emprendesoftcr.modelo.Detalle;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Factura;
import com.emprendesoftcr.modelo.Hacienda;
import com.emprendesoftcr.modelo.TipoCambio;
import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.modelo.UsuarioCaja;
import com.emprendesoftcr.modelo.UsuarioCajaFactura;
import com.emprendesoftcr.web.command.DetalleFacturaCommand;
import com.emprendesoftcr.web.command.FacturaCommand;
import com.emprendesoftcr.web.command.TotalFacturaCommand;
import com.google.gson.Gson;

/**
 * Reglas de la factura FacturaBoImpl.
 * @author jose.
 * @since 3 nov. 2018
 */
@EnableTransactionManagement
@Service("facturaBo")
public class FacturaBoImpl implements FacturaBo {

	@Autowired
	private FacturaDao						facturaDao;

	@Autowired
	private EmpresaBo							empresaBo;

	@Autowired
	private ArticuloDao						articuloDao;

	@Autowired
	private HaciendaDao						haciendaDao;

	@Autowired
	private KardexDao							kardexDao;

	@Autowired
	private CuentaCobrarDao				cuentaCobrarDao;

	@Autowired
	private UsuarioCajaFacturaDao	usuarioCajaFacturaDao;

	@Autowired
	private DetalleDao						detalleDao;

	private Logger								log	= LoggerFactory.getLogger(this.getClass());

	@Transactional
	@Override
	public void agregar(Factura factura) {
		facturaDao.agregar(factura);

	}

	/**
	 * Modificar una factura
	 * @see com.emprendesoftcr.Bo.FacturaBo#modificar(com.emprendesoftcr.modelo.Factura)
	 */

	@Transactional
	@Override
	public void modificar(Factura factura) {
		facturaDao.modificar(factura);
	}

	/**
	 * Eliminar una factura
	 * @see com.emprendesoftcr.Bo.FacturaBo#eliminar(com.emprendesoftcr.modelo.Factura)
	 */
	@Transactional
	@Override
	public void eliminar(Factura factura) {
		facturaDao.eliminar(factura);
	}

	/**
	 * Buscar por id
	 * @see com.emprendesoftcr.Bo.FacturaBo#findById(java.lang.Integer)
	 */

	@Override
	public Factura findById(Long id) {
		return facturaDao.findById(id);
	}

	/**
	 * Buscar por consecutivo y empresa
	 * @see com.emprendesoftcr.Bo.FacturaBo#findByConsecutivoAndEmpresa(java.lang.String, com.emprendesoftcr.modelo.Empresa)
	 */
	@Override
	public Factura findByConsecutivoAndEmpresa(String consecutivo, Empresa empresa) throws Exception {
		Factura factura = null;
		try {
			factura = facturaDao.findByConsecutivoAndEmpresa(consecutivo, empresa);
		} catch (Exception e) {
			log.info("** Error  findByConsecutivoAndEmpresa: " + e.getMessage() + " fecha " + new Date());

			throw e;
		}
		return factura;
	}

	@Override
	@Transactional
	public void eliminarDetalleFacturaPorSP(Factura factura) throws Exception {
		try {
			facturaDao.eliminarDetalleFacturaPorSP(factura);
		} catch (Exception e) {
			log.info("** Error  eliminarDetalleFacturaPorSP: " + e.getMessage() + " fecha " + new Date());
			throw e;
		}
	}

	@Override
	public Collection<Factura> findByEstadoFirma(Integer estadoFirma, Integer reEstadoFirma) {
		return facturaDao.findByEstadoFirma(estadoFirma, reEstadoFirma);
	}

	@Override
	public Collection<Factura> findByClienteAndEmpresa(Cliente cliente, Empresa empresa) {
		return facturaDao.findByClienteAndEmpresa(cliente, empresa);
	}

	@Transactional
	private Factura formaFactura(FacturaCommand facturaCommand, Usuario usuario) throws Exception {
		// Se forma objeto factura
		Factura factura = null;
		try {

			// Se busca la factura por id o se crea un nuevo objeto
			factura = facturaCommand.getId() == null || facturaCommand.getId() == Constantes.ZEROS_LONG ? new Factura() : facturaDao.findById(facturaCommand.getId());
			// Se complentan los datos de la factura
			factura.setCondicionVenta(facturaCommand.getCondicionVenta());

			// Fecha de credito
			if (facturaCommand.getCondicionVenta().equals(Constantes.FACTURA_CONDICION_VENTA_CREDITO)) {
				if (facturaCommand.getFechaCredito() != null) {
					factura.setFechaCredito(Utils.pasarADate(facturaCommand.getFechaCredito(), "yyyy-MM-dd"));
					factura.setPlazoCredito(facturaCommand.getPlazoCredito() == null ? Constantes.CANTIDAD_DIAS_MINIMO_CREDITO : facturaCommand.getPlazoCredito());
				}
			} else {
				factura.setFechaCredito(null);
				factura.setPlazoCredito(Constantes.ZEROS);
			}
			factura.setTotalOtrosCargos(Constantes.ZEROS_DOUBLE);
			factura.setTipoDocumentoOtroCargo(Constantes.EMPTY);
			factura.setDetalleOtroCargo(Constantes.EMPTY);

			factura.setVersionEsquemaXML(Constantes.ESQUEMA_XML_4_3);
			if (!facturaCommand.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_TIQUETE_USO_INTERNO) && !facturaCommand.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_ELECTRONICA) && !facturaCommand.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_TIQUETE) && !facturaCommand.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_PROFORMAS)) {
				factura.setReferenciaTipoDoc(facturaCommand.getReferenciaTipoDoc());
				factura.setReferenciaNumero(facturaCommand.getReferenciaNumero());
				factura.setReferenciaCodigo(facturaCommand.getReferenciaCodigo());
				factura.setReferenciaRazon(facturaCommand.getReferenciaRazon());
				if (facturaCommand.getReferenciaFechaEmision() != null) {
					factura.setReferenciaFechaEmision(Utils.parseDate2(facturaCommand.getReferenciaFechaEmision()));
				}
				Factura facturaReferencia = facturaDao.findByConsecutivoAndEmpresa(facturaCommand.getReferenciaNumero(), usuario.getEmpresa());
				// Si la factura se encuentra en el sistema se agregan los datos propios de ella
				if (facturaReferencia != null) {
					factura.setReferenciaNumero(facturaReferencia.getClave());
					factura.setReferenciaTipoDoc(facturaReferencia.getTipoDoc());
					factura.setReferenciaFechaEmision(facturaReferencia.getFechaEmision());
					factura.setCodigoMoneda(facturaReferencia.getCodigoMoneda());
					factura.setTipoCambio(facturaReferencia.getTipoCambio());
					factura.setDetalleOtroCargo(facturaReferencia.getDetalleOtroCargo() == null ? Constantes.EMPTY : facturaReferencia.getDetalleOtroCargo());
					factura.setTotalOtrosCargos(facturaReferencia.getTotalOtrosCargos() == null ? Constantes.ZEROS_DOUBLE : facturaReferencia.getTotalOtrosCargos());
					factura.setTipoDocumentoOtroCargo(facturaReferencia.getTipoDocumentoOtroCargo() == null ? Constantes.EMPTY : facturaReferencia.getTipoDocumentoOtroCargo());

					if (facturaReferencia.getVersionEsquemaXML().equals(Constantes.ESQUEMA_XML_4_2)) {
						factura.setVersionEsquemaXML(Constantes.ESQUEMA_XML_4_2);
					}

				}

			} else {
				factura.setVersionEsquemaXML(Constantes.ESQUEMA_XML_4_3);
				factura.setReferenciaTipoDoc(Constantes.EMPTY);
				factura.setReferenciaNumero(Constantes.EMPTY);
				factura.setReferenciaCodigo(Constantes.EMPTY);
				factura.setReferenciaRazon(Constantes.EMPTY);
				factura.setFechaEmision(null);
			}
			factura.setCorreoAlternativo(facturaCommand.getCorreoAlternativo());
			factura.setUsuarioCreacion(usuario);
			factura.setEmpresa(usuario.getEmpresa());
			factura.setVendedor(facturaCommand.getVendedor());
			factura.setCliente(facturaCommand.getCliente());
			factura.setFechaEmision(new Date());
			factura.setMedioEfectivo(Constantes.EMPTY);
			factura.setNombreFactura(facturaCommand.getNombreFactura());
			factura.setCorreoAlternativo(facturaCommand.getCorreoAlternativo());
			factura.setUsuarioCreacion(usuario);
			factura.setEmpresa(usuario.getEmpresa());
			factura.setVendedor(facturaCommand.getVendedor());
			factura.setCliente(facturaCommand.getCliente());
			factura.setFechaEmision(new Date());
			factura.setMedioEfectivo(Constantes.EMPTY);
			factura.setNombreFactura(facturaCommand.getNombreFactura());
			factura.setCodigoActividad(facturaCommand.getCodigoActividad() == null ? usuario.getEmpresa().getCodigoActividad() : facturaCommand.getCodigoActividad());
			if (facturaCommand.getTotalEfectivo() > Constantes.ZEROS_DOUBLE) {
				factura.setMedioEfectivo(Constantes.MEDIO_PAGO_EFECTIVO);
			}
			factura.setMedioBanco(Constantes.EMPTY);
			if (facturaCommand.getTotalBanco() > Constantes.ZEROS_DOUBLE) {
				factura.setMedioBanco(Constantes.FACTURA_MEDIO_PAGO_TRANSFERENCIA);
			}
			factura.setMedioTarjeta(Constantes.EMPTY);
			if (facturaCommand.getTotalTarjeta() > Constantes.ZEROS_DOUBLE) {
				factura.setMedioTarjeta(Constantes.FACTURA_MEDIO_PAGO_TARJETA);
			}
			factura.setTipoDoc(facturaCommand.getTipoDoc());
			factura.setPesoTransporteTotal(facturaCommand.getPesoTransporteTotal() != null ? facturaCommand.getPesoTransporteTotal() : Constantes.ZEROS_DOUBLE);
			factura.setNombreFactura(facturaCommand.getNombreFactura());
			factura.setDireccion(facturaCommand.getDireccion());
			factura.setNota(facturaCommand.getNota());
			factura.setComanda(facturaCommand.getComanda());
			factura.setSubTotal(facturaCommand.getSubTotal() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getSubTotal());
			factura.setTotalTransporte(facturaCommand.getTotalTransporte() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotalTransporte());
			factura.setTotalServGravados(facturaCommand.getTotalServGravados() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotalServGravados());
			factura.setTotalServExentos(facturaCommand.getTotalServExentos() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotalServExentos());
			factura.setTotalMercanciasExentas(facturaCommand.getTotalMercanciasExentas() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotalMercanciasExentas());
			factura.setTotalMercanciasGravadas(facturaCommand.getTotalMercanciasGravadas() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotalMercanciasGravadas());
			factura.setTotalGravado(facturaCommand.getTotalGravado() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotalGravado());
			factura.setTotalExento(facturaCommand.getTotalExento() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotalExento());
			factura.setTotalVenta(facturaCommand.getTotalVenta() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotalVenta());
			factura.setTotalDescuentos(facturaCommand.getTotalDescuentos() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotalDescuentos());
			factura.setTotalVentaNeta(facturaCommand.getTotalVentaNeta() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotalVentaNeta());
			factura.setTotalImpuesto(facturaCommand.getTotalImpuesto() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotalImpuesto());
			factura.setTotalComprobante(facturaCommand.getTotalComprobante() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotalComprobante());
			factura.setTotalEfectivo(getTotalEfectivo(facturaCommand));
			factura.setTotalTarjeta(facturaCommand.getTotalTarjeta() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotalTarjeta());
			factura.setTotalBanco(facturaCommand.getTotalBanco() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotalBanco());
			factura.setTotalCredito(facturaCommand.getTotalCredito() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotalCredito());
			factura.setMontoCambio(facturaCommand.getMontoCambio() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getMontoCambio());
			factura.setTotalCambio(facturaCommand.getTotalCambio() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotalCambio());
			factura.setTotalCambioPagar(facturaCommand.getTotalCambioPagar() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotalCambioPagar());
			if (facturaCommand.getCodigoMoneda() != null) {
				// Costa Rica Colones
				if (facturaCommand.getCodigoMoneda().equals(Constantes.CODIGO_MONEDA_COSTA_RICA)) {
					factura.setCodigoMoneda(Constantes.CODIGO_MONEDA_COSTA_RICA);
					factura.setTipoCambio(Constantes.CODIGO_MONEDA_COSTA_RICA_CAMBIO);
				} else if (facturaCommand.getCodigoMoneda().equals(Constantes.CODIGO_MONEDA_DOLAR)) {// Dollar
					factura.setCodigoMoneda(Constantes.CODIGO_MONEDA_DOLAR);
					factura.setTipoCambio(facturaCommand.getTipoCambioMoneda());
				}
			} else {
				factura.setCodigoMoneda(Constantes.CODIGO_MONEDA_COSTA_RICA);
				factura.setTipoCambio(Constantes.CODIGO_MONEDA_COSTA_RICA_CAMBIO);

			}
			factura.setEstado(facturaCommand.getEstado());
			factura.setMesa(facturaCommand.getMesa());
			// if (factura.getId() == Constantes.ZEROS_LONG) {
			factura.setCreated_at(new Date());
			// }
			factura.setFechaEmision(new Date());

		} catch (Exception e) {
			throw e;
		}
		return factura;
	}

	private ArrayList<DetalleFacturaCommand> formaDetallesCommand(FacturaCommand facturaCommand) throws Exception {
		// Detalles, se forma el detalle de la factura, se contabiliza los totales para evitar problemas con el tema de los decimales en el front
		JSONObject json = null;
		ArrayList<DetalleFacturaCommand> detallesFacturaCommand = new ArrayList<>();
		try {
			json = (JSONObject) new JSONParser().parse(facturaCommand.getDetalleFactura());
			// Agregar Lineas de Detalle
			JSONArray jsonArrayDetalleFactura = (JSONArray) json.get("data");
			Gson gson = new Gson();
			if (jsonArrayDetalleFactura != null) {
				Integer numeroLinea = 1;
				for (int i = 0; i < jsonArrayDetalleFactura.size(); i++) {
					DetalleFacturaCommand detalleFacturaCommand = gson.fromJson(jsonArrayDetalleFactura.get(i).toString(), DetalleFacturaCommand.class);
					detallesFacturaCommand.add(detalleFacturaCommand);
					numeroLinea += 1;
				}
			}
		} catch (org.json.simple.parser.ParseException e) {
			throw e;
		}
		return detallesFacturaCommand;
	}

	private void asociaDetallesFactura(Factura factura, FacturaCommand facturaCommand, Usuario usuario, ArrayList<DetalleFacturaCommand> detallesFacturaCommand) throws Exception {

		// Detalles, se forma el detalle de la factura, se contabiliza los totales para evitar problemas con el tema de los decimales en el front

		// Se inicializan los totales
		Double totalServGravados = Constantes.ZEROS_DOUBLE;
		Double totalServExentos = Constantes.ZEROS_DOUBLE;
		Double totalMercanciasGravadas = Constantes.ZEROS_DOUBLE;
		Double totalMercanciasExentas = Constantes.ZEROS_DOUBLE;
		Double totalGravado = Constantes.ZEROS_DOUBLE;
		Double totalExento = Constantes.ZEROS_DOUBLE;
		Double totalVenta = Constantes.ZEROS_DOUBLE;
		Double totalDescuentos = Constantes.ZEROS_DOUBLE;
		Double totalVentaNeta = Constantes.ZEROS_DOUBLE;
		Double totalImpuesto = Constantes.ZEROS_DOUBLE;
		Double totalComprobante = Constantes.ZEROS_DOUBLE;
		Double subTotal = Constantes.ZEROS_DOUBLE;
		Double totalImpServicios = Constantes.ZEROS_DOUBLE;
		Double totalServExonerado = Constantes.ZEROS_DOUBLE;
		Double totalMercExonerada = Constantes.ZEROS_DOUBLE;
		Double totalExonerado = Constantes.ZEROS_DOUBLE;
		Double totalIVADevuelto = Constantes.ZEROS_DOUBLE;
		Double totalOtrosCargos = Constantes.ZEROS_DOUBLE;
		String unidadMedida = Constantes.EMPTY;
		// Agregar Lineas de Detalle
		Integer numeroLinea = 1;
		Double gananciaProducto = Constantes.ZEROS_DOUBLE;
		Double precioUnitario = Constantes.ZEROS_DOUBLE;
		Double costo = Constantes.ZEROS_DOUBLE;

		for (Iterator<DetalleFacturaCommand> iterator = detallesFacturaCommand.iterator(); iterator.hasNext();) {
			DetalleFacturaCommand detalleFacturaCommand = (DetalleFacturaCommand) iterator.next();
			unidadMedida = Constantes.UNIDAD_MEDIDA;
			if (detalleFacturaCommand.getUnidadMedida() != null) {
				if (detalleFacturaCommand.getUnidadMedida().equals(Constantes.EMPTY)) {
					detalleFacturaCommand.setUnidadMedida(unidadMedida);
				}
			} else {
				detalleFacturaCommand.setUnidadMedida(unidadMedida);
			}
			Articulo articulo = articuloDao.buscarPorCodigoYEmpresa(detalleFacturaCommand.getCodigo(), usuario.getEmpresa());
			if (articulo != null) {
				articulo.setUpdated_at(new Date());
				articuloDao.modificar(articulo);
			}
			gananciaProducto = Constantes.ZEROS_DOUBLE;
			precioUnitario = Constantes.ZEROS_DOUBLE;
			costo = Constantes.ZEROS_DOUBLE;
			if (detalleFacturaCommand.getPrecioUnitario() != null) {
				precioUnitario = detalleFacturaCommand.getPrecioUnitario();
			}
			if (articulo != null) {
				if (articulo.getCosto() != null) {
					costo = articulo.getCosto();

				}
			}
			gananciaProducto = getGananciaProducto(precioUnitario * detalleFacturaCommand.getCantidad(), costo * detalleFacturaCommand.getCantidad(), Utils.roundFactura(detalleFacturaCommand.getMontoDescuento(), 5));
			Detalle detalle = new Detalle(detalleFacturaCommand);
			detalle.setPesoTransporte(detalleFacturaCommand.getPesoTransporte() != null ? detalleFacturaCommand.getPesoTransporte() : Constantes.ZEROS_DOUBLE);
			detalle.setPesoTransporteTotal(detalleFacturaCommand.getPesoTransporteTotal() != null ? detalleFacturaCommand.getPesoTransporteTotal() : Constantes.ZEROS_DOUBLE);
			detalle.setCosto(costo);
			detalle.setGanancia(Utils.roundFactura(gananciaProducto, 5));
			detalle.setMontoGanancia(Utils.roundFactura(gananciaProducto, 5));
			detalle.setPorcentajeGanancia(getPorcentajeGananciaProducto(detalleFacturaCommand.getPrecioUnitario(), detalleFacturaCommand.getCosto() != null ? detalleFacturaCommand.getCosto() : Constantes.ZEROS));
			detalle.setMontoGanancia(Utils.roundFactura(detalleFacturaCommand.getMontoGanancia() != null ? detalleFacturaCommand.getMontoGanancia() : Constantes.ZEROS_DOUBLE, 5));
			detalle.setUsuario(usuario);
			detalleFacturaCommand.setTipoImpuesto(detalleFacturaCommand.getTipoImpuesto() != null ? detalleFacturaCommand.getTipoImpuesto() : Constantes.EMPTY);
			detalleFacturaCommand.setTipoImpuesto1(detalleFacturaCommand.getTipoImpuesto1() != null ? detalleFacturaCommand.getTipoImpuesto1() : Constantes.EMPTY);
			detalle.setImpuesto(detalleFacturaCommand.getImpuesto() != null ? detalleFacturaCommand.getImpuesto() : Constantes.ZEROS_DOUBLE);
			detalle.setImpuesto1(detalleFacturaCommand.getImpuesto1() != null ? detalleFacturaCommand.getImpuesto1() : Constantes.ZEROS_DOUBLE);
			Double impuestoNeto = Constantes.ZEROS_DOUBLE;
			detalle.setCodigoTarifa(Constantes.EMPTY);
			if (detalleFacturaCommand.getTipoImpuesto() != null) {
				if (!detalleFacturaCommand.getTipoImpuesto().equals(Constantes.EMPTY)) {
					if (detalleFacturaCommand.getMontoImpuesto() != null) {

						if (detalleFacturaCommand.getMontoImpuesto() > 0) {
							impuestoNeto += detalleFacturaCommand.getMontoImpuesto();
							detalle.setCodigoTarifa(articulo.getCodigoTarifa() != null ? articulo.getCodigoTarifa() : Constantes.EMPTY);
							detalle.setTipoImpuesto(!detalleFacturaCommand.getTipoImpuesto().equals(Constantes.EMPTY) ? detalleFacturaCommand.getTipoImpuesto() : Constantes.TIPO_IMPUESTO_VENTA_ARTICULO);
						}
					}
					if (detalleFacturaCommand.getMontoImpuesto1() != null) {
						if (detalleFacturaCommand.getMontoImpuesto1() > 0) {
							detalle.setCodigoTarifa1(articulo.getCodigoTarifa1() != null ? articulo.getCodigoTarifa1() : Constantes.EMPTY);
							impuestoNeto += detalleFacturaCommand.getMontoImpuesto();
							detalle.setTipoImpuesto1(!detalleFacturaCommand.getTipoImpuesto1().equals(Constantes.EMPTY) ? detalleFacturaCommand.getTipoImpuesto1() : Constantes.TIPO_IMPUESTO_VENTA_ARTICULO);
						}
					}

				}
			} else {
				if (detalleFacturaCommand.getMontoImpuesto() != null) {
					if (detalleFacturaCommand.getMontoImpuesto() > 0) {
						detalle.setCodigoTarifa(articulo.getCodigoTarifa() != null ? articulo.getCodigoTarifa() : Constantes.EMPTY);
						impuestoNeto += detalleFacturaCommand.getMontoImpuesto();
						detalle.setTipoImpuesto(!detalleFacturaCommand.getTipoImpuesto().equals(Constantes.EMPTY) ? detalleFacturaCommand.getTipoImpuesto() : Constantes.TIPO_IMPUESTO_VENTA_ARTICULO);
					}
				}
				if (detalleFacturaCommand.getMontoImpuesto1() != null) {
					if (detalle.getMontoImpuesto1() > 0) {
						detalle.setCodigoTarifa(articulo.getCodigoTarifa1() != null ? articulo.getCodigoTarifa1() : Constantes.EMPTY);
						impuestoNeto += detalleFacturaCommand.getMontoImpuesto();
						detalle.setTipoImpuesto1(!detalleFacturaCommand.getTipoImpuesto1().equals(Constantes.EMPTY) ? detalleFacturaCommand.getTipoImpuesto1() : Constantes.TIPO_IMPUESTO_VENTA_ARTICULO);
					}
				}

			}
			// detalle.setBaseImponible(impuestoNeto > Constantes.ZEROS_DOUBLE?detalle.getPrecioUnitario():Constantes.ZEROS);
			detalle.setImpuestoNeto(impuestoNeto);
			detalle.setNaturalezaDescuento(Constantes.FORMATO_NATURALEZA_DESCUENTO);
			detalle.setNumeroLinea(numeroLinea);
			detalle.setCreated_at(new Date());
			detalle.setUpdated_at(new Date());
			detalle.setTipoCodigo(articulo == null ? detalleFacturaCommand.getTipoCodigo() : articulo.getTipoCodigo());
			detalle.setUnidadMedida(articulo == null ? detalleFacturaCommand.getUnidadMedida() : articulo.getUnidadMedida());
			// Se aplica el redondeo hasta que se facture porque puede ser venta en espera y se necesita la presicion de los decimales
			if (factura.getEstado().equals(Constantes.FACTURA_ESTADO_FACTURADO) || factura.getEstado().equals(Constantes.FACTURA_ESTADO_PROFORMAS)) {
				detalle.setMontoDescuento(detalle.getMontoDescuento() == null ? Constantes.ZEROS_DOUBLE : Utils.roundFactura(detalle.getMontoDescuento(), 5));
				detalle.setCantidad(detalle.getCantidad() == null ? Constantes.ZEROS_DOUBLE : Utils.roundFactura(detalle.getCantidad(), 3));
				detalle.setMontoImpuesto(detalleFacturaCommand.getMontoImpuesto() == null ? Constantes.ZEROS_DOUBLE : Utils.roundFactura(detalleFacturaCommand.getMontoImpuesto(), 5));
				detalle.setMontoImpuesto1(detalleFacturaCommand.getMontoImpuesto1() == null ? Constantes.ZEROS_DOUBLE : Utils.roundFactura(detalleFacturaCommand.getMontoImpuesto1(), 5));
				detalle.setPrecioUnitario(detalle.getPrecioUnitario() == null ? Constantes.ZEROS_DOUBLE : Utils.roundFactura(detalle.getPrecioUnitario(), 5));
				detalle.setMontoTotalLinea(detalle.getMontoTotalLinea() == null ? Constantes.ZEROS_DOUBLE : Utils.roundFactura(detalle.getMontoTotalLinea(), 5));
				detalle.setMontoTotal(detalle.getMontoTotal() == null ? Constantes.ZEROS_DOUBLE : Utils.roundFactura(detalle.getMontoTotal(), 5));
				// Se calcula el subtotal por problemas de decimales
				detalle.setSubTotal(Utils.roundFactura(detalle.getMontoTotal() - detalle.getMontoDescuento(), 5));

				Integer baseImponible = articulo.getBaseImponible() != null ? articulo.getBaseImponible() : Constantes.ZEROS;

				detalle.setBaseImponible(baseImponible.equals(Constantes.BASE_IMPONIBLE_ACTIVO) ? detalle.getSubTotal() : Constantes.ZEROS_DOUBLE);

				// cambios de doble impuesto

				// Con impuesto
				if (detalle.getMontoImpuesto() > Constantes.ZEROS_DOUBLE || detalle.getMontoImpuesto1() > Constantes.ZEROS_DOUBLE) {
					// Cuando es por servicios
					if (detalle.getTipoImpuesto().equals(Constantes.TIPO_CODIGO_ARTICULO_POR_SERVICIO)) {
						totalServGravados = detalle.getMontoTotal() != null ? totalServGravados + detalle.getMontoTotal() : Constantes.ZEROS_DOUBLE;
						totalImpuesto = totalImpuesto + detalle.getMontoImpuesto() + detalle.getMontoImpuesto1();
						totalGravado = detalle.getMontoTotal() != null ? totalGravado + detalle.getMontoTotal() : Constantes.ZEROS_DOUBLE;
					} else {
						totalMercanciasGravadas = detalle.getMontoTotal() != null ? totalMercanciasGravadas + detalle.getMontoTotal() : Constantes.ZEROS_DOUBLE;
						totalGravado = detalle.getMontoTotal() != null ? totalGravado + detalle.getMontoTotal() : Constantes.ZEROS_DOUBLE;
						totalImpuesto = totalImpuesto + detalle.getMontoImpuesto() + detalle.getMontoImpuesto1();
					}

				} else if (detalle.getMontoImpuesto().equals(Constantes.ZEROS_DOUBLE) && detalle.getMontoImpuesto1().equals(Constantes.ZEROS_DOUBLE)) { // Sin Impuesto
					// Cuando es por servicios
					if (detalle.getTipoImpuesto().equals(Constantes.TIPO_CODIGO_ARTICULO_POR_SERVICIO)) {
						totalServExentos = detalle.getMontoTotal() != null ? totalServExentos + detalle.getMontoTotal() : Constantes.ZEROS_DOUBLE;
					} else {
						totalMercanciasExentas = detalle.getMontoTotal() != null ? totalMercanciasExentas + detalle.getMontoTotal() : Constantes.ZEROS_DOUBLE;
					}
					totalExento = detalle.getMontoTotal() != null ? totalExento + detalle.getMontoTotal() : Constantes.ZEROS_DOUBLE;
				}

				// Total del comprobante
				totalComprobante = totalComprobante + detalle.getMontoTotalLinea();
				subTotal = subTotal + detalle.getSubTotal();
				totalDescuentos = totalDescuentos + detalle.getMontoDescuento();
				totalVenta = totalVenta + detalle.getMontoTotal();
			}
			numeroLinea += 1;
			detalle.setFactura(factura);
			detalleDao.agregar(detalle);
			// factura.addDetalle(detalle);

			// Solo aplica para venta POST
			if (factura.getEstado().equals(Constantes.FACTURA_ESTADO_FACTURADO) && facturaCommand.getMesa() != null && !facturaCommand.getMesa().getId().equals(0L) && facturaCommand.getMesa().getImpuestoServicio()) {
				Integer con = 0;
			} else {
				if (detalle.getCodigo().equals(Constantes.CODIGO_ARTICULO_IMPUESTO_SERVICIO)) {
					totalImpServicios = totalImpServicios + detalle.getMontoTotal();
				}
			}
		}
		if (factura.getTotalOtrosCargos() == null) {
			factura.setTotalOtrosCargos(Constantes.ZEROS_DOUBLE);
			factura.setTipoDocumentoOtroCargo(Constantes.EMPTY);
			factura.setDetalleOtroCargo(Constantes.EMPTY);
		}
		// Se agrega un detalle para el costo por servicio de restaurante y se afecta el monto total de la factura
		if (factura.getEstado().equals(Constantes.FACTURA_ESTADO_FACTURADO) && facturaCommand.getMesa() != null && !facturaCommand.getMesa().getId().equals(0L) && facturaCommand.getMesa().getImpuestoServicio()) {
			factura.setTotalOtrosCargos(Utils.roundFactura(subTotal * 0.10, 5));
			factura.setTipoDocumentoOtroCargo(Constantes.TIPO_DOCUMENTO_IMPUESTO_SERVICIO_OTRO_CARGO);
			factura.setDetalleOtroCargo(Constantes.IMPUESTO_SERVICIO_OTRO_CARGO_RESTAURANTE);

			totalOtrosCargos = totalOtrosCargos + factura.getTotalOtrosCargos();

			totalImpServicios = totalImpServicios + factura.getTotalOtrosCargos();
			totalComprobante = totalComprobante + totalOtrosCargos;
		}

		// Se actualoza el resumen de la factura con los detalles asociados
		totalVentaNeta = totalVenta - totalDescuentos;
		factura.setTotalServExonerado(totalServExonerado);
		factura.setTotalMercExonerada(totalMercExonerada);
		factura.setTotalExonerado(totalExonerado);
		factura.setTotalIVADevuelto(totalIVADevuelto);
		if (totalOtrosCargos > Constantes.ZEROS_DOUBLE) {
			factura.setTotalOtrosCargos(totalOtrosCargos);
		} else {
			if (factura.getTotalOtrosCargos() != null) {
				factura.setTotalOtrosCargos(factura.getTotalOtrosCargos());
				totalImpServicios = totalImpServicios + factura.getTotalOtrosCargos();
				totalComprobante = totalComprobante + factura.getTotalOtrosCargos();
			}
		}

		factura.setTotalMercanciasGravadas(Utils.roundFactura(totalMercanciasGravadas, 5));
		factura.setTotalMercanciasExentas(Utils.roundFactura(totalMercanciasExentas, 5));
		factura.setTotalServExentos(Utils.roundFactura(totalServExentos, 5));
		factura.setTotalServGravados(Utils.roundFactura(totalServGravados, 5));
		factura.setTotalGravado(Utils.roundFactura(totalGravado, 5));
		factura.setTotalExento(Utils.roundFactura(totalExento, 5));
		factura.setTotalVenta(Utils.roundFactura(totalVenta, 5));
		factura.setTotalVentaNeta(Utils.roundFactura(totalVentaNeta, 5));
		factura.setTotalDescuentos(Utils.roundFactura(totalDescuentos, 5));
		factura.setTotalImpuesto(Utils.roundFactura(totalImpuesto, 5));
		factura.setTotalComprobante(Utils.roundFactura(totalComprobante, 5));
		factura.setTotalImpuestoServicio(Utils.roundFactura(totalImpServicios, 5));

	}

	private Double getGananciaProducto(Double precioUnitario, Double costo, Double montoDescuento) {
		// si el costo supera al precio unitario el costo es cero
		if (costo > precioUnitario) {
			costo = Constantes.ZEROS_DOUBLE;
		}
		Double resultado = Constantes.ZEROS_DOUBLE;
		Double descuento = montoDescuento != null ? montoDescuento : Constantes.ZEROS_DOUBLE;

		resultado = precioUnitario > costo ? precioUnitario - costo : Constantes.ZEROS_DOUBLE;

		return Utils.roundFactura(resultado - descuento, 5);
	}

	private Double getPorcentajeGananciaProducto(Double precioUnitario, Double costo) {
		// si el costo supera al precio unitario el costo es cero
		if (costo > precioUnitario) {
			return 100d;
		}

		Double resultado = costo / precioUnitario;
		resultado = 1 - resultado;

		return Utils.roundFactura(resultado * 100, 5);
	}

	private void actualizaArticulosInventario(Factura factura, Usuario usuario) throws Exception {
		Collection<Detalle> detalles = detalleDao.findByFactura(factura);
		if (detalles != null) {
			for (Detalle detalle : detalles) {

				Articulo articulo = articuloDao.buscarPorCodigoYEmpresa(detalle.getCodigo(), usuario.getEmpresa());
				if (articulo != null) {
					if (!factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_DEBITO) && !factura.getEstado().equals(Constantes.FACTURA_ESTADO_PROFORMAS)) {
						aplicarInventario(factura, detalle, articulo);
					}
				}
			}

		}
	}

	/**
	 * Crear la factura o el tiquete temporal
	 * @see com.emprendesoftcr.Bo.FacturaBo#crearFactura(com.emprendesoftcr.web.command.FacturaCommand, com.emprendesoftcr.modelo.Usuario)
	 */
	private final ReentrantLock lock = new ReentrantLock();

	@Override
	@Transactional
	public synchronized Factura crearFactura(FacturaCommand facturaCommand, Usuario usuario, UsuarioCaja usuarioCaja, TipoCambio tipoCambio) throws Exception {
		Factura factura = null;
		lock.lock();
		try {
			long id = Thread.currentThread().getId();
			System.out.println(String.format("--start transaccion--> Thread=%d %s", id, "Fecha:" + new Date()));

			Empresa empresa = empresaBo.buscar(facturaCommand.getEmpresa().getId());
			// Se actualizan los datos de la factura command
			if (empresa.getNoFacturaElectronica().equals(Constantes.NO_APLICA_FACTURA_ELECTRONICA) && !facturaCommand.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO)) {
				facturaCommand.setTipoDoc(Constantes.FACTURA_TIPO_DOC_FACTURA_ELECTRONICA);
			}
			facturaCommand.setTotal(facturaCommand.getTotal() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotal());
			facturaCommand.setTotalBanco(facturaCommand.getTotalBanco() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotalBanco());
			facturaCommand.setTotalCambio(facturaCommand.getTotalCambio() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotalCambio());
			facturaCommand.setTotalComprobante(facturaCommand.getTotalComprobante() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotalComprobante());
			facturaCommand.setTotalCambioPagar(facturaCommand.getTotalCambioPagar() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotalCambioPagar());
			facturaCommand.setTotalCredito(facturaCommand.getTotalCredito() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotalCredito());
			facturaCommand.setTotalDescuentos(facturaCommand.getTotalDescuentos() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotalDescuentos());
			facturaCommand.setTotalEfectivo(facturaCommand.getTotalEfectivo() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotalEfectivo());
			facturaCommand.setTotalExento(facturaCommand.getTotalExento() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotalExento());
			facturaCommand.setTotalGravado(facturaCommand.getTotalGravado() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotalGravado());
			facturaCommand.setTotalImpuesto(facturaCommand.getTotalImpuesto() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotalImpuesto());
			facturaCommand.setTotalMercanciasExentas(facturaCommand.getTotalMercanciasExentas() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotalMercanciasExentas());
			facturaCommand.setTotalMercanciasGravadas(facturaCommand.getTotalMercanciasGravadas() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotalMercanciasGravadas());
			facturaCommand.setMedioPago(facturaCommand.getMedioPago() == null ? Constantes.MEDIO_PAGO_EFECTIVO : facturaCommand.getMedioPago());
			facturaCommand.setMontoCambio(facturaCommand.getMontoCambio() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getMontoCambio());
			facturaCommand.setNumeroConsecutivo(facturaCommand.getNumeroConsecutivo() == null ? Constantes.ZEROS : facturaCommand.getNumeroConsecutivo());
			facturaCommand.setPlazoCredito(facturaCommand.getPlazoCredito() == null ? Constantes.ZEROS : facturaCommand.getPlazoCredito());
			facturaCommand.setCodigoMoneda(facturaCommand.getCodigoMoneda() != null ? facturaCommand.getCodigoMoneda() : Constantes.CODIGO_MONEDA_COSTA_RICA);
			facturaCommand.setTotalTarjeta(facturaCommand.getTotalTarjeta() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotalTarjeta());
			// Proformas
			if (facturaCommand.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_PROFORMAS)) {
				facturaCommand.setEstado(Constantes.FACTURA_ESTADO_PROFORMAS);
			}
			// Tiquete de uso interno
			if (facturaCommand.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_TIQUETE_USO_INTERNO)) {
				facturaCommand.setEstado(Constantes.FACTURA_ESTADO_TIQUETE_USO_INTERNO);
			}
			// Se anula las facturas
			if (!facturaCommand.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_TIQUETE_USO_INTERNO) && !facturaCommand.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_ELECTRONICA) && !facturaCommand.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_TIQUETE) && !facturaCommand.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_PROFORMAS)) {
				if (facturaCommand.getReferenciaNumero() != null && facturaCommand.getReferenciaNumero() != Constantes.EMPTY) {
					Factura facturaAnular = findByConsecutivoAndEmpresa(facturaCommand.getReferenciaNumero(), empresa);
					if (facturaAnular != null) {
						facturaAnular.setEstado(Constantes.FACTURA_ESTADO_ANULADA);
						modificar(facturaAnular);
						facturaCommand.setCliente(facturaAnular.getCliente());
					}
				}
			}
			// Se forman los detalles command de las factura
			ArrayList<DetalleFacturaCommand> detallesFacturaCommand = this.formaDetallesCommand(facturaCommand);
			// --------------------------------------------- Se trabaja con el objeto a registrar en bd -----------------------------------------------------
			// Se forma el objeto factura
			factura = this.formaFactura(facturaCommand, usuario);
			// Se asociando los detalles a la factura

			try {
				// Generar el consecutivo de venta
				if (facturaCommand.getEstado().equals(Constantes.FACTURA_ESTADO_FACTURADO)) {

					factura.setNumeroConsecutivo(empresaBo.generarConsecutivoFactura(empresa, usuario, factura));
					if (empresa.getNoFacturaElectronica() != null && empresa.getNoFacturaElectronica().equals(Constantes.SI_APLICA_FACTURA_ELECTRONICA)) {
						factura.setClave(empresaBo.generaClaveFacturaTributacion(empresa, factura.getNumeroConsecutivo(), FacturaElectronicaUtils.COMPROBANTE_ELECTRONICO_NORMAL));

						factura.setEmpresa(empresa);
					}
				}
				// Verifica si esta facturado para cambiar el estado firma y enviar a crear el xml en el proceso automatico
				if (factura.getEmpresa().getNoFacturaElectronica() != null && factura.getEmpresa().getNoFacturaElectronica().equals(Constantes.NO_APLICA_FACTURA_ELECTRONICA) || factura.getEstado().equals(Constantes.FACTURA_ESTADO_TIQUETE_USO_INTERNO) || facturaCommand.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_PROFORMAS)) {
					factura.setEstadoFirma(Constantes.FACTURA_ESTADO_FIRMA_COMPLETO);
				} else {
					if (factura.getEstado().equals(Constantes.FACTURA_ESTADO_FACTURADO)) {
						factura.setEstadoFirma(Constantes.FACTURA_ESTADO_FIRMA_PENDIENTE);
					} else {
						factura.setEstadoFirma(Constantes.FACTURA_ESTADO_FIRMA_EN_PROCESOS);
					}
				}
				if (factura.getEstado().equals(Constantes.FACTURA_ESTADO_PROFORMAS)) {
					if (factura.getConsecutivoProforma() != null) {
						if (factura.getConsecutivoProforma().equals(Constantes.EMPTY)) {
							factura.setConsecutivoProforma(empresaBo.generarConsecutivoProforma(factura.getEmpresa(), usuario));
						}
					} else {
						factura.setConsecutivoProforma(empresaBo.generarConsecutivoProforma(factura.getEmpresa(), usuario));
					}
				}
				// Se almacena la factura, se deja en estado en proceso para que no lo tome los procesos de hacienda
				if (factura.getId() == null) {
					factura.setCreated_at(new Date());
					agregar(factura);
				} else {
					factura.setCreated_at(new Date());
					modificar(factura);
				}
				// Se asociando los detalles a la factura
				this.asociaDetallesFactura(factura, facturaCommand, usuario, detallesFacturaCommand);

				// Efectivo Banco Tarjeta
				if (!factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_PROFORMAS)) {
					if (factura.getEstado().equals(Constantes.FACTURA_ESTADO_FACTURADO) || factura.getEstado().equals(Constantes.FACTURA_ESTADO_TIQUETE_USO_INTERNO)) {
						// montos en ceros de pagar
						if (factura.getTotalEfectivo().equals(Constantes.ZEROS_DOUBLE) && factura.getTotalBanco().equals(Constantes.ZEROS_DOUBLE) && factura.getTotalTarjeta().equals(Constantes.ZEROS_DOUBLE) && factura.getTotalCredito().equals(Constantes.ZEROS_DOUBLE)) {
							if (!factura.getCondicionVenta().equals(Constantes.FACTURA_CONDICION_VENTA_CREDITO)) {
								factura.setTotalEfectivo(factura.getTotalComprobante());
								factura.setTotalCambioPagar(Constantes.ZEROS_DOUBLE);
								factura.setTotalTarjeta(Constantes.ZEROS_DOUBLE);
								factura.setTotalBanco(Constantes.ZEROS_DOUBLE);
								factura.setTotalCredito(Constantes.ZEROS_DOUBLE);
							}
						}
						// credito
						if (factura.getCondicionVenta().equals(Constantes.FACTURA_CONDICION_VENTA_CREDITO)) {
							factura.setTotalEfectivo(Constantes.ZEROS_DOUBLE);
							factura.setTotalCambioPagar(Constantes.ZEROS_DOUBLE);
							factura.setTotalTarjeta(Constantes.ZEROS_DOUBLE);
							factura.setTotalBanco(Constantes.ZEROS_DOUBLE);
							factura.setTotalCredito(factura.getTotalComprobante());
						}
						// Paga solo en efectivo
						if (!factura.getCondicionVenta().equals(Constantes.FACTURA_CONDICION_VENTA_CREDITO) && factura.getTotalBanco().equals(Constantes.ZEROS_DOUBLE) && factura.getTotalEfectivo() > Constantes.ZEROS_DOUBLE && factura.getTotalTarjeta().equals(Constantes.ZEROS_DOUBLE)) {
							factura.setTotalEfectivo(factura.getTotalComprobante());
							factura.setTotalTarjeta(Constantes.ZEROS_DOUBLE);
							factura.setTotalBanco(Constantes.ZEROS_DOUBLE);
							factura.setTotalCredito(Constantes.ZEROS_DOUBLE);
						}
						// Paga solo en banco
						if (!factura.getCondicionVenta().equals(Constantes.FACTURA_CONDICION_VENTA_CREDITO) && factura.getTotalBanco() > Constantes.ZEROS_DOUBLE && factura.getTotalEfectivo().equals(Constantes.ZEROS_DOUBLE) && factura.getTotalTarjeta().equals(Constantes.ZEROS_DOUBLE)) {
							factura.setTotalBanco(factura.getTotalComprobante());
							factura.setTotalEfectivo(Constantes.ZEROS_DOUBLE);
							factura.setTotalTarjeta(Constantes.ZEROS_DOUBLE);
						}
						// Paga solo en tarjeta
						if (!factura.getCondicionVenta().equals(Constantes.FACTURA_CONDICION_VENTA_CREDITO) && factura.getTotalBanco().equals(Constantes.ZEROS_DOUBLE) && factura.getTotalEfectivo().equals(Constantes.ZEROS_DOUBLE) && factura.getTotalTarjeta() > Constantes.ZEROS_DOUBLE) {
							factura.setTotalBanco(Constantes.ZEROS_DOUBLE);
							factura.setTotalEfectivo(Constantes.ZEROS_DOUBLE);
							factura.setTotalTarjeta(factura.getTotalComprobante());
						}
						// Paga a credito
						if (factura.getCondicionVenta().equals(Constantes.FACTURA_CONDICION_VENTA_CREDITO)) {
							factura.setTotalBanco(Constantes.ZEROS_DOUBLE);
							factura.setTotalEfectivo(Constantes.ZEROS_DOUBLE);
							factura.setTotalTarjeta(Constantes.ZEROS_DOUBLE);
						}
						// Paga tarjeta y efectivo
						if (!factura.getCondicionVenta().equals(Constantes.FACTURA_CONDICION_VENTA_CREDITO) && factura.getTotalBanco().equals(Constantes.ZEROS_DOUBLE) && factura.getTotalEfectivo() > Constantes.ZEROS_DOUBLE && factura.getTotalTarjeta() > Constantes.ZEROS_DOUBLE) {
							Double resultado = factura.getTotalComprobante() - factura.getTotalTarjeta();
							factura.setTotalBanco(Constantes.ZEROS_DOUBLE);
							factura.setTotalEfectivo(resultado);
						}
						// Paga tarjeta y banco
						if (!factura.getCondicionVenta().equals(Constantes.FACTURA_CONDICION_VENTA_CREDITO) && factura.getTotalBanco() > Constantes.ZEROS_DOUBLE && factura.getTotalEfectivo().equals(Constantes.ZEROS_DOUBLE) && factura.getTotalTarjeta() > Constantes.ZEROS_DOUBLE) {
							Double resultado = factura.getTotalComprobante() - factura.getTotalTarjeta();
							factura.setTotalBanco(resultado);
							factura.setTotalEfectivo(Constantes.ZEROS_DOUBLE);
						}
						// Paga tarjeta banco y efectivo
						if (!factura.getCondicionVenta().equals(Constantes.FACTURA_CONDICION_VENTA_CREDITO) && factura.getTotalBanco() > Constantes.ZEROS_DOUBLE && factura.getTotalEfectivo() > Constantes.ZEROS_DOUBLE && factura.getTotalTarjeta() > Constantes.ZEROS_DOUBLE) {
							Double resultado = factura.getTotalComprobante() - factura.getTotalTarjeta();
							resultado = resultado - factura.getTotalBanco();
							factura.setTotalEfectivo(resultado);
						}

						// Se agrega solo si no existe en la caja de usuario, casos de reintentos
						if (usuarioCajaFacturaDao.findByFacturaId(factura.getId()) == null) {
							UsuarioCajaFactura usuarioCajaFactura = new UsuarioCajaFactura();
							usuarioCajaFactura.setCreated_at(new Date());
							usuarioCajaFactura.setUpdated_at(new Date());
							usuarioCajaFactura.setFactura(factura);
							usuarioCajaFactura.setUsuarioCaja(usuarioCaja);
							usuarioCajaFacturaDao.agregar(usuarioCajaFactura);
						}
					}
				}
				// Actualiza articulo y inventario
				this.actualizaArticulosInventario(factura, usuario);

				// Crear Credito del cliente
				if (factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_ELECTRONICA) || factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_TIQUETE)) {
					if (factura.getEstado().equals(Constantes.FACTURA_ESTADO_FACTURADO) || factura.getEstado().equals(Constantes.FACTURA_ESTADO_TIQUETE_USO_INTERNO)) {
						if (factura.getCondicionVenta().equals(Constantes.FACTURA_CONDICION_VENTA_CREDITO)) {
							cuentaCobrarDao.crearCuentaXCobrar(factura);
						}
					}
				}

			} catch (Exception e) {
				throw e;
			}

			// Anulacion de la factura anterior
			if (factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO) || factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_DEBITO)) {
				if (factura.getReferenciaNumero() != null) {
					if (factura.getReferenciaNumero() != Constantes.EMPTY) {
						Factura facturaAnterior = findByConsecutivoAndEmpresa(factura.getReferenciaNumero(), empresa);
						if (facturaAnterior != null) {
							CuentaCobrar cuentaCobrar = cuentaCobrarDao.buscarPorConsecutivo(factura.getEmpresa(), factura.getReferenciaNumero());
							if (cuentaCobrar != null) {
								// Eliminar la cuenta por cobrar si el tipo de anulacio es total
								if (factura.getReferenciaCodigo().equals(Constantes.FACTURA_CODIGO_REFERENCIA_ANULA_DOCUMENTO)) {
									cuentaCobrarDao.eliminar(cuentaCobrar);
								} else if (!factura.getReferenciaCodigo().equals(Constantes.FACTURA_CODIGO_REFERENCIA_ANULA_DOCUMENTO)) {
									cuentaCobrar.setTotal(Utils.roundFactura(factura.getTotalComprobante(), 2));
									cuentaCobrar.setTotalSaldo(Utils.roundFactura(factura.getTotalComprobante(), 2));
									cuentaCobrarDao.modificar(cuentaCobrar);

								}
							}
							facturaAnterior.setEstado(Constantes.FACTURA_ESTADO_ANULADA);
							modificar(facturaAnterior);
							if (facturaAnterior.getClave() != null) {
								Hacienda hacienda = haciendaDao.findByEmpresaAndClave(empresa, facturaAnterior.getClave());
								if (hacienda != null) {
									hacienda.setEstado(Constantes.HACIENDA_ESTADO_ANULADA);
									haciendaDao.modificar(hacienda);
								}
							}
						}
					}
				}
			}
			System.out.println(String.format("--Finaliza transaccion--> Thread=%d %s", id, "Fecha:" + new Date()));

		} catch (Exception e) {
			log.info("** Error  crear la factura: " + e.getMessage() + " fecha " + new Date());
			throw e;
		} finally {
			lock.unlock();
		}

		return factura;
	}

	/**
	 * Aplicar el inventario si estado de la venta es facturada Toda nota credito se devuelve al inventario los productos
	 */
	private void aplicarInventario(Factura factura, Detalle detalle, Articulo articulo) throws Exception {
		try {
			if (factura.getEmpresa().getTieneInventario().equals(Constantes.ESTADO_ACTIVO)) {
				if (factura.getEstado().equals(Constantes.FACTURA_ESTADO_FACTURADO) || factura.getEstado().equals(Constantes.FACTURA_ESTADO_TIQUETE_USO_INTERNO)) {
					if (factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO)) {
						if (articulo.getContable() != null) {
							if (articulo.getContable().equals(Constantes.CONTABLE_SI)) {
								String leyenda = Constantes.MOTIVO_INGRESO_INVENTARIO_NOTA_CREDITO + factura.getNumeroConsecutivo();
								kardexDao.entrada(articulo, articulo.getCantidad(), detalle.getCantidad(), Constantes.EMPTY, factura.getNumeroConsecutivo().toString(), Constantes.KARDEX_TIPO_SALIDA, leyenda, factura.getUsuarioCreacion());
							}
						}
					} else {
						if (articulo.getContable() != null) {
							if (articulo.getContable().equals(Constantes.CONTABLE_SI)) {
								String leyenda = Constantes.MOTIVO_SALIDA_INVENTARIO_VENTA + factura.getNumeroConsecutivo();
								kardexDao.salida(articulo, articulo.getCantidad(), detalle.getCantidad(), Constantes.EMPTY, factura.getNumeroConsecutivo().toString(), Constantes.KARDEX_TIPO_SALIDA, leyenda, factura.getUsuarioCreacion());
							}
						}
					}
				}
			}
		} catch (Exception e) {
			log.info("** Error  aplicar en el inventario: " + e.getMessage() + " fecha " + new Date());
			throw e;
		}
	}

	/**
	 * Obtiene el efectivo
	 * @see com.emprendesoftcr.Bo.FacturaBo#getTotalEfectivo(com.emprendesoftcr.web.command.FacturaCommand)
	 */
	@Override
	public Double getTotalEfectivo(FacturaCommand facturaCommand) throws Exception {
		Double resultado = Constantes.ZEROS_DOUBLE;
		try {
			if (facturaCommand.getTotalBanco() == null && facturaCommand.getTotalTarjeta() == null & facturaCommand.getTotalEfectivo() == null) {
				return facturaCommand.getTotalComprobante();
			}
			// Si hay montos en banco y tarjeta debe ser igual en efectivo
			if (facturaCommand.getTotalBanco() > 0 || facturaCommand.getTotalTarjeta() > 0) {
				resultado = facturaCommand.getTotalEfectivo();
			} else {
				resultado = facturaCommand.getTotalComprobante();
			}
		} catch (Exception e) {
			log.info("** Error  aplicar getTotalEfectivo : " + e.getMessage() + " fecha " + new Date());
			throw e;

		}

		return resultado;
	}

	@Override
	public TotalFacturaCommand sumarFacturas(Date fechaInicio, Date fechaFinal, Integer idEmpresa) {
		return facturaDao.sumarFacturas(fechaInicio, fechaFinal, idEmpresa);
	}

	@Override
	public Collection<Factura> facturasRangoEstado(Integer estado, Date fechaInicio, Date fechaFin, Integer idEmpresa) {
		return facturaDao.facturasRangoEstado(estado, fechaInicio, fechaFin, idEmpresa);
	}

	public List<Object[]> proformasByState(Integer estado, Integer idEmpresa) {
		return facturaDao.proformasByState(estado, idEmpresa);
	}
}