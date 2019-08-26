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
	public Factura findByClaveAndEmpresa(String clave, Empresa empresa) throws Exception {
		Factura factura = null;
		try {
			factura = facturaDao.findByClaveAndEmpresa(clave, empresa);
		} catch (Exception e) {
			log.info("** Error  findByClaveAndEmpresa: " + e.getMessage() + " fecha " + new Date());

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
		
				facturaReferencia = facturaReferencia == null ? facturaDao.findByClaveAndEmpresa(facturaCommand.getReferenciaNumero(), usuario.getEmpresa()) : facturaReferencia;
				// Si la factura se encuentra en el sistema se agregan los datos propios de ella
				if (facturaReferencia != null) {
					if(facturaReferencia.getEstado().equals(Constantes.HACIENDA_ESTADO_ACEPTADO_RECHAZADO)) {
						factura.setTipoDoc(Constantes.FACTURA_TIPO_DOC_NOTA_CREDITO_INTERNO);
					}
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
			
			//No se cambia el usuario en la venta solo en la anulacion
			if(factura.getId() == null) {
				factura.setUsuarioCreacion(usuario);	
			}
			
			factura.setEmpresa(usuario.getEmpresa());
			factura.setVendedor(facturaCommand.getVendedor());
			factura.setCliente(facturaCommand.getCliente());
			factura.setFechaEmision(new Date());
			factura.setMedioEfectivo(Constantes.EMPTY);
			factura.setNombreFactura(facturaCommand.getNombreFactura());
			
			
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
			factura.setCreated_at(new Date());
			factura.setFechaEmision(new Date());
			if(factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_NOTA_CREDITO_INTERNO)) {
				factura.setEstado(Constantes.FACTURA_ESTADO_FACTURADO);
				factura.setEstadoFirma(Constantes.FACTURA_ESTADO_FIRMA_COMPLETO);
				factura.setClave(Constantes.EMPTY);
			}

		} catch (Exception e) {
			throw e;
		}
		return factura;
	}

	private ArrayList<DetalleFacturaCommand> formaDetallesCommand(FacturaCommand facturaCommand) throws Exception {
		// Detalles, se forma el detalle de la factura, se contabiliza los totales para
		// evitar problemas con el tema de los decimales en el front
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

		// Detalles, se forma el detalle de la factura, se contabiliza los totales para
		// evitar problemas con el tema de los decimales en el front

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

		Double montoTotalLinea = Constantes.ZEROS_DOUBLE;
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
			// Vefica si el cliente es libre de impuesto para eliminar el impuesto de los
			// articulos de los detalles
			if (getLibreImpuesto(factura.getCliente())) {
				if (factura.getEstado().equals(Constantes.FACTURA_ESTADO_FACTURADO)) {
					detalleFacturaCommand.setTipoDocumentoExoneracion(Constantes.EMPTY);
					detalleFacturaCommand.setPorcentajeExoneracion(Constantes.ZEROS);
					detalleFacturaCommand.setFechaEmisionExoneracion(null);
					detalleFacturaCommand.setNumeroDocumentoExoneracion(Constantes.EMPTY);
					detalleFacturaCommand.setTipoImpuesto(Constantes.EMPTY);
					detalleFacturaCommand.setTipoImpuesto1(Constantes.EMPTY);
					detalleFacturaCommand.setMontoImpuesto(Constantes.ZEROS_DOUBLE);
					detalleFacturaCommand.setMontoImpuesto1(Constantes.ZEROS_DOUBLE);
					detalleFacturaCommand.setImpuesto(Constantes.ZEROS_DOUBLE);
					detalleFacturaCommand.setImpuesto1(Constantes.ZEROS_DOUBLE);

				} else {

					detalleFacturaCommand.setNumeroDocumentoExoneracion(Constantes.DOCUMENTO_LIBRE_IVA);
					detalleFacturaCommand.setPorcentajeExoneracion(Constantes.PORCENTAJE_LIBRE_IVA);
					detalleFacturaCommand.setFechaEmisionExoneracion(new Date());
					detalleFacturaCommand.setTipoDocumentoExoneracion(Constantes.TIPO_LIBRE_IVA);

				}
			}

			gananciaProducto = Constantes.ZEROS_DOUBLE;
			precioUnitario = Constantes.ZEROS_DOUBLE;
			costo = Constantes.ZEROS_DOUBLE;
			if (detalleFacturaCommand.getPrecioUnitario() != null) {
				precioUnitario = Utils.aplicarRedondeo(detalleFacturaCommand.getPrecioUnitario()) ? Utils.roundFactura(detalleFacturaCommand.getPrecioUnitario(), 5) : detalleFacturaCommand.getPrecioUnitario();
			}
			if (articulo != null) {
				if (articulo.getCosto() != null) {
					costo = articulo.getCosto();

				}
			}
			gananciaProducto = getGananciaProducto(precioUnitario * detalleFacturaCommand.getCantidad(), costo * detalleFacturaCommand.getCantidad(), detalleFacturaCommand.getMontoDescuento());
			Detalle detalle = new Detalle(detalleFacturaCommand);
			detalle.setPesoTransporte(detalleFacturaCommand.getPesoTransporte() != null ? detalleFacturaCommand.getPesoTransporte() : Constantes.ZEROS_DOUBLE);
			detalle.setPesoTransporteTotal(detalleFacturaCommand.getPesoTransporteTotal() != null ? detalleFacturaCommand.getPesoTransporteTotal() : Constantes.ZEROS_DOUBLE);
			detalle.setCosto(costo);
			detalle.setGanancia(gananciaProducto);
			detalle.setMontoGanancia(gananciaProducto);
			detalle.setPorcentajeGanancia(getPorcentajeGananciaProducto(detalleFacturaCommand.getPrecioUnitario(), detalleFacturaCommand.getCosto() != null ? detalleFacturaCommand.getCosto() : Constantes.ZEROS));
			detalle.setMontoGanancia(detalleFacturaCommand.getMontoGanancia() != null ? detalleFacturaCommand.getMontoGanancia() : Constantes.ZEROS_DOUBLE);
			detalle.setUsuario(usuario);
			detalleFacturaCommand.setTipoImpuesto(detalleFacturaCommand.getTipoImpuesto() != null ? detalleFacturaCommand.getTipoImpuesto() : Constantes.EMPTY);
			detalleFacturaCommand.setTipoImpuesto1(detalleFacturaCommand.getTipoImpuesto1() != null ? detalleFacturaCommand.getTipoImpuesto1() : Constantes.EMPTY);
			detalle.setImpuesto(detalleFacturaCommand.getImpuesto() != null ? detalleFacturaCommand.getImpuesto() : Constantes.ZEROS_DOUBLE);
			detalle.setImpuesto1(detalleFacturaCommand.getImpuesto1() != null ? detalleFacturaCommand.getImpuesto1() : Constantes.ZEROS_DOUBLE);
			detalle.setCodigoTarifa(articulo.getCodigoTarifa() != null && getLibreImpuesto(factura.getCliente()) == Boolean.FALSE ? articulo.getCodigoTarifa() : Constantes.EMPTY);
			detalle.setCodigoTarifa1(articulo.getCodigoTarifa1() != null && getLibreImpuesto(factura.getCliente()) == Boolean.FALSE ? articulo.getCodigoTarifa1() : Constantes.EMPTY);
			detalle.setTipoImpuesto(!detalleFacturaCommand.getTipoImpuesto().equals(Constantes.EMPTY) ? detalleFacturaCommand.getTipoImpuesto() : Constantes.EMPTY);
			detalle.setTipoImpuesto1(!detalleFacturaCommand.getTipoImpuesto1().equals(Constantes.EMPTY) ? detalleFacturaCommand.getTipoImpuesto1() : Constantes.EMPTY);
			detalle.setFechaEmisionExoneracion(detalleFacturaCommand.getFechaEmisionExoneracion());
			detalle.setNombreInstitucionExoneracion(detalleFacturaCommand.getNombreInstitucionExoneracion() == null ? Constantes.EMPTY : detalleFacturaCommand.getNombreInstitucionExoneracion());
			detalle.setNumeroDocumentoExoneracion(detalleFacturaCommand.getNumeroDocumentoExoneracion() == null ? Constantes.EMPTY : detalleFacturaCommand.getNumeroDocumentoExoneracion());
			detalle.setTipoDocumentoExoneracion(detalleFacturaCommand.getTipoDocumentoExoneracion() == null ? Constantes.EMPTY : detalleFacturaCommand.getTipoDocumentoExoneracion());
			detalle.setPorcentajeExoneracion(detalleFacturaCommand.getPorcentajeExoneracion() == null ? Constantes.ZEROS : detalleFacturaCommand.getPorcentajeExoneracion());

			detalle.setMontoTotal(getMontoTotal(detalle.getPrecioUnitario(), detalle.getCantidad()));
			detalle.setMontoDescuento(getDescuento(detalle.getMontoTotal(), detalle.getPorcentajeDesc()));
			detalle.setSubTotal(getSubtotal(detalle.getMontoTotal(), detalle.getMontoDescuento()));
			detalle.setMontoImpuesto1(getMontoImpuestoSin13(detalle.getSubTotal(), detalle.getImpuesto1()));
			detalle.setMontoExoneracion(getMontoExoneracion(detalle.getTipoDocumentoExoneracion(), detalle.getPorcentajeExoneracion(), detalle.getMontoImpuesto()));
			detalle.setMontoExoneracion1(getMontoExoneracion(detalle.getTipoDocumentoExoneracion(), detalle.getPorcentajeExoneracion(), detalle.getMontoImpuesto1()));

			detalle.setMontoImpuesto(getMontoImpuestoCon13(detalle.getSubTotal(), detalle.getMontoImpuesto1(), detalle.getMontoExoneracion1(), detalle.getImpuesto()));

			detalle.setImpuestoNeto(getImpuestoNetoTotal(detalle.getTipoDocumentoExoneracion(), detalle.getPorcentajeExoneracion(), detalle.getMontoImpuesto(), detalle.getMontoImpuesto1(), detalle.getMontoExoneracion() + detalle.getMontoExoneracion1()));
			Integer baseImponible = articulo.getBaseImponible() != null ? articulo.getBaseImponible() : Constantes.ZEROS;
			detalle.setBaseImponible(getBaseImponibleTotal(articulo.getTipoImpuesto(), detalle.getSubTotal(), baseImponible));

			detalle.setNaturalezaDescuento(detalle.getMontoDescuento() > Constantes.ZEROS_DOUBLE ? Constantes.FORMATO_NATURALEZA_DESCUENTO : Constantes.EMPTY);
			detalle.setNumeroLinea(numeroLinea);
			detalle.setCreated_at(new Date());
			detalle.setUpdated_at(new Date());
			detalle.setTipoCodigo(articulo == null ? detalleFacturaCommand.getTipoCodigo() : articulo.getTipoCodigo());
			detalle.setUnidadMedida(articulo == null ? detalleFacturaCommand.getUnidadMedida() : articulo.getUnidadMedida());

			// cambios de doble impuesto

			totalServGravados = totalServGravados + getTotalServicioGravados(detalle.getTipoImpuesto(), detalle.getUnidadMedida(), detalle.getMontoTotal(), detalle.getMontoImpuesto(), detalle.getMontoImpuesto1());
			totalServExonerado = totalServExonerado + getTotalServExonerado(detalle.getTipoImpuesto(), detalle.getUnidadMedida(), detalle.getMontoExoneracion());
			totalMercExonerada = totalMercExonerada + getTotalMercExonerada(detalle.getTipoImpuesto(), detalle.getUnidadMedida(), detalle.getMontoTotal(), detalle.getPorcentajeExoneracion());

			totalImpuesto = totalImpuesto + getTotalImpuesto(detalle.getMontoImpuesto(), detalle.getMontoImpuesto1(), detalle.getTipoDocumentoExoneracion(), detalle.getImpuestoNeto());
			totalMercanciasGravadas = totalMercanciasGravadas + getTotalMercanciasGravadas(detalle.getTipoImpuesto(), detalle.getUnidadMedida(), detalle.getMontoImpuesto(), detalle.getMontoImpuesto1(), detalle.getMontoTotal(), detalle.getPorcentajeExoneracion());
			totalMercanciasExentas = totalMercanciasExentas + getTotalMercanciasExentas(detalle.getTipoImpuesto(), detalle.getUnidadMedida(), detalle.getMontoImpuesto(), detalle.getMontoImpuesto1(), detalle.getMontoTotal());

			totalServExentos = totalServExentos + getTotalServExentos(detalle.getTipoImpuesto(), detalle.getUnidadMedida(), detalle.getMontoImpuesto(), detalle.getMontoImpuesto1(), detalle.getMontoTotal());

			totalExento = totalExento + getTotalExentos(detalle.getMontoImpuesto(), detalle.getMontoImpuesto1(), detalle.getMontoTotal());

			totalDescuentos = totalDescuentos + Utils.Maximo5Decimales(detalle.getMontoDescuento());
			montoTotalLinea = getMontoTotalLinea(detalle.getSubTotal(), detalle.getMontoImpuesto(), detalle.getMontoImpuesto1(), detalle.getImpuestoNeto(), detalle.getTipoDocumentoExoneracion());
			totalComprobante = totalComprobante + montoTotalLinea;

			detalle.setMontoTotalLinea(montoTotalLinea);
			subTotal = subTotal + detalle.getSubTotal();
			detalle.setNumeroLinea(numeroLinea);
			numeroLinea += 1;
			detalle.setFactura(factura);
			detalleDao.agregar(detalle);

		}
		totalExonerado = totalExonerado + getTotalExonerado(totalServExonerado, totalMercExonerada);
		totalGravado = totalGravado + totalMercanciasGravadas + totalServGravados;
		totalVenta = totalVenta + totalExento + totalGravado + totalExonerado;
		if (factura.getTotalOtrosCargos() == null)

		{
			factura.setTotalOtrosCargos(Constantes.ZEROS_DOUBLE);
			factura.setTipoDocumentoOtroCargo(Constantes.EMPTY);
			factura.setDetalleOtroCargo(Constantes.EMPTY);
		}
		// Se agrega un detalle para el costo por servicio de restaurante y se afecta el
		// monto total de la factura
		if (factura.getEstado().equals(Constantes.FACTURA_ESTADO_FACTURADO) && facturaCommand.getMesa() != null && !facturaCommand.getMesa().getId().equals(0L) && facturaCommand.getMesa().getImpuestoServicio()) {
			factura.setTotalOtrosCargos(subTotal * 0.10);
			factura.setTipoDocumentoOtroCargo(Constantes.TIPO_DOCUMENTO_IMPUESTO_SERVICIO_OTRO_CARGO);
			factura.setDetalleOtroCargo(Constantes.IMPUESTO_SERVICIO_OTRO_CARGO_RESTAURANTE);

			totalOtrosCargos = totalOtrosCargos + factura.getTotalOtrosCargos();

			totalImpServicios = totalImpServicios + factura.getTotalOtrosCargos();

		}

		// Se actualoza el resumen de la factura con los detalles asociados
		totalVentaNeta = totalVenta - totalDescuentos;

		if (totalOtrosCargos > Constantes.ZEROS_DOUBLE) {
			factura.setTotalOtrosCargos(totalOtrosCargos);
		} else {
			if (factura.getTotalOtrosCargos() != null) {
				factura.setTotalOtrosCargos(factura.getTotalOtrosCargos());
				totalImpServicios = totalImpServicios + factura.getTotalOtrosCargos();

			}
		}
		totalComprobante = factura.getTotalOtrosCargos() + totalImpuesto + totalVentaNeta;
		totalComprobante = totalComprobante - totalIVADevuelto;

		factura.setTotalServExonerado(Utils.aplicarRedondeo(totalServExonerado) ? Utils.roundFactura(totalServExonerado, 5) : totalServExonerado);
		factura.setTotalMercExonerada(Utils.aplicarRedondeo(totalMercExonerada) ? Utils.roundFactura(totalMercExonerada, 5) : totalMercExonerada);
		factura.setTotalExonerado(Utils.aplicarRedondeo(totalExonerado) ? Utils.roundFactura(totalExonerado, 6) : totalExonerado);
		factura.setTotalIVADevuelto(Utils.aplicarRedondeo(totalIVADevuelto) ? Utils.roundFactura(totalIVADevuelto, 5) : totalIVADevuelto);
		factura.setTotalMercanciasGravadas(Utils.aplicarRedondeo(totalMercanciasGravadas) ? Utils.roundFactura(totalMercanciasGravadas, 5) : totalMercanciasGravadas);
		factura.setTotalMercanciasExentas(Utils.aplicarRedondeo(totalMercanciasExentas) ? Utils.roundFactura(totalMercanciasExentas, 5) : totalMercanciasExentas);
		factura.setTotalServExentos(Utils.aplicarRedondeo(totalServExentos) ? Utils.roundFactura(totalServExentos, 5) : totalServExentos);
		factura.setTotalServGravados(Utils.aplicarRedondeo(totalServGravados) ? Utils.roundFactura(totalServGravados, 5) : totalServGravados);
		factura.setTotalGravado(Utils.aplicarRedondeo(totalGravado) ? Utils.roundFactura(totalGravado, 5) : totalGravado);
		factura.setTotalExento(Utils.aplicarRedondeo(totalExento) ? Utils.roundFactura(totalExento, 5) : totalExento);
		factura.setTotalVenta(Utils.aplicarRedondeo(totalVenta) ? Utils.roundFactura(totalVenta, 5) : totalVenta);
		factura.setTotalVentaNeta(Utils.aplicarRedondeo(totalVentaNeta) ? Utils.roundFactura(totalVentaNeta, 5) : totalVentaNeta);
		factura.setTotalDescuentos(Utils.aplicarRedondeo(totalDescuentos) ? Utils.roundFactura(totalDescuentos, 5) : totalDescuentos);
		factura.setTotalImpuesto(Utils.aplicarRedondeo(totalImpuesto) ? Utils.roundFactura(totalImpuesto, 5) : totalImpuesto);
		factura.setTotalImpuestoServicio(Utils.aplicarRedondeo(totalImpServicios) ? Utils.roundFactura(totalImpServicios, 5) : totalImpServicios);
		factura.setTotalComprobante(Utils.aplicarRedondeo(totalComprobante) ? Utils.roundFactura(totalComprobante, 5) : totalComprobante);

	}

	/**
	 * Verifica si es libre de impuesto
	 * @param cliente
	 * @return
	 */
	private Boolean getLibreImpuesto(Cliente cliente) {
		Boolean libreImpuesto = Boolean.FALSE;
		if (cliente != null) {
			if(cliente.getLibreImpuesto() == null) {
				return libreImpuesto;
			}
			if (cliente.getLibreImpuesto().equals(Constantes.LIBRE_IMPUESTOS_ACTIVO)) {
				libreImpuesto = true;
			}
		}
		return libreImpuesto;

	}

	/**
	 * Monto Linea
	 * @param subTotal
	 * @param montoImpuesto
	 * @param montoImpuesto1
	 * @param montoImpuestoNeto
	 * @param tipoDocumentoExoneracion
	 * @return
	 */
	private Double getMontoTotalLinea(Double subTotal, Double montoImpuesto, Double montoImpuesto1, Double montoImpuestoNeto, String tipoDocumentoExoneracion) {
		Double resultado = Constantes.ZEROS_DOUBLE;

		if (!tipoDocumentoExoneracion.equals(Constantes.EMPTY)) {
			resultado = Utils.Maximo5Decimales(subTotal) + Utils.Maximo5Decimales(montoImpuestoNeto);
		} else {
			resultado = Utils.Maximo5Decimales(subTotal) + Utils.Maximo5Decimales(montoImpuesto) + Utils.Maximo5Decimales(montoImpuesto1);
		}
    
		return Utils.aplicarRedondeo(resultado) ? Utils.roundFactura(resultado, 5) : resultado;
	}

	/**
	 * Total de servicios exentos
	 * @param tipoImpuesto
	 * @param unidadMedida
	 * @param montoImpuesto
	 * @param montoImpuesto1
	 * @param subTotal
	 * @return
	 */
	private Double getTotalServExentos(String tipoImpuesto, String unidadMedida, Double montoImpuesto, Double montoImpuesto1, Double subTotal) {
		Double resultado = Constantes.ZEROS_DOUBLE;
		Boolean esMercancia = Boolean.TRUE;
//		if (!tipoImpuesto.equals(Constantes.TIPO_CODIGO_ARTICULO_POR_SERVICIO)) {
//			esMercancia = Boolean.FALSE;
//		}
		if (!unidadMedida.equals(Constantes.UNIDAD_MEDIDA_SERVICIO_SP) && !unidadMedida.equals(Constantes.UNIDAD_MEDIDA_SERVICIO_OS) && !unidadMedida.equals(Constantes.UNIDAD_MEDIDA_SERVICIO_SPE) && !unidadMedida.equals(Constantes.UNIDAD_MEDIDA_SERVICIO_ST)) {
			esMercancia = Boolean.FALSE;
		}
		if (esMercancia) {

			if (montoImpuesto1.equals(Constantes.ZEROS_DOUBLE) && montoImpuesto.equals(Constantes.ZEROS_DOUBLE)) {
				resultado = subTotal;
			}

		}
		return resultado;
	}

	/**
	 * @param montoImpuesto
	 * @param montoImpuesto1
	 * @param subTotal
	 * @return
	 */
	private Double getTotalExentos(Double montoImpuesto, Double montoImpuesto1, Double subTotal) {
		Double resultado = Constantes.ZEROS_DOUBLE;
		if (montoImpuesto.equals(Constantes.ZEROS_DOUBLE) && montoImpuesto1.equals(Constantes.ZEROS_DOUBLE)) {
			resultado = subTotal;
		}
		return resultado;
	}

	/**
	 * Total Mercancias Excentas
	 * @param montoImpuesto
	 * @param montoImpuesto1
	 * @param subTotal
	 * @return
	 */
	private Double getTotalMercanciasExentas(String tipoImpuesto, String unidadMedida, Double montoImpuesto, Double montoImpuesto1, Double subTotal) {
		Double resultado = Constantes.ZEROS_DOUBLE;
		Boolean esMercancia = Boolean.TRUE;

//		if (tipoImpuesto.equals(Constantes.TIPO_CODIGO_ARTICULO_POR_SERVICIO)) {
//			esMercancia = Boolean.FALSE;
//		}
		if (unidadMedida.equals(Constantes.UNIDAD_MEDIDA_SERVICIO_SP) || unidadMedida.equals(Constantes.UNIDAD_MEDIDA_SERVICIO_OS) || unidadMedida.equals(Constantes.UNIDAD_MEDIDA_SERVICIO_SPE) || unidadMedida.equals(Constantes.UNIDAD_MEDIDA_SERVICIO_ST)) {
			esMercancia = Boolean.FALSE;
		}
		if (esMercancia) {

			if (montoImpuesto1.equals(Constantes.ZEROS_DOUBLE) && montoImpuesto.equals(Constantes.ZEROS_DOUBLE)) {
				resultado = subTotal;
			}

		}
		return resultado;
	}

	/**
	 * Total Mercancia Gravada
	 * @param tipoImpuesto
	 * @param unidadMedida
	 * @param montoImpuesto
	 * @param montoImpuesto1
	 * @param subTotal
	 * @param porcentajeExoneracion
	 * @return
	 */
	private Double getTotalMercanciasGravadas(String tipoImpuesto, String unidadMedida, Double montoImpuesto, Double montoImpuesto1, Double subTotal, Integer porcentajeExoneracion) {
		Double resultado = Constantes.ZEROS_DOUBLE;
		Boolean esMercancia = Boolean.TRUE;
//		if (tipoImpuesto.equals(Constantes.TIPO_CODIGO_ARTICULO_POR_SERVICIO)) {
//			esMercancia = Boolean.FALSE;
//		}
		if (unidadMedida.equals(Constantes.UNIDAD_MEDIDA_SERVICIO_SP) || unidadMedida.equals(Constantes.UNIDAD_MEDIDA_SERVICIO_OS) || unidadMedida.equals(Constantes.UNIDAD_MEDIDA_SERVICIO_SPE) || unidadMedida.equals(Constantes.UNIDAD_MEDIDA_SERVICIO_ST)) {
			esMercancia = Boolean.FALSE;
		}
		if (esMercancia) {
			if (montoImpuesto1 > Constantes.ZEROS_DOUBLE || montoImpuesto > Constantes.ZEROS_DOUBLE) {
				if (porcentajeExoneracion > Constantes.ZEROS) {
					Double porcentaValor = porcentajeExoneracion / 100d;
					porcentaValor = 1 - porcentaValor;
					resultado = subTotal * porcentaValor;
				} else {
					resultado = subTotal;
				}
			}

		}
		return resultado;
	}

	/**
	 * Total de impuesto
	 * @param montoImpuesto
	 * @param montoImpuesto1
	 * @return
	 */
	private Double getTotalImpuesto(Double montoImpuesto, Double montoImpuesto1, String tipoDocumentoExoneracion, Double montoImpuestoNeto) {
		Double resultado = Constantes.ZEROS_DOUBLE;
		if (!tipoDocumentoExoneracion.equals(Constantes.EMPTY)) {
			resultado = montoImpuestoNeto;
		} else {
			resultado = Utils.Maximo5Decimales(montoImpuesto) + Utils.Maximo5Decimales(montoImpuesto1);
		}

		return resultado;
	}

	/**
	 * Total exonerado si el detalle tiene exoneracion
	 * @param SubTotal
	 * @param montoExonerado
	 * @return
	 */
	private Double getTotalExonerado(Double totalServExonerado, Double totalMercExonerada) {
		Double resultado = totalServExonerado + totalMercExonerada;
		return Utils.aplicarRedondeo(resultado) ? Utils.roundFactura(resultado, 5) : resultado;
	}

	private Double getTotalMercExonerada(String tipoImpuesto, String unidadMedida, Double montoTotal, Integer porcentajeExoneracion) {
		Double resultado = Constantes.ZEROS_DOUBLE;
		Boolean esGravado = Boolean.TRUE;
//		if (tipoImpuesto.equals(Constantes.TIPO_CODIGO_ARTICULO_POR_SERVICIO)) {
//			esGravado = Boolean.FALSE;
//		}
		if (unidadMedida.equals(Constantes.UNIDAD_MEDIDA_SERVICIO_SP) || unidadMedida.equals(Constantes.UNIDAD_MEDIDA_SERVICIO_OS) || unidadMedida.equals(Constantes.UNIDAD_MEDIDA_SERVICIO_SPE) || unidadMedida.equals(Constantes.UNIDAD_MEDIDA_SERVICIO_ST)) {
			esGravado = Boolean.FALSE;
		}
		if (esGravado && porcentajeExoneracion > Constantes.ZEROS) {
			Double porcentaValor = porcentajeExoneracion / 100d;
			resultado = montoTotal * porcentaValor;

		}
		return resultado;
	}

	/**
	 * @param tipoImpuesto
	 * @param unidadMedida
	 * @param montoExonerado
	 * @return
	 */
	private Double getTotalServExonerado(String tipoImpuesto, String unidadMedida, Double montoExonerado) {

		Double resultado = Constantes.ZEROS_DOUBLE;
//		if (tipoImpuesto.equals(Constantes.TIPO_CODIGO_ARTICULO_POR_SERVICIO)) {
//			resultado = montoExonerado;
//		}
		if (unidadMedida.equals(Constantes.UNIDAD_MEDIDA_SERVICIO_SP) || unidadMedida.equals(Constantes.UNIDAD_MEDIDA_SERVICIO_OS) || unidadMedida.equals(Constantes.UNIDAD_MEDIDA_SERVICIO_SPE) || unidadMedida.equals(Constantes.UNIDAD_MEDIDA_SERVICIO_ST)) {
			resultado = montoExonerado;
		}
		return resultado;
	}

	/**
	 * Total de servicios grabados = suma del subtotal si cumple que es un servicio
	 * @param tipoImpuesto
	 * @param unidadMedida
	 * @param SubTotal
	 * @param montoImpuesto
	 * @param montoImpuesto1
	 * @return
	 */
	private Double getTotalServicioGravados(String tipoImpuesto, String unidadMedida, Double SubTotal, Double montoImpuesto, Double montoImpuesto1) {
		Double resultado = Constantes.ZEROS_DOUBLE;
		if (montoImpuesto.equals(Constantes.ZEROS_DOUBLE) && montoImpuesto1.equals(Constantes.ZEROS_DOUBLE)) {
			return resultado;
		}
//		if (tipoImpuesto.equals(Constantes.TIPO_CODIGO_ARTICULO_POR_SERVICIO)) {
//			resultado = SubTotal;
//		}
		if (unidadMedida.equals(Constantes.UNIDAD_MEDIDA_SERVICIO_SP) || unidadMedida.equals(Constantes.UNIDAD_MEDIDA_SERVICIO_OS) || unidadMedida.equals(Constantes.UNIDAD_MEDIDA_SERVICIO_SPE) || unidadMedida.equals(Constantes.UNIDAD_MEDIDA_SERVICIO_ST)) {
			resultado = SubTotal;
		}

		return resultado;
	}

	/**
	 * @param tipoImpuesto
	 * @param subTotal
	 * @return
	 */
	private Double getBaseImponibleTotal(String tipoImpuesto, Double subTotal, Integer activoBaseImponible) {
		Double resultado = Constantes.ZEROS_DOUBLE;

		if (tipoImpuesto.equals(Constantes.TIPO_IMPUESTO_VENTA_IVA_CALCULO_ESPECIAL) && activoBaseImponible.equals(Constantes.BASE_IMPONIBLE_ACTIVO)) {
			return subTotal;

		}

		return Utils.aplicarRedondeo(resultado) ? Utils.roundFactura(resultado, 5) : resultado;

	}

	/**
	 * @param tipoDocumentoExonerado
	 * @param porcentajeExonerado
	 * @param montoImpuesto
	 * @param montoImpuesto1
	 * @param montoExonerado
	 * @return
	 */
	private Double getImpuestoNetoTotal(String tipoDocumentoExonerado, Integer porcentajeExonerado, Double montoImpuesto, Double montoImpuesto1, Double montoExonerado) {
		Double resultado = Constantes.ZEROS_DOUBLE;
		if (!tipoDocumentoExonerado.equals(Constantes.EMPTY)) {
			if (porcentajeExonerado < 100) {
				resultado = Utils.Maximo5Decimales(montoImpuesto) + Utils.Maximo5Decimales(montoImpuesto1);
				resultado = resultado - Utils.Maximo5Decimales(montoExonerado);
			}

		} else {
			resultado = Utils.Maximo5Decimales(montoImpuesto) + Utils.Maximo5Decimales(montoImpuesto1);
		}

		return Utils.aplicarRedondeo(resultado) ? Utils.roundFactura(resultado, 5) : resultado;

	}

	/**
	 * Monto de exonaracion = monto del impuesto * porcentaje de la exoneracion
	 * @param tipodocumento
	 * @param porcentajeExoneracion
	 * @param montoImpuesto
	 * @return
	 */
	private Double getMontoExoneracion(String tipoDocumentoExonerado, Integer porcentajeExoneracion, Double montoImpuesto) {
		if (tipoDocumentoExonerado.equals(Constantes.EMPTY)) {
			return Constantes.ZEROS_DOUBLE;
		}
		Double porcentaje = Double.parseDouble(porcentajeExoneracion.toString()) / 100;
		Double resultado = montoImpuesto * porcentaje;
		return Utils.aplicarRedondeo(resultado) ? Utils.roundFactura(resultado, 5) : resultado;
	}

	/**
	 * Es la suma del subtotal + primer impuesto por la tarifa sacamos el impuesto 13
	 * @param subTotal
	 * @param montoPrimerImpuesto
	 * @param tarifa
	 * @return
	 */
	private Double getMontoImpuestoCon13(Double subTotal, Double montoPrimerImpuesto, Double montoExoneracion1, Double tarifa) {
		Double valor = tarifa / 100d;
		Double resultado = Constantes.ZEROS_DOUBLE;
		if (montoExoneracion1 > Constantes.ZEROS_DOUBLE) {
			resultado = montoPrimerImpuesto - montoExoneracion1;
			resultado = subTotal + resultado;
			resultado = resultado * valor;
		} else {
			resultado = subTotal + montoPrimerImpuesto;
			resultado = resultado * tarifa;
			resultado = resultado / 100d;
		}
		return Utils.aplicarRedondeo(resultado) ? Utils.roundFactura(resultado, 5) : resultado;
	}

	/**
	 * Monto impuesto sin 13 = subTotal * tarifa
	 * @param subTotal
	 * @param tarifa
	 * @return
	 */
	private Double getMontoImpuestoSin13(Double subTotal, Double tarifa) {
		Double valor = tarifa / 100d;
		Double resultado = subTotal * valor;

		return Utils.aplicarRedondeo(resultado) ? Utils.roundFactura(resultado, 5) : resultado;

	}

	/**
	 * SubTotal = Monto Total - Monto Descuento
	 * @param montoDescuento
	 * @param montoTotal
	 * @return
	 */
	private Double getSubtotal(Double montoTotal, Double montoDescuento) {

		Double resultado = Utils.Maximo5Decimales(montoTotal) - Utils.Maximo5Decimales(montoDescuento);
		return Utils.aplicarRedondeo(resultado) ? Utils.roundFactura(resultado, 5) : resultado;
//		return resultado;
	}

	/**
	 * Total Descuento de la linea
	 * @param montoTotal
	 * @param porcentajeDescuento
	 * @return
	 */
	private Double getDescuento(Double montoTotal, Double porcentajeDescuento) {
//		Double valor = porcentajeDescuento / 100d;
		Double resultado = montoTotal * porcentajeDescuento;
		resultado = resultado /100d;
		return Utils.aplicarRedondeo(resultado) ? Utils.roundFactura(resultado, 5) : resultado;
	}

	/**
	 * Monto Total
	 * @param precioUnitario
	 * @param cantidad
	 * @return
	 */
	private Double getMontoTotal(Double precioUnitario, Double cantidad) {
		Double resultado = precioUnitario * cantidad;

		return Utils.aplicarRedondeo(resultado) ? Utils.roundFactura(resultado, 5) : resultado;
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
					facturaAnular = facturaAnular == null ? facturaDao.findByClaveAndEmpresa(facturaCommand.getReferenciaNumero(), usuario.getEmpresa()) : facturaAnular;
					if (facturaAnular != null) {
						if(facturaAnular.getEstado().equals(Constantes.HACIENDA_ESTADO_ACEPTADO_RECHAZADO)) {
							facturaCommand.setTipoDoc(Constantes.FACTURA_TIPO_DOC_NOTA_CREDITO_INTERNO);
						}
						facturaAnular.setEstado(Constantes.FACTURA_ESTADO_ANULADA);
						modificar(facturaAnular);
						facturaCommand.setCliente(facturaAnular.getCliente());
					}
				}
			}
			// Se forman los detalles command de las factura
			ArrayList<DetalleFacturaCommand> detallesFacturaCommand = this.formaDetallesCommand(facturaCommand);
			// --------------------------------------------- Se trabaja con el objeto a
			// registrar en bd -----------------------------------------------------
			// Se forma el objeto factura
			factura = this.formaFactura(facturaCommand, usuario);
			// Se asociando los detalles a la factura

			try {
				// Generar el consecutivo de venta
				if (facturaCommand.getEstado().equals(Constantes.FACTURA_ESTADO_FACTURADO) && !facturaCommand.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_NOTA_CREDITO_INTERNO)) {
          
					factura.setNumeroConsecutivo(empresaBo.generarConsecutivoFactura(empresa, usuario, factura));
					
					if (empresa.getNoFacturaElectronica() != null && empresa.getNoFacturaElectronica().equals(Constantes.SI_APLICA_FACTURA_ELECTRONICA)) {
						factura.setClave(empresaBo.generaClaveFacturaTributacion(empresa, factura.getNumeroConsecutivo(), FacturaElectronicaUtils.COMPROBANTE_ELECTRONICO_NORMAL));

						factura.setEmpresa(empresa);
					}
				}
				// Verifica si esta facturado para cambiar el estado firma y enviar a crear el
				// xml en el proceso automatico
				if (factura.getEmpresa().getNoFacturaElectronica() != null && factura.getEmpresa().getNoFacturaElectronica().equals(Constantes.NO_APLICA_FACTURA_ELECTRONICA) || factura.getEstado().equals(Constantes.FACTURA_ESTADO_TIQUETE_USO_INTERNO) || facturaCommand.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_PROFORMAS)) {
					factura.setEstadoFirma(Constantes.FACTURA_ESTADO_FIRMA_COMPLETO);
				} else {
					if(!factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_NOTA_CREDITO_INTERNO)) {
						if (factura.getEstado().equals(Constantes.FACTURA_ESTADO_FACTURADO)) {
							factura.setEstadoFirma(Constantes.FACTURA_ESTADO_FIRMA_PENDIENTE);
						} else {
							factura.setEstadoFirma(Constantes.FACTURA_ESTADO_FIRMA_EN_PROCESOS);
						}
							
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
				if (factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_NOTA_CREDITO_INTERNO)) {
						factura.setNumeroConsecutivo(empresaBo.generarConsecutivoNotaCreditoInterno(factura.getEmpresa(), usuario));
				}
				// Se almacena la factura, se deja en estado en proceso para que no lo tome los
				// procesos de hacienda
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
				if (factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_ELECTRONICA) || factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_TIQUETE)
						) {
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
			if (factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO) || factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_DEBITO)
					|| factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_NOTA_CREDITO_INTERNO)
					) {
				if (factura.getReferenciaNumero() != null) {
					if (factura.getReferenciaNumero() != Constantes.EMPTY) {
						Factura facturaAnterior = findByConsecutivoAndEmpresa(factura.getReferenciaNumero(), empresa);
						facturaAnterior = facturaAnterior == null ? facturaDao.findByClaveAndEmpresa(factura.getReferenciaNumero(), usuario.getEmpresa()) : facturaAnterior;
						if (facturaAnterior != null) {
							CuentaCobrar cuentaCobrar = cuentaCobrarDao.buscarPorConsecutivo(factura.getEmpresa(), facturaAnterior.getNumeroConsecutivo());
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
//							if (facturaAnterior.getClave() != null) {
//								Hacienda hacienda = haciendaDao.findByEmpresaAndClave(empresa, facturaAnterior.getClave());
//								if (hacienda != null) {
//									hacienda.setEstado(Constantes.HACIENDA_ESTADO_ANULADA);
//									haciendaDao.modificar(hacienda);
//								}
//							}
						}
					}
				}
			}
			System.out.println(String.format("--Finaliza transaccion--> Thread=%d %s", id, "Fecha:" + new Date()));

		} catch (Exception e) {
			log.error("** Error  crear la factura: " + e.getMessage() + " fecha " + new Date());
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
			log.error("** Error  aplicar en el inventario: " + e.getMessage() + " fecha " + new Date());
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
			log.error("** Error  aplicar getTotalEfectivo : " + e.getMessage() + " fecha " + new Date());
			throw e;

		}

		return resultado;
	}

	@Override
	public TotalFacturaCommand sumarFacturas(Date fechaInicio, Date fechaFinal, Integer idEmpresa,Integer estado,String actividadEconomica) {
		return facturaDao.sumarFacturas(fechaInicio, fechaFinal, idEmpresa,estado,actividadEconomica);
	}

	@Override
	public Collection<Factura> facturasRangoEstado(Integer estado, Date fechaInicio, Date fechaFin, Integer idEmpresa,String actividadEconomica) {
		return facturaDao.facturasRangoEstado(estado, fechaInicio, fechaFin, idEmpresa,actividadEconomica);
	}

	public List<Object[]> proformasByState(Integer estado, Integer idEmpresa) {
		return facturaDao.proformasByState(estado, idEmpresa);
	}
}